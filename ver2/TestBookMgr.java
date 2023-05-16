package com.lanqiao.javalearn.bookmanger.ver2;

import java.util.Scanner;

/**
 * @project: 图书管理系统测试
 * @author: mikudd3
 * @version: 2.0
 */
public class TestBookMgr {
    public static void main(String[] args) {
        var input = new Scanner(System.in);
        var bm = new BookMgr();
        while (true) {
            bm.showMenu();
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
}
