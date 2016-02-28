package com.cheng.bigtalkdesignpatterns.state;

/**
 * 上午工作状态
 */
public class ForenoonState extends State {

    @Override
    public void writeProgram(Work work) {
        if (work.getHour() < 12) {
            System.out.println("当前时间：" + work.getHour() + "点， 上午工作，精神百倍");
        } else {
            // 超过12点，则转入中午工作状态
            work.setCurrent(new NoonState());
            work.writeProgram();
        }
    }
}
