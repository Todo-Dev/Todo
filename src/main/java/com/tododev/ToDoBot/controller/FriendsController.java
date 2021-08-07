package com.tododev.ToDoBot.controller;

import com.tododev.ToDoBot.model.ApplicationUser;
import com.tododev.ToDoBot.repository.ApplicationUserRepository;
import com.tododev.ToDoBot.service.ActiveUserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class FriendsController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    ActiveUserStore activeUserStore;
    /**
     * This End Point For getting all user friends
     *
     *
     *
     */

    @GetMapping("/friends")
    public String getFriendsPage(Principal principal, Model model){
        if (principal==null){
            model.addAttribute("user", null);
        }else {
            model.addAttribute("user", "data");

        }

        model.addAttribute("active", activeUserStore.users);
        assert principal != null;
        model.addAttribute("friends", applicationUserRepository.findApplicationUserByUsername(principal.getName()).getFriends());
        return "friends";
    }

    @PostMapping("/unfriend/{id}")
    public RedirectView unFriend(Principal p , @PathVariable Long id){
        ApplicationUser loggedInUser  = applicationUserRepository.findApplicationUserByUsername(p.getName());
        loggedInUser.getFriends().remove(applicationUserRepository.findById(id).get());
        applicationUserRepository.save(loggedInUser);
        return new RedirectView("/friends");
    }
}
