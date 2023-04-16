package com.example.dnlab.domain.user.dto;

import lombok.*;

public class UserDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SignUpReq {
        private String name;
        private int studentId;
        private String id;
        private String pw;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserCheckId {
        private String id;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class loginReq {
        private String id;
        private String pw;
    }

    @Getter
    @Setter
    public static class MyPageRes {
        private String name;
        private int studentId;
        private int generation;

        public MyPageRes(String name, int studentId, int generation) {
        }
    }
}
