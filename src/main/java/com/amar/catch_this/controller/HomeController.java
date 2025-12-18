package com.amar.catch_this.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    /**
     * Home page to confirm the app is running
     */
    @GetMapping("/")
    public String home() {
        return "Catch This API is running! Use /api/v1/catch-probability?playerId=<id>&season=<year>";
    }
}