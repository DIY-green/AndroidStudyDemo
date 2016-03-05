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
public class ViewNatrueTransitionAnimActivity extends AppCompatActivity {

    private FrameLayout mMainContainerFL;
    private FrameLayout mEditModeContainerFL;
    private View mLocalFromView;
    private View mLocalToView;
    private View mDateFromView;
    private View mDateToView;
    private LinearLayout mFirstGroupLL;
    private LinearLayout mSecondGroupLL;
    private LinearLayout mThirdGroupLL;
    private View mFirstSpacerView;
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
        final View view = getLayoutInflater().inflate(R.layout.activity_viewnatruetransitionanim, null);
        view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                v.removeOnLayoutChangeListener(this);
                mHalfHeight = view.getHeight() / 2;
                mEditModeContainerFL.setTranslationY(mHalfHeight);
                mEditModeContainerFL.setAlpha(0f);
                mCustomAnimator.setEditModeHalfHeight(mHalfHeight);
            }
        });
        setContentView(view);
        initView();
        initListener();
    }

    private void initView() {
        this.mMainContainerFL = ViewFinder.findViewById(this, R.id.main_container);
        this.mFirstGroupLL = ViewFinder.findViewById(this, R.id.first_group_container);
        this.mSecondGroupLL = ViewFinder.findViewById(this, R.id.second_group_container);
        this.mThirdGroupLL = ViewFinder.findViewById(this, R.id.third_group_container);
        this.mLocalFromView = ViewFinder.findViewById(this, R.id.localfrom);
        this.mLocalToView = ViewFinder.findViewById(this, R.id.localto);
        this.mFirstSpacerView = ViewFinder.findViewById(this, R.id.first_spacer);
        this.mDateFromView = ViewFinder.findViewById(this, R.id.datefrom);
        this.mDateToView = ViewFinder.findViewById(this, R.id.dateto);
    }

    private void initListener() {
        this.mLocalFromView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActionMode = startActionMode(mActionModeCallback);
                mCustomAnimator.setAnimatorViews(mMainContainerFL, mLocalFromView, mFirstGroupLL, Arrays.asList(new View[]{mSecondGroupLL, mFirstSpacerView, mThirdGroupLL}), null, mEditModeContainerFL, Arrays.asList(new View[]{}));

                mCustomAnimator.prepareAnimation();
                mCustomAnimator.start();
            }
        });
        this.mLocalToView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActionMode = startActionMode(mActionModeCallback);
                mCustomAnimator.setAnimatorViews(mMainContainerFL, mLocalToView, mFirstGroupLL, Arrays.asList(new View[]{mSecondGroupLL, mFirstSpacerView, mThirdGroupLL}), null, mEditModeContainerFL, Arrays.asList(new View[]{}));
                mCustomAnimator.prepareAnimation();
                mCustomAnimator.start();
            }
        });
        this.mDateFromView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActionMode = startActionMode(mActionModeCallback);
                mCustomAnimator.setAnimatorViews(mMainContainerFL, mDateFromView, mSecondGroupLL, Arrays.asList(new View[]{mThirdGroupLL}), null, mEditModeContainerFL, Arrays.asList(new View[]{mFirstGroupLL, mFirstSpacerView}));
                mCustomAnimator.prepareAnimation();
                mCustomAnimator.start();
            }
        });
        this.mDateToView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mActionMode = startActionMode(mActionModeCallback);
                mCustomAnimator.setAnimatorViews(mMainContainerFL, mDateToView, mSecondGroupLL, Arrays.asList(new View[]{mThirdGroupLL}), null, mEditModeContainerFL, Arrays.asList(new View[]{mFirstGroupLL, mFirstSpacerView}));
                mCustomAnimator.prepareAnimation();
                mCustomAnimator.start();
            }
        });

        mEditModeContainerFL = (FrameLayout) findViewById(R.id.edit_mode_container);
    }

    @Override
    public void onBackPressed() {
        mCustomAnimator.prepareAnimation();
        mCustomAnimator.start();
        super.onBackPressed();
    }
}
