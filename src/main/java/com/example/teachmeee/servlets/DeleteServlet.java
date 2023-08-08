package com.example.teachmeee.servlets;

import com.example.teachmeee.DAO.ProductDAO;
import com.example.teachmeee.DTO.ProductDTO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeleteServlet", value = "/DeleteServlet")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO.deleteProduct(Integer.parseInt(request.getParameter("id")));
        response.sendRedirect("/teachMeEE_war_exploded/products");
    }
}
