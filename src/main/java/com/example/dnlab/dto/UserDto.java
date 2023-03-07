package com.example.dnlab.dto;

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
        private int num;
        private String name;
        private String id;
        private String pw;

        //추후 부여받는 필드
        private int generation;
        private boolean leaderYN;
    }

    //회원가입 응답
    @Data
    public static class SignUpRes{
        private String errorMsg;
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
    @Data
    public static class userCheck{
        private String id;
    }
}
