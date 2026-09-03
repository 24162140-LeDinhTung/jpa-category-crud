package com.example.jpacrud.service;

import com.example.jpacrud.entity.User;
import java.util.List;

public interface IUserService {
    User findById(int id);
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAll();
    void insert(User user);
    void update(User user);
    void delete(int id);
    boolean updateProfile(int userId, String fullName, String phone, String avatar);
    
    // ===== THÊM MỚI CHO LOGIN/REGISTER =====
    User login(String username, String password);
    boolean register(String username, String password, String email, String fullname, String phone);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
}