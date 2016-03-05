package com.cheng.animationstudy.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cheng.animationstudy.R;

public class AdvancedAnimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_anim);
    }

    public void toImitateWandoujia(View v) {
        overlay(ImitateWandoujiaActivity.class);
    }

    public void toImitateBaiduRead(View v) {
        overlay(ImitateBaiduReadBubbleActivity.class);
    }

    public void toImitateMogujie(View v) {
        overlay(ImitateMogujieCartActivity.class);
    }

    public void toImitateUCPullDownEye(View v) {
        overlay(ImitateUCPullDownEyeActivity.class);
    }

    public void toImitateTaobaoRetraction(View v) {
        overlay(ImitateTaobaoRetractionActivity.class);
    }

    public void toImitateBrowserZoomView(View v) {
        overlay(ImitateBrowserZoomViewActivity.class);
    }

    public void toViewNatureTransitionAnim(View v) {
        overlay(ViewNatrueTransitionAnimActivity.class);
    }

    public void toDynamicRotationAnim(View v) {
        overlay(DynamicRotationAnimActivity.class);
    }

    public void toLeafFlyLoadingAnim(View v) {
        overlay(LeafFlyLoadingAnimActivity.class);
    }

    public void toSunRosePullToRefreshAnim(View v) {
        overlay(SunRosePullToRefreshAnimActivity.class);
    }

    public void toZrcListView(View v) {
        overlay(ZrcListViewActivity.class);
    }

    public void toImitateATHMPullToRefreshAnim(View v) {
        overlay(ImitateAutoHomePTRAActivity.class);
    }

    public void toImitateMeiTuanPullToRefreshAnim(View v) {
        overlay(ImitateMeiTuanPTRAActivity.class);
    }

    public void toImitateGoogleSwipeRefreshLayoutAnim01(View v) {
        overlay(ImitateGoogleSRLA01Activity.class);
    }

    public void toImitateGoogleSwipeRefreshLayoutAnim02(View v) {
        overlay(ImitateGoogleSRLA02Activity.class);
    }

    public void toBGARefreshLayout(View v) {
        overlay(BGARefreshLayoutActivity.class);
    }

    public void toPullToRefreshAnim01(View v) {
        overlay(PullToRefreshAnim01Activity.class);
    }

    public void toPullToRefreshAnim02(View v) {
        overlay(PullToRefreshAnim02Activity.class);
    }

    public void toPullToRefreshAnim03(View v) {
        overlay(PullToRefreshAnim03Activity.class);
    }

    private void overlay(Class clazz) {
        Intent intent = new Intent(AdvancedAnimActivity.this, clazz);
        startActivity(intent);
    }
}
