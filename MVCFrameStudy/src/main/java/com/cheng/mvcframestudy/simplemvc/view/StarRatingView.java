package com.cheng.mvcframestudy.simplemvc.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.cheng.mvcframestudy.R;
import com.cheng.mvcframestudy.simplemvc.controller.StarRatingController;
import com.cheng.mvcframestudy.simplemvc.model.StarRatingModel;

public final class StarRatingView extends View implements StarRatingModel.Listener {

	private StarRatingModel model;
	private StarRatingController controller;
	
	private float starSize;
	private PointF starTopLeft = new PointF();
	private RectF starRect = new RectF();	

	private Bitmap starBitmap;
	private Paint goldPaint;
	private Paint grayPaint;
	
	public StarRatingView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public StarRatingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public StarRatingView(Context context) {
		super(context);
		init();
	}

	private void init() {
		starBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.star);
		
		goldPaint = new Paint();
		goldPaint.setFilterBitmap(true);
		
		grayPaint = new Paint();
		grayPaint.setColorFilter(new LightingColorFilter(0xff111122, 0xffcccccc));
		grayPaint.setFilterBitmap(true);
		
		setModel(new StarRatingModel());
	}

	@Override
	protected void onDraw(Canvas canvas) {
		for (int i = 0; i < StarRatingModel.MAX_STARS; ++i) {
			float starX = starTopLeft.x + i * starSize;
			float starY = starTopLeft.y;
			
			Paint paint = null;
			if ((i + 1) <= model.getStars()) {
				// draw a gold star
				paint = goldPaint;
			} else {
				// draw a gray star
				paint = grayPaint;
			}
			
			starRect.set(starX, starY, starX + starSize, starY + starSize);
			canvas.drawBitmap(starBitmap, null, starRect, paint);
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		
		if (widthMode != MeasureSpec.EXACTLY) {
			widthSize = 30 * StarRatingModel.MAX_STARS;
		}

		int width = widthSize;
		int height = heightSize;
		if (heightMode != MeasureSpec.EXACTLY) {
			height = width / StarRatingModel.MAX_STARS;
		}
		
		setMeasuredDimension(width, height);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		
		// We have to fit as much as MAX_STARS into the space we have
		starSize = Math.min(h, ((float) w) / StarRatingModel.MAX_STARS);
		starTopLeft.x = (w - starSize * StarRatingModel.MAX_STARS) / 2;
		starTopLeft.y = (h - starSize) / 2;
	}
	
	public void setModel(StarRatingModel model) {
		if (model == null) {
			throw new NullPointerException("model");
		}
		
		StarRatingModel oldModel = this.model;
		if (oldModel != null) {
			oldModel.removeListener(this);
		}
		this.model = model;
		this.model.addListener(this);
		this.controller = new StarRatingController(this.model);
		
		if (oldModel != null) {
			invalidate();
		}
	}
	
	public StarRatingModel getModel() {
		return model;
	}

	@Override
	public void handleStarRatingChanged(StarRatingModel sender) {
		invalidate();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			controller.handleTap(event);
			return true;
		} else {
			return super.onTouchEvent(event);
		}
	}
}
