package com.tododev.ToDoBot.controller;

import com.tododev.ToDoBot.model.ApplicationUser;
import com.tododev.ToDoBot.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;

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
            ApplicationUser loggedUser = applicationUserRepository.findApplicationUserByUsername(principal.getName());
            String dob = loggedUser.getDateOfBirth().toString().substring(0,10);
            model.addAttribute("user", loggedUser);
            model.addAttribute("date" , dob);
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
