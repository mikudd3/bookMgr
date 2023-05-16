package com.lanqiao.javalearn.bookmanger.ver6;

import java.sql.*;
import java.util.ArrayList;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
public class BookDAO {
    public boolean addBook(Book book) {
        boolean flag = false;
        try (Connection conn = JDBCUtil.getConnection();) {
            String sql = "insert into book(name,author,date,press,price) values(?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, book.getName());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getDate());
            ps.setString(4, book.getPress());
            ps.setInt(5, book.getPrice());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flag;
    }

    public boolean deleteById(Integer id) {
        boolean ret = false;
        try (Connection conn = JDBCUtil.getConnection();) {
            String sql = "delete from book where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public boolean updateState(Book book) {
        boolean ret = false;
        try (Connection conn = JDBCUtil.getConnection();) {
            String sql = "update book set state = ?,comment = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, book.getState());
            ps.setString(2, book.getComment());
            ps.setInt(3, book.getId());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public boolean updateAll(Book book) {
        boolean ret = false;
        try (Connection conn = JDBCUtil.getConnection();) {
            String sql = "update book set name = ?,author = ?,date = ?,press = ?,price = ?,state= ?,comment = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, book.getName());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getDate());
            ps.setString(4, book.getPress());
            ps.setInt(5, book.getPrice());
            ps.setInt(6, book.getState());
            ps.setString(7, book.getComment());
            ps.setInt(8, book.getId());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public Book selectByName(String name) {
        Book book = null;
        try (Connection conn = JDBCUtil.getConnection();) {

            String sql = "select * from book where name = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Integer bid = rs.getInt("id");
                String bName = rs.getString("name");
                String bAuthor = rs.getString("author");
                String bDate = rs.getString("date");
                String bPress = rs.getString("press");
                Integer bPrice = rs.getInt("price");
                Integer bState = rs.getInt("state");
                String bComment = rs.getString("comment");

                book = new Book(bid, bName, bAuthor, bDate, bPress, bPrice, bState, bComment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }


    public ArrayList<Book> listAll() {
        ArrayList<Book> list = new ArrayList();
        try (Connection conn = JDBCUtil.getConnection();) {
            Statement stmt = conn.createStatement();
            String sql = "select * from book";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Integer bid = rs.getInt("id");
                String bName = rs.getString("name");
                String bAuthor = rs.getString("author");
                String bDate = rs.getString("date");
                String bPress = rs.getString("press");
                Integer bPrice = rs.getInt("price");
                Integer bState = rs.getInt("state");
                String bComment = rs.getString("comment");

                Book book = new Book(bid, bName, bAuthor, bDate, bPress, bPrice, bState, bComment);
                list.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
