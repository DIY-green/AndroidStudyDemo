package com.cheng.zenofdesignpatterns.principle.ocp;

/**
 * 小说类
 */
public class NovelBook implements IBook {

    // 书籍名称
    private String name;
    // 书籍的价格
    private int price;
    // 书籍的作者
    private String author;
    // 通过构造函数传递书籍数据
    public NovelBook(String _name, int _price, String _author) {
        this.name = _name;
        this.price = _price;
        this.author = _author;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public String getAuthor() {
        return this.author;
    }
}
