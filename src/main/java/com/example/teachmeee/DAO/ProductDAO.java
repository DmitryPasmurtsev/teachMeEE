package com.example.teachmeee.DAO;

import com.example.teachmeee.DB.ConnectorDB;
import com.example.teachmeee.DTO.ProductDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private final ConnectorDB connectorDB;

    private static ProductDAO INSTANCE = null;

    private ProductDAO(ConnectorDB connectorDB) {
        this.connectorDB = connectorDB;
    }

    public static ProductDAO getInstance(ConnectorDB connectorDB) {
        if(INSTANCE==null) INSTANCE=new ProductDAO(connectorDB);
        return INSTANCE;
    }

    public List<ProductDTO> getProducts() {
        List<ProductDTO> products = new ArrayList<>();
        try {
            ResultSet resultSet =connectorDB.getConnection().createStatement().executeQuery("SELECT * FROM products");
            while(resultSet.next()) {
                ProductDTO product = new ProductDTO();
                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setAmount(resultSet.getInt(3));
                product.setPrice(resultSet.getDouble(4));
                product.setUser_id(resultSet.getInt(5));
                products.add(product);
            }
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public ProductDTO getProduct(Integer id) {
        ProductDTO product = new ProductDTO();
        try {
            PreparedStatement statement = connectorDB.getConnection().prepareStatement("SELECT * FROM products WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setAmount(resultSet.getInt(3));
                product.setPrice(resultSet.getDouble(4));
                product.setUser_id(resultSet.getInt(5));
            }
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public void updateProduct(ProductDTO productDTO, Integer id) {
        try {
            PreparedStatement statement = connectorDB.getConnection().prepareStatement("UPDATE products SET name=?,amount=?,price=?,user_id=? WHERE id=?");
            statement.setString(1, productDTO.getName());
            statement.setInt(2, productDTO.getAmount());
            statement.setDouble(3, productDTO.getPrice());
            statement.setInt(4, productDTO.getUser_id());
            statement.setInt(5, id);
            statement.executeUpdate();
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProduct(ProductDTO productDTO) {
        try {
            PreparedStatement statement = connectorDB.getConnection().prepareStatement("INSERT INTO products (name, amount, price, user_id) Values (?, ?, ?, ?)");
            statement.setString(1, productDTO.getName());
            statement.setInt(2, productDTO.getAmount());
            statement.setDouble(3, productDTO.getPrice());
            statement.setInt(4, productDTO.getUser_id());
            statement.executeUpdate();
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(Integer id) {
        try {
            PreparedStatement statement = connectorDB.getConnection().prepareStatement("DELETE FROM products WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
    }
}
