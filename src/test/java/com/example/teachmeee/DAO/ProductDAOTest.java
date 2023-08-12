package com.example.teachmeee.DAO;

import com.example.teachmeee.DB.ConnectorDB;
import com.example.teachmeee.DTO.ProductDTO;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductDAOTest {

    static ProductDAO productDAO;
    static ConnectorDB connectorDB;
    static Connection connection;
    static Statement statement;
    static PreparedStatement preparedStatement;
    static ResultSet resultSet;

    @BeforeAll
    static void init() throws SQLException {
        connectorDB = mock(ConnectorDB.class);
        productDAO = new ProductDAO(connectorDB);
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        statement = mock(Statement.class);
        resultSet = spy(ResultSet.class);
        when(connectorDB.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);
        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(statement.executeQuery(any())).thenReturn(resultSet);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }

    @Test
    void testGetInstance() {
        assertNotNull(ProductDAO.getInstance(connectorDB));
    }

    @Test
    void testGetProducts_twoProductsInResultSet() throws SQLException {

        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt(1)).thenReturn(1, 2);
        when(resultSet.getString(2)).thenReturn("Телевизор", "Телефон");
        when(resultSet.getInt(3)).thenReturn(10, 20);
        when(resultSet.getDouble(4)).thenReturn(100.0, 200.0);
        when(resultSet.getInt(5)).thenReturn(3, 4);

        ArrayList<ProductDTO> products = new ArrayList<>();
        products.add(new ProductDTO(1, "Телевизор", 10, 100.0, 3));
        products.add(new ProductDTO(2, "Телефон", 20, 200.0, 4));

        assertEquals(products, productDAO.getProducts());
    }

    @Test
    void testGetProducts_ResultSetIsEmpty() throws SQLException {
        when(resultSet.next()).thenReturn(false);
        assertEquals(0, productDAO.getProducts().size());
    }

    @Test
    void testGetProduct_positive() throws SQLException {
        ProductDTO product = new ProductDTO(1, "Телевизор", 10, 100.0, 3);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt(1)).thenReturn(1);
        when(resultSet.getString(2)).thenReturn("Телевизор");
        when(resultSet.getInt(3)).thenReturn(10);
        when(resultSet.getDouble(4)).thenReturn(100.0);
        when(resultSet.getInt(5)).thenReturn(3);
        assertEquals(product, productDAO.getProduct(1));
    }

    @Test
    void testGetProduct_negative() throws SQLException {
        when(resultSet.next()).thenReturn(false);
        assertNull(productDAO.getProduct(1));
    }

    @Test
    void testUpdateProduct() {
    }

    @Test
    void testAddProduct() {
    }

    @Test
    void testDeleteProduct() {
    }
}