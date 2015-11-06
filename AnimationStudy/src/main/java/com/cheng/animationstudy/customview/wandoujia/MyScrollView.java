package com.cheng.animationstudy.customview.wandoujia;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;
/**
 * 
 * @author Darcy
 *
 */
public class MyScrollView extends ScrollView{

	private OnScrollChangedListener mScrollListener;
	
	public MyScrollView(Context context) {
		super(context);
	}

	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setOnScrollChangedListener(OnScrollChangedListener l){
		this.mScrollListener = l;
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if(mScrollListener != null)
			mScrollListener.onScrollChanged(l, t, oldl, oldt);
	}
	
	public interface OnScrollChangedListener{
		void onScrollChanged(int l, int t, int oldl, int oldt);
	}
}
