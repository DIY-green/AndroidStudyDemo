package com.cheng.bigtalkdesignpatterns.simplefactory;

/**
 * Created by Administrator on 2015/12/18.
 */
public class OperationAdd extends Operation {

    @Override
    public double getResult() {
        double result = 0;
        result = numberA + numberB;
        return result;
    }

}
