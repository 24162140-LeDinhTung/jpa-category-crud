package com.example.jpacrud.service.impl;

import java.util.List;

import com.example.jpacrud.dao.ICategoryDao;
import com.example.jpacrud.dao.impl.CategoryDao;
import com.example.jpacrud.entity.Category;
import com.example.jpacrud.service.ICategoryService;

public class CategoryServiceImpl implements ICategoryService {

    private ICategoryDao categoryDao = new CategoryDao();

    @Override
    public void insert(Category category) {
        categoryDao.insert(category);
    }

    @Override
    public void update(Category category) {
        categoryDao.update(category);
    }

    @Override
    public void delete(int cateid) {
        try {
            categoryDao.delete(cateid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Category findById(int cateid) {
        return categoryDao.findById(cateid);
    }

    @Override
    public Category findByCategoryname(String name) {
        try {
            return categoryDao.findByCategoryname(name);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public List<Category> searchByName(String catname) {
        return categoryDao.searchByName(catname);
    }

    @Override
    public List<Category> findAll(int page, int pagesize) {
        return categoryDao.findAll(page, pagesize);
    }

    @Override
    public int count() {
        return categoryDao.count();
    }
}