package com.example.teachmeee.DB;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class  ConnectorDB {

    public static final ConnectorDB INSTANCE = new ConnectorDB();
    private ConnectorDB(){}

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/products_jsp_servlet", "root", "1111");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
