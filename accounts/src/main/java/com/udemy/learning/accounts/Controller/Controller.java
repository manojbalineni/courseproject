package com.udemy.learning.accounts.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping(value = "/hello")
    public String helloWorld(){
        return "Hello World";
    }
}
