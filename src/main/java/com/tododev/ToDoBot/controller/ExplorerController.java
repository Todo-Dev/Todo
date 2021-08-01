package com.tododev.ToDoBot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExplorerController {
    @GetMapping("/explorer")
    public String explorerPage(){
        return "explorer";
    }
}
