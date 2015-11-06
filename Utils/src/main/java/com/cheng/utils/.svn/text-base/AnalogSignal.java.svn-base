package com.cheng.utils;

import android.view.KeyEvent;

import java.io.IOException;

/**
 * 模拟系统信号
 */
public class AnalogSignal {

    private static final String TAG = "TweenAnimView";

    public static final class KeyEventSignal {

        public static void up() {
            analogMenuEvent(KeyEvent.KEYCODE_DPAD_UP);
        }

        public static void down() {
            analogMenuEvent(KeyEvent.KEYCODE_DPAD_DOWN);
        }

        public static void left() {
            analogMenuEvent(KeyEvent.KEYCODE_DPAD_LEFT);
        }

        public static void right() {
            analogMenuEvent(KeyEvent.KEYCODE_DPAD_RIGHT);
        }

        public static void center() {
            analogMenuEvent(KeyEvent.KEYCODE_DPAD_CENTER);
        }

        private static void analogMenuEvent(int keyCode) {
            if (keyCode < 0) {
                keyCode = KeyEvent.KEYCODE_MENU;
            }
            try {
                String keyCommand = "input keyevent " + keyCode;
                Runtime runtime = Runtime.getRuntime();
                Process proc = runtime.exec(keyCommand);
            } catch (IOException e) {
                Log.e(e.getMessage());
            }
        }

    }

    private static class Log {

        public static void v(String str) {
            Logger.v(TAG, str);
        }

        public static void d(String str) {
            Logger.d(TAG, str);
        }

        public static void i(String str) {
            Logger.i(TAG, str);
        }

        public static void w(String str) {
            Logger.w(TAG, str);
        }

        public static void e(String str) {
            Logger.e(TAG, str);
        }

    }
}
