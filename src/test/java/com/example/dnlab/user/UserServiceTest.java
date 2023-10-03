package com.example.dnlab.user;

import com.example.dnlab.domain.User;
import com.example.dnlab.dto.user.*;
import com.example.dnlab.repository.UserRepository;
import com.example.dnlab.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@Transactional
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Test
    public void 회원가입() {
        //given
        UserResDto result = userService.join(SignUpReqDto.builder()
                        .name("김태환")
                        .studentId(1111111)
                        .uid("thh123")
                        .pw("a123123")
                        .build());
        //when
        User user = userRepository.findByUid(result.getUid());

        //then
        Assertions.assertThat(user.getName()).isEqualTo(result.getName());

    }

    @Test
    public void 로그인(){
        //given
        LoginTokenResDto result = userService.login(LoginReqDto.builder()
                        .uid("admin")
                        .pw("123")
                        .build());
        //when
        User user = userRepository.findByUid("admin");

        //then
        Assertions.assertThat(user.getUid()).isEqualTo(result.getUid());
    }

    @Test
    public void 회원검색(){

    }

}