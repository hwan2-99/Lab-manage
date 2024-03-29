package com.example.dnlab.service;

import com.example.dnlab.dto.user.*;
import com.example.dnlab.domain.Role;
import com.example.dnlab.domain.User;
import com.example.dnlab.repository.UserRepository;
import com.example.dnlab.utils.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    // 회원 가입
    public UserResDto join(SignUpReqDto req) {
        log.info("name : {}, StudentId :{}, id = {}, pw = {} ", req.getName(), req.getStudentId(), req.getUid(), req.getPw());

        if (userRepository.findByUid(req.getUid()) != null) {
            throw new IllegalArgumentException("이미 존재하는 학번입니다.");
        }
        String salt = BCrypt.gensalt(); // 랜덤한 salt 생성
        String encodedPassword = BCrypt.hashpw(req.getPw(), salt); // 비밀번호 암호화

        User user = req.toEntity();
        user.setPw(encodedPassword);
        // 일반 회원으로 초기 역할 설정
        userRepository.save(user);
        return UserResDto.builder()
                .name(user.getName())
                .uid(user.getUid())
                .studentId(user.getStudentId())
                .build();
    }

    // 로그인
    public LoginTokenResDto login(LoginReqDto req) {
        User user = userRepository.findByUid(req.getUid());
        if(user == null){
            throw new BadCredentialsException("존재하지 않는 아이디입니다. : " + req.getUid());
        }

        if(!passwordEncoder.matches(req.getPw(), user.getPw())){
            throw new BadCredentialsException("비밀번호가 틀렸습니다.: " + req.getPw());
        }

        String accessToken = jwtProvider.createAccessToken(user.getUid());
        String refreshToken = jwtProvider.createRefreshToken();

        return LoginTokenResDto.builder()
                .uid(user.getUid())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
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
    public UserResDto getById(int id){
        User user = userRepository.findById(id);
        return UserResDto.builder()
                .studentId(user.getStudentId())
                .uid(user.getUid())
                .generation(user.getGeneration())
                .name(user.getName())
                .leaderYN(user.isLeaderYN())
                .build();
    }
}
