package com.example.jpacrud.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import com.example.jpacrud.entity.User;
import com.example.jpacrud.service.IUserService;
import com.example.jpacrud.service.impl.UserServiceImpl;
import com.example.jpacrud.util.Constants;

@WebServlet(urlPatterns = {"/profile", "/profile/update"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,
    maxFileSize = 1024 * 1024 * 10,
    maxRequestSize = 1024 * 1024 * 50
)
public class ProfileController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("account") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        User user = (User) session.getAttribute("account");
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/views/user/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("account") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        User currentUser = (User) session.getAttribute("account");

        String fullName = req.getParameter("fullname");
        String phone = req.getParameter("phone");

        // ===== SERVER-SIDE VALIDATION =====
        // Validate fullname
        if (fullName == null || fullName.trim().isEmpty()) {
            req.setAttribute("message", "Ho va ten khong duoc de trong");
            req.setAttribute("alertType", "danger");
            req.setAttribute("user", userService.findById(currentUser.getId()));
            req.getRequestDispatcher("/WEB-INF/views/user/profile.jsp").forward(req, resp);
            return;
        }

        // Validate phone (optional)
        if (phone != null && !phone.trim().isEmpty()) {
            if (!phone.matches("^[0-9]{10,11}$")) {
                req.setAttribute("message", "So dien thoai phai co 10-11 chu so");
                req.setAttribute("alertType", "danger");
                req.setAttribute("user", userService.findById(currentUser.getId()));
                req.getRequestDispatcher("/WEB-INF/views/user/profile.jsp").forward(req, resp);
                return;
            }
        }

        // Xử lý upload ảnh đại diện
        String avatar = null;
        try {
            Part filePart = req.getPart("avatar");
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String ext = "";
                int dotIndex = fileName.lastIndexOf(".");
                if (dotIndex > 0) {
                    ext = fileName.substring(dotIndex);
                }
                
                // ===== VALIDATE FILE TYPE =====
                String contentType = filePart.getContentType();
                if (!contentType.startsWith("image/")) {
                    req.setAttribute("message", "Vui long chon file anh");
                    req.setAttribute("alertType", "danger");
                    req.setAttribute("user", userService.findById(currentUser.getId()));
                    req.getRequestDispatcher("/WEB-INF/views/user/profile.jsp").forward(req, resp);
                    return;
                }
                
                String newFileName = "user_" + currentUser.getId() + "_" + System.currentTimeMillis() + ext;

                File uploadDir = new File(Constants.UPLOAD_DIR + File.separator + "avatar");
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                String filePath = uploadDir.getAbsolutePath() + File.separator + newFileName;
                filePart.write(filePath);
                avatar = "avatar/" + newFileName;
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", "Loi khi upload file: " + e.getMessage());
            req.setAttribute("alertType", "danger");
            req.setAttribute("user", userService.findById(currentUser.getId()));
            req.getRequestDispatcher("/WEB-INF/views/user/profile.jsp").forward(req, resp);
            return;
        }

        // Cập nhật thông tin User
        boolean success = userService.updateProfile(
            currentUser.getId(),
            fullName.trim(),
            phone != null ? phone.trim() : null,
            avatar
        );

        if (success) {
            User updatedUser = userService.findById(currentUser.getId());
            session.setAttribute("account", updatedUser);
            req.setAttribute("message", "Cap nhat thanh cong!");
            req.setAttribute("alertType", "success");
        } else {
            req.setAttribute("message", "Cap nhat that bai!");
            req.setAttribute("alertType", "danger");
        }

        req.setAttribute("user", userService.findById(currentUser.getId()));
        req.getRequestDispatcher("/WEB-INF/views/user/profile.jsp").forward(req, resp);
    }
}