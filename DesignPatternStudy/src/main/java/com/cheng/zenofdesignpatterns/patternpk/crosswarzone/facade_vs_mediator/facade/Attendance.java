package com.cheng.zenofdesignpatterns.patternpk.crosswarzone.facade_vs_mediator.facade;

import java.util.Random;

/**
 * 考勤情况
 */
public class Attendance {

    //得到出勤天数
    public int getWorkDays() {
        return (new Random()).nextInt(30);
    }

}