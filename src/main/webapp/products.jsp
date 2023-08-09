<%@ page import="com.example.teachmeee.DAO.ProductDAO" %>
<%@ page import="com.example.teachmeee.DTO.ProductDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.teachmeee.CheckAuth" %>
<%@ page import="com.example.teachmeee.DAO.AuthDAO" %><%--
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
<h1>Товары на складе</h1><br>
<%
    Integer userId = CheckAuth.checkAuth(request, response);
%>
<%
    List<ProductDTO> products = ProductDAO.getProducts();
    for(ProductDTO product: products) {
        out.print("<b>Название: </b>" + product.getName() + "<br>");
        out.print("<b>Стоимость: </b>" + product.getPrice() + "<br>");
        out.print("<b>Количество на складе: </b>" + product.getAmount() + "<br>");
        out.print("<b>Продавец: </b>" + AuthDAO.getLoginById(product.getUser_id())+ "<br>");
%>
<%
    if(userId==product.getUser_id()) {
%>
<form action="product-editing.jsp">
    <input hidden="hidden" name="id" value="<%=product.getId()%>">
    <button>Редактировать</button>
</form>
<form action="/DeleteServlet" method="post">
    <input hidden="hidden" name="id" value="<%=product.getId()%>">
    <input name="user_id" hidden="hidden" value=<%=product.getUser_id()%>>
    <button>Удалить</button>
</form>
<%      }
    out.print("<hr><br>");
    }
%>
<h3>Добавление товара</h3>
<form action="/CreationServlet" method="post">
    <b>Название: </b><input name="name" type="text"><br><br>
    <b>Стоимость: </b><input name="price" type="number" step="0.01"><br><br>
    <b>Количество: </b><input name="amount" type="number" step="1"><br><br>
    <input name="user_id" hidden="hidden" value=<%=userId%>>
    <button>Добавить</button>
</form><hr><br>
</body>
</html>
