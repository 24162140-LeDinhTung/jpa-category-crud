package com.example.jpacrud.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/image")
public class ImageController extends HttpServlet {
    private static final String UPLOAD_DIR = "C:/uploads";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String fname = req.getParameter("fname");
        if (fname == null || fname.isEmpty()) {
            resp.sendError(404);
            return;
        }
        File file = new File(UPLOAD_DIR + File.separator + fname);
        if (!file.exists()) {
            resp.sendError(404);
            return;
        }
        resp.setContentType(getServletContext().getMimeType(fname));
        resp.setContentLengthLong(file.length());
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.transferTo(resp.getOutputStream());
        }
    }
}