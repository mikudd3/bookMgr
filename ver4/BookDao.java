package com.lanqiao.javalearn.bookmanger.ver4;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * project : 图书管理系统
 *
 * @author mikudd3
 * @version: 4.0
 */
public class BookDao {
    private Scanner input = new Scanner(System.in);
    private ArrayList<Book> bList = new ArrayList<>();

    public BookDao() {
        //File file = new File("src/com/lanqiao/javalearn/bookmanger/ver4/book.txt"); //序列化文件
//        File file = new File("src/com/lanqiao/javalearn/bookmanger/ver4/book1.txt");       //字节流
//        File file = new File("src/com/lanqiao/javalearn/bookmanger/ver4/book2.txt");       //字符流
//        File file = new File("src/com/lanqiao/javalearn/bookmanger/ver4/book3.txt");       //字节缓冲流
//        File file = new File("src/com/lanqiao/javalearn/bookmanger/ver4/book4.txt");       //字符缓冲流
        File file = new File("src/com/lanqiao/javalearn/bookmanger/ver4/book5.txt");       //数据流
        if (file.exists()) {
            //将文件中的数据放入集合
            OutputFile();
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

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
        //每加入一本书，则将书放入文件中
        inputFile();
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
            //将修改后的数据放入文件
            inputFile();
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
        //将删除后的数据重新放入文件中
        inputFile();
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
        //将修改后的数据写入文件
        inputFile();
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
        //将修改后的数据写入文件
        inputFile();
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

    /**
     * 将集合数据放入文件,序列化
     */
    private void inputFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/com/lanqiao/javalearn/bookmanger/ver4/book.txt"))) {
            //将集合中的数据直接放入文件中
            oos.writeObject(bList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 字节流
     */
    /*private void inputFile() {
        for (Book book : bList) {
            // 转为字符串
            String s = "" + book.getId() + "," + book.getName() + "," + book.getAuthor() + ","
                    + book.getDate() + "," + book.getPress() + "," + book.getPrice() + ","
                    + book.getState() + "," + book.getComment();
            try (OutputStream fis = new FileOutputStream("src/com/lanqiao/javalearn/bookmanger/ver4/book1.txt")) {
                fis.write(s.getBytes());
                fis.write("\n".getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }*/

    /**
     * 字符流
     */
    /*private void inputFile() {
        for (Book book : bList) {
            // 转为字符串
            String s = "" + book.getId() + "," + book.getName() + "," + book.getAuthor() + ","
                    + book.getDate() + "," + book.getPress() + "," + book.getPrice() + ","
                    + book.getState() + "," + book.getComment();
            try (FileWriter fw = new FileWriter("src/com/lanqiao/javalearn/bookmanger/ver4/book2.txt")) {
                fw.write(s);
                fw.write("\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }*/

    /**
     * 字节缓冲流
     */
    /*private void inputFile() {
        for (Book book : bList) {
            // 转为字符串
            String s = "" + book.getId() + "," + book.getName() + "," + book.getAuthor() + ","
                    + book.getDate() + "," + book.getPress() + "," + book.getPrice() + ","
                    + book.getState() + "," + book.getComment();
            try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("src/com/lanqiao/javalearn/bookmanger/ver4/book3.txt"))) {
                bos.write(s.getBytes());
                bos.write("\n".getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }*/

    /**
     * 字符缓冲流
     */
    /*private void inputFile() {
        for (Book book : bList) {
            // 转为字符串
            String s = "" + book.getId() + "," + book.getName() + "," + book.getAuthor() + ","
                    + book.getDate() + "," + book.getPress() + "," + book.getPrice() + ","
                    + book.getState() + "," + book.getComment();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/com/lanqiao/javalearn/bookmanger/ver4/book4.txt"))) {
                bw.write(s);
                bw.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }*/

    /**
     * 数据流
     */
    /*private void inputFile() {
        // 转为字符串
        try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("src/com/lanqiao/javalearn/bookmanger/ver4/book5.txt")))) {
            for (Book book : bList) {
                dos.writeUTF(book.getId());
                dos.writeUTF(book.getName());
                dos.writeUTF(book.getAuthor());
                dos.writeUTF(book.getDate());
                dos.writeUTF(book.getPress());
                dos.writeInt(book.getPrice());
                dos.writeInt(book.getState());
                dos.writeUTF(book.getComment() + "");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


    /**
     * 从文件中读取数据并放入集合
     */
    public void OutputFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/com/lanqiao/javalearn/bookmanger/ver4/book.txt"))) {
            //将文件中的数据放入集合
            bList = (ArrayList<Book>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 字节流
     */
    /*public void OutputFile() {
        try (FileInputStream fis = new FileInputStream("src/com/lanqiao/javalearn/bookmanger/ver4/book1.txt")) {
            //字节数组
            byte[] bytes = new byte[1024];
            //读取数据
            int len;
            while ((len = fis.read(bytes)) != -1) {
                String[] strs = new String(bytes, 0, len).split(",");
//                System.out.println(strs);
                //创建对象
                bList.add(new Book(strs[0], strs[1], strs[2], strs[3], strs[4],
                        Integer.parseInt(strs[5]), Integer.parseInt(strs[6]), strs[7]));

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

    /**
     * 字符流
     */
    /*public void OutputFile() {
        try (FileReader fr = new FileReader("src/com/lanqiao/javalearn/bookmanger/ver4/book2.txt")) {
            //字节数组
            char[] chars = new char[1024];
            //读取数据
            int len;
            while ((len = fr.read(chars)) != -1) {
                String[] strs = new String(chars, 0, len).split(",");
//                System.out.println(strs);
                //创建对象
                bList.add(new Book(strs[0], strs[1], strs[2], strs[3], strs[4],
                        Integer.parseInt(strs[5]), Integer.parseInt(strs[6]), strs[7]));

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

    /**
     * 字节缓冲流
     */
    /*public void OutputFile() {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("src/com/lanqiao/javalearn/bookmanger/ver4/book3.txt"))) {
            //字节数组
            byte[] bytes = new byte[1024];
            //读取数据
            int len;
            while ((len = bis.read(bytes)) != -1) {
                String[] strs = new String(bytes, 0, len).split(",");
//                System.out.println(strs);
                //创建对象
                bList.add(new Book(strs[0], strs[1], strs[2], strs[3], strs[4],
                        Integer.parseInt(strs[5]), Integer.parseInt(strs[6]), strs[7]));

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

    /**
     * 字符缓冲流
     */
    /*public void OutputFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/com/lanqiao/javalearn/bookmanger/ver4/book4.txt"))) {
            //字节数组
            char[] chars = new char[1024];
            //读取数据
            int len;
            while ((len = br.read(chars)) != -1) {
                String[] strs = new String(chars, 0, len).split(",");
//                System.out.println(strs);
                //创建对象
                bList.add(new Book(strs[0], strs[1], strs[2], strs[3], strs[4],
                        Integer.parseInt(strs[5]), Integer.parseInt(strs[6]), strs[7]));

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

    /**
     * 数据流
     */
    /*public void OutputFile() {
        try (DataInputStream br = new DataInputStream(new FileInputStream("src/com/lanqiao/javalearn/bookmanger/ver4/book5.txt"))) {
            while (true) {
                bList.add(new Book(br.readUTF(), br.readUTF(), br.readUTF(), br.readUTF(),
                        br.readUTF(), br.readInt(), br.readInt(), br.readUTF()));
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }*/
}

