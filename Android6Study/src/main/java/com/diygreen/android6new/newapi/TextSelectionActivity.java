package com.diygreen.android6new.newapi;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.diygreen.android6new.R;


public class TextSelectionActivity extends AppCompatActivity {

    private ActionMode mActionMode;

    private TextView mTestTV;
    private ActionMode.Callback2 mCallback2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textselection);

        initView();
        initData();
    }

    private void initView() {
        this.mTestTV = (TextView) findViewById(R.id.tv_test);
        this.mTestTV.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                testTextSelection();
                return false;
            }
        });
    }

    @TargetApi(23)
    private void initData() {
        mCallback2 = new ActionMode.Callback2() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                if (inflater == null) {
                    return false;
                }
                inflater.inflate(R.menu.actionmode_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                Toast.makeText(TextSelectionActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();
                mode.finish();
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }

            // 控制这个浮动菜单的位置
            @Override
            public void onGetContentRect(ActionMode mode, View view, Rect outRect) {
                super.onGetContentRect(mode, view, outRect);
            }

        };
        this.mTestTV.setCustomSelectionActionModeCallback(mCallback2);
    }

    @TargetApi(23)
    private void testTextSelection() {
        mActionMode = startActionMode(mCallback2, ActionMode.TYPE_FLOATING);
    }

}
