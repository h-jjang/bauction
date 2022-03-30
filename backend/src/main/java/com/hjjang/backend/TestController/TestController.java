package com.hjjang.backend.TestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/api/hello1")
    public String hello1() {
        return "hello";
    }
}
