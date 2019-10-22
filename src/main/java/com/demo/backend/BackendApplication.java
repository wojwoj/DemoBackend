package com.demo.backend;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Slf4j
    @RestController
    @RequestMapping("api")
    public static class RocketController {

        private List<Rocket> rockets = new ArrayList<>();

        @GetMapping
        @ResponseBody
        public List<Rocket> get() {
            log.info("Getting all rockets");
            return rockets;
        }

        @PutMapping
        @ResponseBody
        public Rocket put() throws UnknownHostException {
            log.info("Building new rocket");
            Rocket rocket = Rocket.builder()
                    .host(InetAddress.getLocalHost().getHostName())
                    .timestamp(LocalDateTime.now())
                    .build();
            rockets.add(rocket);
            return rocket;
        }
    }

    @Data
    @Builder
    public static class Rocket {
        private String host;
        private LocalDateTime timestamp;
    }
}
