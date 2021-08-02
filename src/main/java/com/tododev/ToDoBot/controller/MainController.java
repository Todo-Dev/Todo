package com.tododev.ToDoBot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {


    @GetMapping("/loading")
    public String getLoadingPage() {
        return "loading";
    }


    @GetMapping("/board")
    public String getBoardPage() {
        return "board";
    }
}
