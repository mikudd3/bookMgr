package com.lanqiao.javalearn.bookmanger.ver5;

/**
 * @project: 图书类
 * @author: mikudd3
 * @version: 5.0
 */
public class Book {
    private String id;             //书籍编号
    private String name;        //书名
    private String author;      //作者
    private String date;        //出版时间
    private String press;       //出版社
    private int price;          //价格
    private int state;          //书籍状态 0表示不可以借 1表示可以借
    private String comment;     //备注


    public Book() {
    }

    public Book(String id, String name, String author, String date, String press, int price, int state, String comment) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.date = date;
        this.press = press;
        this.price = price;
        this.state = state;
        this.comment = comment;
    }

    /**
     * 获取
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * 设置
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 获取
     * @return press
     */
    public String getPress() {
        return press;
    }

    /**
     * 设置
     * @param press
     */
    public void setPress(String press) {
        this.press = press;
    }

    /**
     * 获取
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     * 设置
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * 获取
     * @return state
     */
    public int getState() {
        return state;
    }

    /**
     * 设置
     * @param state
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * 获取
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    public String toString() {
        return "Book{id = " + id + ", name = " + name + ", author = " + author + ", date = " + date + ", press = " + press + ", price = " + price + ", state = " + state + ", comment = " + comment + "}";
    }
}
