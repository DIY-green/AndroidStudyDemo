package com.cheng.bigtalkdesignpatterns.builder;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 产品类，由多个部件组成
 */
public class Product {

    private static final String TAG = "Product";

    private List<String> parts = new ArrayList<>();

    /**
     * 添加产品部件
     * @param part
     */
    public void add(String part) {
        parts.add(part);
    }

    public void show() {
        Log.e(TAG, "\n 产品 创建-----");
        for (String part : parts) {
            Log.e(TAG, part);
        }
    }
}
