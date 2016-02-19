package com.cheng.utils.view;

import android.os.CountDownTimer;
import android.widget.Button;

/**
 * 倒计时View帮助类
 * 这只是一个使用CountDownTimer的示例，
 * 用于说明使用时的注意事项，
 * 具体使用还是内部类好用点
 */
public class CountDownButtonHelper {

    private CountDownTimer mCountDownTimer;     // 倒计时Timer
    private OnFinishListener mOnFinishListener; // 计时结束的回调接口

    private Button mCountDownBtn;


    /**
     * 计时结束的回调接口
     */
    public interface OnFinishListener {
        public void finish();
    }


    /**
     * @param button 需要显示倒计时的Button
     * @param defaultString 默认显示的字符串
     * @param max 需要进行倒计时的最大值,单位是秒
     * @param interval 倒计时的间隔，单位是秒
     */
    public CountDownButtonHelper(final Button button,
                                 final String defaultString, int max, int interval) {
        this.mCountDownBtn = button;
        // 由于CountDownTimer并不是准确计时，在onTick方法调用的时候，time会有1-10ms左右的误差，这会导致最后一秒不会调用onTick()
        // 因此，设置间隔的时候，默认减去了10ms，从而减去误差。
        // 经过以上的微调，最后一秒的显示时间会由于10ms延迟的积累，导致显示时间比1s长max*10ms的时间，其他时间的显示正常,总时间正常
        mCountDownTimer = new CountDownTimer(max * 1000, interval * 1000 - 10) {
            @Override
            public void onTick(long time) {
                // 第一次调用会有1-10ms的误差，因此需要+15ms，防止第一个数不显示，第二个数显示2s
                button.setText(defaultString + "(" + ((time + 15) / 1000)
                        + "秒)");
            }

            @Override
            public void onFinish() {
                button.setEnabled(true);
                button.setText(defaultString);
                if (mOnFinishListener != null) {
                    mOnFinishListener.finish();
                }
            }
        };
    }

    /**
     * 开始倒计时
     */
    public void start() {
        mCountDownBtn.setEnabled(false);
        mCountDownTimer.start();
    }

    /**
     * 设置倒计时结束的监听器
     * @param listener
     */
    public void setOnFinishListener(OnFinishListener listener) {
        this.mOnFinishListener = listener;
    }

    /**
     * 说明：
     *  CountDownTimer是一个系统提供的一个关于倒计数的类，
     *  可以设置倒计时的总时间，还有倒计时的间隔，这样每过一段固定时间，
     *  就可以在回调函数中进行我们的操作
     *  注意：
     *  由于CountDownTimer并不是准确计时，在onTick方法调用的时候，
     *  time会有1-10ms左右的误差，这会导致最后一秒不会调用onTick()
     *  因此，设置间隔的时候，默认减去了10ms，从而减去误差
     *  经过以上的微调，最后一秒的显示时间会由于10ms延迟的积累，
     *  导致显示时间比1s长max*10ms的时间，其他时间的显示正常,总时间正常
     */

}
