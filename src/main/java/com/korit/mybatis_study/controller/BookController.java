package com.korit.mybatis_study.controller;

import com.korit.mybatis_study.dto.AddBookReqDto;
import com.korit.mybatis_study.dto.EditBookReqDto;
import com.korit.mybatis_study.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody AddBookReqDto addBookReqDto) {
        return ResponseEntity.ok(bookService.addBook(addBookReqDto));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getBookList() {
        return ResponseEntity.ok(bookService.getBookList());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBookByBookId(@PathVariable Integer bookId) {
        return ResponseEntity.ok(bookService.getBookByBookId(bookId));
    }

    @PostMapping("/update")
    public ResponseEntity<?> editBook(@RequestBody EditBookReqDto editBookReqDto) {
        return ResponseEntity.ok(bookService.editBook(editBookReqDto));
    }

    @PostMapping("/remove")
    public ResponseEntity<?> removeBook(@RequestParam Integer bookId) {
        return ResponseEntity.ok(bookService.removeBook(bookId));
    }

}
