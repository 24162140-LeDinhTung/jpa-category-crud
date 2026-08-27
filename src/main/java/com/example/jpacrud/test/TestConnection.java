package com.example.jpacrud.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/JPACRUD?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String user = "root";
        String password = "Dinhtung110@";  

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Kết nối MySQL thành công!");
            conn.close();
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Không tìm thấy driver MySQL!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("❌ Lỗi kết nối MySQL!");
            e.printStackTrace();
        }
    }
}