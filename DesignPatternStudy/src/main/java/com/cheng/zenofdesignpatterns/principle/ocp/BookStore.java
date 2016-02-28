package com.cheng.zenofdesignpatterns.principle.ocp;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * 书店售书类
 */
public class BookStore {
    private final static ArrayList<IBook> bookList1 = new ArrayList<>();
    private final static ArrayList<IBook> bookList2 = new ArrayList<>();
    private final static ArrayList<IBook> bookList3 = new ArrayList<>();
    // static静态模块初始化数据，实际项目中一般由持久层完成
    static {
        bookList1.add(new NovelBook("天龙八部", 3200, "金庸"));
        bookList1.add(new NovelBook("巴黎圣母院", 5600, "雨果"));
        bookList1.add(new NovelBook("悲惨世界", 3500, "雨果"));
        bookList1.add(new NovelBook("金瓶梅", 4300, "兰陵笑笑生"));

        bookList2.add(new OffNovelBook("天龙八部", 3200, "金庸"));
        bookList2.add(new OffNovelBook("巴黎圣母院", 5600, "雨果"));
        bookList2.add(new OffNovelBook("悲惨世界", 3500, "雨果"));
        bookList2.add(new OffNovelBook("金瓶梅", 4300, "兰陵笑笑生"));

        bookList3.add(new ComputerBook("Think in Java", 4300, "Bruce Eckel", "编程"));
    }

    // 模拟书店买书
    public static void sell() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        formatter.setMaximumFractionDigits(2);
        System.out.println("----------书店卖出去的书籍数据如下：----------");
        for (IBook book : bookList1) {
            System.out.println("书籍名称：" + book.getName() +
            "\t书籍作者：" + book.getAuthor() +
            "\t书籍价格：" + formatter.format(book.getPrice()/100.0) + "元");
        }
        System.out.println();
        System.out.println();
        System.out.println("----------书店卖出去的书籍数据如下：----------");
        for (IBook book : bookList2) {
            System.out.println("书籍名称：" + book.getName() +
            "\t书籍作者：" + book.getAuthor() +
            "\t书籍价格：" + formatter.format(book.getPrice()/100.0) + "元");
        }
        System.out.println();
        System.out.println();
        System.out.println("----------书店卖出去的书籍数据如下：----------");
        for (IBook book : bookList3) {
            System.out.println("书籍名称：" + book.getName() +
            "\t书籍作者：" + book.getAuthor() +
            "\t书籍价格：" + formatter.format(book.getPrice()/100.0) + "元");
        }
    }
}
