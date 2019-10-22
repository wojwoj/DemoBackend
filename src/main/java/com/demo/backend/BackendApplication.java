package com.demo.backend;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Slf4j
    @RestController
    @RequestMapping("api")
    public static class RocketController {
        @GetMapping
        @ResponseBody
        public RocketResponse get() {
            log.info("Building new rocket");
            return RocketResponse.builder()
                    .host("localhost")
                    .timestamp(LocalDateTime.now())
                    .build();
        }
    }

    @Data
    @Builder
    public static class RocketResponse {
        String host;
        LocalDateTime timestamp;
    }
}
