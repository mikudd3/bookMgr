package com.lanqiao.javalearn.bookmanger.ver6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/books?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
    private static final String USER = "root";
    private static final String PWD = "123456";

    static {
        //1. 加载驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        //2. 获取连接
        try {
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
