package com.korit.mybatis_study.service;

import com.korit.mybatis_study.dto.AddBookReqDto;
import com.korit.mybatis_study.dto.ApiRespDto;
import com.korit.mybatis_study.dto.EditBookReqDto;
import com.korit.mybatis_study.entity.Book;
import com.korit.mybatis_study.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public ApiRespDto<?> addBook(AddBookReqDto addBookReqDto) {
        Optional<Book> foundBook = bookRepository.findBookByTitle(addBookReqDto.getTitle());
        if (foundBook.isPresent()) {
            return new ApiRespDto<>("failed", "이미 사용중인 title 입니다.", null);
        }
        Optional<Book> book = bookRepository.addBook(addBookReqDto.toEntity());
        if (book.isEmpty()) {
            return new ApiRespDto<>("failed", "추가하는데 문제 발생", null);
        }
        return new ApiRespDto<>("success", "추가 완료", book.get());
    }

    public ApiRespDto<?> getBookList() {
        return new ApiRespDto<>("success", "Book 전체 조회 완료", bookRepository.getBookList());
    }

    public ApiRespDto<?> getBookByBookId(Integer bookId) {
        Optional<Book> foundBook = bookRepository.getBookByBookId(bookId);
        if (foundBook.isEmpty()) {
            return new ApiRespDto<>("failed", "해당 title은 존재하지 않습니다.", null);
        }
        return new ApiRespDto<>("success", "title 조회 완료", foundBook.get());
    }

    public ApiRespDto<?> editBook(EditBookReqDto editBookReqDto) {
        Optional<Book> foundBook = bookRepository.getBookByBookId(editBookReqDto.getBookId());
        if (foundBook.isEmpty()) {
            return new ApiRespDto<>("failed", "해당 title은 존재하지 않습니다.", null);
        }
        int result = bookRepository.editBook(editBookReqDto.toEntity());
        if (result != 1) {
            return new ApiRespDto<>("failed", "수정 실패", null);
        }
        return new ApiRespDto<>("success", "수정 완료", null);
    }

    public ApiRespDto<?> removeBook(Integer bookId) {
        Optional<Book> foundBook = bookRepository.getBookByBookId(bookId);
        if (foundBook.isEmpty()) {
            return new ApiRespDto<>("failed", "해당 title은 존재하지 않습니다.", null);
        }
        int result = bookRepository.removeBook(foundBook.get());
        if (result != 1) {
            return new ApiRespDto<>("filed", "삭제 실패", null);
        }
        return new ApiRespDto<>("success", "삭제 성공", null);
    }
}
