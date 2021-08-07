package com.tododev.ToDoBot.controller;

import com.tododev.ToDoBot.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class BoardController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    /*
    Get Boards page
     */
    @GetMapping("/myboards")
    public String getBoardsPage(Principal principal, Model model) {
        if (principal==null){
            model.addAttribute("user", null);
        }else {
            model.addAttribute("user", "data");
        }
        assert principal != null;
        model.addAttribute("board",applicationUserRepository.findApplicationUserByUsername(principal.getName()).getBoardLists());
        return "myboards";
    }
}
