package com.example.dnlab.domain.book.dto;

import lombok.*;

public class BookDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class insertBookReq{
        private String title;
        private String author;
        private boolean borrowYN;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class borrowBookReq{
        private int bookNum;
        private String title;
        private String author;
        private boolean borrowYN;
    }

}
