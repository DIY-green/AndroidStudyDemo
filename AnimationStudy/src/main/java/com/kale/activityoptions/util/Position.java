package com.kale.activityoptions.util;

import android.graphics.Rect;
import android.view.View;

/**
 * @author:Jack Tony
 * @tips  :得到view相对于屏幕的左上角的绝对坐标，还有view的本身宽高
 * 这个宽高和view当前显示的区域无关
 * @date  :2014-11-24
 */
public class Position {
    
    public static Rect getGlobalVisibleRect(View v) {
    	//得到view的左上角坐标（相对于整个屏幕）
        int[] position = new int[2];
        v.getLocationOnScreen(position);
        Rect mRect = new Rect();
        mRect.left = position[0];
        mRect.top = position[1];
        mRect.right = mRect.left + v.getWidth();
        mRect.bottom = mRect.top + v.getHeight();
        return mRect;
    }

    /**
     * @param v
     * @return 得到view当前真实显示的位置和大小，超过屏幕显示的大小就是0
     */
    public static Rect getRealVisibleRect(View v) {
    	//得到view的左上角坐标（相对于整个屏幕）
        int[] position = new int[2];
        v.getLocationOnScreen(position);
        Rect bounds = new Rect();
        boolean isInScreen = v.getGlobalVisibleRect(bounds);
        Rect mRect = new Rect();
        mRect.left = position[0];
        mRect.top = position[1];
        if (isInScreen) {
        	mRect.right = mRect.left + bounds.width();
        	mRect.bottom = mRect.top + bounds.height();
		}else {
			mRect.right = mRect.left;
	        mRect.bottom = mRect.top;
		}
        return mRect;
    }
}
