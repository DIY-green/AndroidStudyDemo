package com.cheng.multithreadstudy.sunframework.http.HttpCallBack;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 支持穿透解析
 */
public abstract class AbstractParserCallBack<T> {

    private String[] mTitle;

    public AbstractParserCallBack(String... title) {
        this.mTitle = title;
    }

    public final void onSuccess(String stringResponseInfo) {
        try {
            parserResult(interceptParser(stringResponseInfo), mTitle);
        } catch (JSONException e) {
            onFailureCallBack(e);
        }
    }

    private T parserResult(String wrapString, String... title) {
        if (!TextUtils.isEmpty(wrapString)) {
            try {
                if (title == null || title.length == 0) {
                    if (!onSuccessWithWrapString(wrapString)) {
                        if (getCurrentClass() == String.class) {
                            return (T) wrapString;
                        }
                        JSONObject wrapJsonObject = new JSONObject(wrapString);
                        if (!onSuccessWithWrapJson(wrapJsonObject)) {
                            // T t = JSON.parseObject(wrapString, getCurrentClass());
                            T t = null;
                            onSuccessWithObject(t);
                            return t;
                        }
                    }
                } else {
                    JSONObject currentObject = new JSONObject(wrapString);
                    return parserResult(currentObject.optString(title[0]), Arrays.copyOfRange(title, 1, title.length));
                }
            } catch (JSONException e) {
                try {
                    JSONArray jsonArray = new JSONArray(wrapString);
                    if (title == null || title.length == 0) {
//                        onSuccessWithObjectList(new ArrayList<T>(JSON.parseArray(wrapString, getCurrentClass())));
                        onSuccessWithObjectList(new ArrayList<T>());
                    } else {
                        int length = jsonArray.length();
                        ArrayList<T> list = new ArrayList<>(length);
                        for (int i = 0; i < length; i++) {
                            T t = parserResult(jsonArray.getString(i), title);
                            if (t != null) {
                                list.add(t);
                            }
                        }
                        onSuccessWithObjectList(list);
                    }
                } catch (Exception e1) {
                    onFailureCallBack(e1);
                }
            }
        }
        if (getCurrentClass() == String.class) {
            return (T) wrapString;
        }
        return null;
    }


    public final void onFailure(Exception e, String s) {
        onFailureCallBack(e, s);
    }

    private final void onFailureCallBack(Exception e) {
        onFailureCallBack(e, e == null ? "" : e.getMessage());
    }

    protected void onFailureCallBack(Exception e, String msg) {

    }

    /**
     * 任意一个返回true 既不会再往下执行，解析传递到此结束。
     * 当然可以中间拦截 返回false，继续执行. 除非解析到最后需要的节点
     * 最后返回顺序为：String－>JSON->Object或者String->List
     *
     * @param wrapString
     * @return
     */
    protected boolean onSuccessWithWrapString(String wrapString) {
        return false;
    }

    protected boolean onSuccessWithWrapJson(JSONObject jsonObject) {
        return false;
    }

    protected boolean onSuccessWithObject(T t) {
        return false;
    }

    protected boolean onSuccessWithObjectList(ArrayList<T> list) {
        return false;
    }

    protected abstract Class<T> getCurrentClass();

    protected String interceptParser(String result) throws JSONException {
        return result;
    }

}
