<%@ page import="com.example.teachmeee.DTO.ProductDTO" %>
<%@ page import="com.example.teachmeee.DAO.ProductDAO" %><%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 08.08.2023
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Товары</title>
</head>
<body>
<h1>Редактирование товара</h1>
<%
    ProductDTO product = ProductDAO.getProduct(Integer.parseInt(request.getParameter("id")));
%>
<form action="/EditingServlet" method="post">
    <input hidden="hidden" name="id" value=<%=product.getId()%>>
    <b>Название: </b><input name="name" value=<%=product.getName()%>><br><br>
    <b>Стоимость: </b><input name="price" value=<%=product.getPrice()%>><br><br>
    <b>Количество: </b><input name="amount" value=<%=product.getAmount()%>><br><br>
    <button>Сохранить</button>
</form>
</body>
</html>
