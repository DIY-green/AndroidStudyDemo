package com.cheng.animationstudy.customview.zrclistview;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.LongSparseArray;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.HapticFeedbackConstants;
import android.view.InputDevice;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.animation.Interpolator;
import android.widget.ListAdapter;
import android.widget.Scroller;

import com.cheng.animationstudy.R;

public abstract class ZrcAbsListView extends ZrcAdapterView<ListAdapter>
        implements ViewTreeObserver.OnTouchModeChangeListener {
    public static final int TRANSCRIPT_MODE_DISABLED = 0;
    public static final int TRANSCRIPT_MODE_NORMAL = 1;
    public static final int TRANSCRIPT_MODE_ALWAYS_SCROLL = 2;
    public static final int[] NOTHING = new int[]{0};
    static final int TOUCH_MODE_INVALID = -2;
    static final int TOUCH_MODE_REST = -1;
    int mTouchMode = TOUCH_MODE_REST;
    static final int TOUCH_MODE_DOWN = 0;
    static final int TOUCH_MODE_TAP = 1;
    static final int TOUCH_MODE_DONE_WAITING = 2;
    static final int TOUCH_MODE_SCROLL = 3;
    static final int TOUCH_MODE_FLING = 4;
    static final int TOUCH_MODE_RESCROLL = 5;
    static final int LAYOUT_NORMAL = 0;
    int mLayoutMode = LAYOUT_NORMAL;
    static final int LAYOUT_FORCE_TOP = 1;
    static final int LAYOUT_FORCE_BOTTOM = 3;
    private static final int TOUCH_MODE_UNKNOWN = -1;
    private int mLastTouchMode = TOUCH_MODE_UNKNOWN;
    private static final int TOUCH_MODE_ON = 0;
    private static final int TOUCH_MODE_OFF = 1;
    private static final int INVALID_POINTER = -1;
    private int mActivePointerId = INVALID_POINTER;
    final RecycleBin mRecycler = new RecycleBin();
    final boolean[] mIsScrap = new boolean[1];
    protected boolean mIsAttached;
    protected boolean showHeader = false;
    protected int mFirstTopOffset = 0;
    protected int mLastBottomOffset = 0;
    protected float mDensity = 0;
    AdapterDataSetObserver mDataSetObserver;
    ListAdapter mAdapter;
    boolean mAdapterHasStableIds;
    boolean mDrawSelectorOnTop = false;
    Drawable mSelector;
    int mSelectorPosition = INVALID_POSITION;
    Rect mSelectorRect = new Rect();
    Rect mListPadding = new Rect();
    int mWidthMeasureSpec = 0;
    View mScrollUp;
    View mScrollDown;
    boolean mCachingStarted;
    boolean mCachingActive;
    boolean mScrollingCacheEnabled;
    int mMotionPosition;
    int mMotionX;
    int mMotionY;
    int mLastY;
    int mMotionCorrection;
    Runnable mPositionScrollAfterLayout;
    private Runnable mClearScrollingCache;
    private VelocityTracker mVelocityTracker;
    private FlingRunnable mFlingRunnable;
    private ZrcListView.OnScrollListener mOnScrollListener;
    private boolean mSmoothScrollbarEnabled = true;
    private Rect mTouchFrame;
    private PerformClick mPerformClick;
    private Runnable mPendingCheckForTap;
    private Runnable mTouchModeReset;
    private int mTranscriptMode;
    private int mCacheColorHint;
    private boolean mIsChildViewEnabled;
    private int mLastScrollState = ZrcListView.OnScrollListener.SCROLL_STATE_IDLE;
    private int mTouchSlop;
    private int mMinimumVelocity;
    private int mMaximumVelocity;
    private float mVelocityScale = 1.0f;
    private Headable mZrcHeader;
    private Footable mZrcFooter;
    private ZrcListView.OnStartListener onRefreshStart;
    private ZrcListView.OnStartListener onLoadMoreStart;
    private ZrcListView.OnScrollStateListener onScrollStateListener;
    private int mScrollState = ZrcListView.OnScrollStateListener.EDGE;
    private boolean isLoadingMore = false;
    private boolean isLoadMoreOn = false;
    private boolean isRefreshing = false;
    private Runnable mResetRunnable = new Runnable() {
        @Override
        public void run() {
            mZrcHeader.stateChange(Headable.STATE_REST, null);
        }
    };
    private float mVerticalScrollFactor;

    public ZrcAbsListView(Context context) {
        super(context);
        initAbsListView();
    }

    public ZrcAbsListView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.absListViewStyle);
    }

    public ZrcAbsListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAbsListView();
        TypedArray a =
                context.obtainStyledAttributes(attrs, R.styleable.ZrcAbsListView, defStyle, 0);
        Drawable d = a.getDrawable(R.styleable.ZrcAbsListView_android_listSelector);
        if (d != null) {
            setSelector(d);
        }
        mDrawSelectorOnTop =
                a.getBoolean(R.styleable.ZrcAbsListView_android_drawSelectorOnTop, false);
        boolean scrollingCacheEnabled =
                a.getBoolean(R.styleable.ZrcAbsListView_android_scrollingCache, true);
        setScrollingCacheEnabled(scrollingCacheEnabled);
        int transcriptMode = a.getInt(R.styleable.ZrcAbsListView_android_transcriptMode,
                TRANSCRIPT_MODE_DISABLED);
        setTranscriptMode(transcriptMode);
        int color = a.getColor(R.styleable.ZrcAbsListView_android_cacheColorHint, 0);
        setCacheColorHint(color);
        boolean smoothScrollbar =
                a.getBoolean(R.styleable.ZrcAbsListView_android_smoothScrollbar, true);
        setSmoothScrollbarEnabled(smoothScrollbar);
        a.recycle();
    }

    static int getDistance(Rect source, Rect dest, int direction) {
        int sX, sY;
        int dX, dY;
        switch (direction) {
        case View.FOCUS_RIGHT:
            sX = source.right;
            sY = source.top + source.height() / 2;
            dX = dest.left;
            dY = dest.top + dest.height() / 2;
            break;
        case View.FOCUS_DOWN:
            sX = source.left + source.width() / 2;
            sY = source.bottom;
            dX = dest.left + dest.width() / 2;
            dY = dest.top;
            break;
        case View.FOCUS_LEFT:
            sX = source.left;
            sY = source.top + source.height() / 2;
            dX = dest.right;
            dY = dest.top + dest.height() / 2;
            break;
        case View.FOCUS_UP:
            sX = source.left + source.width() / 2;
            sY = source.top;
            dX = dest.left + dest.width() / 2;
            dY = dest.bottom;
            break;
        case View.FOCUS_FORWARD:
        case View.FOCUS_BACKWARD:
            sX = source.right + source.width() / 2;
            sY = source.top + source.height() / 2;
            dX = dest.left + dest.width() / 2;
            dY = dest.top + dest.height() / 2;
            break;
        default:
            throw new IllegalArgumentException("direction must be one of "
                    + "{FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT, " +
                    "FOCUS_FORWARD, FOCUS_BACKWARD}.");
        }
        int deltaX = dX - sX;
        int deltaY = dY - sY;
        return deltaY * deltaY + deltaX * deltaX;
    }

    static View retrieveFromScrap(ArrayList<View> scrapViews, int position) {
        int size = scrapViews.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                View view = scrapViews.get(i);
                if (((LayoutParams) view.getLayoutParams()).scrappedFromPosition == position) {
                    scrapViews.remove(i);
                    return view;
                }
            }
            return scrapViews.remove(size - 1);
        } else {
            return null;
        }
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private void initAbsListView() {
        setClickable(true);
        setFocusableInTouchMode(true);
        setWillNotDraw(false);
        setAlwaysDrawnWithCacheEnabled(false);
        setScrollingCacheEnabled(true);
        final ViewConfiguration configuration = ViewConfiguration.get(getContext());
        mTouchSlop = configuration.getScaledTouchSlop();
        mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
        mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
        mDensity = getResources().getDisplayMetrics().density;
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        if (adapter != null) {
            mAdapterHasStableIds = mAdapter.hasStableIds();
        }
    }

    @Override
    public boolean performItemClick(View view, int position, long id) {
        boolean handled = false;
        boolean dispatchItemClick = true;
        if (dispatchItemClick) {
            handled |= super.performItemClick(view, position, id);
        }
        return handled;
    }

    @ViewDebug.ExportedProperty
    public boolean isSmoothScrollbarEnabled() {
        return mSmoothScrollbarEnabled;
    }

    public void setSmoothScrollbarEnabled(boolean enabled) {
        mSmoothScrollbarEnabled = enabled;
    }

    public void setOnScrollListener(ZrcListView.OnScrollListener l) {
        mOnScrollListener = l;
        invokeOnItemScrollListener();
    }

    void invokeOnItemScrollListener() {
        if (mOnScrollListener != null) {
            mOnScrollListener.onScroll(this, mFirstPosition, getChildCount(), mItemCount);
        }
        onScrollChanged(0, 0, 0, 0);
    }

    @ViewDebug.ExportedProperty
    public boolean isScrollingCacheEnabled() {
        return mScrollingCacheEnabled;
    }

    public void setScrollingCacheEnabled(boolean enabled) {
        if (mScrollingCacheEnabled && !enabled) {
            clearScrollingCache();
        }
        mScrollingCacheEnabled = enabled;
    }

    @Override
    public void getFocusedRect(Rect r) {
        super.getFocusedRect(r);
    }

    private void useDefaultSelector() {
        setSelector(getResources().getDrawable(android.R.drawable.list_selector_background));
    }

    void requestLayoutIfNecessary() {
        if (getChildCount() > 0) {
            resetList();
            requestLayout();
            invalidate();
        }
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        if (gainFocus && !isInTouchMode()) {
            if (!mIsAttached && mAdapter != null) {
                mDataChanged = true;
                mOldItemCount = mItemCount;
                mItemCount = mAdapter.getCount();
            }
        }
    }

    @Override
    public void requestLayout() {
        if (!mBlockLayoutRequests && !mInLayout) {
            super.requestLayout();
        }
    }

    void resetList() {
        removeAllViewsInLayout();
        mFirstPosition = 0;
        mFirstTop = mFirstTopOffset + mListPadding.top;
        mDataChanged = false;
        mPositionScrollAfterLayout = null;
        mSelectorPosition = INVALID_POSITION;
        mSelectorRect.setEmpty();
        invalidate();
    }

    @Override
    protected int computeVerticalScrollExtent() {
        final int count = getChildCount();
        if (count > 0) {
            if (mSmoothScrollbarEnabled) {
                final int range = (getHeight() - mFirstTopOffset - mLastBottomOffset) * 10;
                final int cnt = mItemCount;
                int extent = count * range / cnt;
                View view = getChildAt(0);
                final int top = view.getTop();
                int height = view.getHeight();
                if (height > 0 && top < 0) {
                    extent += (top * range) / height / cnt;
                }
                view = getChildAt(count - 1);
                final int bottom = view.getBottom();
                height = view.getHeight();
                if (height > 0 && bottom > getHeight()) {
                    extent -= ((bottom - getHeight()) * range) / height / cnt;
                }
                return extent;
            } else {
                return 1;
            }
        }
        return 0;
    }

    @Override
    protected int computeVerticalScrollOffset() {
        final int firstPosition = mFirstPosition;
        final int childCount = getChildCount();
        final int mScrollY = getScrollY();
        if (firstPosition >= 0 && childCount > 0) {
            if (mSmoothScrollbarEnabled) {
                final View view = getChildAt(0);
                final int top = view.getTop() - mFirstTopOffset;
                int height = view.getHeight();
                final int range = (getHeight() - mFirstTopOffset - mLastBottomOffset) * 10;
                final int count = mItemCount;
                if (height > 0) {
                    return Math.max((firstPosition * range - top * range / height) / count +
                            mFirstTopOffset * 10
                            + mScrollY * 10, mFirstTopOffset * 10);
                }
            } else {
                int index;
                final int count = mItemCount;
                if (firstPosition == 0) {
                    index = 0;
                } else if (firstPosition + childCount == count) {
                    index = count;
                } else {
                    index = firstPosition + childCount / 2;
                }
                return (int) (firstPosition + childCount * (index / (float) count));
            }
        }
        return 0;
    }

    @Override
    protected int computeVerticalScrollRange() {
        int result;
        if (mSmoothScrollbarEnabled) {
            result = Math.max(getHeight() * 10, 0);
        } else {
            result = mItemCount;
        }
        return result;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mSelector == null) {
            useDefaultSelector();
        }
        final Rect listPadding = mListPadding;
        listPadding.left = getPaddingLeft();
        listPadding.top = getPaddingTop();
        listPadding.right = getPaddingRight();
        listPadding.bottom = getPaddingBottom();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mInLayout = true;
        if (changed) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                getChildAt(i).forceLayout();
            }
            mRecycler.markChildrenDirty();
        }
        layoutChildren();
        mInLayout = false;
    }

    protected void layoutChildren() {
    }

    void updateScrollIndicators() {
        if (mScrollUp != null) {
            boolean canScrollUp;
            canScrollUp = mFirstPosition > 0;
            if (!canScrollUp) {
                if (getChildCount() > 0) {
                    View child = getChildAt(0);
                    canScrollUp = child.getTop() < mListPadding.top;
                }
            }
            mScrollUp.setVisibility(canScrollUp ? View.VISIBLE : View.INVISIBLE);
        }
        if (mScrollDown != null) {
            boolean canScrollDown;
            int count = getChildCount();
            canScrollDown = (mFirstPosition + count) < mItemCount;
            if (!canScrollDown && count > 0) {
                View child = getChildAt(count - 1);
                canScrollDown = child.getBottom() > getBottom() - mListPadding.bottom;
            }
            mScrollDown.setVisibility(canScrollDown ? View.VISIBLE : View.INVISIBLE);
        }
    }

    public int getListPaddingTop() {
        return mListPadding.top;
    }

    public int getListPaddingBottom() {
        return mListPadding.bottom;
    }

    public int getListPaddingLeft() {
        return mListPadding.left;
    }

    public int getListPaddingRight() {
        return mListPadding.right;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    View obtainView(int position, boolean[] isScrap) {
        isScrap[0] = false;
        View scrapView;
        scrapView = mRecycler.getTransientStateView(position);
        if (scrapView == null) {
            scrapView = mRecycler.getScrapView(position);
        }
        View child;
        if (scrapView != null) {
            child = mAdapter.getView(position, scrapView, this);
            if (child != scrapView) {
                mRecycler.addScrapView(scrapView, position);
                if (mCacheColorHint != 0) {
                    child.setDrawingCacheBackgroundColor(mCacheColorHint);
                }
            } else {
                isScrap[0] = true;
            }
        } else {
            child = mAdapter.getView(position, null, this);
            if (mCacheColorHint != 0) {
                child.setDrawingCacheBackgroundColor(mCacheColorHint);
            }
        }
        if (mAdapterHasStableIds) {
            final ViewGroup.LayoutParams vlp = child.getLayoutParams();
            LayoutParams lp;
            if (vlp == null) {
                lp = (LayoutParams) generateDefaultLayoutParams();
            } else if (!checkLayoutParams(vlp)) {
                lp = (LayoutParams) generateLayoutParams(vlp);
            } else {
                lp = (LayoutParams) vlp;
            }
            lp.itemId = mAdapter.getItemId(position);
            child.setLayoutParams(lp);
        }
        return child;
    }

    void positionSelector(int position, View sel) {
        if (position != INVALID_POSITION) {
            mSelectorPosition = position;
        }
        final Rect selectorRect = mSelectorRect;
        invalidate(selectorRect);
        selectorRect.set(sel.getLeft(), sel.getTop(), sel.getRight(), sel.getBottom());
        invalidate(selectorRect);
        final boolean isChildViewEnabled = mIsChildViewEnabled;
        if (sel.isEnabled() != isChildViewEnabled) {
            mIsChildViewEnabled = !isChildViewEnabled;
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        final Headable zrcHeader = mZrcHeader;
        final int childCount = getChildCount();
        if (zrcHeader != null) {
            final int firstPosition = mFirstPosition;
            final int firstTop = childCount == 0 ? mFirstTop : getChildAt(0).getTop();
            final Rect listPadding = mListPadding;
            final int viewTop = listPadding.top;
            final boolean needShowHeader = (firstPosition == 0 && firstTop >= viewTop);
            if (needShowHeader) {
                boolean more = zrcHeader
                        .draw(canvas, listPadding.left, viewTop + mFirstTopOffset, getWidth()
                                - listPadding.right, firstTop);

                if (more) {
                    postInvalidate(listPadding.left, listPadding.top,
                            getWidth() - listPadding.right, firstTop);
                }
            }
        }
        final Footable footer = mZrcFooter;
        if (childCount > 0 && (isRefreshing || isLoadingMore) && footer!=null) {
            final int firstPosition = mFirstPosition;

            if (firstPosition + childCount == mItemCount) {
                final int lastBottom = getChildAt(childCount - 1).getBottom();
                final Rect listPadding = mListPadding;
                final boolean isTooShort = childCount == mItemCount
                        && lastBottom - mFirstTop - mFirstTopOffset < getHeight();
                final int viewBottom = getHeight() - listPadding.bottom;
                if (lastBottom < viewBottom) {
                    boolean more = footer.draw(canvas, listPadding.left, lastBottom,
                            getWidth() - listPadding.right,
                            isTooShort ? lastBottom + footer.getHeight() :
                                    viewBottom - mLastBottomOffset);
                    if (more) {
                        postInvalidate(listPadding.left, listPadding.top,
                                getWidth() - listPadding.right, viewBottom
                                - mLastBottomOffset);
                    }
                }
            }
        }
        final boolean drawSelectorOnTop = mDrawSelectorOnTop;
        if (!drawSelectorOnTop) {
            drawSelector(canvas);
        }

        super.dispatchDraw(canvas);
        if (drawSelectorOnTop) {
            drawSelector(canvas);
        }
    }

    @Override
    protected int getLeftPaddingOffset() {
        return getPaddingLeft();
    }

    @Override
    protected int getTopPaddingOffset() {
        return getPaddingTop();
    }

    @Override
    protected int getRightPaddingOffset() {
        return getPaddingRight();
    }

    @Override
    protected int getBottomPaddingOffset() {
        return getPaddingBottom();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (getChildCount() > 0) {
            mDataChanged = true;
        }
    }

    boolean touchModeDrawsInPressedState() {
        switch (mTouchMode) {
        case TOUCH_MODE_TAP:
        case TOUCH_MODE_DONE_WAITING:
            return true;
        default:
            return false;
        }
    }

    boolean shouldShowSelector() {
        return (!isInTouchMode()) || (touchModeDrawsInPressedState() && isPressed());
    }

    private void drawSelector(Canvas canvas) {
        if (!mSelectorRect.isEmpty()) {
            final Drawable selector = mSelector;
            selector.setBounds(mSelectorRect);
            selector.draw(canvas);
        }
    }

    public void setDrawSelectorOnTop(boolean onTop) {
        mDrawSelectorOnTop = onTop;
    }

    public void setSelector(int resID) {
        setSelector(getResources().getDrawable(resID));
    }

    public Drawable getSelector() {
        return mSelector;
    }

    public void setSelector(Drawable sel) {
        if (mSelector != null) {
            mSelector.setCallback(null);
            unscheduleDrawable(mSelector);
        }
        mSelector = sel;
        sel.setCallback(this);
        updateSelectorState();
    }

    public void setScrollIndicators(View up, View down) {
        mScrollUp = up;
        mScrollDown = down;
    }

    void updateSelectorState() {
        if (mSelector != null) {
            if (shouldShowSelector()) {
                mSelector.setState(getDrawableState());
            } else {
                mSelector.setState(NOTHING);
            }
        }
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        updateSelectorState();
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        if (mIsChildViewEnabled) {
            return super.onCreateDrawableState(extraSpace);
        }
        final int enabledState = ENABLED_STATE_SET[0];
        int[] state = super.onCreateDrawableState(extraSpace + 1);
        int enabledPos = -1;
        for (int i = state.length - 1; i >= 0; i--) {
            if (state[i] == enabledState) {
                enabledPos = i;
                break;
            }
        }
        if (enabledPos >= 0) {
            System.arraycopy(state, enabledPos + 1, state, enabledPos,
                    state.length - enabledPos - 1);
        }
        return state;
    }

    @Override
    public boolean verifyDrawable(Drawable dr) {
        return mSelector == dr || super.verifyDrawable(dr);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if ((Build.VERSION.SDK_INT >= 11) && mSelector != null) {
            mSelector.jumpToCurrentState();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mAdapter != null && mDataSetObserver == null) {
            mDataSetObserver = new AdapterDataSetObserver();
            mAdapter.registerDataSetObserver(mDataSetObserver);
            mDataChanged = true;
            mOldItemCount = mItemCount;
            mItemCount = mAdapter.getCount();
        }
        setPressed(false);
        mIsAttached = true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mRecycler.clear();
        if (mAdapter != null && mDataSetObserver != null) {
            mAdapter.unregisterDataSetObserver(mDataSetObserver);
            mDataSetObserver = null;
        }
        if (mClearScrollingCache != null) {
            removeCallbacks(mClearScrollingCache);
        }
        if (mPerformClick != null) {
            removeCallbacks(mPerformClick);
        }
        if (mTouchModeReset != null) {
            removeCallbacks(mTouchModeReset);
            mTouchModeReset.run();
        }
        mIsAttached = false;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        final int touchMode = isInTouchMode() ? TOUCH_MODE_ON : TOUCH_MODE_OFF;
        if (!hasWindowFocus) {
            setChildrenDrawingCacheEnabled(false);
        } else {
            if (touchMode != mLastTouchMode && mLastTouchMode != TOUCH_MODE_UNKNOWN) {
                if (touchMode == TOUCH_MODE_OFF) {
                } else {
                    mLayoutMode = LAYOUT_NORMAL;
                    layoutChildren();
                }
            }
        }
        mLastTouchMode = touchMode;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onCancelPendingInputEvents() {
        super.onCancelPendingInputEvents();
        if (mPerformClick != null) {
            removeCallbacks(mPerformClick);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    boolean performLongPress(final View child, final int longPressPosition,
            final long longPressId) {
        boolean handled = false;
        if (mOnItemLongClickListener != null) {
            handled = mOnItemLongClickListener
                    .onItemLongClick((ZrcListView) this, child, longPressPosition,
                            longPressId);
        }
        if (handled) {
            performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
        }
        return handled;
    }

    @Override
    protected void dispatchSetPressed(boolean pressed) {
    }

    public int pointToPosition(int x, int y) {
        Rect frame = mTouchFrame;
        if (frame == null) {
            mTouchFrame = new Rect();
            frame = mTouchFrame;
        }
        final int count = getChildCount();
        for (int i = count - 1; i >= 0; i--) {
            final View child = getChildAt(i);
            if (child.getVisibility() == View.VISIBLE) {
                child.getHitRect(frame);
                if (frame.contains(x, y)) {
                    return mFirstPosition + i;
                }
            }
        }
        return INVALID_POSITION;
    }

    public long pointToRowId(int x, int y) {
        int position = pointToPosition(x, y);
        if (position >= 0) {
            return mAdapter.getItemId(position);
        }
        return INVALID_ROW_ID;
    }

    private boolean startScrollIfNeeded(int x, int y) {
        final int deltaX = x - mMotionX;
        final int distanceX = Math.abs(deltaX);
        final int deltaY = y - mMotionY;
        final int distanceY = Math.abs(deltaY);
        if (distanceX > mTouchSlop || distanceY > mTouchSlop) {
            if (distanceY > distanceX * 2) {
                createScrollingCache();
                mTouchMode = TOUCH_MODE_SCROLL;
                mMotionCorrection = deltaY > 0 ? mTouchSlop : -mTouchSlop;
                setPressed(false);
                if (getChildCount() > 0) {
                    final View motionView = getChildAt(mMotionPosition - mFirstPosition);
                    if (motionView != null) {
                        motionView.setPressed(false);
                    }
                }
                mSelectorPosition = INVALID_POSITION;
                reportScrollStateChange(ZrcListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL);
                final ViewParent parent = getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
                scrollIfNeeded(x, y);
                return true;
            } else {
                mTouchMode = TOUCH_MODE_INVALID;
            }
        }
        return false;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void scrollIfNeeded(int x, int y) {
        final int rawDeltaY = y - mMotionY;
        final int deltaY = rawDeltaY - mMotionCorrection;
        int incrementalDeltaY = mLastY != Integer.MIN_VALUE ? y - mLastY : deltaY;
        if (mTouchMode == TOUCH_MODE_SCROLL) {
            if (y != mLastY) {
                if (Math.abs(rawDeltaY) > mTouchSlop) {
                    final ViewParent parent = getParent();
                    if (parent != null) {
                        parent.requestDisallowInterceptTouchEvent(true);
                    }
                }
                boolean atEdge = false;
                if (incrementalDeltaY != 0) {
                    atEdge = trackMotionScroll(deltaY, incrementalDeltaY);
                }
                if (atEdge) {
                    if (mVelocityTracker != null) {
                        mVelocityTracker.clear();
                    }
                }
                mMotionX = x;
                mMotionY = y;
                mLastY = y;
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onTouchModeChanged(boolean isInTouchMode) {
        if (isInTouchMode) {
            if (getHeight() > 0 && getChildCount() > 0) {
                layoutChildren();
            }
            updateSelectorState();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            if (!isEnabled()) {
                return isClickable() || isLongClickable();
            }
            if (!mIsAttached) {
                return false;
            }
            initVelocityTrackerIfNotExists();
            mVelocityTracker.addMovement(ev);
            final int actionMasked = ev.getActionMasked();
            switch (actionMasked) {
            case MotionEvent.ACTION_DOWN: {
                onTouchDown(ev);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                onTouchMove(ev);
                break;
            }
            case MotionEvent.ACTION_UP: {
                onTouchUp(ev);
                break;
            }
            case MotionEvent.ACTION_CANCEL: {
                onTouchCancel();
                break;
            }
            case MotionEvent.ACTION_POINTER_UP: {
                onSecondaryPointerUp(ev);
                final int x = mMotionX;
                final int y = mMotionY;
                final int motionPosition = pointToPosition(x, y);
                if (motionPosition >= 0) {
                    mMotionPosition = motionPosition;
                }
                mLastY = y;
                break;
            }
            case MotionEvent.ACTION_POINTER_DOWN: {
                final int index = ev.getActionIndex();
                final int id = ev.getPointerId(index);
                final int x = (int) ev.getX(index);
                final int y = (int) ev.getY(index);
                mMotionCorrection = 0;
                mActivePointerId = id;
                mMotionX = x;
                mMotionY = y;
                final int motionPosition = pointToPosition(x, y);
                if (motionPosition >= 0) {
                    mMotionPosition = motionPosition;
                }
                mLastY = y;
                break;
            }
            }
            return true;
        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }
    }

    private void onTouchDown(MotionEvent ev) {
        mActivePointerId = ev.getPointerId(0);
        final int x = (int) ev.getX();
        final int y = (int) ev.getY();
        int motionPosition = pointToPosition(x, y);
        if (!mDataChanged) {
            if (mTouchMode == TOUCH_MODE_FLING || mTouchMode == TOUCH_MODE_RESCROLL) {
                createScrollingCache();
                mFlingRunnable.mScroller.abortAnimation();
                mTouchMode = TOUCH_MODE_SCROLL;
                mMotionCorrection = 0;
                motionPosition = findMotionRow(y);
            } else if ((motionPosition >= 0) && getAdapter().isEnabled(motionPosition)) {
                mTouchMode = TOUCH_MODE_DOWN;
                if (mPendingCheckForTap == null) {
                    mPendingCheckForTap = new CheckForTap();
                }
                postDelayed(mPendingCheckForTap, ViewConfiguration.getTapTimeout());
            } else {
                createScrollingCache();
                mTouchMode = TOUCH_MODE_SCROLL;
                mMotionCorrection = 0;
                motionPosition = findMotionRow(y);
            }
        }
        mMotionX = x;
        mMotionY = y;
        mMotionPosition = motionPosition;
        mLastY = Integer.MIN_VALUE;
    }

    private void onTouchMove(MotionEvent ev) {
        if (mTouchMode == TOUCH_MODE_INVALID) {
            mTouchMode = TOUCH_MODE_SCROLL;
        }
        int pointerIndex = ev.findPointerIndex(mActivePointerId);
        if (pointerIndex == -1) {
            pointerIndex = 0;
            mActivePointerId = ev.getPointerId(pointerIndex);
        }
        if (mDataChanged) {
            layoutChildren();
        }
        final int x = (int) ev.getX(pointerIndex);
        final int y = (int) ev.getY(pointerIndex);
        switch (mTouchMode) {
        case TOUCH_MODE_DOWN:
        case TOUCH_MODE_TAP:
        case TOUCH_MODE_DONE_WAITING:
            startScrollIfNeeded(x, y);
            break;
        case TOUCH_MODE_SCROLL:
            scrollIfNeeded(x, y);
            break;
        }
    }

    private void onTouchUp(MotionEvent ev) {
        switch (mTouchMode) {
        case TOUCH_MODE_DOWN:
        case TOUCH_MODE_TAP:
        case TOUCH_MODE_DONE_WAITING:
            final int motionPosition = mMotionPosition;
            final View child =
                    getChildCount() == 0 ? null : getChildAt(motionPosition - mFirstPosition);
            if (child != null) {
                if (mTouchMode != TOUCH_MODE_DOWN) {
                    child.setPressed(false);
                }
                final float x = ev.getX();
                final boolean inList = x > mListPadding.left && x < getWidth() - mListPadding.right;
                if (inList && !child.hasFocusable()) {
                    if (mPerformClick == null) {
                        mPerformClick = new PerformClick();
                    }
                    final PerformClick performClick = mPerformClick;
                    performClick.mClickMotionPosition = motionPosition;
                    performClick.rememberWindowAttachCount();
                    if (mTouchMode == TOUCH_MODE_DOWN || mTouchMode == TOUCH_MODE_TAP) {
                        mLayoutMode = LAYOUT_NORMAL;
                        if (!mDataChanged && mAdapter.isEnabled(motionPosition)) {
                            mTouchMode = TOUCH_MODE_TAP;
                            child.setPressed(true);
                            positionSelector(mMotionPosition, child);
                            setPressed(true);
                            if (mSelector != null) {
                                Drawable d = mSelector.getCurrent();
                                if (d != null && d instanceof TransitionDrawable) {
                                    ((TransitionDrawable) d).resetTransition();
                                }
                            }
                            if (mTouchModeReset != null) {
                                removeCallbacks(mTouchModeReset);
                            }
                            mTouchModeReset = new Runnable() {
                                @Override
                                public void run() {
                                    mTouchModeReset = null;
                                    mTouchMode = TOUCH_MODE_REST;
                                    child.setPressed(false);
                                    setPressed(false);
                                    invalidate();
                                    if (!mDataChanged && mIsAttached) {
                                        performClick.run();
                                    }
                                }
                            };
                            postDelayed(mTouchModeReset,
                                    ViewConfiguration.getPressedStateDuration());
                        } else {
                            mTouchMode = TOUCH_MODE_REST;
                            updateSelectorState();
                        }
                        return;
                    } else if (!mDataChanged && mAdapter.isEnabled(motionPosition)) {
                        performClick.run();
                    }
                }
            }
            mTouchMode = TOUCH_MODE_REST;
            updateSelectorState();
            break;
        case TOUCH_MODE_SCROLL:
            if (mFlingRunnable == null) {
                mFlingRunnable = new FlingRunnable();
            }
            if (!mFlingRunnable.scrollToAdjustViewsUpOrDown()) {
                final VelocityTracker velocityTracker = mVelocityTracker;
                velocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
                final int initialVelocity =
                        (int) (velocityTracker.getYVelocity(mActivePointerId) * mVelocityScale);
                if (Math.abs(initialVelocity) > mMinimumVelocity) {
                    if (mFlingRunnable == null) {
                        mFlingRunnable = new FlingRunnable();
                    }
                    reportScrollStateChange(ZrcListView.OnScrollListener.SCROLL_STATE_FLING);
                    mFlingRunnable.start(-initialVelocity);
                } else {
                    mTouchMode = TOUCH_MODE_REST;
                    reportScrollStateChange(ZrcListView.OnScrollListener.SCROLL_STATE_IDLE);
                    if (mFlingRunnable != null) {
                        mFlingRunnable.endFling();
                    }
                }
            }
            break;
        }
        setPressed(false);
        invalidate();
        recycleVelocityTracker();
        mActivePointerId = INVALID_POINTER;
    }

    private void onTouchCancel() {
        mTouchMode = TOUCH_MODE_REST;
        setPressed(false);
        final View motionView =
                getChildCount() == 0 ? null : this.getChildAt(mMotionPosition - mFirstPosition);
        if (motionView != null) {
            motionView.setPressed(false);
        }
        clearScrollingCache();
        recycleVelocityTracker();
        mSelectorPosition = INVALID_POSITION;
        invalidate();
        mActivePointerId = INVALID_POINTER;
        scrollToAdjustViewsUpOrDown();
    }

    protected void invalidateParentIfNeeded() {
        if (getParent() instanceof View) {
            ((View) getParent()).invalidate();
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        if (Build.VERSION.SDK_INT >= 12) {
            if ((event.getSource() & InputDevice.SOURCE_CLASS_POINTER) != 0) {
                switch (event.getAction()) {
                case MotionEvent.ACTION_SCROLL: {
                    if (mTouchMode == TOUCH_MODE_REST) {
                        final float vscroll = event.getAxisValue(MotionEvent.AXIS_VSCROLL);
                        if (vscroll != 0) {
                            final int delta = (int) (vscroll * getVerticalScrollFactor());
                            if (!trackMotionScroll(delta, delta)) {
                                return true;
                            }
                        }
                    }
                }
                }
            }
        }
        return super.onGenericMotionEvent(event);
    }

    protected float getVerticalScrollFactor() {
        if (mVerticalScrollFactor == 0) {
            mVerticalScrollFactor = 64 * getResources().getDisplayMetrics().density;
        }
        return mVerticalScrollFactor;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void setScrollY(int y) {
        if (Build.VERSION.SDK_INT >= 14) {
            super.setScrollY(y);
        } else {
            scrollTo(getScrollX(), y);
        }
    }

    private void initOrResetVelocityTracker() {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        } else {
            mVelocityTracker.clear();
        }
    }

    private void initVelocityTrackerIfNotExists() {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
    }

    private void recycleVelocityTracker() {
        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        if (disallowIntercept) {
            recycleVelocityTracker();
        }
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (!mIsAttached) {
            return false;
        }
        switch (action & MotionEvent.ACTION_MASK) {
        case MotionEvent.ACTION_DOWN: {
            int touchMode = mTouchMode;
            final int x = (int) ev.getX();
            final int y = (int) ev.getY();
            mActivePointerId = ev.getPointerId(0);
            int motionPosition = findMotionRow(y);
            if (touchMode != TOUCH_MODE_FLING && touchMode != TOUCH_MODE_RESCROLL &&
                    motionPosition >= 0) {
                mMotionPosition = motionPosition;
                mTouchMode = TOUCH_MODE_DOWN;
                clearScrollingCache();
            }
            mMotionX = x;
            mMotionY = y;
            mLastY = y;
            initOrResetVelocityTracker();
            mVelocityTracker.addMovement(ev);
            if (touchMode == TOUCH_MODE_FLING || touchMode == TOUCH_MODE_RESCROLL) {
                return true;
            }
            break;
        }
        case MotionEvent.ACTION_MOVE: {
            if (mTouchMode == TOUCH_MODE_INVALID) {
                return false;
            }
            switch (mTouchMode) {
            case TOUCH_MODE_DOWN:
                int pointerIndex = ev.findPointerIndex(mActivePointerId);
                if (pointerIndex == -1) {
                    pointerIndex = 0;
                    mActivePointerId = ev.getPointerId(pointerIndex);
                }
                final int x = (int) ev.getX(pointerIndex);
                final int y = (int) ev.getY(pointerIndex);
                initVelocityTrackerIfNotExists();
                mVelocityTracker.addMovement(ev);
                if (startScrollIfNeeded(x, y)) {
                    return true;
                }
                break;
            }
            break;
        }
        case MotionEvent.ACTION_CANCEL:
        case MotionEvent.ACTION_UP: {
            mTouchMode = TOUCH_MODE_REST;
            mActivePointerId = INVALID_POINTER;
            recycleVelocityTracker();
            reportScrollStateChange(ZrcListView.OnScrollListener.SCROLL_STATE_IDLE);
            break;
        }
        case MotionEvent.ACTION_POINTER_UP: {
            onSecondaryPointerUp(ev);
            break;
        }
        }
        return false;
    }

    private void onSecondaryPointerUp(MotionEvent ev) {
        final int pointerIndex = (ev.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >>
                MotionEvent.ACTION_POINTER_INDEX_SHIFT;
        final int pointerId = ev.getPointerId(pointerIndex);
        if (pointerId == mActivePointerId) {
            final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
            mMotionX = (int) ev.getX(newPointerIndex);
            mMotionY = (int) ev.getY(newPointerIndex);
            mMotionCorrection = 0;
            mActivePointerId = ev.getPointerId(newPointerIndex);
        }
    }

    @Override
    public void addTouchables(ArrayList<View> views) {
        final int count = getChildCount();
        final int firstPosition = mFirstPosition;
        final ListAdapter adapter = mAdapter;
        if (adapter == null) {
            return;
        }
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (adapter.isEnabled(firstPosition + i)) {
                views.add(child);
            }
            child.addTouchables(views);
        }
    }

    void reportScrollStateChange(int newState) {
        if (newState != mLastScrollState) {
            if (mOnScrollListener != null) {
                mLastScrollState = newState;
                mOnScrollListener.onScrollStateChanged(this, newState);
            }
        }
    }

    protected void scrollToAdjustViewsUpOrDown() {
        if (mFlingRunnable != null) {
            mFlingRunnable.scrollToAdjustViewsUpOrDown();
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void setFriction(float friction) {
        if (mFlingRunnable == null) {
            mFlingRunnable = new FlingRunnable();
        }
        mFlingRunnable.mScroller.setFriction(friction);
    }

    public void setVelocityScale(float scale) {
        mVelocityScale = scale;
    }

    public void smoothScrollBy(int distance, int duration) {
        smoothScrollBy(distance, duration, false);
    }

    void smoothScrollBy(int distance, int duration, boolean linear) {
        if (mFlingRunnable == null) {
            mFlingRunnable = new FlingRunnable();
        }
        final int firstPos = mFirstPosition;
        final int childCount = getChildCount();
        final int lastPos = firstPos + childCount;
        final int topLimit = getPaddingTop();
        final int bottomLimit = getHeight() - getPaddingBottom();
        if (distance == 0 || mItemCount == 0 || childCount == 0
                || (firstPos == 0 && getChildAt(0).getTop() == topLimit && distance < 0)
                ||
                (lastPos == mItemCount && getChildAt(childCount - 1).getBottom() == bottomLimit &&
                        distance > 0)) {
            mFlingRunnable.endFling();
        } else {
            reportScrollStateChange(ZrcListView.OnScrollListener.SCROLL_STATE_FLING);
            mFlingRunnable.startScroll(distance, duration, linear);
        }
    }

    private void createScrollingCache() {
        if (mScrollingCacheEnabled && !mCachingStarted && !isSupportHardwareAccelerated()) {
            setChildrenDrawnWithCacheEnabled(true);
            setChildrenDrawingCacheEnabled(true);
            mCachingStarted = mCachingActive = true;
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void clearScrollingCache() {
        if (!isSupportHardwareAccelerated()) {
            if (mClearScrollingCache == null) {
                mClearScrollingCache = new Runnable() {
                    @Override
                    public void run() {
                        if (mCachingStarted) {
                            mCachingStarted = mCachingActive = false;
                            setChildrenDrawnWithCacheEnabled(false);
                            if ((getPersistentDrawingCache() & PERSISTENT_SCROLLING_CACHE) == 0) {
                                setChildrenDrawingCacheEnabled(false);
                            }
                            if (!isAlwaysDrawnWithCacheEnabled()) {
                                invalidate();
                            }
                        }
                    }
                };
            }
            post(mClearScrollingCache);
        }
    }

    public void scrollListBy(int y) {
        trackMotionScroll(-y, -y);
    }

    public boolean canScrollList(int direction) {
        final int childCount = getChildCount();
        if (childCount == 0) {
            return false;
        }
        final int firstPosition = mFirstPosition;
        final Rect listPadding = mListPadding;
        if (direction > 0) {
            final int lastBottom = getChildAt(childCount - 1).getBottom();
            final int lastPosition = firstPosition + childCount;
            return lastPosition < mItemCount ||
                    lastBottom > getHeight() - listPadding.bottom - mLastBottomOffset;
        } else {
            final int firstTop = getChildAt(0).getTop();
            return firstPosition > 0 || firstTop < listPadding.top + mFirstTopOffset;
        }
    }

    boolean trackMotionScroll(int deltaY, int incrementalDeltaY) {
        final int childCount = getChildCount();
        final int firstPosition = mFirstPosition;
        final int firstTop = childCount == 0 ? mFirstTop : getChildAt(0).getTop();
        int lastBottom = childCount == 0 ? firstTop : getChildAt(childCount - 1).getBottom();
        if (firstPosition + childCount >= mItemCount - 1) {
            if (!isRefreshing && !isLoadingMore && isLoadMoreOn && onLoadMoreStart != null) {
                isLoadingMore = true;
                onLoadMoreStart.onStart();
            }
        }
        if (isRefreshing || isLoadingMore) {
            if(mZrcFooter!=null) {
                lastBottom += mZrcFooter.getHeight();
            }
        }
        final int mPaddingBottom = getPaddingBottom();
        final int mPaddingTop = getPaddingTop();
        final Rect listPadding = mListPadding;
        int effectivePaddingTop = 0;
        int effectivePaddingBottom = 0;
        final int spaceAbove = effectivePaddingTop - firstTop;
        final int end = getHeight() - effectivePaddingBottom;
        final int spaceBelow = lastBottom - end;
        final int height = getHeight() - mPaddingBottom - mPaddingTop;
        if (incrementalDeltaY < 0) {
            incrementalDeltaY = Math.max(-(height - 1), incrementalDeltaY);
        } else {
            incrementalDeltaY = Math.min(height - 1, incrementalDeltaY);
        }
        final Headable zrcHeader = mZrcHeader;
        final boolean isTooShort = childCount == mItemCount && lastBottom - firstTop < getHeight();
        final int topOffset = firstTop -
                (listPadding.top + mFirstTopOffset + (showHeader ? zrcHeader.getHeight() : 0));
        final int bottomOffset = isTooShort ? firstTop - listPadding.top : lastBottom - getHeight()
                + listPadding.bottom + mLastBottomOffset;
        final boolean isOutOfTop = firstPosition == 0 && topOffset > 0;
        final boolean isOutOfBottom = firstPosition + childCount == mItemCount && bottomOffset < 0;
        final boolean cannotScrollDown = (isOutOfTop && incrementalDeltaY > 0);
        final boolean cannotScrollUp = (isOutOfBottom && incrementalDeltaY <= 0);
        if (isTooShort && cannotScrollDown && mTouchMode == TOUCH_MODE_RESCROLL) {
            mTouchMode = TOUCH_MODE_FLING;
            return true;
        }
        if (isOutOfTop || isOutOfBottom) {
            if (mTouchMode == TOUCH_MODE_SCROLL) {
                incrementalDeltaY /= 1.7f;
                if (zrcHeader != null && isOutOfTop) {
                    final int state = zrcHeader.getState();
                    if (topOffset >= zrcHeader.getHeight()) {
                        if (state == Headable.STATE_PULL || state == Headable.STATE_REST) {
                            zrcHeader.stateChange(Headable.STATE_RELEASE, null);
                        }
                    } else {
                        if (state == Headable.STATE_RELEASE || state == Headable.STATE_REST) {
                            zrcHeader.stateChange(Headable.STATE_PULL, null);
                        }
                    }
                }
            }
            if (mTouchMode == TOUCH_MODE_RESCROLL && false) {
                if (isOutOfTop && zrcHeader != null) {
                    final int state = zrcHeader.getState();
                    if (topOffset < 10 &&
                            (state == Headable.STATE_SUCCESS || state == Headable.STATE_FAIL)) {
                        zrcHeader.stateChange(Headable.STATE_REST, null);
                        removeCallbacks(mResetRunnable);
                    }
                }
            }
            if (mTouchMode == TOUCH_MODE_FLING) {
                if (cannotScrollDown) {
                    incrementalDeltaY /= 1.7f;
                    int duration = firstTop - listPadding.top;
                    if (duration > getHeight() / 6) {
                        return true;
                    }
                } else if (cannotScrollUp && !isOutOfTop) {
                    incrementalDeltaY /= 1.7f;
                    int duration = bottomOffset;
                    if (duration < -getHeight() / 6) {
                        return true;
                    }
                }
            } else {
                if (incrementalDeltaY > 0) {
                    int duration = firstTop - listPadding.top;
                    if (duration > getHeight() / 2) {
                        return true;
                    }
                } else if (incrementalDeltaY < 0 && !isOutOfTop) {
                    int duration = bottomOffset;
                    if (duration < -getHeight() / 2) {
                        return true;
                    }
                }
            }
            if (onScrollStateListener != null) {
                if (mScrollState != ZrcListView.OnScrollStateListener.EDGE) {
                    mScrollState = ZrcListView.OnScrollStateListener.EDGE;
                    onScrollStateListener.onChange(ZrcListView.OnScrollStateListener.EDGE);
                }
            }
        } else {
            if (zrcHeader != null) {
                if (zrcHeader.getState() == Headable.STATE_PULL) {
                    zrcHeader.stateChange(Headable.STATE_REST, null);
                }
            }
            if (incrementalDeltaY > 5) {
                if (onScrollStateListener != null) {
                    if (mScrollState != ZrcListView.OnScrollStateListener.UP) {
                        mScrollState = ZrcListView.OnScrollStateListener.UP;
                        onScrollStateListener.onChange(ZrcListView.OnScrollStateListener.UP);
                    }
                }
            } else if (incrementalDeltaY < -5) {
                if (onScrollStateListener != null) {
                    if (mScrollState != ZrcListView.OnScrollStateListener.DOWN) {
                        mScrollState = ZrcListView.OnScrollStateListener.DOWN;
                        onScrollStateListener.onChange(ZrcListView.OnScrollStateListener.DOWN);
                    }
                }
            }
        }
        final boolean down = incrementalDeltaY < 0;
        final int headerViewsCount = getHeaderViewsCount();
        final int footerViewsStart = mItemCount - getFooterViewsCount();
        int start = 0;
        int count = 0;
        if (down) {
            int top = -incrementalDeltaY;
            for (int i = 0; i < childCount; i++) {
                final View child = getChildAt(i);
                if (child.getBottom() >= top + Math.min(0, bottomOffset)) {
                    break;
                } else {
                    count++;
                    int position = firstPosition + i;
                    if (position >= headerViewsCount && position < footerViewsStart) {
                        mRecycler.addScrapView(child, position);
                    }
                }
            }
        } else {
            int bottom = getHeight() - incrementalDeltaY;
            for (int i = childCount - 1; i >= 0; i--) {
                final View child = getChildAt(i);
                if (child.getTop() <= bottom + Math.max(0, topOffset)) {
                    break;
                } else {
                    start = i;
                    count++;
                    int position = firstPosition + i;
                    if (position >= headerViewsCount && position < footerViewsStart) {
                        mRecycler.addScrapView(child, position);
                    }
                }
            }
        }
        mBlockLayoutRequests = true;
        if (count > 0) {
            detachViewsFromParent(start, count);
            mRecycler.removeSkippedScrap();
        }
        if (!awakenScrollBars()) {
            invalidate();
        }
        offsetChildrenTopAndBottom(incrementalDeltaY);
        if (down) {
            mFirstPosition += count;
        }
        final int absIncrementalDeltaY = Math.abs(incrementalDeltaY);
        if (spaceAbove < absIncrementalDeltaY || spaceBelow < absIncrementalDeltaY) {
            fillGap(down);
        }

        mFirstTop = getChildCount() == 0 ? mFirstTop + incrementalDeltaY : getChildAt(0).getTop();
        if (mSelectorPosition != INVALID_POSITION) {
            final int childIndex = mSelectorPosition - mFirstPosition;
            if (childIndex >= 0 && childIndex < getChildCount()) {
                positionSelector(INVALID_POSITION, getChildAt(childIndex));
            }
        } else {
            mSelectorRect.setEmpty();
        }
        mBlockLayoutRequests = false;
        invokeOnItemScrollListener();
        return false;
    }

    public void offsetChildrenTopAndBottom(int offset) {
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View v = getChildAt(i);
            v.offsetTopAndBottom(offset);
        }
    }

    int getHeaderViewsCount() {
        return 0;
    }

    int getFooterViewsCount() {
        return 0;
    }

    abstract void fillGap(boolean down);

    abstract int findMotionRow(int y);

    int findClosestMotionRow(int y) {
        final int childCount = getChildCount();
        if (childCount == 0) {
            return INVALID_POSITION;
        }
        final int motionRow = findMotionRow(y);
        return motionRow != INVALID_POSITION ? motionRow : mFirstPosition + childCount - 1;
    }

    public void invalidateViews() {
        mDataChanged = true;
        requestLayout();
        invalidate();
    }

    protected void handleDataChanged() {
        mRecycler.clearTransientStateViews();
        // mLayoutMode = mStackFromBottom ? LAYOUT_FORCE_BOTTOM :
        // LAYOUT_FORCE_TOP;
        mSelectorPosition = INVALID_POSITION;
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, 0);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    public int getTranscriptMode() {
        return mTranscriptMode;
    }

    public void setTranscriptMode(int mode) {
        mTranscriptMode = mode;
    }

    @Override
    public int getSolidColor() {
        return mCacheColorHint;
    }

    @ViewDebug.ExportedProperty(category = "drawing")
    public int getCacheColorHint() {
        return mCacheColorHint;
    }

    public void setCacheColorHint(int color) {
        if (color != mCacheColorHint) {
            mCacheColorHint = color;
            int count = getChildCount();
            for (int i = 0; i < count; i++) {
                getChildAt(i).setDrawingCacheBackgroundColor(color);
            }
            mRecycler.setCacheColorHint(color);
        }
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void reclaimViews(List<View> views) {
        int childCount = getChildCount();
        RecyclerListener listener = mRecycler.mRecyclerListener;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            if (lp != null && mRecycler.shouldRecycleViewType(lp.viewType)) {
                views.add(child);
                if (listener != null) {
                    listener.onMovedToScrapHeap(child);
                }
            }
        }
        mRecycler.reclaimScrapViews(views);
        removeAllViewsInLayout();
    }

    public void startLoadMore() {
        isLoadingMore = false;
        isLoadMoreOn = true;
    }

    public void setLoadMoreSuccess() {
        isLoadingMore = false;
    }

    public void stopLoadMore() {
        isLoadingMore = false;
        isLoadMoreOn = false;
        if (mFlingRunnable != null) {
            mFlingRunnable.scrollToAdjustViewsUpOrDown();
        }
    }

    public void refresh() {
        post(new Runnable() {
            @Override
            public void run() {
                if (mZrcHeader == null || mZrcHeader.getState() == Headable.STATE_LOADING ||
                        onRefreshStart == null
                        || mTouchMode != TOUCH_MODE_REST) {
                    return;
                }
                if (mFirstPosition == 0) {
                    int newTop = mZrcHeader.getHeight() + mFirstTopOffset;
                    if (mFlingRunnable == null) {
                        mFlingRunnable = new FlingRunnable();
                    }
                    isRefreshing = true;
                    onRefreshStart.onStart();
                    showHeader = true;
                    mZrcHeader.stateChange(Headable.STATE_LOADING, null);
                    mFlingRunnable.startScroll(mFirstTop - newTop,
                            (int) (Math.abs(newTop - mFirstTop) / mDensity) + 50, true);
                    mTouchMode = TOUCH_MODE_RESCROLL;
                }
            }
        });
    }

    protected void setRecyclerListener(RecyclerListener listener) {
        mRecycler.mRecyclerListener = listener;
    }

    public void setOnRefreshStartListener(ZrcListView.OnStartListener onStart) {
        this.onRefreshStart = onStart;
    }

    public void setOnLoadMoreStartListener(ZrcListView.OnStartListener onStart) {
        this.onLoadMoreStart = onStart;
    }

    public void setOnScrollStateListener(ZrcListView.OnScrollStateListener onScrollListener) {
        this.onScrollStateListener = onScrollListener;
    }

    public void setRefreshSuccess() {
        setRefreshSuccess("刷新成功");
    }

    public void setRefreshSuccess(String msg) {
        setRefreshDone(msg, Headable.STATE_SUCCESS);
    }

    public void setRefreshFail() {
        setRefreshFail("刷新失败");
    }

    public void setRefreshFail(String msg) {
        setRefreshDone(msg, Headable.STATE_FAIL);
    }

    public void reset() {
        if (mZrcHeader != null) {
            mZrcHeader.stateChange(Headable.STATE_REST, "");
        }
    }

    private void setRefreshDone(String msg, int state) {
        final Headable zrcHeader = mZrcHeader;
        if (zrcHeader != null && zrcHeader.getState() == Headable.STATE_LOADING) {
            isRefreshing = false;
            zrcHeader.stateChange(state, msg);
            final int childCount = getChildCount();
            final int firstTop = childCount == 0 ? mFirstTop : getChildAt(0).getTop();
            final boolean unShownState = mFirstPosition > 0
                    || firstTop < mListPadding.top + mFirstTopOffset + zrcHeader.getHeight();
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mFlingRunnable != null /*&& mTouchMode != TOUCH_MODE_SCROLL*/) {
                        showHeader = false;
                        boolean hasScroll = mFlingRunnable.scrollToAdjustViewsUpOrDown();
                        if (!hasScroll) {
                            zrcHeader.stateChange(Headable.STATE_REST, null);
                        } else {
                            postDelayed(mResetRunnable, 500);
                        }
                    } else {
                        postDelayed(mResetRunnable, 500);
                    }
                }
            }, 1000);
            if (unShownState) {
                zrcHeader.toastResultInOtherWay(getContext(), state);
            }
        }
    }

    public Headable getHeadable() {
        return mZrcHeader;
    }

    public void setHeadable(Headable header) {
        this.mZrcHeader = header;
    }

    public Footable getFootable() {
        return mZrcFooter;
    }

    public void setFootable(Footable footer) {
        this.mZrcFooter = footer;
    }

    public void setFirstTopOffset(int offset) {
        this.mFirstTopOffset = offset;
    }

    public void setLastBottomOffset(int offset) {
        this.mLastBottomOffset = offset;
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.mFirstPosition = mFirstPosition;
        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        mFirstPosition = ss.mFirstPosition;
        requestLayout();
        if (mFlingRunnable != null) {
            mFlingRunnable.scrollToAdjustViewsUpOrDown();
        }
    }

    public static interface RecyclerListener {
        void onMovedToScrapHeap(View view);
    }

    static class SavedState extends BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR =
                new Parcelable.Creator<SavedState>() {
                    @Override
                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }

                    @Override
                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
        private int mFirstPosition;

        public SavedState(Parcelable arg0) {
            super(arg0);
        }

        private SavedState(Parcel in) {
            super(in);
            mFirstPosition = in.readInt();
            // mFirstTop = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(mFirstPosition);
            // out.writeInt(mFirstTop);
        }

        @Override
        public String toString() {
            return "AbsListView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) +
                    " firstPosition="
                    + mFirstPosition
                    /* + " firstTop=" + mFirstTop */ + "}";
        }
    }

    public static class LayoutParams extends ViewGroup.LayoutParams {
        int viewType;
        boolean recycledHeaderFooter;
        boolean forceAdd;
        int scrappedFromPosition;
        long itemId = -1;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int w, int h) {
            super(w, h);
        }

        public LayoutParams(int w, int h, int viewType) {
            super(w, h);
            this.viewType = viewType;
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }

    private class WindowRunnnable {
        private int mOriginalAttachCount;

        public void rememberWindowAttachCount() {
            mOriginalAttachCount = getWindowAttachCount();
        }

        public boolean sameWindow() {
            return getWindowAttachCount() == mOriginalAttachCount;
        }
    }

    private class PerformClick extends WindowRunnnable implements Runnable {
        int mClickMotionPosition;

        @Override
        public void run() {
            if (mDataChanged) {
                return;
            }
            final ListAdapter adapter = mAdapter;
            final int motionPosition = mClickMotionPosition;
            if (adapter != null && mItemCount > 0 && motionPosition != INVALID_POSITION
                    && motionPosition < adapter.getCount() && sameWindow()) {
                final View view = getChildAt(motionPosition - mFirstPosition);
                if (view != null) {
                    performItemClick(view, motionPosition, adapter.getItemId(motionPosition));
                }
            }
        }
    }

    final class CheckForTap implements Runnable {
        @Override
        public void run() {
            if (mTouchMode == TOUCH_MODE_DOWN) {
                mTouchMode = TOUCH_MODE_TAP;
                final View child = getChildAt(mMotionPosition - mFirstPosition);
                if (child != null && !child.hasFocusable()) {
                    mLayoutMode = LAYOUT_NORMAL;
                    if (!mDataChanged) {
                        child.setPressed(true);
                        positionSelector(mMotionPosition, child);
                        setPressed(true);
                        refreshDrawableState();
                        final int longPressTimeout = ViewConfiguration.getLongPressTimeout();
                        final boolean longClickable = isLongClickable();
                        if (mSelector != null) {
                            Drawable d = mSelector.getCurrent();
                            if (d != null && d instanceof TransitionDrawable) {
                                if (longClickable) {
                                    ((TransitionDrawable) d).startTransition(longPressTimeout);
                                } else {
                                    ((TransitionDrawable) d).resetTransition();
                                }
                            }
                        }
                        mTouchMode = TOUCH_MODE_DONE_WAITING;
                    } else {
                        mTouchMode = TOUCH_MODE_DONE_WAITING;
                    }
                }
            }
        }
    }

    private class FlingRunnable implements Runnable {
        private final Scroller mScroller;
        private int mLastFlingY;

        FlingRunnable() {
            mScroller = new Scroller(getContext(), new Interpolator() {
                @Override
                public float getInterpolation(float t) {
                    t -= 1;
                    return t * t * t * t * t + 1;
                }
            });
        }

        void start(int initialVelocity) {
            if (!mScroller.isFinished()) {
                mScroller.abortAnimation();
            }
            int initialY = initialVelocity < 0 ? Integer.MAX_VALUE : 0;
            mLastFlingY = initialY;
            mScroller.fling(0, initialY, 0, initialVelocity, 0, Integer.MAX_VALUE, 0,
                    Integer.MAX_VALUE);
            mTouchMode = TOUCH_MODE_FLING;
            ViewCompat.postOnAnimation(ZrcAbsListView.this, this);
        }

        void startScroll(int distance, int duration, boolean linear) {
            if (!mScroller.isFinished()) {
                mScroller.abortAnimation();
            }
            int initialY = distance < 0 ? Integer.MAX_VALUE : 0;
            mLastFlingY = initialY;
            mScroller.startScroll(0, initialY, 0, distance, duration);
            mTouchMode = TOUCH_MODE_FLING;
            ViewCompat.postOnAnimation(ZrcAbsListView.this, this);
        }

        void endFling() {
            int oldTouchMode = mTouchMode;
            mTouchMode = TOUCH_MODE_REST;
            removeCallbacks(this);
            reportScrollStateChange(ZrcListView.OnScrollListener.SCROLL_STATE_IDLE);
            clearScrollingCache();
            mScroller.abortAnimation();

            if (mZrcHeader != null && oldTouchMode == TOUCH_MODE_RESCROLL
                    && mZrcHeader.getState() == Headable.STATE_RELEASE) {
                scrollToAdjustViewsUpOrDown();
            }
        }

        boolean scrollToAdjustViewsUpOrDown() {
            final int firstPosition = mFirstPosition;
            final int childCount = getChildCount();
            final int firstTop = childCount == 0 ? mFirstTop : getChildAt(0).getTop();
            int lastBottom = childCount == 0 ? firstTop : getChildAt(childCount - 1).getBottom();
            if (firstPosition + childCount >= mItemCount - 1) {
                if (!isRefreshing && !isLoadingMore && isLoadMoreOn && onLoadMoreStart != null) {
                    isLoadingMore = true;
                    onLoadMoreStart.onStart();
                }
            }
            if (isRefreshing || isLoadingMore) {
                lastBottom += mZrcFooter.getHeight();
            }
            final Rect listPadding = mListPadding;
            final Headable zrcHeader = mZrcHeader;
            final boolean isOnLoading =
                    zrcHeader != null && zrcHeader.getState() == Headable.STATE_LOADING;
            final int topOffset = firstTop
                    -
                    (listPadding.top + mFirstTopOffset + (showHeader ? zrcHeader.getHeight() : 0));
            final boolean cannotScrollDown = (firstPosition == 0 && topOffset > 0);
            final boolean isTooShort =
                    childCount == mItemCount && lastBottom - firstTop < getHeight();
            final int bottomOffset =
                    isTooShort ? firstTop - listPadding.top - mFirstTopOffset : lastBottom
                            - getHeight() + listPadding.bottom + mLastBottomOffset;
            final boolean cannotScrollUp =
                    firstPosition + childCount == mItemCount && bottomOffset < 0;
            if (cannotScrollDown) {
                int duration = mFirstTopOffset + listPadding.top - firstTop;
                if ((zrcHeader != null && onRefreshStart != null &&
                        zrcHeader.getState() == Headable.STATE_RELEASE)) {
                    if (!isOnLoading) {
                        isRefreshing = true;
                        showHeader = true;
                        zrcHeader.stateChange(Headable.STATE_LOADING, null);
                        onRefreshStart.onStart();
                    }
                    duration += zrcHeader.getHeight();
                } else if(showHeader){
                    duration += zrcHeader.getHeight();
                }
                startScroll(-duration, (int) Math.abs(duration / mDensity) + 50, true);
                mTouchMode = TOUCH_MODE_RESCROLL;
            } else if (cannotScrollUp) {
                int duration = bottomOffset;
                startScroll(duration, (int) Math.abs(duration / mDensity) + 50, true);
                mTouchMode = TOUCH_MODE_RESCROLL;
            } else {
                return false;
            }
            return true;
        }

        @Override
        public void run() {
            switch (mTouchMode) {
            default:
                endFling();
                return;
            case TOUCH_MODE_SCROLL:
                if (mScroller.isFinished()) {
                    return;
                }
            case TOUCH_MODE_RESCROLL:
            case TOUCH_MODE_FLING: {
                if (mDataChanged) {
                    layoutChildren();
                }
                final Scroller scroller = mScroller;
                boolean more = scroller.computeScrollOffset();
                final int y = scroller.getCurrY();
                final int mPaddingBottom = getPaddingBottom();
                final int mPaddingTop = getPaddingTop();
                int delta = mLastFlingY - y;
                if (delta > 0) {
                    mMotionPosition = mFirstPosition;
                    delta = Math.min(getHeight() - mPaddingBottom - mPaddingTop - 1, delta);
                } else {
                    int offsetToLast = getChildCount() - 1;
                    mMotionPosition = mFirstPosition + offsetToLast;
                    delta = Math.max(-(getHeight() - mPaddingBottom - mPaddingTop - 1), delta);
                }
                final boolean atEdge = trackMotionScroll(delta, delta);
                final boolean atEnd = atEdge && (delta != 0);
                final int touchMode = mTouchMode;
                if (atEnd) {
                    endFling();
                    if (touchMode == TOUCH_MODE_FLING) {
                        scrollToAdjustViewsUpOrDown();
                    }
                    break;
                }
                if (more && !atEnd) {
                    mLastFlingY = y;
                    ViewCompat.postOnAnimation(ZrcAbsListView.this, this);
                } else {
                    endFling();
                    if (touchMode == TOUCH_MODE_FLING) {
                        scrollToAdjustViewsUpOrDown();
                    }
                }
                break;
            }
            }
        }
    }

    class AdapterDataSetObserver extends ZrcAdapterView<ListAdapter>.AdapterDataSetObserver {
        @Override
        public void onChanged() {
            // if(mFlingRunnable!=null){
            // mFlingRunnable.endFling();
            // }
            super.onChanged();
        }

        @Override
        public void onInvalidated() {
            // if(mFlingRunnable!=null){
            // mFlingRunnable.endFling();
            // }
            super.onInvalidated();
        }
    }

    class RecycleBin {
        private RecyclerListener mRecyclerListener;
        private int mFirstActivePosition;
        private View[] mActiveViews = new View[0];
        private ArrayList<View>[] mScrapViews;
        private int mViewTypeCount;
        private ArrayList<View> mCurrentScrap;
        private ArrayList<View> mSkippedScrap;
        private SparseArrayCompat<View> mTransientStateViews;
        private LongSparseArray<View> mTransientStateViewsById;

        @SuppressWarnings("unchecked")
        public void setViewTypeCount(int viewTypeCount) {
            if (viewTypeCount < 1) {
                throw new IllegalArgumentException("Can't have a viewTypeCount < 1");
            }
            ArrayList<View>[] scrapViews = new ArrayList[viewTypeCount];
            for (int i = 0; i < viewTypeCount; i++) {
                scrapViews[i] = new ArrayList<View>();
            }
            mViewTypeCount = viewTypeCount;
            mCurrentScrap = scrapViews[0];
            mScrapViews = scrapViews;
        }

        public void markChildrenDirty() {
            if (mViewTypeCount == 1) {
                final ArrayList<View> scrap = mCurrentScrap;
                final int scrapCount = scrap.size();
                for (int i = 0; i < scrapCount; i++) {
                    scrap.get(i).forceLayout();
                }
            } else {
                final int typeCount = mViewTypeCount;
                for (int i = 0; i < typeCount; i++) {
                    final ArrayList<View> scrap = mScrapViews[i];
                    final int scrapCount = scrap.size();
                    for (int j = 0; j < scrapCount; j++) {
                        scrap.get(j).forceLayout();
                    }
                }
            }
            if (mTransientStateViews != null) {
                final int count = mTransientStateViews.size();
                for (int i = 0; i < count; i++) {
                    mTransientStateViews.valueAt(i).forceLayout();
                }
            }
            if (mTransientStateViewsById != null) {
                final int count = mTransientStateViewsById.size();
                for (int i = 0; i < count; i++) {
                    mTransientStateViewsById.valueAt(i).forceLayout();
                }
            }
        }

        public boolean shouldRecycleViewType(int viewType) {
            return viewType >= 0;
        }

        void clear() {
            if (mViewTypeCount == 1) {
                final ArrayList<View> scrap = mCurrentScrap;
                final int scrapCount = scrap.size();
                for (int i = 0; i < scrapCount; i++) {
                    removeDetachedView(scrap.remove(scrapCount - 1 - i), false);
                }
            } else {
                final int typeCount = mViewTypeCount;
                for (int i = 0; i < typeCount; i++) {
                    final ArrayList<View> scrap = mScrapViews[i];
                    final int scrapCount = scrap.size();
                    for (int j = 0; j < scrapCount; j++) {
                        removeDetachedView(scrap.remove(scrapCount - 1 - j), false);
                    }
                }
            }
            if (mTransientStateViews != null) {
                mTransientStateViews.clear();
            }
            if (mTransientStateViewsById != null) {
                mTransientStateViewsById.clear();
            }
        }

        void fillActiveViews(int childCount, int firstActivePosition) {
            if (mActiveViews.length < childCount) {
                mActiveViews = new View[childCount];
            }
            mFirstActivePosition = firstActivePosition;
            final View[] activeViews = mActiveViews;
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (lp != null && lp.viewType != ITEM_VIEW_TYPE_HEADER_OR_FOOTER) {
                    activeViews[i] = child;
                }
            }
        }

        View getActiveView(int position) {
            int index = position - mFirstActivePosition;
            final View[] activeViews = mActiveViews;
            if (index >= 0 && index < activeViews.length) {
                final View match = activeViews[index];
                activeViews[index] = null;
                return match;
            }
            return null;
        }

        View getTransientStateView(int position) {
            if (mAdapter != null && mAdapterHasStableIds && mTransientStateViewsById != null) {
                long id = mAdapter.getItemId(position);
                View result = mTransientStateViewsById.get(id);
                mTransientStateViewsById.remove(id);
                return result;
            }
            if (mTransientStateViews != null) {
                final int index = mTransientStateViews.indexOfKey(position);
                if (index >= 0) {
                    View result = mTransientStateViews.valueAt(index);
                    mTransientStateViews.removeAt(index);
                    return result;
                }
            }
            return null;
        }

        void clearTransientStateViews() {
            if (mTransientStateViews != null) {
                mTransientStateViews.clear();
            }
            if (mTransientStateViewsById != null) {
                mTransientStateViewsById.clear();
            }
        }

        View getScrapView(int position) {
            if (mViewTypeCount == 1) {
                return retrieveFromScrap(mCurrentScrap, position);
            } else {
                int whichScrap = mAdapter.getItemViewType(position);
                if (whichScrap >= 0 && whichScrap < mScrapViews.length) {
                    return retrieveFromScrap(mScrapViews[whichScrap], position);
                }
            }
            return null;
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        void addScrapView(View scrap, int position) {
            final LayoutParams lp = (LayoutParams) scrap.getLayoutParams();
            if (lp == null) {
                return;
            }
            lp.scrappedFromPosition = position;
            final int viewType = lp.viewType;
            if (!shouldRecycleViewType(viewType)) {
                return;
            }
            final boolean scrapHasTransientState =
                    Build.VERSION.SDK_INT >= 16 ? scrap.hasTransientState()
                            : false;
            if (scrapHasTransientState) {
                if (mAdapter != null && mAdapterHasStableIds) {
                    if (mTransientStateViewsById == null) {
                        mTransientStateViewsById = new LongSparseArray<View>();
                    }
                    mTransientStateViewsById.put(lp.itemId, scrap);
                } else if (!mDataChanged) {
                    if (mTransientStateViews == null) {
                        mTransientStateViews = new SparseArrayCompat<View>();
                    }
                    mTransientStateViews.put(position, scrap);
                } else {
                    if (mSkippedScrap == null) {
                        mSkippedScrap = new ArrayList<View>();
                    }
                    mSkippedScrap.add(scrap);
                }
            } else {
                if (mViewTypeCount == 1) {
                    mCurrentScrap.add(scrap);
                } else {
                    mScrapViews[viewType].add(scrap);
                }
                if (mRecyclerListener != null) {
                    mRecyclerListener.onMovedToScrapHeap(scrap);
                }
            }
        }

        void removeSkippedScrap() {
            if (mSkippedScrap == null) {
                return;
            }
            final int count = mSkippedScrap.size();
            for (int i = 0; i < count; i++) {
                removeDetachedView(mSkippedScrap.get(i), false);
            }
            mSkippedScrap.clear();
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        void scrapActiveViews() {
            final View[] activeViews = mActiveViews;
            final boolean hasListener = mRecyclerListener != null;
            final boolean multipleScraps = mViewTypeCount > 1;
            ArrayList<View> scrapViews = mCurrentScrap;
            final int count = activeViews.length;
            for (int i = count - 1; i >= 0; i--) {
                final View victim = activeViews[i];
                if (victim != null) {
                    final LayoutParams lp = (LayoutParams) victim.getLayoutParams();
                    int whichScrap = lp.viewType;
                    activeViews[i] = null;
                    final boolean scrapHasTransientState = Build.VERSION.SDK_INT >= 16 ? victim
                            .hasTransientState() : false;
                    if (!shouldRecycleViewType(whichScrap) || scrapHasTransientState) {
                        if (whichScrap != ITEM_VIEW_TYPE_HEADER_OR_FOOTER &&
                                scrapHasTransientState) {
                            removeDetachedView(victim, false);
                        }
                        if (scrapHasTransientState) {
                            if (mAdapter != null && mAdapterHasStableIds) {
                                if (mTransientStateViewsById == null) {
                                    mTransientStateViewsById = new LongSparseArray<View>();
                                }
                                long id = mAdapter.getItemId(mFirstActivePosition + i);
                                mTransientStateViewsById.put(id, victim);
                            } else {
                                if (mTransientStateViews == null) {
                                    mTransientStateViews = new SparseArrayCompat<View>();
                                }
                                mTransientStateViews.put(mFirstActivePosition + i, victim);
                            }
                        }
                        continue;
                    }
                    if (multipleScraps) {
                        scrapViews = mScrapViews[whichScrap];
                    }
                    victim.onStartTemporaryDetach();
                    lp.scrappedFromPosition = mFirstActivePosition + i;
                    scrapViews.add(victim);
                    if (hasListener) {
                        mRecyclerListener.onMovedToScrapHeap(victim);
                    }
                }
            }
            pruneScrapViews();
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        private void pruneScrapViews() {
            final int maxViews = mActiveViews.length;
            final int viewTypeCount = mViewTypeCount;
            final ArrayList<View>[] scrapViews = mScrapViews;
            for (int i = 0; i < viewTypeCount; ++i) {
                final ArrayList<View> scrapPile = scrapViews[i];
                int size = scrapPile.size();
                final int extras = size - maxViews;
                size--;
                for (int j = 0; j < extras; j++) {
                    removeDetachedView(scrapPile.remove(size--), false);
                }
            }
            if (mTransientStateViews != null) {
                for (int i = 0; i < mTransientStateViews.size(); i++) {
                    final View v = mTransientStateViews.valueAt(i);
                    if (!v.hasTransientState()) {
                        mTransientStateViews.removeAt(i);
                        i--;
                    }
                }
            }
        }

        void reclaimScrapViews(List<View> views) {
            if (mViewTypeCount == 1) {
                views.addAll(mCurrentScrap);
            } else {
                final int viewTypeCount = mViewTypeCount;
                final ArrayList<View>[] scrapViews = mScrapViews;
                for (int i = 0; i < viewTypeCount; ++i) {
                    final ArrayList<View> scrapPile = scrapViews[i];
                    views.addAll(scrapPile);
                }
            }
        }

        void setCacheColorHint(int color) {
            if (mViewTypeCount == 1) {
                final ArrayList<View> scrap = mCurrentScrap;
                final int scrapCount = scrap.size();
                for (int i = 0; i < scrapCount; i++) {
                    scrap.get(i).setDrawingCacheBackgroundColor(color);
                }
            } else {
                final int typeCount = mViewTypeCount;
                for (int i = 0; i < typeCount; i++) {
                    final ArrayList<View> scrap = mScrapViews[i];
                    final int scrapCount = scrap.size();
                    for (int j = 0; j < scrapCount; j++) {
                        scrap.get(j).setDrawingCacheBackgroundColor(color);
                    }
                }
            }
            final View[] activeViews = mActiveViews;
            final int count = activeViews.length;
            for (int i = 0; i < count; ++i) {
                final View victim = activeViews[i];
                if (victim != null) {
                    victim.setDrawingCacheBackgroundColor(color);
                }
            }
        }
    }
}
