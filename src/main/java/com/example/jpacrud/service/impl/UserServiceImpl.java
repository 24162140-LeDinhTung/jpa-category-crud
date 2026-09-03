package com.example.jpacrud.service.impl;

import com.example.jpacrud.dao.IUserDao;
import com.example.jpacrud.dao.impl.UserDaoImpl;
import com.example.jpacrud.entity.User;
import com.example.jpacrud.service.IUserService;
import com.example.jpacrud.util.Constants;

import java.io.File;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements IUserService {

    private IUserDao userDao = new UserDaoImpl();

    // ===== CÁC PHƯƠNG THỨC ĐÃ CÓ =====
    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(int id) {
        try {
            User user = userDao.findById(id);
            if (user != null && user.getAvatar() != null) {
                File file = new File(Constants.UPLOAD_DIR + File.separator + user.getAvatar());
                if (file.exists()) {
                    file.delete();
                }
            }
            userDao.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean updateProfile(int userId, String fullName, String phone, String avatar) {
        try {
            User user = userDao.findById(userId);
            if (user != null) {
                user.setFullName(fullName);
                user.setPhone(phone);
                if (avatar != null && !avatar.isEmpty()) {
                    String oldAvatar = user.getAvatar();
                    if (oldAvatar != null && !oldAvatar.isEmpty()) {
                        File oldFile = new File(Constants.UPLOAD_DIR + File.separator + oldAvatar);
                        if (oldFile.exists()) {
                            oldFile.delete();
                        }
                    }
                    user.setAvatar(avatar);
                }
                userDao.update(user);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ===== THÊM MỚI CHO LOGIN/REGISTER =====
    @Override
    public User login(String username, String password) {
        User user = this.findByUsername(username);
        if (user != null && password.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public boolean register(String username, String password, String email, String fullname, String phone) {
        if (userDao.findByUsername(username) != null) {
            return false; // Username đã tồn tại
        }
        
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setFullName(fullname);
        user.setPhone(phone);
        user.setRoleId(3); // Role mặc định: User
        user.setCreatedDate(new Date());
        
        userDao.insert(user);
        return true;
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userDao.findByEmail(email) != null;
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDao.findByUsername(username) != null;
    }

    @Override
    public boolean checkExistPhone(String phone) {
        // Có thể thêm phương thức findByPhone nếu cần
        return false;
    }
}