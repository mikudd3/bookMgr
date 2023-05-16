package com.lanqiao.javalearn.bookmanger.ver1;

import java.util.Scanner;

public class BookMgr {
    static Scanner input = new Scanner(System.in);
    static int[] id = new int[10];              //书籍编号
    static String[] name = new String[10];      //书名
    static String[] author = new String[10];    //作者
    static String[] date = new String[10];      //出版时间
    static String[] press = new String[10];     //出版社
    static int[] price = new int[10];          //价格
    static int[] state = new int[10];           //书籍状态 0表示不可以借 1表示可以借
    static int count = 0;

    /**
     * mikudd3图书管理系统菜单
     */
    public static void showMenu() {
        System.out.println("--------mikudd3 -图书管理系统---------");
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
    public static void inputBook() {
        //添加书籍
        System.out.print("请输入书籍编号:");
        id[count] = input.nextInt();
        System.out.print("请输入书名:");
        name[count] = input.next();
        System.out.print("请输入作者:");
        author[count] = input.next();
        System.out.print("请输入出版时间:");
        date[count] = input.next();
        System.out.print("请输入出版社:");
        press[count] = input.next();
        System.out.print("请输入价格:");
        price[count] = input.nextInt();
        //初始状态为可以借1
        state[count] = 1;
        count++;

    }

    /**
     * 更新图书功能
     */
    public static void updateBook() {
        //修改书籍信息
        System.out.println("请输入你要修改的书名:");
        String name1 = input.next();
        int index = getIndex(name1);
        if (index < 0) {
            System.out.println("查无此书");

        } else {
            miniMenu();
            int select = input.nextInt();
            switch (select) {
                case 1 -> {
                    System.out.println("请输入修改后的书籍名：");
                    String bName = input.next();
                    name[index] = bName;
                }
                case 2 -> {
                    System.out.println("请输入修改后的作者：");
                    String bAuthor = input.next();
                    author[index] = bAuthor;
                }
                case 3 -> {
                    System.out.println("请输入修改后出版时间：");
                    String bDate = input.next();
                    date[index] = bDate;
                }
                case 4 -> {
                    System.out.println("请输入要修改后出版社：");
                    String bPess = input.next();
                    press[index] = bPess;
                }
                case 5 -> {
                    System.out.println("请输入修改后的价格：");
                    int bPrice = input.nextInt();
                    price[index] = bPrice;
                }
                case 6 -> {
                    System.out.println("请输入修改后的状态(0已借出，1在管)：");
                    int bState = input.nextInt();
                    state[index] = bState;
                }
            }
        }

    }

    /**
     * 更新图书小菜单
     */
    private static void miniMenu() {
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
    public static void deleteBook() {
        //删除书籍
        System.out.println("请输入你要删除的书名");
        String name1 = input.next();
        int index = getIndex(name1);
        if (index == count - 1) {
            id[count - 1] = 0;
            name[count - 1] = null;
            author[count - 1] = null;
            date[count - 1] = null;
            press[count - 1] = null;
            price[count - 1] = 0;
            state[count - 1] = 0;
        } else {
            id[index] = id[count - 1];
            name[index] = name[count - 1];
            author[index] = author[count - 1];
            date[index] = date[count - 1];
            press[index] = press[count - 1];
            price[index] = price[count - 1];
            state[index] = state[count - 1];
        }
        count--;

    }

    /**
     * 借书功能
     */
    public static void borrowBook() {
        //借书
        System.out.println("请输入图书ID");
        int ID = input.nextInt();
        int index = getIndex(ID);
        if (index < 0) {
            System.out.println("查无此书");
        } else {
            state[index] = 0;
        }
    }

    /**
     * 还书功能
     */
    public static void returnBook() {
        //还书
        System.out.println("请输入图书ID");
        int ID = input.nextInt();
        int index = getIndex(ID);
        if (index < 0) {
            System.out.println("查无此书");
        } else {
            state[index] = 1;
        }
    }

    /**
     * 查看指定书功能
     */
    public static void viewBook() {
        //查看指定书籍
        System.out.println("请输入你要查看的书名");
        String name1 = input.next();
        int index = getIndex(name1);
        if (index < 0) {
            System.out.println("查无此书");
        } else {
            showState(index);
        }
    }

    /**
     * 输出书籍的状态
     */
    private static void showState(int index) {

        System.out.printf("书籍编号:" + id[index] + "\t书名:" + name[index] + "\t作者:"
                + author[index] + "\t出版时间:" + date[index] + "\t出版社:" + press[index]
                + "\t价格:" + price[index] + "\t状态：" + (state[index] == 1 ? "可借" : "不可借") + "%n");


    }

    /**
     * 查看所有书
     */
    public static void seeAll() {
        //查看所有书籍
        for (int i = 0; i < count; i++) {
            showState(i);
        }


    }

    /**
     * 根据姓名查看书籍下标
     */
    private static int getIndex(String name1) {
        for (int i = 0; i < count; i++) {
            if (name1.equals(name[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据编号查看书籍的下标
     */
    private static int getIndex(int ID) {
        for (int i = 0; i < count; i++) {
            if (id[i] == ID) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        while (true) {
            showMenu();
            System.out.println("请选择:");
            int choose = input.nextInt();
            switch (choose) {
                //添加书籍
                case 1 -> inputBook();
                //修改图书信息
                case 2 -> updateBook();
                //图书下架
                case 3 -> deleteBook();
                //借书
                case 4 -> borrowBook();
                //还书
                case 5 -> returnBook();
                //查看指定图书信息
                case 6 -> viewBook();
                //查看所有图书
                case 7 -> seeAll();
                //退出系统
                case 0 -> System.exit(0);
                default -> System.out.println("输入有误，请重新输入");
            }
        }
    }


}

