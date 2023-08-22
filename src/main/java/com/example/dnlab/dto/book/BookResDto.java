package com.example.dnlab.dto.book;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookResDto {
    private String title;
    private String author;
    private boolean borrowYN;
}
