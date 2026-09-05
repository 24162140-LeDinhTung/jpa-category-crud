package com.example.jpacrud.service.impl;

import com.example.jpacrud.dao.IProductDao;
import com.example.jpacrud.dao.impl.ProductDaoImpl;
import com.example.jpacrud.entity.Product;
import com.example.jpacrud.service.IProductService;

import java.util.List;

public class ProductServiceImpl implements IProductService {

    private IProductDao productDao = new ProductDaoImpl();

    @Override
    public void insert(Product product) {
        productDao.insert(product);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public void delete(int id) {
        try {
            productDao.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product findById(int id) {
        return productDao.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public List<Product> findLatest(int limit) {
        return productDao.findLatest(limit);
    }

    @Override
    public List<Product> findByCategory(int categoryId) {
        return productDao.findByCategory(categoryId);
    }

    @Override
    public List<Product> findPaginated(int page, int pageSize) {
        return productDao.findPaginated(page, pageSize);
    }

    @Override
    public int count() {
        return productDao.count();
    }

    @Override
    public int getTotalPages(int pageSize) {
        int total = count();
        return (int) Math.ceil((double) total / pageSize);
    }

    @Override
    public List<Product> search(String keyword) {
        return productDao.search(keyword);
    }
}