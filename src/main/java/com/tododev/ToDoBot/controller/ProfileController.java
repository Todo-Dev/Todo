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
import java.util.Date;

@Controller
public class ProfileController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @PostMapping("/updateprofile")
    public RedirectView updateProfile(
             @RequestParam String firstname, @RequestParam String lastname
            , @RequestParam String email
            , @RequestParam String dateofbirth
            , @RequestParam String profession , Principal principal
    ) throws ParseException {
        ApplicationUser loggedUser = applicationUserRepository.findApplicationUserByUsername(principal.getName());
        if (dateofbirth.equals("")) {
            loggedUser.setDateOfBirth(loggedUser.getDateOfBirth());
        }
        else {
            SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
            Date date=formatter2.parse(dateofbirth);
            loggedUser.setDateOfBirth(date);
        }
        loggedUser.setFirstName(firstname);
        loggedUser.setLastName(lastname);
        loggedUser.setEmail(email);
        loggedUser.setProfession(profession);
        applicationUserRepository.save(loggedUser);
        return new RedirectView("/profile");
    }
}
