package com.cheng.animationstudy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.cheng.animationstudy.R;
import com.cheng.utils.ViewFinder;

public class LayoutAnimDefaultActivity extends AppCompatActivity {

    private GridLayout mGridContainerGL;
    private LinearLayout mGridContainerLL;
    private Button mAddButton;
    private CheckBox mUseGridlayoutCB;
    private int mNumButtons = 1;
    private boolean mIsUseGridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layoutanimdefault);

        initView();
        initListener();
    }

    private void initView() {
        // GridLayout是API Level 14才有的
        this.mGridContainerGL = ViewFinder.findViewById(this, R.id.gridContainer_gl);
        this.mGridContainerLL = ViewFinder.findViewById(this, R.id.gridContainer_ll);
        this.mAddButton = ViewFinder.findViewById(this, R.id.btn_addnew);
        this.mUseGridlayoutCB = ViewFinder.findViewById(this, R.id.sdi_usegridlayout_cb);
    }

    private void initListener() {
        this.mIsUseGridLayout = mUseGridlayoutCB.isChecked();
        this.mAddButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Button newButton = new Button(LayoutAnimDefaultActivity.this);
                newButton.setText(String.valueOf(mNumButtons++));
                newButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (mIsUseGridLayout) {
                            mGridContainerGL.removeView(v);
                        } else {
                            mGridContainerLL.removeView(v);
                        }
                    }
                });
                if (mIsUseGridLayout) {
                    mGridContainerGL.addView(newButton,
                            Math.min(1, mGridContainerGL.getChildCount()));
                } else {
                    mGridContainerLL.addView(newButton,
                            Math.min(1, mGridContainerLL.getChildCount()));
                }
            }
        });
        this.mUseGridlayoutCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mNumButtons = 0;
                if (mGridContainerGL != null && mGridContainerGL.getChildCount() > 0)
                    mGridContainerGL.removeAllViews();
                if (mGridContainerLL != null && mGridContainerLL.getChildCount() > 0)
                    mGridContainerLL.removeAllViews();
                mGridContainerGL.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                mGridContainerLL.setVisibility(!isChecked ? View.VISIBLE : View.GONE);
            }
        });
    }

}
