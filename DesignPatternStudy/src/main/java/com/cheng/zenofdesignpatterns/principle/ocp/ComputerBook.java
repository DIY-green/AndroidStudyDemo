package com.cheng.zenofdesignpatterns.principle.ocp;

/**
 * 计算机书籍实现类
 */
public class ComputerBook implements IComputerBook {

    private String name;
    private String scope;
    private String author;
    private int price;

    public ComputerBook(String _name, int _price, String _author, String _scope) {
        this.name = _name;
        this.price = _price;
        this.author = _author;
        this.scope = _scope;
    }

    @Override
    public String getScope() {
        return scope;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getAuthor() {
        return author;
    }
}
