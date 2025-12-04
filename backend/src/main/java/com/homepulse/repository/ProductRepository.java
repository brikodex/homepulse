package com.homepulse.repository;

import com.homepulse.model.Product;

import java.util.List;



public interface ProductRepository {
    Product findById(Long id);
    List<Product> findAll();
    void save(Product product);
    void deleteById(Long id);
    List<Product> findByCategoryId(Long categoryId);
    List<Product> search(String keyword);
}
