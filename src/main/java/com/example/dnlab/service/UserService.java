package com.example.dnlab.service;

import com.example.dnlab.dto.user.*;
import com.example.dnlab.domain.Role;
import com.example.dnlab.domain.User;
import com.example.dnlab.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    // 회원 가입
    public UserResDto join(SignUpReqDto req) {
        log.info("name : {}, StudentId :{}, id = {}, pw = {} ", req.getName(), req.getStudentId(), req.getId(), req.getPw());

        if (userRepository.findById(req.getId()) != null) {
            throw new IllegalArgumentException("이미 존재하는 학번입니다.");
        }
        String salt = BCrypt.gensalt(); // 랜덤한 salt 생성
        String encodedPassword = BCrypt.hashpw(req.getPw(), salt); // 비밀번호 암호화

        User user = req.toEntity();

        // 일반 회원으로 초기 역할 설정
        user.addRole(Role.MEMBER);
        userRepository.save(user);
        return UserResDto.builder()
                .num(user.getNum())
                .name(user.getName())
                .id(user.getId())
                .studentId(user.getStudentId())
                .build();
    }

    // 로그인
    public ResponseEntity<Void> login(LoginReqDto req, HttpSession session) {
        log.info("ser");

        User user = userRepository.findById(req.getId());
        log.info("id: {}, pw: {}", req.getId(), req.getPw());
        log.info("userId: {}, userPw: {}", user.getId(), user.getPw());

        // 아이디 확인
        if (user != null) {
            // 패스워드 비교 (암호화된 패스워드로 비교)
            if (BCrypt.checkpw(req.getPw(), user.getPw())) {
                // 로그인 성공 처리
                session.setAttribute("user", user);
                return ResponseEntity.ok().header("SESSION-ID", session.getId()).build();
            }
        }
        // 로그인 실패 처리
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 사용자 정보를 UserRepository를 사용하여 가져옴
        User user = userRepository.findById(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with id: " + username);
        }

        // 권한 정보를 SimpleGrantedAuthority로 변환하여 UserDetails 객체 생성
        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())) // Spring Security에서는 권한 이름을 "ROLE_"로 시작해야 함
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getId(), user.getPw(), authorities);
    }

    //전체 회원 조회
    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();

        if (userList.isEmpty()) {
            throw new NoSuchElementException("찾는 연구생 없음.");
        }else{
            Collections.sort(userList, Comparator.comparing(User::getGeneration)); // Generation을 기준으로 오름차순 정렬
        }
        return userList;
    }

    //이름으로 회원 조회
    public UserPaginationResDto getUserByName(SearchReqDto req, Pageable pageable) {
        log.info("ser");
        Slice<User> allUserSliceBy = userRepository.findUserByName(req.getName(), pageable);
        List<UserResDto> userResDto = allUserSliceBy.getContent().stream().map((UserResDto::of)).collect(Collectors.toList());

        if (userResDto.isEmpty()) {
            throw new NoSuchElementException("찾는 연구생 없음.");
        }else{
            return UserPaginationResDto.builder()
                    .users(userResDto)
                    .numberOfUser(allUserSliceBy.getNumberOfElements())
                    .page(allUserSliceBy.getPageable().getPageNumber())
                    .build();
        }
    }

    //회원 한명의 정보 받아오기
    public User getUserByNum(int num){
        return userRepository.getUserByNum(num);
    }
}
