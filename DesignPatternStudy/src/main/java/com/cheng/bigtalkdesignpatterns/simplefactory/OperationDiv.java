package com.cheng.bigtalkdesignpatterns.simplefactory;

/**
 * Created by Administrator on 2015/12/18.
 */
public class OperationDiv extends Operation {

    @Override
    public double getResult() {
        double result = 0;
        if (numberB == 0) {
            throw new RuntimeException("除数不能为0");
        }
        result = numberA / numberB;
        return result;
    }

}
