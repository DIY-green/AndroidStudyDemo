package com.cheng.bigtalkdesignpatterns.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体公司类
 */
public class ConcreteCompany extends Company {

    private List<Company> children = new ArrayList<>();

    public ConcreteCompany(String _name) {
        super(_name);
    }

    @Override
    public void add(Company c) {
        children.add(c);
    }

    @Override
    public void remove(Company c) {
        children.remove(c);
    }

    @Override
    public void display(int depth) {
        System.out.println(getStrByDepth(depth) + name);
        for (Company component : children) {
            component.display(depth + 2);
        }
    }

    private String getStrByDepth(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("-");
        }
        return sb.toString();
    }

    @Override
    public void lineOfDuty() {
        for (Company component : children) {
            component.lineOfDuty();
        }
    }
}
