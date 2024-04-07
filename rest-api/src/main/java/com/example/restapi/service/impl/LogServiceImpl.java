package com.example.restapi.service.impl;

import com.example.restapi.service.LogService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl implements LogService {
    @Value("${LOGIN_LOG}")
    private String loginLog;
    @Value("${EVENTS_LOG}")
    private String eventsLog;

    @Override
    @SneakyThrows
    public String getLoginLogs() {
        return getFileContent(loginLog);
    }

    @Override
    @SneakyThrows
    public String getEventLogs() {
        return getFileContent(eventsLog);
    }

    private String getFileContent(String filename) throws IOException {
        return String.join("\n", Files.readAllLines(Path.of(filename)));
    }
}
