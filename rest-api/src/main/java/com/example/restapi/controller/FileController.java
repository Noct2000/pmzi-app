package com.example.restapi.controller;

import com.example.restapi.model.Role;
import com.example.restapi.model.User;
import com.example.restapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
@Log4j2
public class FileController {
    private static final Pattern PATTERN = Pattern.compile("\\.txt$");
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Resource> openFile(
            Authentication authentication,
            @RequestParam("document") MultipartFile multipartFile
            ) {
        log.info("User with username: {} opened file: {}",
                authentication.getName(),
                multipartFile.getOriginalFilename()
        );
        Set<Role.RoleName> roleNames = userService.findByUsername(authentication.getName()).get().getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
        return getResponseEntity(multipartFile, roleNames);
    }

    private ResponseEntity<Resource> getResponseEntity(MultipartFile multipartFile, Set<Role.RoleName> roleNames) {
        if (roleNames.contains(Role.RoleName.DEMO) && !isTxtFormat(multipartFile.getOriginalFilename())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(multipartFile.getResource());
    }

    private boolean isTxtFormat(String filename) {
        return PATTERN.matcher(filename).find();
    }
}
