package com.example.teachmeee.servlets;

import com.example.teachmeee.DAO.ProductDAO;
import com.example.teachmeee.DTO.ProductDTO;
import com.example.teachmeee.Mapper.ProductMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "EditingServlet", value = "/EditingServlet")
public class EditingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDTO product = ProductMapper.toProductDTO((request.getParameter("name")), Integer.parseInt(request.getParameter("amount")), Double.parseDouble(request.getParameter("price")));
        ProductDAO.updateProduct(product, Integer.parseInt(request.getParameter("id")));
        response.sendRedirect("/teachMeEE_war_exploded/products");
    }
}