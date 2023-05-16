package com.lanqiao.javalearn.bookmanger.ver2;

import java.util.Scanner;

/**
 * project : 图书管理系统
 *
 * @author mikudd3
 * @version: 2.0
 */
public class BookMgr {
    private Scanner input = new Scanner(System.in);
    private Book[] books;   //书数组
    private int size;   //数组元素个数
    private Book book;
    public BookMgr() {
        //开辟空间,初始化
        this.books = new Book[10];
        this.size = 0;
    }

    /**
     * mikudd3图书管理系统菜单
     */
    public void showMenu() {
        System.out.println("--------mikudd3 图书管理系统---------");
        System.out.println("1.图书上架");
        System.out.println("2.图书信息修改");
        System.out.println("3.图书下架");
        System.out.println("4.借书");
        System.out.println("5.还书");
        System.out.println("6.图书信息查看");
        System.out.println("7.查看所有图书");
        System.out.println("0.退出");
        System.out.println("----------------------------");
    }

    /**
     * 添加图书功能
     */
    public void inputBook() {
        //当空间已满
        if (size == books.length) {
            resize(books.length * 2);
        }
        //创建书对象
        book = new Book();
        String id;
        //如果id已存在
        do {
            System.out.print("请输入书籍编号:");
            id = input.next();
            if (isExit(id)) {
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
        //把书放入数组
        books[size] = book;
        System.out.println("添加成功");
        //输出所添加的书
        System.out.println(book);
        size++;
    }

    /**
     * 判断编号是否已经存在
     *
     * @param id
     * @return
     */
    private boolean isExit(String id) {
        for (int i = 0; i < size; i++) {
            if (books[i].getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 给数组扩容
     *
     * @param newSize
     */
    private void resize(int newSize) {
        //临时数组
        Book[] temp = books;
        books = new Book[newSize];
        if (size >= 0) {
            System.arraycopy(temp, 0, books, 0, size);
        }
    }

    /**
     * 更新图书功能
     */
    public void updateBook() {
        //修改书籍信息
        System.out.println("请输入你要修改的书名:");
        String name = input.next();
        int index = getIndex(name);

        if (-1 == index) {
            System.out.println("查无此书");
        } else {
            miniMenu();
            book = books[index];
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
        String name1 = input.next();
        int index = getIndex(name1);
        if (-1 == index) {
            System.out.println("删除失败，没有找到该书");
        } else {
            //打印要删除的书
            System.out.println(books[index]);
            books[index] = books[size - 1];
        }

        size--;
        System.out.println("删除成功");

        if (size > 0 && size == books.length / 4) {
            resize(books.length / 2);
        }
    }

    /**
     * 借书功能
     */
    public void borrowBook() {
        System.out.println("请输入你要借阅的书名");
        String name1 = input.next();
        int index = getIndex(name1);
        if (-1 == index) {
            System.out.println("查无此书");
        } else {
            book = books[index];
            if (1 == book.getState()) {
                //展示要借的书
                System.out.println(book);
                book.setState(0);
                System.out.println("请输入借阅人姓名：");
                book.setComment(input.next());
                System.out.println("借阅成功");

            } else {
                System.out.println(books[index]);
                System.out.println("当前书不可借阅");
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
        int index = getIndex(name);
        if (-1 == index) {
            System.out.println("查无此书");
        } else {
            //展示要还的书
            book = books[index];
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
        int index = getIndex(name);
        if (-1 == index) {
            System.out.println("查无此书");
        } else {
            showBook(index);
        }
    }

    /**
     * 输出书籍的信息
     */
    private void showBook(int index) {
        System.out.println(books[index]);
    }

    /**
     * 查看所有书
     */
    public void seeAll() {
        //查看所有书籍
        for (int i = 0; i < size; i++) {
            showBook(i);
        }
    }

    /**
     * 根据姓名查看书籍下标
     */
    private int getIndex(String name1) {
        for (int i = 0; i < size; i++) {
            if (name1.equals(books[i].getName())) {
                return i;
            }
        }
        return -1;
    }
}

