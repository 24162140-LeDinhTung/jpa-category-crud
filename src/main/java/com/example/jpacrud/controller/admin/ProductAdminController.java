package com.example.jpacrud.controller.admin;

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
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import com.example.jpacrud.entity.Category;
import com.example.jpacrud.entity.Product;
import com.example.jpacrud.entity.User;
import com.example.jpacrud.service.ICategoryService;
import com.example.jpacrud.service.IProductService;
import com.example.jpacrud.service.impl.CategoryServiceImpl;
import com.example.jpacrud.service.impl.ProductServiceImpl;
import com.example.jpacrud.util.Constants;

@WebServlet(urlPatterns = {
    "/admin/product/list",
    "/admin/product/add",
    "/admin/product/insert",
    "/admin/product/edit",
    "/admin/product/update",
    "/admin/product/delete"
})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,
    maxFileSize = 1024 * 1024 * 10,
    maxRequestSize = 1024 * 1024 * 50
)
public class ProductAdminController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private IProductService productService = new ProductServiceImpl();
    private ICategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("account") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        User user = (User) session.getAttribute("account");
        if (user.getRoleId() != 1) {
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }

        String path = req.getRequestURI();

        if (path.contains("/admin/product/list")) {
            List<Product> products = productService.findAll();
            req.setAttribute("products", products);
            req.getRequestDispatcher("/WEB-INF/views/admin/product-list.jsp").forward(req, resp);

        } else if (path.contains("/admin/product/add")) {
            List<Category> categories = categoryService.findAll();
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/WEB-INF/views/admin/product-add.jsp").forward(req, resp);

        } else if (path.contains("/admin/product/edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Product product = productService.findById(id);
            List<Category> categories = categoryService.findAll();
            req.setAttribute("product", product);
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/WEB-INF/views/admin/product-edit.jsp").forward(req, resp);

        } else if (path.contains("/admin/product/delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            productService.delete(id);
            resp.sendRedirect(req.getContextPath() + "/admin/product/list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String path = req.getRequestURI();

        if (path.contains("/admin/product/insert")) {
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            double price = Double.parseDouble(req.getParameter("price"));
            int stock = Integer.parseInt(req.getParameter("stock"));
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));

            Category category = categoryService.findById(categoryId);

            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setStock(stock);
            product.setCategory(category);

            String fileName = uploadFile(req);
            if (fileName != null && !fileName.isEmpty()) {
                product.setImage("products/" + fileName);
            }

            productService.insert(product);
            resp.sendRedirect(req.getContextPath() + "/admin/product/list");

        } else if (path.contains("/admin/product/update")) {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            double price = Double.parseDouble(req.getParameter("price"));
            int stock = Integer.parseInt(req.getParameter("stock"));
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));

            Product product = productService.findById(id);
            if (product != null) {
                product.setName(name);
                product.setDescription(description);
                product.setPrice(price);
                product.setStock(stock);
                product.setCategory(categoryService.findById(categoryId));

                String fileName = uploadFile(req);
                if (fileName != null && !fileName.isEmpty()) {
                    String oldFile = product.getImage();
                    if (oldFile != null && !oldFile.isEmpty()) {
                        File old = new File(Constants.UPLOAD_DIR + File.separator + oldFile);
                        if (old.exists()) old.delete();
                    }
                    product.setImage("products/" + fileName);
                }

                productService.update(product);
            }
            resp.sendRedirect(req.getContextPath() + "/admin/product/list");
        }
    }

    private String uploadFile(HttpServletRequest req) throws IOException, ServletException {
        String fileName = null;
        Part part = req.getPart("image");
        if (part != null && part.getSize() > 0) {
            String originalName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            String ext = "";
            int dotIndex = originalName.lastIndexOf(".");
            if (dotIndex > 0) {
                ext = originalName.substring(dotIndex);
            }
            fileName = System.currentTimeMillis() + ext;
            File uploadDir = new File(Constants.UPLOAD_DIR + File.separator + "products");
            if (!uploadDir.exists()) uploadDir.mkdirs();
            part.write(uploadDir.getAbsolutePath() + File.separator + fileName);
        }
        return fileName;
    }
}