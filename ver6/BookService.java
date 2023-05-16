package com.lanqiao.javalearn.bookmanger.ver6;

import java.util.Scanner;

/**
 * project : 图书管理系统
 *
 * @author mikudd3
 * @version: 5.0
 */
public class BookService {
    private Scanner input = new Scanner(System.in);

    private BookDAO bookDAO = new BookDAO();

    /**
     * 添加图书功能
     */
    public void inputBook() {
        //创建书对象
        Book book = new Book();
        book.setId(null);
        System.out.print("请输入书名:");
        book.setName(input.next());
        System.out.print("请输入作者:");
        book.setAuthor(input.next());
        System.out.print("请输入出版时间:");
        book.setDate(input.next());
        System.out.print("请输入出版社:");
        book.setPress(input.next());
        System.out.print("请输入价格:");
        book.setPrice(input.nextInt());
        book.setState(1);
        book.setComment(null);
        System.out.println(bookDAO.addBook(book) ? "添加成功" : "添加失败");
    }

    /**
     * 更新图书功能
     */
    public void updateBook() {
        //修改书籍信息
        System.out.println("请输入你要修改的书名:");
        String name = input.next();
        Book book = getBook(name);
        miniMenu();
        if (book == null) {
            System.out.println("查无此书");
        } else {
            int select = input.nextInt();
            //使用无直通增强switch
            switch (select) {
                case 1 -> {
                    System.out.println("请输入修改后的书籍名：");
                    book.setName(input.next());
                }
                case 2 -> {
                    System.out.println("请输入修改后的作者：");
                    book.setAuthor(input.next());
                }
                case 3 -> {
                    System.out.println("请输入修改后出版时间：");
                    book.setDate(input.next());
                }
                case 4 -> {
                    System.out.println("请输入要修改后出版社：");
                    book.setPress(input.next());
                }
                case 5 -> {
                    System.out.println("请输入修改后的价格：");
                    book.setPrice(input.nextInt());
                }
                case 6 -> {
                    System.out.println("请输入修改后的状态(0已借出，1在管)：");
                    book.setState(input.nextInt());
                }
            }
            System.out.println(bookDAO.updateAll(book) ? "更新成功" : "更新失败");

        }

    }

    /**
     * 更新图书小菜单
     */
    private void miniMenu() {
        System.out.println("请输入要修改的位置：");
        System.out.println("1.书籍名");
        System.out.println("2.作者");
        System.out.println("3.出版时间");
        System.out.println("4.出版社");
        System.out.println("5.价格");
        System.out.println("6.书籍状态");
    }

    /**
     * 删除图书功能
     */
    public void deleteBook() {
        //删除书籍
        System.out.println("请输入你要删除书的id");
        Integer id = input.nextInt();
        System.out.println(bookDAO.deleteById(id) ? "删除成功" : "删除失败");
    }

    /**
     * 借书功能
     */
    public void borrowBook() {
        System.out.println("请输入你要借阅的书名");
        String name = input.next();
        Book book = getBook(name);
        if (book == null) {
            System.out.println("查无此书");
        } else {
            book.setState(0);
            System.out.println("请输入借阅人姓名：");
            book.setComment(input.next());
            System.out.println(bookDAO.updateState(book) ? "借书成功" : "借书失败");
        }
    }

    /**
     * 还书功能
     */
    public void returnBook() {
        //还书
        System.out.println("请输入你要归还的书名");
        String name = input.next();
        Book book = getBook(name);
        if (book == null) {
            System.out.println("查无此书");
        } else {
            book.setState(1);
            book.setComment(null);
            System.out.println(bookDAO.updateState(book) ? "还书成功" : "还书失败");
        }
    }

    /**
     * 查看指定书功能
     */
    public void viewBook() {
        //查看指定书籍
        System.out.println("请输入你要查看的书名");
        String name = input.next();
        System.out.println(bookDAO.selectByName(name));
    }

    /**
     * 查看所有书
     */
    public void seeAll() {
        //查看所有书籍
        bookDAO.listAll().forEach(System.out::println);
    }

    /**
     * 根据姓名返回书对象
     */
    private Book getBook(String name) {
        for (Book book : bookDAO.listAll()) {
            if (book.getName().equals(name)) {
                return book;
            }
        }
        return null;
    }
}

