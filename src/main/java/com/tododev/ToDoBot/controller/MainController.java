package com.tododev.ToDoBot.controller;

import com.tododev.ToDoBot.model.ApplicationUser;
import com.tododev.ToDoBot.repository.ApplicationUserRepository;
import com.tododev.ToDoBot.service.ActiveUserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
public class MainController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;


    @Autowired
    ActiveUserStore activeUserStore;

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
            String dob = loggedUser.getDateOfBirth().toString();
            model.addAttribute("user", loggedUser);
            model.addAttribute("date" , dob);
            model.addAttribute("active", activeUserStore.getUsers());
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
