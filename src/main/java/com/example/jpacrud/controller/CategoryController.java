package com.example.jpacrud.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import com.example.jpacrud.entity.Category;
import com.example.jpacrud.service.ICategoryService;
import com.example.jpacrud.service.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = {
        "/admin/categories",
        "/admin/category/add",
        "/admin/category/insert",
        "/admin/category/edit",
        "/admin/category/update",
        "/admin/category/delete"
})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, 
        maxFileSize = 1024 * 1024 * 10,      
        maxRequestSize = 1024 * 1024 * 50    
)
public class CategoryController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ICategoryService categoryService = new CategoryServiceImpl();
    private static final String UPLOAD_DIR = "C:/uploads"; 

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String path = req.getRequestURI();

        if (path.contains("/admin/categories")) {
            
            List<Category> list = categoryService.findAll();
            req.setAttribute("listcate", list);
            req.getRequestDispatcher("/WEB-INF/views/admin/category-list.jsp").forward(req, resp);

        } else if (path.contains("/admin/category/add")) {
           
            req.getRequestDispatcher("/WEB-INF/views/admin/category-add.jsp").forward(req, resp);

        } else if (path.contains("/admin/category/edit")) {
            
            int id = Integer.parseInt(req.getParameter("id"));
            Category category = categoryService.findById(id);
            req.setAttribute("cate", category);
            req.getRequestDispatcher("/WEB-INF/views/admin/category-edit.jsp").forward(req, resp);

        } else if (path.contains("/admin/category/delete")) {
           
            int id = Integer.parseInt(req.getParameter("id"));
            categoryService.delete(id);
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String path = req.getRequestURI();

        if (path.contains("/admin/category/insert")) {
            
            String categoryname = req.getParameter("categoryname");
            int status = Integer.parseInt(req.getParameter("status"));
            String imagesLink = req.getParameter("images");

            Category category = new Category();
            category.setCategoryname(categoryname);
            category.setStatus(status);

            
            String fileName = uploadFile(req);
            if (fileName != null && !fileName.isEmpty()) {
                category.setImages(fileName);
            } else if (imagesLink != null && !imagesLink.isEmpty()) {
                category.setImages(imagesLink);
            } else {
                category.setImages("default.png");
            }

            categoryService.insert(category);
            resp.sendRedirect(req.getContextPath() + "/admin/categories");

        } else if (path.contains("/admin/category/update")) {
            
            int id = Integer.parseInt(req.getParameter("categoryid"));
            String categoryname = req.getParameter("categoryname");
            int status = Integer.parseInt(req.getParameter("status"));
            String imagesLink = req.getParameter("images");

            Category category = categoryService.findById(id);
            if (category != null) {
                category.setCategoryname(categoryname);
                category.setStatus(status);

                
                String fileName = uploadFile(req);
                if (fileName != null && !fileName.isEmpty()) {
                    
                    String oldFile = category.getImages();
                    if (oldFile != null && !oldFile.isEmpty() && !oldFile.startsWith("http")) {
                        File old = new File(UPLOAD_DIR + "/" + oldFile);
                        if (old.exists()) old.delete();
                    }
                    category.setImages(fileName);
                } else if (imagesLink != null && !imagesLink.isEmpty()) {
                    category.setImages(imagesLink);
                }
               

                categoryService.update(category);
            }
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        }
    }

    private String uploadFile(HttpServletRequest req) throws IOException, ServletException {
        String fileName = null;
        Part part = req.getPart("images1");
        if (part != null && part.getSize() > 0) {
            String originalName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            String ext = "";
            int dotIndex = originalName.lastIndexOf(".");
            if (dotIndex > 0) {
                ext = originalName.substring(dotIndex);
            }
            fileName = System.currentTimeMillis() + ext;
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) uploadDir.mkdirs();
            part.write(UPLOAD_DIR + File.separator + fileName);
        }
        return fileName;
    }
}