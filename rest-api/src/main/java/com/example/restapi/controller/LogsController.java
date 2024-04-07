package com.example.restapi.controller;

import com.example.restapi.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
public class LogsController {
    private final LogService logService;

    @GetMapping("/login")
    public ResponseEntity<String> getLoginLogs() {
        return ResponseEntity.ok(logService.getLoginLogs());
    }

    @GetMapping("/event")
    public ResponseEntity<String> getEventLogs() {
        return ResponseEntity.ok(logService.getEventLogs());
    }
}
