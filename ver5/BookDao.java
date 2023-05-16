package com.lanqiao.javalearn.bookmanger.ver5;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * project : 图书管理系统
 *
 * @author mikudd3
 * @version: 5.0
 */
public class BookDao {
    private Scanner input = new Scanner(System.in);
    private ArrayList<Book> bList = new ArrayList<>();

    private String filename = "src/com/lanqiao/javalearn/bookmanger/ver5/Books.xml";


    public BookDao() {
        File file = new File(filename);
        if (file.exists()) {
            fileToList();
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
        //写入xml文件
        saveToXmlFile();
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
        saveToXmlFile();
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
        saveToXmlFile();
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
        saveToXmlFile();
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
        saveToXmlFile();
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
     * 将数据存储到xml中
     */
    public void saveToXmlFile() {
        // 创建工厂类对象
        DocumentFactory factory = new DocumentFactory();
        // 创建文档对象
        Document document = factory.createDocument();
        // 创建根元素
        Element root = factory.createElement("books");
        // 将根元素放入到文档对象中
        document.add(root);

        //获取Book数据
        for (Book book : bList) {
            //创建子元素
            Element b = factory.createElement("book");
            //将子元素放入到跟元素中
            root.add(b);
            // 创建属性，并同时设置属性名和属性值
            Attribute bId = factory.createAttribute(b, "id", book.getId());
            // 将属性对象放入子元素中
            b.add(bId);
            //创建子元素对象
            //姓名
            Element bName = factory.createElement("name");
            b.add(bName);
            //创建文本对象
            Text name = factory.createText(book.getName());
            //将文本字符数据放入到子元素中
            bName.add(name);

            //作者
            Element bAuthor = factory.createElement("author");
            b.add(bAuthor);
            //创建文本对象
            Text author = factory.createText(book.getAuthor());
            //将文本字符数据放入到子元素中
            bAuthor.add(author);

            //出版时间
            Element bDate = factory.createElement("date");
            b.add(bDate);
            //创建文本对象
            Text date = factory.createText(book.getDate());
            //将文本字符数据放入到子元素中
            bDate.add(date);

            //出版社
            Element bPress = factory.createElement("press");
            b.add(bPress);
            //创建文本对象
            Text press = factory.createText(book.getPress());
            //将文本字符数据放入到子元素中
            bPress.add(press);

            //价格
            Element bPrice = factory.createElement("price");
            b.add(bPrice);
            Text price = factory.createText(String.valueOf(book.getPrice()));
            bPrice.add(price);

            //价格
            Element bState = factory.createElement("state");
            b.add(bState);
            Text state = factory.createText(String.valueOf(book.getState()));
            bState.add(state);

            //价格
            Element bComment = factory.createElement("comment");
            b.add(bComment);
            Text comment = factory.createText(book.getComment() + "");
            bComment.add(comment);
        }
        // 设置写入xml文档样式
        OutputFormat format = OutputFormat.createPrettyPrint();
        //将文档写入到指定的xml文件中
        XMLWriter writer = null;
        try {
            writer = new XMLWriter(new FileWriter(filename), format);
            writer.write(document);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    // 关闭流
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 采用 Dom4j 框架进行文档解析
     */
    public void fileToList() {
        //创建SAX解析对象
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(filename);
            //获取根元素
            Element books = doc.getRootElement();
            //调用数据获取的方法
            this.getData(books, "book");
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取子元素以及子元素文本数据
     *
     * @param element
     * @param elementName
     */
    public void getData(Element element, String elementName) {
        //获取子元素的列表集合
        List<Element> elementList = element.elements(elementName);
        for (Element e : elementList) {
            //创建书本
            Book book = new Book();
            //获取属性信息
            Attribute bId = e.attribute("id");
            //获取编号
            if (bId != null) {
                book.setId(bId.getValue());
            }
            //获取子元素文本数据
            //获取书名
            Element bName = e.element("name");
            book.setName(bName.getText());

            //获取书作者
            Element bAuthor = e.element("author");
            book.setAuthor(bAuthor.getText());

            //获取书出版时间
            Element bDate = e.element("date");
            book.setDate(bDate.getText());

            //获取书出版社
            Element bPress = e.element("press");
            book.setPress(bPress.getText());

            //获取书价格
            book.setPrice(Integer.parseInt(e.elementText("price")));

            //获取书籍状态 0表示不可以借 1表示可以借
            book.setState(Integer.parseInt(e.elementText("state")));

            //获取书备注
            Element bComment = e.element("comment");
            book.setComment(bComment.getText());

            //将书加入集合
            bList.add(book);
        }
    }
}

