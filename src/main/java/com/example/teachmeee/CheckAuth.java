package com.example.teachmeee;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CheckAuth {
    public static Integer checkAuth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer userId = null;
        Cookie[] cookies = request.getCookies();
        if(cookies.length==0) response.sendRedirect("/login");
        for (Cookie cookie: cookies) {
            if(cookie.getName().equals("user_id")) userId=Integer.parseInt(cookie.getValue());
        }
        if(userId==null) response.sendRedirect("/login");
        return userId;
    }
}
