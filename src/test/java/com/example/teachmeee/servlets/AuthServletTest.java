package com.example.teachmeee.servlets;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServletTest {
    private AuthServlet servlet = new AuthServlet();
    private  HttpServletRequest request = spy(HttpServletRequest.class);
    private HttpServletResponse response = spy(HttpServletResponse.class);

    private Cookie cookie = mock(Cookie.class);



    @Test
    void exitMethodTest() throws ServletException, IOException {
        servlet.doGet(request, response);
        verify(response).addCookie(any());
        verify(response).sendRedirect("/login");
    }

    @Test
    void authMethodTest() {

    }
}