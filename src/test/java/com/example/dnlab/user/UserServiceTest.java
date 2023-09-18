//package com.example.dnlab.user;
//
//import com.example.dnlab.domain.Role;
//import com.example.dnlab.domain.User;
//import com.example.dnlab.dto.user.LoginReqDto;
//import com.example.dnlab.dto.user.SignUpReqDto;
//import com.example.dnlab.dto.user.UserResDto;
//import com.example.dnlab.repository.UserRepository;
//import com.example.dnlab.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.security.crypto.bcrypt.BCrypt;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import javax.servlet.http.HttpSession;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@ExtendWith(MockitoExtension.class)
//public class UserServiceTest {
//    private static final Logger log = LoggerFactory.getLogger(UserServiceTest.class);
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private HttpSession session;
//
//    @InjectMocks
//    private UserService userService;
//
////    @Test
////    public void 모든유저조회() {
////        List<User> userList = new ArrayList<>();
////        userList.add(new User(/* Initialize user data here */));
////        // Add more users as needed
////
////        when(userRepository.findAll()).thenReturn(userList);
////
////        List<User> result = userService.getAllUsers();
////
////        // Assert that the returned result matches the mocked userList
////        assertEquals(userList, result);
////    }
//
//
//    @Test
//    public void 회원가입() throws Exception {
//        // 테스트할 데이터 생성
//        SignUpReqDto signUpReqDto = SignUpReqDto.builder()
//                .name("1")
//                .studentId(1)
//                .id("1")
//                .pw("1")
//                .build();
//
//
//        // UserService의 join 메서드가 호출될 때 반환할 데이터 설정
//        UserResDto userResDto = UserResDto.builder()
//                .name(signUpReqDto.getName())
//                .id(signUpReqDto.getId())
//                .studentId(signUpReqDto.getStudentId())
//                .build();
//
//        when(userService.join(signUpReqDto)).thenReturn(userResDto);
//        // POST 요청으로 회원 가입 시도
//        mockMvc.perform(MockMvcRequestBuilders.post("/user/signUp")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"name\":\"1\",\"studentId\":\"1\",\"id\":\"1\",\"pw\":\"1\"}"))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("1"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.studentId").value("1"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
//    }
////    @Test
////    public void testLoginSuccess() {
////        // 테스트 데이터 설정
////        String uid = "admin";
////        String password = "123";
////        String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
////
////        User expectedUser = User.builder()
////                .uid(uid)
////                .pw(encryptedPassword) // 암호화된 패스워드 저장
////                .studentId(1)
////                .name("1")
////                .build();
////
////        // userRepository.findById 메서드가 호출될 때 반환할 사용자 객체 설정
////        when(userRepository.findByUid(uid)).thenReturn(expectedUser);
////
////        LoginReqDto loginReqDto = LoginReqDto.builder()
////                .uid(uid)
////                .pw(password)
////                .build();
////
////        UserResDto loginResult = userService.login(loginReqDto, session);
////        log.info(loginResult.getName());
////
////        // 결과 검증
////        assertNotNull(loginResult);
////        assertEquals(expectedUser.getName(), loginResult.getName());
////        assertEquals(expectedUser.getId(), loginResult.getId());
////        assertTrue(loginResult.isLoginSuccess());
////
////        // 세션에 사용자가 저장되었는지 확인
////        verify(session, times(1)).setAttribute("user", expectedUser);
////    }
//
//
//
//}