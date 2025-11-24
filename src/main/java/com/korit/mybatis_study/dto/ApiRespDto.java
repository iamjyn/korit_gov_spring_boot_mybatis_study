package com.korit.mybatis_study.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ApiRespDto<T> {
    private String status;
    private String message;
    private T data;
}
