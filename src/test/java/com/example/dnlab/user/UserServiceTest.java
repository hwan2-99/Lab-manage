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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

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

        // 패스워드를 BCrypt로 암호화하여 저장
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // userRepository.findById 메서드가 호출될 때 반환할 사용자 객체 설정
        User user = User.builder()
                .num(1)
                .id(username)
                .pw(hashedPassword)
                .studentId(12345)
                .name("Test User")
                .role(Role.MEMBER)
                .build();

        // 모의 객체(Mock)를 사용하여 userRepository.findById의 동작을 설정
        when(userRepository.findById(username)).thenReturn(user);

        // POST 요청으로 로그인 시도
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"" + username + "\",\"pw\":\"" + password + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test User"))
                .andExpect(jsonPath("$.id").value(username))
                .andExpect(jsonPath("$.loginSuccess").value(true));
    }

}