package com.example.jpacrud.test;

import com.example.jpacrud.config.JpaConfig;
import com.example.jpacrud.entity.Category;
import com.example.jpacrud.entity.Video;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class TestJPA {

    public static void main(String[] args) {
        EntityManager em = JpaConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

           
            Category cate = new Category();
            cate.setCategoryname("Điện thoại");
            cate.setImages("dienthoai.jpg");
            cate.setStatus(1);

            
            Video video = new Video();
            video.setVideoId("v001");
            video.setTitle("Review iPhone 16");
            video.setDescription("Đánh giá chi tiết");
            video.setActive(true);
            video.setViews(1000);
            video.setCategory(cate); 

            
            em.persist(cate);
            em.persist(video);

            tx.commit();
            System.out.println("✅ Thêm thành công!");

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}