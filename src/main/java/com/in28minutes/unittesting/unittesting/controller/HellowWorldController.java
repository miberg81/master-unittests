package com.in28minutes.unittesting.unittesting.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HellowWorldController {

    // controller method cannot be called like a regular one
    // by initializing class and calling it (as we did with Mochito)
    // this method should be called only by http call to URI
    // This is where MockMvc comes to help
    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World";
    }
}
