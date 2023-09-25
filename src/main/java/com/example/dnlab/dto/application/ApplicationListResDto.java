package com.example.dnlab.dto.application;

import com.example.dnlab.domain.Application;
import com.example.dnlab.domain.ApplicationStatus;
import lombok.Getter;

@Getter
public class ApplicationListResDto {
    private int id; // pk
    private String motive; //지원동기
    private String intro; // 자기소개
    private String wanted; // 가입 후 하고싶은 연구활동
    private ApplicationStatus status; // 신청서 승낙여부
    private int userId;
    private String userName;
    private int studentId;

    public ApplicationListResDto(Application entity) {
        this.id = entity.getId();
        this.motive = entity.getMotive();
        this.intro = entity.getIntro();
        this.wanted = entity.getWanted();
        this.status = entity.getStatus();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getName();
        this.studentId = entity.getUser().getStudentId();
    }


}
