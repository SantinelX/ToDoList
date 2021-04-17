package com.todolist.todolist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(path = "/test/public")
    public  String testPublic(){
        return "Public";
    }

    @GetMapping(path = "/test/private")
    public  String testPrivate(){
        return "Private";
    }

    @GetMapping(path = "/test/manager")
    public  String testManager(){
        return "Manager";
    }

    @GetMapping(path = "/test/developer")
    public  String testDeveloper(){
        return "Developer";
    }
}
