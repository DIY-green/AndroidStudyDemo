package com.cheng.animationstudy.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cheng.animationstudy.R;

public class UiAdvanced extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_advanced);
    }

    public void toImitateWandoujia(View v) {
        overlay(UiImitateWandoujia.class);
    }

    public void toImitateBaiduRead(View v) {
        overlay(UiImitateBaiduReadBubble.class);
    }

    public void toImitateMogujie(View v) {
        overlay(UiImitateMogujieCart.class);
    }

    public void toImitateUCPullDownEye(View v) {
        overlay(UiImitateUCPullDownEye.class);
    }

    public void toImitateTaobaoRetraction(View v) {
        overlay(UiImitateTaobaoRetraction.class);
    }

    public void toImitateBrowserZoomView(View v) {
        overlay(UiImitateBrowserZoomView.class);
    }

    public void toViewNatureTransitionAnim(View v) {
        overlay(UiViewNatrueTransitionAnim.class);
    }

    public void toDynamicRotationAnim(View v) {
        overlay(UiDynamicRotationAnim.class);
    }

    public void toLeafFlyLoadingAnim(View v) {
        overlay(UiLeafFlyLoadingAnim.class);
    }

    public void toSunRosePullToRefreshAnim(View v) {
        overlay(UiSunRosePullToRefreshAnim.class);
    }

    public void toZrcListView(View v) {
        overlay(UiZrcListView.class);
    }

    public void toImitateATHMPullToRefreshAnim(View v) {
        overlay(UiImitateAutoHomePTRA.class);
    }

    public void toImitateMeiTuanPullToRefreshAnim(View v) {
        overlay(UiImitateMeiTuanPTRA.class);
    }

    public void toImitateGoogleSwipeRefreshLayoutAnim01(View v) {
        overlay(UiImitateGoogleSRLA01.class);
    }

    public void toImitateGoogleSwipeRefreshLayoutAnim02(View v) {
        overlay(UiImitateGoogleSRLA02.class);
    }

    public void toBGARefreshLayout(View v) {
        overlay(UiBGARefreshLayout.class);
    }

    public void toPullToRefreshAnim01(View v) {
        overlay(UiPullToRefreshAnim01.class);
    }

    public void toPullToRefreshAnim02(View v) {
        overlay(UiPullToRefreshAnim02.class);
    }

    public void toPullToRefreshAnim03(View v) {
        overlay(UiPullToRefreshAnim03.class);
    }

    private void overlay(Class clazz) {
        Intent intent = new Intent(UiAdvanced.this, clazz);
        startActivity(intent);
    }
}
