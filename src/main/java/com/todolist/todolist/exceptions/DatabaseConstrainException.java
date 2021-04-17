package com.todolist.todolist.exceptions;

public class DatabaseConstrainException extends RuntimeException {

    public DatabaseConstrainException(String message){
        super(message);
    }
}
