package com.cheng.utils;

import android.app.Activity;
import android.view.View;

/**
 * 李旺成
 */
public class ViewFinder {

    public static <T extends View> T findViewById(View convertView, int id) {
        return (T)convertView.findViewById(id);
    }

    public static <T extends View> T findViewById(Activity activity, int id) {
        return activity==null ? null : (T)activity.findViewById(id);
    }
}
