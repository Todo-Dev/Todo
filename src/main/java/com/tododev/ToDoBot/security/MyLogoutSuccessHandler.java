package com.tododev.ToDoBot.security;

import com.tododev.ToDoBot.repository.ApplicationUserRepository;
import com.tododev.ToDoBot.service.ActiveUserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component("myLogoutSuccessHandler")
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    ActiveUserStore activeUserStore;
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        System.out.println("hello");
        HttpSession session = request.getSession();
        if (session != null){
            activeUserStore.getUsers().remove(applicationUserRepository.findApplicationUserByUsername(authentication.getName()).getUsername());
            session.removeAttribute("myLoggedUser");
        }
        response.sendRedirect("/");
    }
}
