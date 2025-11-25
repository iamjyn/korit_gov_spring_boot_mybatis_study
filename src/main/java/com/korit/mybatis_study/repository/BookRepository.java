package com.korit.mybatis_study.repository;

import com.korit.mybatis_study.entity.Board;
import com.korit.mybatis_study.entity.Book;
import com.korit.mybatis_study.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {

    @Autowired
    private BookMapper bookMapper;

    public Optional<Book> findBookByTitle(String title) {
        return bookMapper.findBookByTitle(title);
    }

    public Optional<Book> addBook(Book book) {
        try {
            bookMapper.addBook(book);
        } catch (DuplicateKeyException e) {
            return Optional.empty();
        }
        return Optional.of(book);
    }

    public List<Book> getBookList() {
        return bookMapper.getBookList();
    }

    public Optional<Book> getBookByBookId(Integer bookId) {
        return bookMapper.findByBookId(bookId);
    }

    public int editBook(Book book) {
        return bookMapper.editBook(book);
    }

    public int removeBook(Book book) {
        return bookMapper.removeBook(book);
    }
}
