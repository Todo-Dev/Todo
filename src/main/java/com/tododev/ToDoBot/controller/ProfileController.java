package com.tododev.ToDoBot.controller;

import com.tododev.ToDoBot.model.ApplicationUser;
import com.tododev.ToDoBot.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

@Controller
public class ProfileController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @PostMapping("/updateprofile")
    public RedirectView updateProfile(
             @RequestParam String firstname, @RequestParam String lastname
            , @RequestParam String email
            , @RequestParam Date dateofbirth
            , @RequestParam String profession , Principal principal
    ) throws ParseException {
        ApplicationUser loggedUser = applicationUserRepository.findApplicationUserByUsername(principal.getName());

            loggedUser.setDateOfBirth(dateofbirth);
        loggedUser.setFirstName(firstname);
        loggedUser.setLastName(lastname);
        loggedUser.setEmail(email);
        loggedUser.setProfession(profession);
        applicationUserRepository.save(loggedUser);
        return new RedirectView("/profile");
    }
}
