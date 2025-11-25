package com.korit.mybatis_study.mapper;

import com.korit.mybatis_study.entity.Board;
import com.korit.mybatis_study.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BookMapper {
    Optional<Book> findBookByTitle(String title);
    int addBook(Book book);
    List<Book> getBookList();
    Optional<Book> findByBookId(Integer bookId);
    int editBook(Book book);
    int removeBook(Book book);
}
