package com.lanqiao.javalearn.bookmanger.ver3;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * project : 图书管理系统
 *
 * @author mikudd3
 * @version: 3.0
 */
public class BookDao {
    private Scanner input = new Scanner(System.in);
    private ArrayList<Book> bList = new ArrayList<>();

    /**
     * 添加图书功能
     */
    public void inputBook() {
        //创建书对象
        Book book = new Book();
        String id;
        //如果id已存在
        do {
            System.out.print("请输入书籍编号:");
            id = input.next();
            if (isExist(id)) {
                System.out.println("该编号已经存在，请重新输入");
            } else {
                break;
            }
        } while (true);
        book.setId(id);
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
        //把书放入集合
        bList.add(book);
        System.out.println("添加成功");
        //输出所添加的书
        System.out.println(book);
    }

    /**
     * 判断编号是否已经存在
     *
     * @param id
     * @return
     */
    private boolean isExist(String id) {
        for (Book book : bList) {
            if (book.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 更新图书功能
     */
    public void updateBook() {
        //修改书籍信息
        System.out.println("请输入你要修改的书名:");
        String name = input.next();
        Book book = getBook(name);
        //判断编号是否已经存在
        if (book == null) {
            System.out.println("查无此书");
        } else {
            miniMenu();
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
            //输出修改后
            System.out.println(book);
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
        System.out.println("请输入你要删除的书名");
        String name = input.next();
        Book book = getBook(name);
        if (book == null) {
            System.out.println("删除失败，没有找到该书");
        } else {
            //输出要删除的书
            System.out.println(book);
            //删除书本
            bList.remove(book);
            System.out.println("删除成功");
        }
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
            if (1 == book.getState()) {
                //展示要借的书
                System.out.println(book);
                //借书之后修改书状态
                book.setState(0);
                System.out.println("请输入借阅人姓名：");
                book.setComment(input.next());
                System.out.println("借阅成功");

            } else {
                System.out.println("当前书不可借阅");
                System.out.println(book);
            }
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
            //展示要还的书
            System.out.println(book);
            book.setState(1);
            book.setComment(null);
            System.out.println("归还成功");
        }
    }

    /**
     * 查看指定书功能
     */
    public void viewBook() {
        //查看指定书籍
        System.out.println("请输入你要查看的书名");
        String name = input.next();
        Book book = getBook(name);
        if (book == null) {
            System.out.println("查无此书");
        } else {
            System.out.println(book);
        }
    }

    /**
     * 查看所有书
     */
    public void seeAll() {
        //查看所有书籍
        //方法引用
        bList.forEach(System.out::println);
    }

    /**
     * 根据姓名返回书对象
     */
    private Book getBook(String name) {
        for (Book book : bList) {
            if (book.getName().equals(name)) {
                return book;
            }
        }
        return null;
    }
}

