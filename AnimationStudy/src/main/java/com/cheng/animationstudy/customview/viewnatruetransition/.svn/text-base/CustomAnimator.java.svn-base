package com.cheng.animationstudy.customview.viewnatruetransition;

import android.animation.PointFEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CustomAnimator extends ValueAnimator implements ValueAnimator.AnimatorUpdateListener {

    private final int ANIMATION_DURATION = 300;

    private ViewGroup mMainContainer;
    private View mFocusedView;
    private View mFocusedViewContainer;
    private List<View> mFadedOutToBottomViews;
    private List<View> mSlideToTop;
    private View mStickyTo;

    private ViewGroup mEditModeViewGroup;
    private final Rect mTmpRect;
    private boolean mIsRevert;
    private int mEditModeHalfHeight;

    public CustomAnimator() {
        super();
        mTmpRect = new Rect();
        mIsRevert = false;
        mFadedOutToBottomViews = new ArrayList<View>();
        mSlideToTop = new ArrayList<View>();

        setInterpolator(new DecelerateInterpolator());
        setDuration(ANIMATION_DURATION);
        addUpdateListener(this);
    }

    public void setAnimatorViews(ViewGroup mainContainer, View focusedView, View focusedViewContainer, List<View> fadedOutToBottomViews, View stickyTo, ViewGroup editModeView, List<View> slideToTop) {
        if (mainContainer == null) throw new NullPointerException();
        if (focusedView == null) throw new NullPointerException();
        if (focusedViewContainer == null) throw new NullPointerException();
        if (editModeView == null) throw new NullPointerException();

        mMainContainer = mainContainer;
        mFocusedView = focusedView;
        mFocusedViewContainer = focusedViewContainer;
        mFadedOutToBottomViews.clear();
        if (fadedOutToBottomViews != null) {
            mFadedOutToBottomViews.addAll(fadedOutToBottomViews);
        }
        mStickyTo = stickyTo;
        mEditModeViewGroup = editModeView;
        mSlideToTop.clear();
        if (slideToTop != null) {
            mSlideToTop.addAll(slideToTop);
        }

        removeAllListeners();

        addListener(new LayerEnablingAnimatorListener(focusedView));
        for (View v : mFadedOutToBottomViews) {
            addListener(new LayerEnablingAnimatorListener(v));
        }
        for (View v : mSlideToTop) {
            addListener(new LayerEnablingAnimatorListener(v));
        }
        addListener(new LayerEnablingAnimatorListener(editModeView));
    }

    public void setEditModeHalfHeight(int editModeHalfHeight) {
        mEditModeHalfHeight = editModeHalfHeight;
    }

    private Positions getPositionsStart() {
        // focusOn
        float focusY = mFocusedViewContainer.getY();

        // slideToTop
        int slideToTopSize = mSlideToTop.size();
        float[] slideToTopY = new float[slideToTopSize];
        for (int i = 0; i < slideToTopSize; i++) {
            slideToTopY[i] = mSlideToTop.get(i).getY();
        }

        // fadeToBottom
        int fadeToBottomSize = mFadedOutToBottomViews.size();
        float[] nextContainersY = new float[fadeToBottomSize];
        for (int i = 0; i < fadeToBottomSize; i++) {
            nextContainersY[i] = mFadedOutToBottomViews.get(i).getY();
        }
        float nextContainersAlpha = 1;

        // stickToY
        float stickToY = mStickyTo==null ? 0 : mStickyTo.getY();

        // fadeInToTop
        float editY = mEditModeViewGroup.getY();
        float editAlpha = 0f;

        return new Positions(focusY, nextContainersY, nextContainersAlpha, stickToY, editY, editAlpha, slideToTopY);
    }

    private Positions getPositionsEnd() {
        // focusOn
        mFocusedView.getDrawingRect(mTmpRect);
        mMainContainer.offsetDescendantRectToMyCoords(mFocusedView, mTmpRect);
        float focusY = mFocusedViewContainer.getY() - mTmpRect.top;

        // slideToTop
        float[] slideToTopY = new float[mSlideToTop.size()];
        for (int i = 0; i < mSlideToTop.size(); i++) {
            slideToTopY[i] = mSlideToTop.get(i).getY() - mTmpRect.top;
        }

        // fadeToBottom
        float[] nextContainersY = new float[mFadedOutToBottomViews.size()];
        for (int i = 0; i < mFadedOutToBottomViews.size(); i++) {
            nextContainersY[i] = mFadedOutToBottomViews.get(i).getY() + mEditModeHalfHeight;
        }
        float nextContainersAlpha = 0;

        // stickyTo
        float stickToY = 0;
        if (mStickyTo != null) {
            mStickyTo.getDrawingRect(mTmpRect);
            mMainContainer.offsetDescendantRectToMyCoords(mStickyTo, mTmpRect);
            stickToY = mStickyTo.getY() + (mStickyTo.getHeight() - mTmpRect.top);
        }

        // fadeInToTop
        float editY = 0;
        float editAlpha = 1f;

        return new Positions(focusY, nextContainersY, nextContainersAlpha, stickToY, editY, editAlpha, slideToTopY);
    }

    @SuppressLint("NewApi")
    public void prepareAnimation() {
        mIsRevert = false;
        setObjectValues(getPositionsStart(), getPositionsEnd());
        setEvaluator(new PositionTypeEvaluator());
    }
    private Positions getRevertPositionsStart() {
        // focusOn
        float focusY = mFocusedViewContainer.getY();

        // slideToTop
        float[] slideToTopY = new float[mSlideToTop.size()];
        for (int i = 0; i < mSlideToTop.size(); i++) {
            slideToTopY[i] = mSlideToTop.get(i).getY();
        }

        // fadeToBottom
        float[] nextContainersY = new float[mFadedOutToBottomViews.size()];
        for (int i = 0; i < mFadedOutToBottomViews.size(); i++) {
            nextContainersY[i] = mFadedOutToBottomViews.get(i).getY();
        }
        float nextContainersAlpha = 0;

        // stickyTo
        float stickToY = mStickyTo == null ? 0 : mStickyTo.getY();

        // fadeInToTop
        float editY = mEditModeViewGroup.getY();
        float editAlpha = 1f;

        return new Positions(focusY, nextContainersY, nextContainersAlpha, stickToY, editY, editAlpha, slideToTopY);
    }

    private Positions getRevertPositionsEnd() {
        // focusOn
        mFocusedView.getDrawingRect(mTmpRect);
        mMainContainer.offsetDescendantRectToMyCoords(mFocusedView, mTmpRect);
        float focusY = mFocusedViewContainer.getY() + mTmpRect.top;

        // slideToTop
        float[] slideToTopY = new float[mSlideToTop.size()];
        for (int i = 0; i < mSlideToTop.size(); i++) {
            slideToTopY[i] = mSlideToTop.get(i).getY() + mTmpRect.top;
        }

        // fadeToBottom
        float[] nextContainersY = new float[mFadedOutToBottomViews.size()];
        for (int i = 0; i < mFadedOutToBottomViews.size(); i++) {
            nextContainersY[i] = mFadedOutToBottomViews.get(i).getY() - mEditModeHalfHeight;
        }
        float nextContainersAlpha = 1;

        // stickyTo
        float stickToY = 0;
        if (mStickyTo != null) {
            mStickyTo.getDrawingRect(mTmpRect);
            mMainContainer.offsetDescendantRectToMyCoords(mStickyTo, mTmpRect);
            stickToY = mStickyTo.getY() - (mStickyTo.getHeight() - mTmpRect.top);
        }

        // fadeInToTop
        float editY = mEditModeHalfHeight;
        float editAlpha = 0f;

        return new Positions(focusY, nextContainersY, nextContainersAlpha, stickToY, editY, editAlpha, slideToTopY);
    }

    public void prepareRevert() {
        mIsRevert = true;

        setObjectValues(getRevertPositionsStart(), getRevertPositionsEnd());
        setEvaluator(new PositionTypeEvaluator());
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        Positions currentPos = (Positions) animation.getAnimatedValue();
        if (!mIsRevert) {
            for (int i = 0; i < mSlideToTop.size(); i++) {
                mSlideToTop.get(i).setY(currentPos.getSlideToTop()[i]);
            }

            mFocusedViewContainer.setY(currentPos.getFocusY());

            for (int i = 0; i < mFadedOutToBottomViews.size(); i++) {
                mFadedOutToBottomViews.get(i).setY(currentPos.getNextContainersY()[i]);
                mFadedOutToBottomViews.get(i).setAlpha(currentPos.getNextContainersAlpha());
            }

            if (mStickyTo != null)
                mStickyTo.setY(currentPos.getStickToY());

            mEditModeViewGroup.setY(currentPos.getEditY());
            mEditModeViewGroup.setAlpha(currentPos.getEditAlpha());
            mEditModeViewGroup.setVisibility(View.VISIBLE);
        } else {
            mEditModeViewGroup.setY(currentPos.getEditY());
            mEditModeViewGroup.setAlpha(currentPos.getEditAlpha());

            if (mStickyTo != null)
                mStickyTo.setY(currentPos.getStickToY());

            for (int i = 0; i < mFadedOutToBottomViews.size(); i++) {
                mFadedOutToBottomViews.get(i).setY(currentPos.getNextContainersY()[i]);
                mFadedOutToBottomViews.get(i).setAlpha(currentPos.getNextContainersAlpha());
            }

            mFocusedViewContainer.setY(currentPos.getFocusY());

            for (int i = 0; i < mSlideToTop.size(); i++) {
                mSlideToTop.get(i).setY(currentPos.getSlideToTop()[i]);
            }
        }
    }
}
