package com.cheng.animationstudy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.cheng.animationstudy.R;
import com.cheng.animationstudy.customview.viewnatruetransition.CustomAnimator;
import com.cheng.utils.ViewFinder;

import java.util.Arrays;

/**
 * 选择控件与选择界面自然过渡效果的实现
 */
public class UiViewNatrueTransitionAnim extends AppCompatActivity {

    private FrameLayout mMainContainer;
    private FrameLayout mEditModeContainer;
    private View mLocalFrom;
    private View mLocalTo;
    private View mDateFrom;
    private View mDateTo;
    private LinearLayout mFirstGroup;
    private LinearLayout mSecondGroup;
    private LinearLayout mThirdGroup;
    private View mFirstSpacer;
    private ActionMode mActionMode;
    private int mHalfHeight;
    private CustomAnimator mCustomAnimator = new CustomAnimator();
    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_viewnatruetaansition_mode, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.done:
                    mCustomAnimator.prepareRevert();
                    mCustomAnimator.start();
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mCustomAnimator.prepareRevert();
            mCustomAnimator.start();
            mActionMode = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = getLayoutInflater().inflate(R.layout.ui_viewnatruetransitionanim, null);
        view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                v.removeOnLayoutChangeListener(this);
                mHalfHeight = view.getHeight() / 2;
                mEditModeContainer.setTranslationY(mHalfHeight);
                mEditModeContainer.setAlpha(0f);
                mCustomAnimator.setEditModeHalfHeight(mHalfHeight);
            }
        });
        setContentView(view);
        initView();
        initListener();
    }

    private void initView() {
        this.mMainContainer = ViewFinder.findViewById(this, R.id.main_container);
        this.mFirstGroup = ViewFinder.findViewById(this, R.id.first_group_container);
        this.mSecondGroup = ViewFinder.findViewById(this, R.id.second_group_container);
        this.mThirdGroup = ViewFinder.findViewById(this, R.id.third_group_container);
        this.mLocalFrom = ViewFinder.findViewById(this, R.id.localfrom);
        this.mLocalTo = ViewFinder.findViewById(this, R.id.localto);
        this.mFirstSpacer = ViewFinder.findViewById(this, R.id.first_spacer);
        this.mDateFrom = ViewFinder.findViewById(this, R.id.datefrom);
        this.mDateTo = ViewFinder.findViewById(this, R.id.dateto);
    }

    private void initListener() {
        this.mLocalFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActionMode = startActionMode(mActionModeCallback);
                mCustomAnimator.setAnimatorViews(mMainContainer, mLocalFrom, mFirstGroup, Arrays.asList(new View[]{mSecondGroup,mFirstSpacer,mThirdGroup}), null, mEditModeContainer, Arrays.asList(new View[]{}));

                mCustomAnimator.prepareAnimation();
                mCustomAnimator.start();
            }
        });
        this.mLocalTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActionMode = startActionMode(mActionModeCallback);
                mCustomAnimator.setAnimatorViews(mMainContainer, mLocalTo, mFirstGroup, Arrays.asList(new View[]{mSecondGroup,mFirstSpacer,mThirdGroup}), null, mEditModeContainer, Arrays.asList(new View[]{}));
                mCustomAnimator.prepareAnimation();
                mCustomAnimator.start();
            }
        });
        this.mDateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActionMode = startActionMode(mActionModeCallback);
                mCustomAnimator.setAnimatorViews(mMainContainer, mDateFrom, mSecondGroup, Arrays.asList(new View[]{mThirdGroup}), null, mEditModeContainer, Arrays.asList(new View[]{mFirstGroup, mFirstSpacer}));
                mCustomAnimator.prepareAnimation();
                mCustomAnimator.start();
            }
        });
        this.mDateTo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mActionMode = startActionMode(mActionModeCallback);
                mCustomAnimator.setAnimatorViews(mMainContainer, mDateTo, mSecondGroup, Arrays.asList(new View[]{mThirdGroup}), null, mEditModeContainer, Arrays.asList(new View[]{mFirstGroup, mFirstSpacer}));
                mCustomAnimator.prepareAnimation();
                mCustomAnimator.start();
            }
        });

        mEditModeContainer = (FrameLayout) findViewById(R.id.edit_mode_container);
    }

    @Override
    public void onBackPressed() {
        mCustomAnimator.prepareAnimation();
        mCustomAnimator.start();
        super.onBackPressed();
    }
}
