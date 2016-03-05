package com.cheng.animationstudy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cheng.animationstudy.R;

public class BGARefreshLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bgarefreshlayout);
    }

    public void toBGANormalListView(View v) {
        overlay(BGANormalListViewActivity.class);
    }

    public void toBGANormalRecyclerView(View v) {
        overlay(BGANormalRecyclerViewActivity.class);
    }

    public void toBGASwipeListView(View v) {
        overlay(BGASwipeListViewActivity.class);
    }

    public void toBGASwipeRecyclerView(View v) {
        overlay(BGASwipeRecyclerViewActivity.class);
    }

    public void toBGAScrollView(View v) {
        overlay(BGAScrollViewActivity.class);
    }

    public void toBGAWebView(View v) {
        overlay(BGAWebViewActivity.class);
    }

    public void toBGAViewPager(View v) {
        overlay(BGAViewPagerActivity.class);
    }

    private void overlay(Class clazz) {
        Intent intent = new Intent(BGARefreshLayoutActivity.this, clazz);
        startActivity(intent);
    }

}
