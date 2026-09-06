package com.example.jpacrud.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.example.jpacrud.service.IUserService;
import com.example.jpacrud.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/register", "/Register"})
public class RegisterController extends HttpServlet {

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
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");
        String confirmPassword = req.getParameter("confirmPassword");

        // ===== SERVER-SIDE VALIDATION =====
        // Validate username
        if (username == null || username.trim().isEmpty()) {
            req.setAttribute("alert", "Ten dang nhap khong duoc de trong");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        }
        if (username.length() < 3 || username.length() > 30) {
            req.setAttribute("alert", "Ten dang nhap phai tu 3-30 ky tu");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        }
        if (!username.matches("^[a-zA-Z0-9_]{3,30}$")) {
            req.setAttribute("alert", "Ten dang nhap chi bao gom chu cai, so, dau gach duoi");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        }

        // Validate fullname
        if (fullname == null || fullname.trim().isEmpty()) {
            req.setAttribute("alert", "Ho va ten khong duoc de trong");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        }

        // Validate email
        if (email == null || email.trim().isEmpty()) {
            req.setAttribute("alert", "Email khong duoc de trong");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        }
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            req.setAttribute("alert", "Email khong hop le");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        }

        // Validate phone (optional)
        if (phone != null && !phone.trim().isEmpty()) {
            if (!phone.matches("^[0-9]{10,11}$")) {
                req.setAttribute("alert", "So dien thoai phai co 10-11 chu so");
                req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
                return;
            }
        }

        // Validate password
        if (password == null || password.trim().isEmpty()) {
            req.setAttribute("alert", "Mat khau khong duoc de trong");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        }
        if (password.length() < 6) {
            req.setAttribute("alert", "Mat khau phai co it nhat 6 ky tu");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        }

        // Validate confirm password
        if (!password.equals(confirmPassword)) {
            req.setAttribute("alert", "Mat khau xac nhan khong khop");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        }

        // Check existing username
        if (userService.checkExistUsername(username.trim())) {
            req.setAttribute("alert", "Ten dang nhap da ton tai");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        }

        // Check existing email
        if (userService.checkExistEmail(email.trim())) {
            req.setAttribute("alert", "Email da duoc su dung");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        }

        boolean success = userService.registerWithOTP(username.trim(), password, email.trim(), fullname.trim(), phone);

        if (success) {
            req.setAttribute("success", "Dang ky thanh cong. Ma OTP da duoc gui den email cua ban.");
            req.setAttribute("email", email);
            req.getRequestDispatcher("/WEB-INF/views/verify-otp.jsp").forward(req, resp);
        } else {
            req.setAttribute("alert", "Dang ky that bai. Vui long thu lai.");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
        }
    }
}