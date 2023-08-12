package com.example.teachmeee.DAO;

import com.example.teachmeee.DB.ConnectorDB;
import com.example.teachmeee.DTO.AuthDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDAO {

    private static AuthDAO INSTANCE = null;
    private ConnectorDB connectorDB;

    private AuthDAO(ConnectorDB connectorDB) {
        this.connectorDB = connectorDB;
    }
     public static AuthDAO getInstance(ConnectorDB connectorDB) {
        if(INSTANCE==null) INSTANCE = new AuthDAO(connectorDB);
        return INSTANCE;
     }
    public void addInfo(AuthDTO user) {
        try {
            PreparedStatement statement = connectorDB.getConnection().prepareStatement("INSERT INTO users (login, password) Values (?, ?)");
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPass());
            statement.executeUpdate();
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
    }

    public AuthDTO getInfo(AuthDTO user) {
        try {
            PreparedStatement statement = connectorDB.getConnection().prepareStatement("SELECT id FROM users WHERE login = ? AND password = ?");
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPass());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                return user;
            }
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public String getLoginById(Integer id) {
        try {
            PreparedStatement statement = connectorDB.getConnection().prepareStatement("SELECT login FROM users WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
