package com.tododev.ToDoBot.controller;

import com.tododev.ToDoBot.model.ApplicationUser;
import com.tododev.ToDoBot.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class FriendsController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    /**
     * This End Point For getting all user friends
     * @param principal
     * @param model
     * @return
     */
    @GetMapping("/friends")
    public String getFriendsPage(Principal principal, Model model){
        if (principal==null){
            model.addAttribute("user", null);
        }else {
            model.addAttribute("user", "data");
        }

        model.addAttribute("friends", applicationUserRepository.findApplicationUserByUsername(principal.getName()).getFriends());
        return "friends";
    }
}
