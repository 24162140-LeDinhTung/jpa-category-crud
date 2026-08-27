package com.example.jpacrud.dao.impl;

import java.util.List;

import com.example.jpacrud.config.JpaConfig;
import com.example.jpacrud.dao.ICategoryDao;
import com.example.jpacrud.entity.Category;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class CategoryDao implements ICategoryDao {

    @Override
    public void insert(Category category) {
        EntityManager em = JpaConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(category);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Category category) {
        EntityManager em = JpaConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(category);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(int cateid) throws Exception {
        EntityManager em = JpaConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Category category = em.find(Category.class, cateid);
            if (category != null) {
                em.remove(category);
            } else {
                throw new Exception("Không tìm thấy category");
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
    public Category findById(int cateid) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            return em.find(Category.class, cateid);
        } finally {
            em.close();
        }
    }

    @Override
    public Category findByCategoryname(String name) throws Exception {
        EntityManager em = JpaConfig.getEntityManager();
        String jpql = "SELECT c FROM Category c WHERE c.categoryname = :name";
        try {
            TypedQuery<Category> query = em.createQuery(jpql, Category.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Không tìm thấy category với tên: " + name);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Category> findAll() {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            TypedQuery<Category> query = em.createNamedQuery("Category.findAll", Category.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Category> searchByName(String catname) {
        EntityManager em = JpaConfig.getEntityManager();
        String jpql = "SELECT c FROM Category c WHERE c.categoryname LIKE :catname";
        try {
            TypedQuery<Category> query = em.createQuery(jpql, Category.class);
            query.setParameter("catname", "%" + catname + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Category> findAll(int page, int pagesize) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            TypedQuery<Category> query = em.createNamedQuery("Category.findAll", Category.class);
            query.setFirstResult(page * pagesize);
            query.setMaxResults(pagesize);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public int count() {
        EntityManager em = JpaConfig.getEntityManager();
        String jpql = "SELECT COUNT(c) FROM Category c";
        try {
            Query query = em.createQuery(jpql);
            return ((Long) query.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}