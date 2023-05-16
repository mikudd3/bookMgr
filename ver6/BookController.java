package com.lanqiao.javalearn.bookmanger.ver6;

import java.util.Scanner;

/**
 * @project: 图书管理系统测试
 * @author: mikudd3
 * @version: 5.0
 */
public class BookController {
    public void service() {
        Scanner input = new Scanner(System.in);
        BookService bm = new BookService();
        while (true) {
            showMenu();
            System.out.println("请选择:");
            int choose = input.nextInt();
            switch (choose) {
                //添加书籍
                case 1 -> bm.inputBook();
                //修改图书信息
                case 2 -> bm.updateBook();
                //图书下架
                case 3 -> bm.deleteBook();
                //借书
                case 4 -> bm.borrowBook();
                //还书
                case 5 -> bm.returnBook();
                //查看指定图书信息
                case 6 -> bm.viewBook();
                //查看所有图书
                case 7 -> bm.seeAll();
                //退出系统
                case 0 -> System.exit(0);
                default -> System.out.println("输入有误，请重新输入");
            }
        }
    }

    /**
     * mikudd3图书管理系统菜单
     */
    private void showMenu() {
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


}
