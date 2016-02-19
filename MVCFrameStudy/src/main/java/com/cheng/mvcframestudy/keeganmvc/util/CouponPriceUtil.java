/**
 * Copyright (C) 2015. Keegan小钢（http://keeganlee.me）
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cheng.mvcframestudy.keeganmvc.util;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;

import java.text.DecimalFormat;

/**
 * 处理券价格的拼接
 *
 * @version 1.0 创建时间：15/6/28
 */
public class CouponPriceUtil {
    /**
     * 自动处理double数据,保留非0的2位小数
     */
    public static String handleDouble(double price) {
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        return decimalFormat.format(price);
    }

    /**
     * sp 转 px
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 现金券显示价格样式
     */
    public static SpannableString getCashPrice(Context context, double oldPrice, double newPrice) {
        StringBuilder builder = new StringBuilder();
        builder.append(handleDouble(newPrice)).append("元").append(" ").append(handleDouble(oldPrice)).append("元");
        int start = 0;
        int middle = builder.indexOf(" ") + 1;
        int end = builder.length();
        SpannableString string = new SpannableString(builder);
        /*改变文字的大小*/
        string.setSpan(new AbsoluteSizeSpan(sp2px(context, 20)), start, middle, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        string.setSpan(new AbsoluteSizeSpan(sp2px(context, 14)), middle, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        /*给文字设置删除线*/
        string.setSpan(new StrikethroughSpan(), middle, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        /*改变文字的颜色*/
        int textOrange = context.getResources().getColor(android.R.color.holo_red_light);
        int textGray = context.getResources().getColor(android.R.color.darker_gray);
        string.setSpan(new ForegroundColorSpan(textOrange), start, middle, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        string.setSpan(new ForegroundColorSpan(textGray), middle, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return string;
    }

    /**
     * 抵用券显示样式
     */
    public static SpannableString getVoucherPrice(Context context, double voucher, double miniAmount) {
        StringBuilder builder = new StringBuilder();
        int textOrange = context.getResources().getColor(android.R.color.holo_red_light);
        int textGray = context.getResources().getColor(android.R.color.darker_gray);
        SpannableString string;
        if (miniAmount > 0) {
            builder.append("满").append(handleDouble(miniAmount)).append("元减").append(handleDouble(voucher)).append("元");
            int index = builder.indexOf("元") + 1;
            string = new SpannableString(builder);
            /*改变文字的颜色*/
            int size = string.length();
            string.setSpan(new ForegroundColorSpan(textGray), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            string.setSpan(new ForegroundColorSpan(textOrange), 1, index, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            string.setSpan(new ForegroundColorSpan(textGray), index, index + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            string.setSpan(new ForegroundColorSpan(textOrange), index + 1, size, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            builder.append("立减").append(handleDouble(voucher)).append("元");
            string = new SpannableString(builder);
            /*改变文字的颜色*/
            int size = string.length();
            string.setSpan(new ForegroundColorSpan(textGray), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            string.setSpan(new ForegroundColorSpan(textOrange), 2, size, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return string;
    }

    /**
     * 折扣券显示样式
     */
    public static SpannableString getDiscountPrice(Context context, double discount, double miniAmount) {
        discount = discount * 0.1;
        StringBuilder builder = new StringBuilder();
        int textOrange = context.getResources().getColor(android.R.color.holo_red_light);
        int textGray = context.getResources().getColor(android.R.color.darker_gray);
        SpannableString string;
        if (miniAmount > 0) {
            builder.append("满").append(handleDouble(miniAmount)).append("元享").append(handleDouble(discount)).append("折");
            int index = builder.indexOf("元") + 1;
            string = new SpannableString(builder);
            /*改变文字的颜色*/
            int size = string.length();
            string.setSpan(new ForegroundColorSpan(textGray), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            string.setSpan(new ForegroundColorSpan(textOrange), 1, index, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            string.setSpan(new ForegroundColorSpan(textGray), index, index + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            string.setSpan(new ForegroundColorSpan(textOrange), index + 1, size - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            string.setSpan(new ForegroundColorSpan(textGray), size - 1, size, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            builder.append(handleDouble(discount)).append("折");
            string = new SpannableString(builder);
            /*改变文字的颜色*/
            int size = string.length();
            string.setSpan(new ForegroundColorSpan(textOrange), 0, size - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            string.setSpan(new ForegroundColorSpan(textGray), size - 1, size, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return string;
    }
}
