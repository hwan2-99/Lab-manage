package com.example.dnlab.domain.application.entity;

import com.example.dnlab.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    private int num; // pk
    private String motive; //지원동기
    private String intro; // 자기소개
    private String wanted; // 가입 후 하고싶은 연구활동
    private Date createdAt; // 신청서 작성날짜
    private Date updatedAt; // 신청서 수정날짜
    private int user_num; // 회원 외래키

    public Application(String motive, String intro, String wanted, int user_num) {
        this.motive = motive;
        this.intro = intro;
        this.wanted = wanted;
        this.user_num = user_num;
    }

}
