package com.diygreen.android6new.newwidget2;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.diygreen.android6new.R;

public class NavigationViewActivity extends AppCompatActivity {

    private DrawerLayout mRootDL;
    private View mContentView;
    private NavigationView mLeftNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigationview);

        initView();
    }

    private void initView() {
        mRootDL = (DrawerLayout) findViewById(R.id.dl_root);
        mContentView = findViewById(R.id.fl_content);
        mLeftNavigation = (NavigationView) findViewById(R.id.navigation_left);

        initToolbar();
        setupDrawerLayout();
    }

    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.tb_title);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu_black_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupDrawerLayout() {
        mLeftNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override public boolean onNavigationItemSelected(MenuItem menuItem) {
                Snackbar.make(mContentView, menuItem.getTitle() + " pressed", Snackbar.LENGTH_LONG).show();
                menuItem.setChecked(true);
                mRootDL.closeDrawers();
                return true;
            }
        });
    }
}
