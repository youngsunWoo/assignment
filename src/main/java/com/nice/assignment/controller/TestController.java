package com.nice.assignment.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test/{name}")
    public ResponseEntity<String> test(@PathVariable("name") String name) {
        return ResponseEntity.ok()
                .body("Hello! "+name);
    }
}
