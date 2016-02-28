package com.cheng.bigtalkdesignpatterns.state;

/**
 * 晚间工作状态
 */
public class EveningState extends State {

    @Override
    public void writeProgram(Work work) {
        if (work.isfinish()) {
            work.setCurrent(new RestState());
            work.writeProgram();
        } else {
            if (work.getHour() < 21) {
                System.out.println("当前时间：" + work.getHour() + "点， 加班哦，劳累之极");
            } else {
                work.setCurrent(new SleepingState());
                work.writeProgram();
            }
        }
    }
}
