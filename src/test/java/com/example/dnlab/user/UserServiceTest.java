package com.example.dnlab.user;

import com.example.dnlab.domain.Role;
import com.example.dnlab.domain.User;
import com.example.dnlab.dto.user.LoginReqDto;
import com.example.dnlab.dto.user.SignUpReqDto;
import com.example.dnlab.dto.user.UserResDto;
import com.example.dnlab.repository.UserRepository;
import com.example.dnlab.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private static final Logger log = LoggerFactory.getLogger(UserServiceTest.class);
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @Mock
    private HttpSession session;

    @InjectMocks
    private UserService userService;

//    @Test
//    public void 모든유저조회() {
//        List<User> userList = new ArrayList<>();
//        userList.add(new User(/* Initialize user data here */));
//        // Add more users as needed
//
//        when(userRepository.findAll()).thenReturn(userList);
//
//        List<User> result = userService.getAllUsers();
//
//        // Assert that the returned result matches the mocked userList
//        assertEquals(userList, result);
//    }


    @Test
    public void 회원가입() throws Exception {
        // 테스트할 데이터 생성
        SignUpReqDto signUpReqDto = SignUpReqDto.builder()
                .name("1")
                .studentId(1)
                .id("1")
                .pw("1")
                .build();


        // UserService의 join 메서드가 호출될 때 반환할 데이터 설정
        UserResDto userResDto = UserResDto.builder()
                .name(signUpReqDto.getName())
                .id(signUpReqDto.getId())
                .studentId(signUpReqDto.getStudentId())
                .build();

        when(userService.join(signUpReqDto)).thenReturn(userResDto);
        // POST 요청으로 회원 가입 시도
        mockMvc.perform(MockMvcRequestBuilders.post("/user/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"1\",\"studentId\":\"1\",\"id\":\"1\",\"pw\":\"1\"}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.studentId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
    }
    @Test
    public void testLoginSuccess() {
        // 테스트 데이터 설정
        String username = "1";
        String password = "1";
        String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        User expectedUser = User.builder()
                .num(1)
                .id(username)
                .pw(encryptedPassword) // 암호화된 패스워드 저장
                .studentId(12345)
                .name("1")
                .build();

        log.info("Expected User: {}", expectedUser.getName()); // 예상 사용자 정보를 로그에 출력

        // userRepository.findById 메서드가 호출될 때 반환할 사용자 객체 설정
        when(userRepository.findById(username)).thenReturn(expectedUser);

        LoginReqDto loginReqDto = LoginReqDto.builder()
                .id(username)
                .pw(password)
                .build();

        log.info("Login Request: id={}, pw={}", loginReqDto.getId(), loginReqDto.getPw()); // 로그인 요청 정보를 로그에 출력

        UserResDto loginResult = userService.login(loginReqDto, session);

        log.info("Login Result: name={}, id={}, loginSuccess={}", loginResult.getName(), loginResult.getId(), loginResult.isLoginSuccess()); // 로그인 결과를 로그에 출력

        // 결과 검증
        assertNotNull(loginResult);
        assertEquals(expectedUser.getName(), loginResult.getName());
        assertEquals(expectedUser.getId(), loginResult.getId());
        assertTrue(loginResult.isLoginSuccess());

        // 로그 메시지 추가
        log.info("로그인 테스트 결과: 사용자 '{}'가 성공적으로 로그인하였습니다.", username);
    }


    @Test
    public void 로그인성공() throws Exception {
        // 테스트할 데이터 생성
        String username = "testuser";
        String password = "testpassword";

        UserResDto userResDto = UserResDto.builder()
                .num(1)
                .name("Test User")
                .id(username)
                .loginSuccess(true)
                .studentId(12345)
                .build();

        // userRepository.findById 메서드가 호출될 때 반환할 사용자 객체 설정
        User user = User.builder()
                .num(1)
                .id(username)
                .pw(password)
                .studentId(12345)
                .name("Test User")
                .role(Role.MEMBER)
                .build();

        when(userRepository.findById(username)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"" + username + "\",\"pw\":\"" + password + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test User"))
                .andExpect(jsonPath("$.id").value(username))
                .andExpect(jsonPath("$.loginSuccess").value(true));
    }

}