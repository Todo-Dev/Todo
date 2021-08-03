package com.tododev.ToDoBot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {

    @GetMapping("/")
    public String getHomePage(Principal principal , Model model){
        if (principal==null){
            model.addAttribute("user", null);
        }else {
            model.addAttribute("user", "data");
        }
        return "index";
    }
    @GetMapping("/profile")
    public String getProfilePage(Principal principal,Model model) {
        if (principal==null){
            model.addAttribute("user", null);
        }else {
            model.addAttribute("user", "data");
        }
        return "profile";
    }
    @GetMapping("/loading")
    public String getLoadingPage() {
        return "loading";
    }
    @GetMapping("/board")
    public String getBoardPage(Principal principal ,Model model) {
        if (principal==null){
            model.addAttribute("user", null);
        }else {
            model.addAttribute("user", "data");
        }
        return "board";
    }
}
