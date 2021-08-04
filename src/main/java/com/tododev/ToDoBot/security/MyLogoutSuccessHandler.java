package com.tododev.ToDoBot.security;

import com.tododev.ToDoBot.service.ActiveUserStore;
import jdk.nashorn.tools.Shell;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        System.out.println("hello");
        HttpSession session = request.getSession();
        if (session != null){
            activeUserStore.users.remove(authentication.getName());
            session.removeAttribute("user");
        }
        response.sendRedirect("/");
    }
}
