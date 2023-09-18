package com.example.dnlab.dto.user;

import com.example.dnlab.domain.User;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;

@Getter
@Builder
public class UserResDto {
    private int id;  // 회원의 pk
    private String name; //이름
    private int studentId; //학번
    private String uid; //아이디
    private int generation; //기수
    private boolean loginSuccess;
    private boolean leaderYN; // 연구실장 유무

    public static UserResDto of(User user) {

        return UserResDto.builder()
                .id(user.getId())
                .name(user.getName())
                .studentId(user.getStudentId())
                .generation(user.getGeneration())
                .leaderYN(user.isLeaderYN())
                .build();
    }
}
