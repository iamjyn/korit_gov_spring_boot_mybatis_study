package com.korit.mybatis_study.service;

import com.korit.mybatis_study.dto.AddTodoReqDto;
import com.korit.mybatis_study.dto.ApiRespDto;
import com.korit.mybatis_study.dto.EditTodoReqDto;
import com.korit.mybatis_study.entity.Todo;
import com.korit.mybatis_study.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public ApiRespDto<?> addTodo(AddTodoReqDto addTodoReqDto) {
        Optional<Todo> foundTodo = todoRepository.findByTodoTitle(addTodoReqDto.getTitle());
        if (foundTodo.isPresent()) {
            return new ApiRespDto<>("failed", "이미 등록된 todo 입니다.", null);
        }
        Optional<Todo> todo = todoRepository.addTodo(addTodoReqDto.toEntity());
        if (todo.isEmpty()) {
            return new ApiRespDto<>("failed", "추가하는데 문제가 발생했습니다.", null);
        }
        return new ApiRespDto<>("success", "Todo 추가 완료", todo.get());
    }

    public ApiRespDto<?> getTodoList() {
        return new ApiRespDto<>("success", "Todo 전체 조회 완료", todoRepository.getTodoList());
    }

    public ApiRespDto<?> getTodoByTodoId(Integer todoId) {
        Optional<Todo> foundTodo = todoRepository.getTodoByTodoId(todoId);
        if (foundTodo.isEmpty()) {
            return new ApiRespDto<>("failed", "해당 Todo는 존재하지 않습니다.", null);
        }
        return new ApiRespDto<>("success", "Todo 조회 완료", foundTodo.get());
    }

    public ApiRespDto<?> editTodo(EditTodoReqDto editTodoReqDto) {
        Optional<Todo> foundTodo = todoRepository.getTodoByTodoId(editTodoReqDto.getTodoId());
        if (foundTodo.isEmpty()) {
            return new ApiRespDto<>("failed", "해당 Todo는 존재하지 않습니다.", null);
        }
        int result = todoRepository.editTodo(editTodoReqDto.toEntity());
        if (result != 1) {
            return new ApiRespDto<>("failed", "Todo 수정에 실패하였습니다.", null);
        }
        return new ApiRespDto<>("success", "Todo 수정 완료", null);
    }

    public ApiRespDto<?> removeTodo(Integer todoId) {
        Optional<Todo> foundTodo = todoRepository.getTodoByTodoId(todoId);
        if (foundTodo.isEmpty()) {
            return new ApiRespDto<>("failed", "해당 Todo는 존재하지 않습니다.", null);
        }
        int result = todoRepository.removeTodo(foundTodo.get());
        if (result != 1) {
            return new ApiRespDto<>("failed", "Todo 삭제에 실패하였습니다.", null);
        }
        return new ApiRespDto<>("success", "Todo 삭제 성공", null);
    }


}
