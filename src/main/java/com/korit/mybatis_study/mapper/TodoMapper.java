package com.korit.mybatis_study.mapper;

import com.korit.mybatis_study.entity.Todo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TodoMapper {
    Optional<Todo> findByTodoTitle(String title);
    int addTodo(Todo todo);
    List<Todo> getTodoList();
    Optional<Todo> findByTodoId(Integer todoId);
    int editTodo(Todo todo);
    int removeTodo(Todo todo);
}
