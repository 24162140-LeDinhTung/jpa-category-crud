package com.example.jpacrud.service;

import com.example.jpacrud.entity.Product;
import java.util.List;

public interface IProductService {
    void insert(Product product);
    void update(Product product);
    void delete(int id);
    Product findById(int id);
    List<Product> findAll();
    List<Product> findLatest(int limit);
    List<Product> findByCategory(int categoryId);
    List<Product> findPaginated(int page, int pageSize);
    int count();
    int getTotalPages(int pageSize);
    List<Product> search(String keyword);
}