package com.example.teachmeee.servlets;

import com.example.teachmeee.DAO.AuthDAO;
import com.example.teachmeee.DB.ConnectorDB;
import com.example.teachmeee.DTO.AuthDTO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AuthServlet", value = "/AuthServlet")
public class AuthServlet extends HttpServlet {
    AuthDAO authDAO = AuthDAO.getInstance(ConnectorDB.INSTANCE);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("user_id", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        response.sendRedirect("/login");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthDTO user = new AuthDTO(null, request.getParameter("login"), request.getParameter("password"));
        if(authDAO.getInfo(user).getId()==null) response.sendRedirect("/login");
        else {
            Cookie cookie = new Cookie("user_id", String.valueOf(user.getId()));
            cookie.setMaxAge(-1);
            response.addCookie(cookie);
            response.sendRedirect("/products");
        }
    }
}
