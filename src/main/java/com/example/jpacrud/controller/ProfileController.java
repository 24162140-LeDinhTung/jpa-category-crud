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

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("account") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        User currentUser = (User) session.getAttribute("account");

        String fullName = req.getParameter("fullname");
        String phone = req.getParameter("phone");

       
        String avatar = null;
        Part filePart = req.getPart("avatar");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String ext = "";
            int dotIndex = fileName.lastIndexOf(".");
            if (dotIndex > 0) {
                ext = fileName.substring(dotIndex);
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

       
        boolean success = userService.updateProfile(
            currentUser.getId(),
            fullName,
            phone,
            avatar
        );

        if (success) {
       
            User updatedUser = userService.findById(currentUser.getId());
            session.setAttribute("account", updatedUser);
            req.setAttribute("message", "Cập nhật thành công!");
            req.setAttribute("alertType", "success");
        } else {
            req.setAttribute("message", "Cập nhật thất bại!");
            req.setAttribute("alertType", "danger");
        }

        req.setAttribute("user", userService.findById(currentUser.getId()));
        req.getRequestDispatcher("/WEB-INF/views/user/profile.jsp").forward(req, resp);
    }
}