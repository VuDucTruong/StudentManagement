package com.vdt.student_management.common.controller;

import com.vdt.student_management.common.dto.TestRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class TestController {

    @PostMapping
    ResponseEntity<Void> test(@RequestBody TestRequest testRequest) {
        log.info("test request: {}", testRequest);
        return ResponseEntity.ok().build();
    }
}
