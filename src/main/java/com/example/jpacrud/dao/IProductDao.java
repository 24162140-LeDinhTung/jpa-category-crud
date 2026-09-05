package com.example.jpacrud.dao;

import com.example.jpacrud.entity.Product;
import java.util.List;

public interface IProductDao {
    void insert(Product product);
    void update(Product product);
    void delete(int id) throws Exception;
    Product findById(int id);
    List<Product> findAll();
    List<Product> findLatest(int limit);
    List<Product> findByCategory(int categoryId);
    List<Product> findPaginated(int page, int pageSize);
    int count();
    List<Product> search(String keyword);
}