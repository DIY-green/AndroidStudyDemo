package com.cheng.animationstudy.model;

import java.io.Serializable;

import android.graphics.Path;

public class Fllower implements Serializable {

    private int resId;
    private float x;
    private float y;
    private Path path;
    private float value;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Fllower [resId=" + resId + ", x=" + x + ", y=" + y + ", path="
                + path + ", value=" + value + "]";
    }


}
