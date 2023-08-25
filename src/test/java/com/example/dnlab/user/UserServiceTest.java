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

    @Test
    public void testGetUserByName() {
        String searchName = "조대수";
        Slice<User> userSlice = mock(Slice.class);
        Pageable pageable = mock(Pageable.class);

        // Create a list of User instances
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setNum(1);
        user1.setName("조대수");
        user1.setStudentId(1);
        user1.setId("admin");
        user1.setGeneration(1);
        user1.setLeaderYN(true);
        userList.add(user1);
        // Add more User instances as needed

        // Create a list of UserResDto instances using UserResDto.of()
        List<UserResDto> userResDtoList = userList.stream()
                .map(UserResDto::of)
                .collect(Collectors.toList());

        // Mock the userRepository.findUserByName() to return the userSlice
        when(userRepository.findUserByName(eq(searchName), eq(pageable))).thenReturn(userSlice);

        // Create a SearchReqDto and set the searchName
        SearchReqDto searchReqDto = new SearchReqDto();
        searchReqDto.setName(searchName);

        // Call the method being tested with the created SearchReqDto
        UserPaginationResDto result = userService.getUserByName(searchReqDto, pageable);

        assertNotNull(result);
        // Add more specific assertions if needed
    }


    @Test
    public void testGetUserByNum() {
        int userNum = 1;
        User user = new User(/* Initialize user data here */);

        when(userRepository.getUserByNum(userNum)).thenReturn(user);

        UserResDto result = userService.getUserByNum(userNum);

        // Add your assertions here to validate the result
        assertNotNull(result);
        // You can add more specific assertions based on the behavior of your code
    }

    // Add more test cases for other methods in UserService

}