package com.cheng.animationstudy.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cheng.animationstudy.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toDrawableAnim(View v) {
        overlay(FrameAnimActivity.class);
    }

    public void toTweenAnim(View v) {
        overlay(TweenAnimActivity.class);
    }

    public void toPropertyAnim(View v) {
        overlay(PropertyAnimActivity.class);
    }

    public void toAdvanced(View v) {
        overlay(AdvancedAnimActivity.class);
    }

    public void toActivityJump(View v) {
        overlay(TransitionActivityFirst.class);
    }

    public void toShowDialog(View v) {
        overlay(ShowDialogActivity.class);
    }

    public void toFragmentTransition(View v) {
        overlay(FragmentTransitionActivity.class);
    }

    public void toViewPagerTransition(View v) {
        overlay(ViewPagerTransitionView.class);
    }

    private void overlay(Class clazz) {
        Intent intent = new Intent(MainActivity.this, clazz);
        startActivity(intent);
    }

}
