package com.springbootapi.springbootapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    private static final Logger log = LoggerFactory.getLogger(HelloWorldController.class);

    @Autowired
    private Environment env;

    @GetMapping("/hello")
    public ResponseEntity<String> getHelloWorld(){
        log.info("Sever is running on port: {}", env.getProperty("server.port"));
        return ResponseEntity.status(200).body("<h1>Hello Krissh!</h1>");
    }
}
