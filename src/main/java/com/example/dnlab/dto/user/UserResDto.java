package com.example.dnlab.dto.user;

import com.example.dnlab.domain.User;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;

@Getter
@Builder
public class UserResDto {
    private int num;  // 회원의 pk
    private String name; //이름
    private int studentId; //학번
    private String id; //아이디
    private int generation; //기수
    private boolean leaderYN; // 연구실장 유무

    public static UserResDto of(User user) {


        return UserResDto.builder()
                .num(user.getNum())
                .name(user.getName())
                .studentId(user.getStudentId())
                .generation(user.getGeneration())
                .leaderYN(user.isLeaderYN())
                .build();
    }
}
