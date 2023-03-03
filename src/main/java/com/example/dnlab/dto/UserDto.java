package com.example.dnlab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

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

    @Data
    public static class SignUpRes{
        private String errorMsg;
    }
}
