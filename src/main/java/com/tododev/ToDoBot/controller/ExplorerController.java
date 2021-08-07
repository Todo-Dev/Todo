package com.tododev.ToDoBot.controller;

import com.tododev.ToDoBot.model.ApplicationUser;
import com.tododev.ToDoBot.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class ExplorerController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @GetMapping("/explorer")
    public String explorerPage(Principal principal,Model model){
        if (principal==null){
            model.addAttribute("user", null);
        }else {
            model.addAttribute("user", "data");
        }
        List<ApplicationUser> users= applicationUserRepository.findAll();
        ApplicationUser currentUser = applicationUserRepository.findApplicationUserByUsername(principal.getName());
        users.removeAll(currentUser.getFriends());
        users.remove(currentUser);
        model.addAttribute("searchUser",users);
        return "explorer";
    }


    @GetMapping("/findUser")
    public String findUser(@RequestParam String username, Model model,Principal principal){
        if (principal==null){
            model.addAttribute("user", null);
        }else {
            model.addAttribute("user", "data");
        }
        List<ApplicationUser> users= applicationUserRepository.findAll();
        ApplicationUser currentUser = applicationUserRepository.findApplicationUserByUsername(principal.getName());
        users.removeAll(currentUser.getFriends());
        users.remove(currentUser);
        List<ApplicationUser>  searchedUser = new ArrayList<>();
        for(ApplicationUser user : users){
            if (user.getUsername().toLowerCase(Locale.ROOT).contains(username)){
                searchedUser.add(user);
            }
        }
        model.addAttribute("searchUser",searchedUser);

         return "explorer";
    }

    @PostMapping("/addFriend/{id}")
    public RedirectView addFriend(@PathVariable Long id , Principal principal){
        ApplicationUser user = applicationUserRepository.findApplicationUserByUsername(principal.getName());
        ApplicationUser friend = applicationUserRepository.findById(id).get();
        user.getFriends().add(friend);
        applicationUserRepository.save(user);
        return new RedirectView("/explorer");
    }
}
