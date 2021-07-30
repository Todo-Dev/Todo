package com.tododev.ToDoBot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/loading")
    public String getLoadingPage() {
        return "loading";
    }
}
