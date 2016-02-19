package com.cheng.multithreadstudy.sunframework.http.HttpCallBack;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 无限层次解析
 */
public class BaseParserCallBack<T> extends AbstractParserCallBack<T> {

    public BaseParserCallBack(String... title) {
        super(title);
    }

    @Override
    protected String interceptParser(String result) throws JSONException {
        JSONObject jsonObject = new JSONObject(result);
        return super.interceptParser(jsonObject.toString());
    }

    @Override
    protected Class<T> getCurrentClass() {
        return (Class<T>) String.class;
    }
}
