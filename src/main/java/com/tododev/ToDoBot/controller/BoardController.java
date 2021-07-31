package com.tododev.ToDoBot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
    /*
    Get Boards page
     */
    @GetMapping("/myboards")
    public String getBoardsPage() {
        return "myboards";
    }
}
