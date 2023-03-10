package com.example.dnlab.global.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    //회원가입 요청
    @Data
    public static class SignUpReq {
        // 필수 입력 필드
        private String name;
        private String id;
        private String pw;

    }

    //회원가입 응답
    @Data
    public static class SignUpRes{
        private String name;
        private String id;
        private String pw;
    }
    //로그인 요청
    @Data
    public static class loginReq{
        private String id;
        private String pw;
    }
    //로그인 응답
    @Data
    public static class loginRes{
        private String id;
        private String pw;
    }
    //회원가입 Id 중복확인
    @Data
    public static class dupleId{
        private String id;
    }
    @Data
    public static class UserCheckId{
        private String id;
    }
}
