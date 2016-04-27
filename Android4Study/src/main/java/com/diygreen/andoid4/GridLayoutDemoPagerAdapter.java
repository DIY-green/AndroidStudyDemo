/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.diygreen.andoid4;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

/**
 * Description:
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年3月7日
 *
 * @author 李旺成    liwangcheng@jiashuangkuaizi.com
 * @version 1.0
 */

public class GridLayoutDemoPagerAdapter extends PagerAdapter {

    private View[] mDataArr;

    public GridLayoutDemoPagerAdapter(@NonNull View[] dataArr) {
        this.mDataArr = dataArr;
    }

    @Override
    public int getCount() {
        return mDataArr.length;
    }

    // 滑动切换的时候销毁当前的组件
    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        ((ViewPager) container).removeView(mDataArr[position]);
    }

    // 每次滑动的时候生成的组件
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager) container).addView(mDataArr[position]);
        return mDataArr[position];
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
