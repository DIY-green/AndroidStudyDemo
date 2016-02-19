package com.cheng.animationstudy.customview.zrclistview;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Build;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

abstract class ZrcAdapterView<T extends Adapter> extends ViewGroup {
    public static final int ITEM_VIEW_TYPE_IGNORE = -1;
    public static final int ITEM_VIEW_TYPE_HEADER_OR_FOOTER = -2;
    public static final int INVALID_POSITION = -1;
    public static final long INVALID_ROW_ID = Long.MIN_VALUE;
    int mFirstPosition = 0;
    int mFirstTop = 0;
    boolean mInLayout = false;
    ZrcListView.OnItemClickListener mOnItemClickListener;
    ZrcListView.OnItemLongClickListener mOnItemLongClickListener;
    boolean mDataChanged;
    int mItemCount;
    int mOldItemCount;
    boolean mBlockLayoutRequests = false;
    private View mEmptyView;
    private boolean mDesiredFocusableState;
    private boolean mDesiredFocusableInTouchModeState;

    public ZrcAdapterView(Context context) {
        super(context);
    }

    public ZrcAdapterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ZrcAdapterView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public final ZrcListView.OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setOnItemClickListener(ZrcListView.OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public boolean performItemClick(View view, int position, long id) {
        if (mOnItemClickListener != null) {
            playSoundEffect(SoundEffectConstants.CLICK);
            mOnItemClickListener.onItemClick((ZrcListView) this, view, position, id);
            return true;
        }
        return false;
    }

    public final ZrcListView.OnItemLongClickListener getOnItemLongClickListener() {
        return mOnItemLongClickListener;
    }

    public void setOnItemLongClickListener(ZrcListView.OnItemLongClickListener listener) {
        if (!isLongClickable()) {
            setLongClickable(true);
        }
        mOnItemLongClickListener = listener;
    }

    public abstract T getAdapter();

    public abstract void setAdapter(T adapter);

    @Override
    public void addView(View child) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addView(View child, int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addView(View child, LayoutParams params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addView(View child, int index, LayoutParams params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeView(View child) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeViewAt(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeAllViews() {
        throw new UnsupportedOperationException();
    }

    public int getCount() {
        return mItemCount;
    }

    public int getPositionForView(View view) {
        View listItem = view;
        try {
            View v;
            while (!(v = (View) listItem.getParent()).equals(this)) {
                listItem = v;
            }
        } catch (ClassCastException e) {
            // We made it up to the window without find this list view
            return INVALID_POSITION;
        }

        // Search the children for the list item
        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (getChildAt(i).equals(listItem)) {
                return mFirstPosition + i;
            }
        }
        return INVALID_POSITION;
    }

    public int getFirstVisiblePosition() {
        return mFirstPosition;
    }

    public int getLastVisiblePosition() {
        return mFirstPosition + getChildCount() - 1;
    }

    public View getEmptyView() {
        return mEmptyView;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setEmptyView(View emptyView) {
        mEmptyView = emptyView;

        final T adapter = getAdapter();
        final boolean empty = ((adapter == null) || adapter.isEmpty());
        updateEmptyStatus(empty);
    }

    @Override
    public void setFocusable(boolean focusable) {
        final T adapter = getAdapter();
        final boolean empty = adapter == null || adapter.getCount() == 0;

        mDesiredFocusableState = focusable;
        if (!focusable) {
            mDesiredFocusableInTouchModeState = false;
        }
        super.setFocusable(focusable && (!empty));
    }

    @Override
    public void setFocusableInTouchMode(boolean focusable) {
        final T adapter = getAdapter();
        final boolean empty = adapter == null || adapter.getCount() == 0;
        mDesiredFocusableInTouchModeState = focusable;
        if (focusable) {
            mDesiredFocusableState = true;
        }
        super.setFocusableInTouchMode(focusable && (!empty));
    }

    void checkFocus() {
        final T adapter = getAdapter();
        final boolean empty = adapter == null || adapter.getCount() == 0;
        final boolean focusable = !empty;
        super.setFocusableInTouchMode(focusable && mDesiredFocusableInTouchModeState);
        super.setFocusable(focusable && mDesiredFocusableState);
        if (mEmptyView != null) {
            updateEmptyStatus((adapter == null) || adapter.isEmpty());
        }
    }

    private void updateEmptyStatus(boolean empty) {
        if (empty) {
            if (mEmptyView != null) {
                mEmptyView.setVisibility(View.VISIBLE);
                setVisibility(View.GONE);
            } else {
                setVisibility(View.VISIBLE);
            }
        } else {
            if (mEmptyView != null) {
                mEmptyView.setVisibility(View.GONE);
            }
            setVisibility(View.VISIBLE);
        }
    }

    public Object getItemAtPosition(int position) {
        T adapter = getAdapter();
        return (adapter == null || position < 0) ? null : adapter.getItem(position);
    }

    public long getItemIdAtPosition(int position) {
        T adapter = getAdapter();
        return (adapter == null || position < 0) ? INVALID_ROW_ID : adapter.getItemId(position);
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        throw new RuntimeException();
    }

    @Override
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        dispatchFreezeSelfOnly(container);
    }

    @Override
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        dispatchThawSelfOnly(container);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    protected boolean canAnimate() {
        return super.canAnimate() && mItemCount > 0;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    protected boolean isSupportHardwareAccelerated() {
        return (Build.VERSION.SDK_INT>=11) && isHardwareAccelerated();
    }

    class AdapterDataSetObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            mDataChanged = true;
            mOldItemCount = mItemCount;
            mItemCount = getAdapter().getCount();
            checkFocus();
            requestLayout();
        }

        @Override
        public void onInvalidated() {
            mDataChanged = true;
            mOldItemCount = mItemCount;
            mItemCount = 0;
            checkFocus();
            requestLayout();
        }
    }
}
