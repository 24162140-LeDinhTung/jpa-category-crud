package com.example.jpacrud.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.jpacrud.service.IUserService;
import com.example.jpacrud.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/verify-otp", "/resend-otp"})
public class VerifyOTPController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String path = req.getRequestURI();

        if (path.contains("/verify-otp")) {
            String email = req.getParameter("email");
            req.setAttribute("email", email);
            req.getRequestDispatcher("/WEB-INF/views/verify-otp.jsp").forward(req, resp);

        } else if (path.contains("/resend-otp")) {
            String email = req.getParameter("email");
            boolean success = userService.sendOTP(email);
            if (success) {
                req.setAttribute("success", "OTP moi da duoc gui den email cua ban");
            } else {
                req.setAttribute("alert", "Khong the gui OTP. Email khong ton tai hoac da kich hoat.");
            }
            req.setAttribute("email", email);
            req.getRequestDispatcher("/WEB-INF/views/verify-otp.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String email = req.getParameter("email");
        String otp = req.getParameter("otp");

        boolean activated = userService.activateAccount(email, otp);

        if (activated) {
            req.setAttribute("success", "Kich hoat tai khoan thanh cong. Vui long dang nhap.");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("alert", "OTP khong dung hoac da het han. Vui long thu lai.");
            req.setAttribute("email", email);
            req.getRequestDispatcher("/WEB-INF/views/verify-otp.jsp").forward(req, resp);
        }
    }
}