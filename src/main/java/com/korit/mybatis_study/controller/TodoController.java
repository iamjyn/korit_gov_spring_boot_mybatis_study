package com.korit.mybatis_study.controller;

import com.korit.mybatis_study.dto.AddTodoReqDto;
import com.korit.mybatis_study.dto.EditTodoReqDto;
import com.korit.mybatis_study.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    // 투두 추가
    @PostMapping("/add")
    public ResponseEntity<?> addTodo(@RequestBody AddTodoReqDto addTodoReqDto) {
        return ResponseEntity.ok(todoService.addTodo(addTodoReqDto));
    }

    // 투두 전체 조회
    @GetMapping("/all")
    public ResponseEntity<?> getTodoList() {
        return ResponseEntity.ok(todoService.getTodoList());
    }

    // 투두 단건 조회
    @GetMapping("/{todoId}")
    public ResponseEntity<?> getTodoByTodoId(@PathVariable Integer todoId) {
        return ResponseEntity.ok(todoService.getTodoByTodoId(todoId));
    }

    // 투두 수정
    @PostMapping("/update")
    public ResponseEntity<?> editTodo(@RequestBody EditTodoReqDto editTodoReqDto) {
        return ResponseEntity.ok(todoService.editTodo(editTodoReqDto));
    }

    // 투두 삭제
    @PostMapping("/remove")
    public ResponseEntity<?> removeTodo(@RequestParam Integer todoId) {
        return ResponseEntity.ok(todoService.removeTodo(todoId));
    }


}
