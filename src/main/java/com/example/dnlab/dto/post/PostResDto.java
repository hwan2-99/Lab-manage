package com.example.dnlab.dto.post;

import com.example.dnlab.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
public class PostResDto {
    private int id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String userName;
}
