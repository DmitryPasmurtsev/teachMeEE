package com.example.teachmeee.servlets;

import com.example.teachmeee.DAO.AuthDAO;
import com.example.teachmeee.DB.ConnectorDB;
import com.example.teachmeee.DTO.AuthDTO;
import com.example.teachmeee.DTO.ProductDTO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "RegistrationServlet", value = "/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

    AuthDAO authDAO = AuthDAO.getInstance(ConnectorDB.INSTANCE);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthDTO user = new AuthDTO(null, request.getParameter("login"), request.getParameter("password"));
        authDAO.addInfo(user);
        response.sendRedirect("/login");
    }
}
