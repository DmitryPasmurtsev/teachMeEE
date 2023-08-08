<%@ page import="com.example.teachmeee.DAO.ProductDAO" %>
<%@ page import="com.example.teachmeee.DTO.ProductDTO" %>
<%@ page import="java.util.List" %><%--
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
    ProductDAO productDAO = new ProductDAO();
    List<ProductDTO> products = productDAO.getProducts();
    for(ProductDTO product: products) {
        out.print("<b>Название: </b>" + product.getName() + "<br>");
        out.print("<b>Стоимость: </b>" + product.getPrice() + "<br>");
        out.print("<b>Количество на складе: </b>" + product.getAmount() + "<br>");
        %>
<form action="product-editing.jsp">
    <input hidden="hidden" name="id" value="<%=product.getId()%>">
    <button>Редактировать</button>
</form>
<form action="/teachMeEE_war_exploded/DeleteServlet" method="post">
    <input hidden="hidden" name="id" value="<%=product.getId()%>">
    <button>Удалить</button>
</form><hr><br>
<%
    }
%>
<h3>Добавление товара</h3>
<form action="/teachMeEE_war_exploded/CreationServlet" method="post">
    <b>Название: </b><input name="name" type="text"><br><br>
    <b>Стоимость: </b><input name="price" type="number" step="0.01"><br><br>
    <b>Количество: </b><input name="amount" type="number" step="1"><br><br>
    <button>Добавить</button>
</form><hr><br>
</body>
</html>
