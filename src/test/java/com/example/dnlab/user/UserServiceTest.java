package com.example.dnlab.user;

import com.example.dnlab.domain.User;
import com.example.dnlab.dto.user.SearchReqDto;
import com.example.dnlab.dto.user.UserPaginationResDto;
import com.example.dnlab.dto.user.UserResDto;
import com.example.dnlab.repository.UserRepository;
import com.example.dnlab.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(/* Initialize user data here */));
        // Add more users as needed

        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.getAllUsers();

        // Assert that the returned result matches the mocked userList
        assertEquals(userList, result);
    }



}