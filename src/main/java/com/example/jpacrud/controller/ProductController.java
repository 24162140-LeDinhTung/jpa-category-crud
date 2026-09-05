package com.example.jpacrud.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.jpacrud.entity.Product;
import com.example.jpacrud.service.IProductService;
import com.example.jpacrud.service.impl.ProductServiceImpl;

@WebServlet(urlPatterns = {"/product", "/product/detail"})
public class ProductController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private IProductService productService = new ProductServiceImpl();
    private static final int PAGE_SIZE = 6;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String path = req.getRequestURI();

        if (path.contains("/product/detail")) {
            String id = req.getParameter("id");
            if (id != null) {
                Product product = productService.findById(Integer.parseInt(id));
                req.setAttribute("product", product);
                req.getRequestDispatcher("/WEB-INF/views/product-detail.jsp").forward(req, resp);
            }
            return;
        }

        // Danh sách sản phẩm phân trang
        int page = 0;
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException e) {
            page = 0;
        }

        int total = productService.count();
        int totalPages = (int) Math.ceil((double) total / PAGE_SIZE);

        List<Product> products = productService.findPaginated(page, PAGE_SIZE);
        req.setAttribute("products", products);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);

        req.getRequestDispatcher("/WEB-INF/views/product-list.jsp").forward(req, resp);
    }
}