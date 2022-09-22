package com.academy.gama.projeto.adocao.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private String message;
    private Integer status;
    private String errorTime;
}
