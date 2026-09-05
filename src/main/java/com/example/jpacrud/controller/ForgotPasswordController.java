package com.example.jpacrud.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.jpacrud.service.IUserService;
import com.example.jpacrud.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/forgot-password", "/reset-password"})
public class ForgotPasswordController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String path = req.getRequestURI();

        if (path.contains("/forgot-password")) {
            req.getRequestDispatcher("/WEB-INF/views/forgot-password.jsp").forward(req, resp);
        } else if (path.contains("/reset-password")) {
            String email = req.getParameter("email");
            req.setAttribute("email", email);
            req.getRequestDispatcher("/WEB-INF/views/reset-password.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String email = req.getParameter("email");
        String otp = req.getParameter("otp");
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");

        if (email != null && !email.isEmpty() && otp == null) {
            boolean sent = userService.sendOTP(email);
            if (sent) {
                req.setAttribute("success", "OTP da duoc gui den email cua ban");
                req.setAttribute("email", email);
                req.getRequestDispatcher("/WEB-INF/views/reset-password.jsp").forward(req, resp);
            } else {
                req.setAttribute("alert", "Email khong ton tai trong he thong");
                req.getRequestDispatcher("/WEB-INF/views/forgot-password.jsp").forward(req, resp);
            }
            return;
        }

        if (newPassword != null && !newPassword.isEmpty()) {
            if (!newPassword.equals(confirmPassword)) {
                req.setAttribute("alert", "Mat khau xac nhan khong khop");
                req.setAttribute("email", email);
                req.getRequestDispatcher("/WEB-INF/views/reset-password.jsp").forward(req, resp);
                return;
            }

            boolean verified = userService.verifyOTP(email, otp);
            if (!verified) {
                req.setAttribute("alert", "OTP khong dung hoac da het han");
                req.setAttribute("email", email);
                req.getRequestDispatcher("/WEB-INF/views/reset-password.jsp").forward(req, resp);
                return;
            }

            boolean reset = userService.resetPassword(email, newPassword);
            if (reset) {
                req.setAttribute("success", "Dat lai mat khau thanh cong. Vui long dang nhap.");
                req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            } else {
                req.setAttribute("alert", "Dat lai mat khau that bai");
                req.getRequestDispatcher("/WEB-INF/views/reset-password.jsp").forward(req, resp);
            }
        }
    }
}