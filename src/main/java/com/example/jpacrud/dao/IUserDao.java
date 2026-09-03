package com.example.jpacrud.dao;

import com.example.jpacrud.entity.User;
import java.util.List;

public interface IUserDao {
    User findById(int id);
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAll();
    void insert(User user);
    void update(User user);
    void delete(int id) throws Exception;
}