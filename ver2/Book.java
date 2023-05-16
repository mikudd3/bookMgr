package com.lanqiao.javalearn.bookmanger.ver2;

/**
 * @project: 图书类
 * @author: mikudd3
 * @version: 2.0
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

    public Book() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "图书基本信息{" +
                "书籍编号：" + id +
                ", 书名：" + name + '\'' +
                ", 图书作者：" + author + '\'' +
                ", 出版时间：" + date + '\'' +
                ", 出版社：" + press + '\'' +
                ", 价格：" + price +
                ", 书籍状态：" + (state == 1 ? "可借" : "不可借") +
                ", 书籍备注：" + comment +
                '}';
    }
}
