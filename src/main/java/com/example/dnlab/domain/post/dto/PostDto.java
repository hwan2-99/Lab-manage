package com.example.dnlab.domain.post.dto;

import lombok.*;

public class PostDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class postReq{
        private String title;
        private String content;

    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class updateReq{
        private String content; //할일 내용
    }
}
