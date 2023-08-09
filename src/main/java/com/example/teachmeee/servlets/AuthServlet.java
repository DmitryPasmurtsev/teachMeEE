package com.example.teachmeee.servlets;

import com.example.teachmeee.DAO.AuthDAO;
import com.example.teachmeee.DTO.AuthDTO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AuthServlet", value = "/AuthServlet")
public class AuthServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthDTO user = new AuthDTO(null, request.getParameter("login"), request.getParameter("password"));
        if(AuthDAO.getInfo(user).getId()==null) response.sendRedirect("/login");
        Cookie cookie = new Cookie("user_id", String.valueOf(user.getId()));
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
        response.sendRedirect("/products");
    }
}
