package com.example.jpacrud.dao.impl;

import com.example.jpacrud.config.JpaConfig;
import com.example.jpacrud.dao.IProductDao;
import com.example.jpacrud.entity.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ProductDaoImpl implements IProductDao {

    @Override
    public void insert(Product product) {
        EntityManager em = JpaConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(product);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Product product) {
        EntityManager em = JpaConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(product);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        EntityManager em = JpaConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Product product = em.find(Product.class, id);
            if (product != null) {
                em.remove(product);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Product findById(int id) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            return em.find(Product.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Product> findAll() {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            TypedQuery<Product> query = em.createNamedQuery("Product.findAll", Product.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Product> findLatest(int limit) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            TypedQuery<Product> query = em.createNamedQuery("Product.findLatest", Product.class);
            query.setMaxResults(limit);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Product> findByCategory(int categoryId) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            String jpql = "SELECT p FROM Product p WHERE p.category.id = :categoryId ORDER BY p.id DESC";
            TypedQuery<Product> query = em.createQuery(jpql, Product.class);
            query.setParameter("categoryId", categoryId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Product> findPaginated(int page, int pageSize) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            TypedQuery<Product> query = em.createNamedQuery("Product.findAll", Product.class);
            query.setFirstResult(page * pageSize);
            query.setMaxResults(pageSize);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public int count() {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            String jpql = "SELECT COUNT(p) FROM Product p";
            Query query = em.createQuery(jpql);
            return ((Long) query.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Product> search(String keyword) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            String jpql = "SELECT p FROM Product p WHERE p.name LIKE :keyword OR p.description LIKE :keyword ORDER BY p.id DESC";
            TypedQuery<Product> query = em.createQuery(jpql, Product.class);
            query.setParameter("keyword", "%" + keyword + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}