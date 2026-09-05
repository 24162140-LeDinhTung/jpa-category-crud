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
    User login(String username, String password);
    boolean register(String username, String password, String email, String fullname, String phone);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);

    boolean registerWithOTP(String username, String password, String email, String fullname, String phone);
    boolean activateAccount(String email, String otp);
    boolean sendOTP(String email);
    boolean verifyOTP(String email, String otp);
    boolean resetPassword(String email, String newPassword);
}