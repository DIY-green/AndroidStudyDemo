package com.cheng.animationstudy.customevaluator;

import android.animation.TypeEvaluator;
import android.graphics.Color;

/**
 *
 */
public class HsvEvaluator implements TypeEvaluator<Integer> {

    @Override
    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        float[] startHsv = new float[3];
        float[] endHsv = new float[3];
        float[] currentHsv = new float[3];

        Color.colorToHSV(startValue, startHsv);
        Color.colorToHSV(endValue, endHsv);

        for (int i = 0; i < 3; i++) {
            currentHsv[i] = (1 - fraction) * startHsv[i] + fraction * endHsv[i];
        }

        while (currentHsv[0] >= 360.0f) {
            currentHsv[0] -= 360.0f;
        }
        while (currentHsv[0] < 0.0f) {
            currentHsv[0] += 360.0f;
        }

        return Color.HSVToColor(currentHsv);
    }

}
