package com.example.jpacrud.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.example.jpacrud.entity.Product;
import com.example.jpacrud.entity.User;
import com.example.jpacrud.service.IProductService;
import com.example.jpacrud.service.impl.ProductServiceImpl;

@WebServlet(urlPatterns = {"/home", "/Home"})
public class HomeController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private IProductService productService = new ProductServiceImpl();
    private static final int LATEST_PRODUCTS_LIMIT = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            req.setAttribute("user", user);
        }

        List<Product> latestProducts = productService.findLatest(LATEST_PRODUCTS_LIMIT);
        req.setAttribute("latestProducts", latestProducts);

        req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
    }
}