package com.cheng.animationstudy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cheng.animationstudy.R;

public class UiBGARefreshLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_bgarefreshlayout);
    }

    public void toBGANormalListView(View v) {
        overlay(UiBGANormalListView.class);
    }

    public void toBGANormalRecyclerView(View v) {
        overlay(UiBGANormalRecyclerView.class);
    }

    public void toBGASwipeListView(View v) {
        overlay(UiBGASwipeListView.class);
    }

    public void toBGASwipeRecyclerView(View v) {
        overlay(UiBGASwipeRecyclerView.class);
    }

    public void toBGAScrollView(View v) {
        overlay(UiBGAScrollView.class);
    }

    public void toBGAWebView(View v) {
        overlay(UiBGAWebView.class);
    }

    public void toBGAViewPager(View v) {
        overlay(UiBGAViewPager.class);
    }

    private void overlay(Class clazz) {
        Intent intent = new Intent(UiBGARefreshLayout.this, clazz);
        startActivity(intent);
    }

}
