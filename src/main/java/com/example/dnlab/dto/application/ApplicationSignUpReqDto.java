package com.example.dnlab.dto.application;

import com.example.dnlab.domain.Application;
import com.example.dnlab.domain.ApplicationStatus;
import com.example.dnlab.domain.User;
import lombok.*;

import static com.example.dnlab.domain.ApplicationStatus.PENDING;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationSignUpReqDto {
    private String motive; //지원동기
    private String intro; // 자기소개
    private String wanted; // 가입 후 하고싶은 연구활동
    private String userName; // 유저 이름
    private ApplicationStatus status;
    private int studentId; // 유저 학번

    public Application toEntity(User user) {
        return Application.builder()
                .motive(motive)
                .intro(intro)
                .status(PENDING)
                .wanted(wanted)
                .user(user)
                .build();
    }
}
