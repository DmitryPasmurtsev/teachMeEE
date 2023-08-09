package com.example.teachmeee.servlets;

import com.example.teachmeee.CheckAuth;
import com.example.teachmeee.DAO.ProductDAO;
import com.example.teachmeee.DTO.ProductDTO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CreationServlet", value = "/CreationServlet")
public class CreationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CheckAuth.checkAuth(request, response);
        ProductDTO product = new ProductDTO(null, (request.getParameter("name")), Integer.parseInt(request.getParameter("amount")), Double.parseDouble(request.getParameter("price")), Integer.parseInt(request.getParameter("user_id")));
        ProductDAO.addProduct(product);
        response.sendRedirect("/products");
    }
}
