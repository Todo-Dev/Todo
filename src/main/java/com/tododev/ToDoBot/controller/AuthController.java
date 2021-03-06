package com.tododev.ToDoBot.controller;

import com.tododev.ToDoBot.model.ApplicationUser;
import com.tododev.ToDoBot.repository.ApplicationUserRepository;
import com.tododev.ToDoBot.service.ActiveUserStore;
import com.tododev.ToDoBot.service.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.text.ParseException;
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
            , @RequestParam String profession ,
                                          @RequestParam("image")MultipartFile multipartFile
                                          ) throws IOException {

        ApplicationUser newUser = new ApplicationUser(username , encoder.encode(password) , firstname , lastname , email , dateofbirth , profession , "Add your bio Here");
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        newUser.setPhotos(fileName);
        applicationUserRepository.save(newUser);
        String uploadDir = "src/main/resources/static/img/" + newUser.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        Authentication auth = new UsernamePasswordAuthenticationToken(newUser , null , new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(auth);
        activeUserStore.users.add(newUser.getUsername());
        return new RedirectView("/");
    }

    @GetMapping("/online")
    public String getOnline(Model m) {
        m.addAttribute("users" , activeUserStore.getUsers());
        return "online";
    }

}
