package com.cheng.animationstudy.customview.zrclistview;

import android.content.Context;
import android.graphics.Canvas;

public interface Headable {
    public static final int STATE_REST = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_SUCCESS = 2;
    public static final int STATE_FAIL = 3;
    public static final int STATE_PULL = 4;
    public static final int STATE_RELEASE = 5;

    void stateChange(int state, String msg);

    int getState();

    boolean draw(Canvas canvas, int left, int top, int right, int bottom);

    void toastResultInOtherWay(Context context, int state);

    int getHeight();
}
