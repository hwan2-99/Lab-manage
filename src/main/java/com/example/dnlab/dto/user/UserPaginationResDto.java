package com.example.dnlab.dto.user;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Getter
@Builder
public class UserPaginationResDto {
    List<UserResDto> users;
    private int numberOfUser;
    private long page;
}
