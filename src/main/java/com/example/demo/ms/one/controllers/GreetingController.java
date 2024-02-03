package com.example.demo.ms.one.controllers;

import com.example.demo.ms.one.models.GreetingResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalTime;

@Slf4j
@RestController
public class GreetingController {

    @Value("${spring.application.name}")
    private String applicationId;

    @GetMapping("/greeting")
    public GreetingResponse greeting(@RequestParam(name = "name", defaultValue = "World") String name) {
        log.info("Received request to /greeting with name = {}", name);
        LocalTime currentTime = LocalTime.now();
        String greetingMessage = "Hello, " + name + "! Current time is " + currentTime;

        return new GreetingResponse(applicationId, greetingMessage);
    }


}
