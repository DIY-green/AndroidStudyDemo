package com.cheng.multithreadstudy.sunframework.proxy;

/**
 * 消息msg.what用作标识（不要随意使用） 消息 msg.arg1用作集中处理的消息(不要随意使用) 消息msg.arg2留作以后传递参数用
 * 消息msg.obj用作传递数据
 */
public class MessageArg {

    public static class ARG1 {
        public static final int TOAST_MESSAGE = 1;
        public static final int CALL_BACK_METHOD = 2;
        public static final int PROGRESSDIALOG_MESSAGE = 3;
    }

    public static class ARG2 {
    }

    public static class WHAT {
        public static final int UI_MESSAGE = 0;
    }

}
