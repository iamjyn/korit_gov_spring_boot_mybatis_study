package com.korit.mybatis_study.dto;

import com.korit.mybatis_study.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EditBookReqDto {
    private Integer bookId;
    private String title;
    private String author;

    public Book toEntity() {
        return Book.builder()
                .bookId(bookId)
                .title(title)
                .author(author)
                .build();
    }
}
