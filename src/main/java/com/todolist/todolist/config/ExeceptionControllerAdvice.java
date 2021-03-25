package com.todolist.todolist.config;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// @RestControllerAdvice - prinde exceptiile din backend si le transforma in mesaje sugestive pentru utilizatoir
@RestControllerAdvice
public class ExeceptionControllerAdvice {

    /**
     * 200 - SUCCESS
     * 300 - REDIRECT
     * 400 - CLIENT SIDE ERROR
     * 500 - BACKEND ERROR
     */

    @ExceptionHandler(value ={ConfigDataResourceNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(ConfigDataResourceNotFoundException ex){

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMesage(ex.getMessage());
        return errorMessage;
    }
}
