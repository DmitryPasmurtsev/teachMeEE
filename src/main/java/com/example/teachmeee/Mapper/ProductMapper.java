package com.example.teachmeee.Mapper;

import com.example.teachmeee.DTO.ProductDTO;

public class ProductMapper {
    public static ProductDTO toProductDTO(String name, Integer amount, Double price) {
        ProductDTO product = new ProductDTO();
        product.setName(name);
        product.setAmount(amount);
        product.setPrice(price);
        return product;
    }
}
