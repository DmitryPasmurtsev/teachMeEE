package com.example.teachmeee.DAO;

import com.example.teachmeee.DB.ConnectorDB;
import com.example.teachmeee.DTO.AuthDTO;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthDAOTest {
    static AuthDTO authDTO;

    static ConnectorDB connectorDB;
    static AuthDAO authDAO;
    static Connection connection;
    static PreparedStatement preparedStatement;
    static ResultSet resultSet;

    @BeforeAll
    static void init() throws SQLException {
        authDTO = new AuthDTO(0, "smth", "smth");
        connectorDB = mock(ConnectorDB.class);
        authDAO = AuthDAO.getInstance(connectorDB);
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);
        when(connectorDB.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
    }



    @Test
    void addInfo() throws SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(1);
        authDAO.addInfo(authDTO);
        assertEquals(1, preparedStatement.executeUpdate());
    }

    @Test
    void getInfo() throws SQLException {
        when(resultSet.getInt(1)).thenReturn(1);
        authDTO = authDAO.getInfo(authDTO);
        assertEquals(1, authDTO.getId());
    }

    @Test
    void getLoginById() throws SQLException {
        when(resultSet.getString(1)).thenReturn("login");
        assertEquals("login", authDAO.getLoginById(authDTO.getId()));
    }
}