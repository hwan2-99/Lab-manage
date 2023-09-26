package com.example.dnlab.dto.application;

import com.example.dnlab.domain.ApplicationStatus;
import com.example.dnlab.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicationResDto {
    private int id; // pk
    private String motive; //지원동기
    private String intro; // 자기소개
    private String wanted; // 가입 후 하고싶은 연구활동
    private ApplicationStatus status; // 신청서 승낙여부
    private int userId;
    private String userName;
    private int studentId;
}
