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



    public static List<ProductDTO> getProducts() {
        List<ProductDTO> products = new ArrayList<>();
        try {
            ResultSet resultSet =ConnectorDB.CONNECTION.createStatement().executeQuery("SELECT * FROM products");
            while(resultSet.next()) {
                ProductDTO product = new ProductDTO();
                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setAmount(resultSet.getInt(3));
                product.setPrice(resultSet.getDouble(4));
                products.add(product);
            }
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static ProductDTO getProduct(Integer id) {
        ProductDTO product = new ProductDTO();
        try {
            PreparedStatement statement = ConnectorDB.CONNECTION.prepareStatement("SELECT * FROM products WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setAmount(resultSet.getInt(3));
                product.setPrice(resultSet.getDouble(4));
            }
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public static void updateProduct(ProductDTO productDTO, Integer id) {
        try {
            PreparedStatement statement = ConnectorDB.CONNECTION.prepareStatement("UPDATE products SET name=?,amount=?,price=? WHERE id=?");
            statement.setString(1, productDTO.getName());
            statement.setInt(2, productDTO.getAmount());
            statement.setDouble(3, productDTO.getPrice());
            statement.setInt(4, id);
            statement.executeUpdate();
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addProduct(ProductDTO productDTO) {
        try {
            PreparedStatement statement = ConnectorDB.CONNECTION.prepareStatement("INSERT INTO products (name, amount, price) Values (?, ?, ?)");
            statement.setString(1, productDTO.getName());
            statement.setInt(2, productDTO.getAmount());
            statement.setDouble(3, productDTO.getPrice());
            statement.executeUpdate();
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProduct(Integer id) {
        try {
            PreparedStatement statement = ConnectorDB.CONNECTION.prepareStatement("DELETE FROM products WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
    }
}
