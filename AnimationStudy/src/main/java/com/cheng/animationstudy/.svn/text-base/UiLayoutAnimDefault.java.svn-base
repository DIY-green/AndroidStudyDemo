package com.cheng.animationstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.cheng.utils.ViewFinder;

public class UiLayoutAnimDefault extends AppCompatActivity {

    private GridLayout mGridContainerGL;
    private LinearLayout mGridContainerLL;
    private Button mAddButton;
    private CheckBox mUseGridlayoutCB;
    private int numButtons = 1;
    private boolean isUseGridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_layoutanimdefault);

        initView();
        initListener();
    }

    private void initView() {
        // GridLayout是API Level 14才有的
        this.mGridContainerGL = ViewFinder.findViewById(this, R.id.gridContainer_gl);
        this.mGridContainerLL = ViewFinder.findViewById(this, R.id.gridContainer_ll);
        this.mAddButton = ViewFinder.findViewById(this, R.id.addNewButton);
        this.mUseGridlayoutCB = ViewFinder.findViewById(this, R.id.sdi_usegridlayout_cb);
    }

    private void initListener() {
        isUseGridLayout = mUseGridlayoutCB.isChecked();
        mAddButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Button newButton = new Button(UiLayoutAnimDefault.this);
                newButton.setText(String.valueOf(numButtons++));
                newButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (isUseGridLayout) {
                            mGridContainerGL.removeView(v);
                        } else {
                            mGridContainerLL.removeView(v);
                        }
                    }
                });
                if (isUseGridLayout) {
                    mGridContainerGL.addView(newButton,
                            Math.min(1, mGridContainerGL.getChildCount()));
                } else {
                    mGridContainerLL.addView(newButton,
                            Math.min(1, mGridContainerLL.getChildCount()));
                }
            }
        });
        mUseGridlayoutCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                numButtons = 0;
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
