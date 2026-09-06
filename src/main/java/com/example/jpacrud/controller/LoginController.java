package com.example.jpacrud.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.example.jpacrud.entity.User;
import com.example.jpacrud.service.IUserService;
import com.example.jpacrud.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/login", "/Login"})
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        // ===== SERVER-SIDE VALIDATION =====
        // Validate username
        if (username == null || username.trim().isEmpty()) {
            req.setAttribute("alert", "Ten dang nhap khong duoc de trong");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            return;
        }
        
        // Validate password
        if (password == null || password.trim().isEmpty()) {
            req.setAttribute("alert", "Mat khau khong duoc de trong");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            return;
        }
        
        // Validate username length
        if (username.length() < 3 || username.length() > 30) {
            req.setAttribute("alert", "Ten dang nhap phai tu 3-30 ky tu");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            return;
        }
        
        // Validate password length
        if (password.length() < 6) {
            req.setAttribute("alert", "Mat khau phai co it nhat 6 ky tu");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            return;
        }

        User user = userService.findByUsername(username.trim());

        if (user == null) {
            req.setAttribute("alert", "Tai khoan khong ton tai");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            return;
        }

        if (user.getStatus() == 0) {
            req.setAttribute("alert", "Tai khoan chua duoc kich hoat. Vui long kiem tra email.");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            return;
        }

        if (!password.equals(user.getPassword())) {
            req.setAttribute("alert", "Mat khau khong dung");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("account", user);
        session.setMaxInactiveInterval(30 * 60);

        if (user.getRoleId() == 1) {
            resp.sendRedirect(req.getContextPath() + "/admin/home");
        } else {
            resp.sendRedirect(req.getContextPath() + "/home");
        }
    }
}