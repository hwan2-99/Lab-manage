package com.example.dnlab.dto.post;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class PostResDto {
    private int id;
    private String title;
    private String content;
    private LocalDate createdAt;
    private LocalDateTime updatedAt;

    private String userName;
}
