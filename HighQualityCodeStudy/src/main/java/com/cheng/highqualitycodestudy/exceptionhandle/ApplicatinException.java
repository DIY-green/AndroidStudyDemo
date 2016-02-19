package com.cheng.highqualitycodestudy.exceptionhandle;

/**
 * Created by Administrator on 2015/11/23.
 */
public class ApplicatinException extends Exception {

    public ApplicatinException(String msg, Exception ex) {
        super(msg, ex);
    }
}
