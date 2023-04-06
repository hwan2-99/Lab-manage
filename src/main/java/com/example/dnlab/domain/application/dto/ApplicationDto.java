package com.example.dnlab.domain.application.dto;

import lombok.*;

public class ApplicationDto {


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class LabSignUpReq{
        private String motive; //지원동기
        private String intro; // 자기소개
        private String wanted; // 가입 후 하고싶은 연구활동
        private String userName; // 유저 이름
        private int studentId; // 유저 학번
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ApplicationWithUser {
        private Long id;
        private String motive; //지원동기
        private String intro; // 자기소개
        private String wanted; // 가입 후 하고싶은 연구활동
        private String userName; // 유저 이름
        private int studentId; // 유저 학번
        private String createdAt; // 신청서 작성날짜
        private String updatedAt; // 신청서 수정날짜
    }
}
