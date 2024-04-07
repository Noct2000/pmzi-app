package com.example.restapi.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class HealthController {
    @GetMapping("/success")
    public String success() {
        log.info("/success endpoint was triggered");
        return "success";
    }
}
