package com.example.teachmeee.servlets;

import com.example.teachmeee.CheckAuth;
import com.example.teachmeee.DAO.ProductDAO;
import com.example.teachmeee.DB.ConnectorDB;
import com.example.teachmeee.DTO.ProductDTO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeleteServlet", value = "/DeleteServlet")
public class DeleteServlet extends HttpServlet {

    ProductDAO productDAO = ProductDAO.getInstance(ConnectorDB.INSTANCE);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(CheckAuth.checkAuth(request, response)==Integer.parseInt(request.getParameter("user_id")))
            productDAO.deleteProduct(Integer.parseInt(request.getParameter("id")));
        response.sendRedirect("/products");
    }
}
