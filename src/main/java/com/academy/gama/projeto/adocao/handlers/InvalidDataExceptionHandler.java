package com.academy.gama.projeto.adocao.handlers;

import com.academy.gama.projeto.adocao.exception.ErrorResponse;
import com.academy.gama.projeto.adocao.exception.InvalidDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

import static com.academy.gama.projeto.adocao.utils.Constants.DATE_TIME_PATTERN;

@ControllerAdvice
public class InvalidDataExceptionHandler {

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidDataException(InvalidDataException ex) {

        ErrorResponse error = new ErrorResponse();

        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setErrorTime(LocalDateTime.now().format(DATE_TIME_PATTERN));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
