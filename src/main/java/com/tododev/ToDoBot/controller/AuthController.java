package com.tododev.ToDoBot.controller;

import com.tododev.ToDoBot.model.ApplicationUser;
import com.tododev.ToDoBot.repository.ApplicationUserRepository;
import com.tododev.ToDoBot.service.ActiveUserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

@Controller
public class AuthController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    ActiveUserStore activeUserStore;
    /*
    Login And signup controllers
     */

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String getSignUpPage() {
        return "signup";
    }

    /*
    Sign Up ROUTE
     */

    @PostMapping("/signup")
    public RedirectView attemptSignUpUser(@RequestParam String username ,
                                   @RequestParam String password , @RequestParam String firstname, @RequestParam String lastname
            , @RequestParam String email
            , @RequestParam Date dateofbirth
            , @RequestParam String profession) throws ParseException {

        ApplicationUser newUser = new ApplicationUser(username , encoder.encode(password) , firstname , lastname , email , dateofbirth , profession , "Add your bio Here");
        applicationUserRepository.save(newUser);
        Authentication auth = new UsernamePasswordAuthenticationToken(newUser , null , new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(auth);
        activeUserStore.users.add(newUser.getUsername());
        return new RedirectView("/");
    }

}
