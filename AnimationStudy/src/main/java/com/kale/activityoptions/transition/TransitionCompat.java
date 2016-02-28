package com.kale.activityoptions.transition;

import java.util.ArrayList;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.kale.activityoptions.ActivityOptionsCompatICS;
import com.kale.activityoptions.anim.SceneFade;
import com.kale.activityoptions.anim.SceneScaleUp;
import com.kale.activityoptions.anim.ViewAnim;
import com.kale.activityoptions.util.Position;

public class TransitionCompat {
	
	private final static String TAG = "TransitionCompat";

	private static Bundle mBundle;
	private static int mAnimationType;
	
	private static boolean mIsVerticalScreen;
	private static boolean mIsInTheScreen;
	private static boolean mIsStartFullScreen;
	// 存放bitmap的对象
	private static Bitmap mThumbnail;
	
	private static int mWidth;
	private static int mHeight;
	private static int mStartX;
	private static int mStartY;
	
	private static int mLayoutResId;
    // 分享元素的id和id list
	private static int mSharedElementId;
    private static ArrayList<Integer> mSharedElementIds;
    // 分享元素的绘制区域
    private static ArrayList<Rect> mSharedElementBounds;
    // 分享元素是否在屏幕上的boolean数组
    private static boolean[] mIsInTheScreenArr;
    // 判断动画是否在进行中
    private static boolean isPlaying = false;
	// 设定ThumbnailScaleUp和sceneTransition动画的差值器和屏幕动画无关，屏幕动画应该用接口自定义实现
    private static TimeInterpolator mInterpolator = new AccelerateDecelerateInterpolator();
	private static long mAnimTime = 300;//default
	private static long mStartDelay = 0;
	
	private static TransitionAnims mTransitionAnims = null;
	
	/** @hide 内部的listener */
	private static MyTransitionListener mListener;
	private static ViewAnimationListener mViewAnimListener;
	private static TransitionListener mTransitionListener;
	
	public static boolean isEnter;// 判断当前是否是activity的进入状态
	
	/**
	 * @param activity
	 * @param layoutResId
	 * @param background
	 */
	public static void startTransition(final Activity activity, final int layoutResId) {
		if (isPlaying) {
			return;
		}
		Bundle bundle = activity.getIntent().getExtras();
		if (bundle == null) {
			Log.w(TAG, "ActivityOptions's Bundle is null");
			return;
		}
		isEnter = true;
		mListener = new TransitionCompat().new MyTransitionListener();
		mBundle = bundle;
		mAnimationType = bundle.getInt(ActivityOptionsCompatICS.KEY_ANIM_TYPE, ActivityOptionsCompatICS.ANIM_NONE);
		mIsVerticalScreen = bundle.getBoolean(ActivityOptionsCompatICS.KEY_IS_VERTICAL_SCREEN);
		/**
		 * 根据type的不同开始执行不同的动画效果
		 */
		switch (mAnimationType) {
		
		case ActivityOptionsCompatICS.ANIM_SCALE_UP:
			mIsInTheScreen = bundle.getBoolean(ActivityOptionsCompatICS.KEY_IS_IN_THE_SCREEN);
			mWidth = bundle.getInt(ActivityOptionsCompatICS.KEY_ANIM_WIDTH);
			mHeight = bundle.getInt(ActivityOptionsCompatICS.KEY_ANIM_HEIGHT);
			mStartX = bundle.getInt(ActivityOptionsCompatICS.KEY_ANIM_START_X);
			mStartY = bundle.getInt(ActivityOptionsCompatICS.KEY_ANIM_START_Y);
			//开始执行动画，这里的tue表示是开始状态
			scaleUpAnimation(activity, true);
			break;
			
		case ActivityOptionsCompatICS.ANIM_THUMBNAIL_SCALE_UP:
			mIsStartFullScreen = bundle.getBoolean(ActivityOptionsCompatICS.KEY_IS_START_FULL_SCREEN);
			mThumbnail = (Bitmap) bundle.getParcelable(ActivityOptionsCompatICS.KEY_ANIM_THUMBNAIL);
			mIsInTheScreen = bundle.getBoolean(ActivityOptionsCompatICS.KEY_IS_IN_THE_SCREEN);
			mWidth = bundle.getInt(ActivityOptionsCompatICS.KEY_ANIM_WIDTH);
			mHeight = bundle.getInt(ActivityOptionsCompatICS.KEY_ANIM_HEIGHT);
			mStartX = bundle.getInt(ActivityOptionsCompatICS.KEY_ANIM_START_X);
			mStartY = bundle.getInt(ActivityOptionsCompatICS.KEY_ANIM_START_Y);
			//开始执行动画，这里的tue表示是开始状态
			thumbnailScaleUpAnimation(activity, true);
			break;
			
		case ActivityOptionsCompatICS.ANIM_SCENE_TRANSITION:
			mIsStartFullScreen = bundle.getBoolean(ActivityOptionsCompatICS.KEY_IS_START_FULL_SCREEN);
			mIsInTheScreenArr = bundle.getBooleanArray(ActivityOptionsCompatICS.kEY_IS_IN_THE_SCREEN_ARR);
			mSharedElementIds = bundle.getIntegerArrayList(ActivityOptionsCompatICS.kEY_SHARED_ELEMENT_ID_LIST);
			mSharedElementBounds = bundle.getParcelableArrayList(ActivityOptionsCompatICS.kEY_SHARED_ELEMENT_BOUNDS_LIST);
			mLayoutResId = layoutResId;
			//开始执行动画，这里的tue表示是开始状态
			sceneTransitionAnimation(activity, layoutResId, true);
			break;
		case ActivityOptionsCompatICS.ANIM_NONE:
			break;
		default:
			break;
		}
		//执行场景进入的动画
		if (mTransitionAnims != null) {
			mTransitionAnims.setAnimsInterpolator(mInterpolator);
			mTransitionAnims.setAnimsStartDelay(mStartDelay);
			mTransitionAnims.setAnimsDuration(mAnimTime);
			mTransitionAnims.addListener(mListener);
			mTransitionAnims.playScreenEnterAnims();
		}
		mTransitionAnims = null;
		//起始完成后就用不到bundle了，为了避免干扰，这里置空
		mBundle = null;
	}
	
	/**
	 * 在Activity.onBackPressed()中执行的方法，请不要执行onBackPressed()的super方法体
	 * 这时候已经可以确保要执行动画的view显示完成了，所以可以安全的执行这个方法
	 * 
	 * @param activity
	 * @param enterAnim
	 * @param exitAnim
	 */
	public static void finishAfterTransition(Activity activity, int enterAnim, int exitAnim) {
		isEnter = false;
		activity.overridePendingTransition(enterAnim, exitAnim);
	}
	
	/**
	 * 在Activity.onBackPressed()中执行的方法，请不要执行onBackPressed()的super方法体
	 * 这时候已经可以确保要执行动画的view显示完成了，所以可以安全的执行这个方法
	 * 
	 * @param activity
	 */
	public static void finishAfterTransition(Activity activity) {
		if (isPlaying) {
			return;
		}
		isEnter = false;
		activity.setResult(ActivityOptionsCompatICS.RESULT_CODE);
		mListener = new TransitionCompat().new MyTransitionListener();
		//开始执行动画，这里的false表示执行结束的动画
		switch (mAnimationType) {
		
		case ActivityOptionsCompatICS.ANIM_SCALE_UP:
			scaleUpAnimation(activity, false);
			break;
		case ActivityOptionsCompatICS.ANIM_THUMBNAIL_SCALE_UP:
			thumbnailScaleUpAnimation(activity, false);
			break;
		case ActivityOptionsCompatICS.ANIM_SCENE_TRANSITION:
			sceneTransitionAnimation(activity, mLayoutResId, false);
			break;
		case ActivityOptionsCompatICS.ANIM_NONE:
			activity.finish();
			return;
		default:
			activity.finish();
			return;
		}
		//执行场景退出的动画
		if (mTransitionAnims != null) {
			
			mTransitionAnims.setAnimsInterpolator(mInterpolator);
			mTransitionAnims.setAnimsStartDelay(mStartDelay);
			mTransitionAnims.setAnimsDuration(mAnimTime);
			mTransitionAnims.addListener(mListener);
			mTransitionAnims.playScreenExitAnims();
		}
		mTransitionAnims = null;
	}
	
	////////////////////////// 下面是各种动画的方法 ///////////////////////////
	
	/**
	 * 
	 * @param activity：当前的activity，可以是actionbarActivity
	 * @param isStart：是否是开始动画，如果是fale则执行结束的动画
	 * 
	 * 执行ScaleUpAnimation动画，包括开始和结束的动画效果。
	 * 【开始】
	 * 前提判断开始的activity和当前的activity是不是处于同一个横竖模式，开始的activity的view是不是处于屏幕上
	 * 这个动画涉及到了渐变，拉伸，移动的效果，三个动画效果需要同时执行。
	 * 这里的动画起始坐标是相对于屏幕的坐标，渐变是从无到有。
	 * alpha 0f->1f
	 * x,y (startX , startY)->(0,0)屏幕左上角是(0,0)，注意下要通过setPivotX，setPivotY设定位移的开始坐标
	 * scaleX，scaleY都是通过计算得出的，这个计算也比较容易就是算开始的宽高和屏幕宽高的比值。
	 * 计算好后就可以执行动画了。这里的动画仅仅是做平面的动画，动画的view是当前屏幕的根视图。
	 * 我为了动画执行方便，所以根视图我设定的是透明，所以用到动画效果的时候，可以自己给activity的主背景进行设置颜色。
	 * 【结束】
	 * 结束也是一样，开始判断横竖屏模式，还有起始时view在不在屏幕上
	 * 接着就开始执行动画了，这部分比较简单，就是简单的基础动画。难点在于获得activity的根view，还有设定动画透明度
	 */
	private static void scaleUpAnimation(final Activity activity, final boolean isEnter) {
		//如果开始的view不在屏幕上，那么就不执行动画
		if (!mIsInTheScreen) {
			return;
		}	
		//如果当前手机的横竖模式和前一个activity的横竖模式不同，就不执行动画
		if (mIsVerticalScreen != ActivityOptionsCompatICS.isVerticalScreen(activity)) {
			return;
		}
		//执行屏幕的动画
		if (mTransitionAnims == null) {
			final SceneScaleUp anim = new SceneScaleUp(activity,
					mStartX, mStartY, mWidth, mHeight);
			anim.setAnimsInterpolator(mInterpolator);
			anim.setAnimsStartDelay(mStartDelay);
			anim.setAnimsDuration(mAnimTime);
			anim.addListener(mListener);
			activity.getWindow().getDecorView().post(new Runnable() {
				
				@Override
				public void run() {
					// TODO 自动生成的方法存根
					anim.playScreenAnims(isEnter);
				}
			});
			
		}
	}
	
	/**
	 * @param activity：当前的activity，可以是actionbarActivity
	 * @param isStart：是否是开始动画，如果是fale则执行结束的动画
	 * 
	 * 为什么api里面有这个方法呢，其实很简单要想实现一个view放到渐变为activity的效果就必须让另一个
	 * activity得到这个view，才能执行动画。但问题是bundle是不能传递view对象的，除非实现某个接口
	 * 或者是用其他的强制转换方法。但就算传递过来了view的参数怎么弄呢？还有父控件的各种设置，不厌其烦。
	 * 最后退而求其次来传递一个bitmap对象，因为bitmap实现了Parcelable的接口，所以可以通过bundle
	 * 来进行传递。在新的activity得到这个bitmap后完全可以将其放入一个imageview中产生一个view，
	 * 通过view执行动画，就能最终实现这个效果啦。
	 * 这个效果和分享元素的效果挺像的，二者有什么不同呢？这个效果的view在第二个activity没有对应的view，
	 * 也就是仅仅一个view没办法产生两点一线，自然就不能像分享元素动画效果那样构成补间动画了。
	 * 【开始】
	 * 至于动画的开始和结束和scale的动画十分相像，view动画的最终位置是整个屏幕，并且最后的透明度应该是0
	 * 【结束】
	 * 动画开始view的透明度是0，最后通过动画变得可见，最后回到最初的坐标点。
	 */
	private static void thumbnailScaleUpAnimation(final Activity activity, final boolean isEnter) {
		//如果开始的view不在屏幕上，那么就不执行动画
		if (!mIsInTheScreen) {
			return;
		}	
		//如果当前手机的横竖模式和前一个activity的横竖模式不同，就不执行动画
		if (mIsVerticalScreen != ActivityOptionsCompatICS.isVerticalScreen(activity)) {
			return;
		}
		if (isEnter) {
			startBitmapAnimation(activity);
		}else {
			endBitmapAnimation(activity);
		}
		//执行屏幕的动画
		if (mTransitionAnims == null) {
			final SceneScaleUp anim = new SceneScaleUp(activity,
					mStartX, mStartY, mWidth, mHeight);
			anim.setAnimsInterpolator(mInterpolator);
			anim.setAnimsStartDelay(mStartDelay);
			anim.setAnimsDuration(mAnimTime);
			anim.addListener(mListener);
			activity.getWindow().getDecorView().post(new Runnable() {
				
				@Override
				public void run() {
					// TODO 自动生成的方法存根
					anim.playScreenAnims(isEnter);
				}
			});
			
		}
	}
	
	/**
	 * @param activity
	 * @return 通过bitmap得到一个fitXY的imageview，用来做动画。
	 */
	private static ImageView getThumbnailOriginalImageView(Activity activity) {
		//如果目标图片没有显示在屏幕上，直接不执行动画
		if (!mIsInTheScreen) {
			return null;
		}
		//如果当前手机的横竖模式和前一个activity的横竖模式不同，就不执行动画
		if (mIsVerticalScreen != ActivityOptionsCompatICS.isVerticalScreen(activity)) {
			return null;
		}
		ImageView bitmapImageView = new ImageView(activity);
		bitmapImageView.setImageBitmap(mThumbnail);
		bitmapImageView.setScaleType(ScaleType.FIT_XY);
		return bitmapImageView;
	}
	
	/**
	 * 开始执行bitmap的动画效果，这里从透明度100%开始渐变
	 * @param activity
	 */
	private static void startBitmapAnimation(Activity activity) {
		float fraction = 0.8f;//设置动画的比率，这个动画会先于屏幕动画完成，屏幕动画的比率是1f
		final ImageView bitmapImageView = getThumbnailOriginalImageView(activity);
		/**
		 * 开始设定view开始时的大小和坐标位置
		 */
		LayoutParams orginalParams = new LayoutParams(mWidth, mHeight);
		ViewGroup rootView = (ViewGroup)(activity.getWindow().getDecorView());
		rootView.addView(bitmapImageView, orginalParams);
		bitmapImageView.setX(mStartX);
		bitmapImageView.setY(mStartY);
	
		final Rect finalBounds = new Rect(0, 0, 
				(int)(ActivityOptionsCompatICS.getScreenWidth(activity) * fraction),
				(int)(ActivityOptionsCompatICS.getScreenHeight(activity) * fraction));
		/**
		 * 这里可以设置动画的持续时间，开始延迟，添加监听器
		 */
		final ViewAnim anim = new ViewAnim();
		anim.setDuration((long) (mAnimTime * fraction));
		anim.setStartDelay(mStartDelay);
		anim.addListener(mViewAnimListener);
		anim.setTimeInterpolator(mInterpolator);
		bitmapImageView.post(new Runnable() {
			
			@Override
			public void run() {
				/**其实这里可以计算偏移量的，但因为是从不透明到透明，最后的位置对于动画影响相当小，所以为了效率
				 * 就没有像sceneTransitionAnimation中一样进行详细的计算。如果真的有需求可以参考
				 * sceneTransitionAnimation中的计算方式，二者是完全一样的。
				 */
				anim.startViewSimpleAnim(bitmapImageView, finalBounds, 0, 0, 1f, 0f);
			}
		});
	}
	
	/**
	 * 设定结束动画，bitmap从透明度0f变为1f
	 * @param activity
	 */
	private static void endBitmapAnimation(Activity activity) {
		final ImageView bitmapImageView = getThumbnailOriginalImageView(activity);
		bitmapImageView.setVisibility(View.INVISIBLE);
		/**
		 * 开始设定view开始时的大小和坐标位置
		 */
		LayoutParams orginalParams = new LayoutParams(
				ActivityOptionsCompatICS.getScreenWidth(activity), 
				ActivityOptionsCompatICS.getScreenHeight(activity));
		
		ViewGroup rootView = (ViewGroup)(activity.getWindow().getDecorView());
		rootView.addView(bitmapImageView, orginalParams);
		bitmapImageView.setX(0);
		bitmapImageView.setY(0);
		/**
		 * 根据两个activity是否是全屏来计算开始的坐标和偏移量
		 * 从全屏切换到不全屏会出现位置偏移，这里进行处理
		 */
		final int finalOffsetY;
		boolean isFinalFullScreen = ActivityOptionsCompatICS.isFullScreen(activity);
		if (mIsStartFullScreen == false && isFinalFullScreen == true) {
			finalOffsetY = ActivityOptionsCompatICS.getStatusBarHeight(activity);
		}
		else {
			finalOffsetY = 0;
		}
		final Rect finalBounds = new Rect(mStartX, mStartY, mStartX + mWidth,mStartY + mHeight);
		/**
		 * 这里可以设置动画的持续时间，开始延迟，添加监听器
		 */
		final ViewAnim anim = new ViewAnim();
		anim.setDuration((long) (mAnimTime));
		anim.setStartDelay(mStartDelay);
		anim.addListener(mViewAnimListener);
		anim.setTimeInterpolator(mInterpolator);
		bitmapImageView.post(new Runnable() {
			
			@Override
			public void run() {
				anim.startViewSimpleAnim(bitmapImageView, finalBounds, 0, finalOffsetY, 0f, 1f);
			}
		});
	}
	
	/**
	 * @param activity
	 * @param layoutResId：当前activity的content的layout
	 * @param isStart:是否是开始动画，如果是fale则执行结束的动画
	 * 
	 * 执行SceneTransitionAnimation动画效果，包括开始和结束。（在view显示完整后才能有效）
	 * 【开始】
	 * 首先执行元素动画，元素动画首先进行判断执行动画的元素和目标元素是否在屏幕上显示，如果二者有一个或多个不在屏幕上，
	 * 那就不执行动画，否则开始元素动画。元素动画和屏幕动画是同时进行的，前后无太大关系。为了保证效果的流畅这里将二者的动画
	 * 持续时间设置为一样的。
	 * 屏幕动画默认是从没有渐变显示到全部可见，可以通过接口来自定义
	 * 这里不用activity的api动画的原因是：如果用api动画，那么元素也会跟着执行动画，不符合元素一直可见的要求。
	 * 在两个activity切换时我们可能会遇到各种问题，比如两个activity可能是一个全屏一个不全屏，或者是切换到新的activity后
	 * 新的activity改变了屏幕的方向，也就是说两个activity的横竖不一致。对于全屏的问题我这里通过计算偏移量解决了，对于
	 * 横竖屏不一致的问题，我直接做了忽视。因为这种情况相当少见，出现这种情况就执行屏幕动画而不做元素动画。
	 * 【结束】
	 * 结束时仍旧是让元素动画和屏幕动画一起执行。
	 * 结束时仍旧要判断要执行动画的元素是否在屏幕上，如果不在就不执行动画了。由于屏幕滚动的问题，元素被销毁或者不在屏幕上是很常见的。
	 * 起始这里还没有考虑一种情况，那么就是如果元素被遮挡，是不是也不应该做元素动画。目前没找到元素被遮挡的方案，可能会用元素当前
	 * 状态来判断，如果有新的判断方式，可以到activityOption中的isInScreen方法中进行添加。
	 */
	private static void sceneTransitionAnimation(Activity activity, int layoutResId, boolean isEnter) {
		for (int i = 0; i < mSharedElementIds.size(); i++) {
			mIsInTheScreen = mIsInTheScreenArr[i];
			mSharedElementId = mSharedElementIds.get(i);
			Rect bounds = mSharedElementBounds.get(i);
			mStartX = bounds.left;
			mStartY = bounds.top;
			mWidth = bounds.width();
			mHeight = bounds.height();
			if (isEnter) {
				startSharedElementAnimation(activity, layoutResId);
			}else {
				endSharedElementAnimation(activity, layoutResId);
			}
		}
		// 执行屏幕的动画
		if (mTransitionAnims == null) {
			SceneFade anim = new SceneFade(activity);
			anim.setAnimsInterpolator(mInterpolator);
			anim.setAnimsStartDelay(mStartDelay);
			anim.setAnimsDuration(mAnimTime);
			anim.addListener(mListener);
			anim.playScreenAnims(isEnter);
		}
	}
	
	/**
	 * @param activity
	 * @param layoutResId
	 * @return 得到已经初始化的原始view，如果找不到就返回null
	 */
	private static View getSharedElementOrginalView(Activity activity, int layoutResId) {
		//如果目标图片没有显示在屏幕上，直接不执行动画
		if (!mIsInTheScreen) {
			return null;
		}
		//如果当前手机的横竖模式和前一个activity的横竖模式不同，就不执行动画
		if (mIsVerticalScreen != ActivityOptionsCompatICS.isVerticalScreen(activity)) {
			return null;
		}
		//只有用填充器获得的对象才能正常执行动画，用activity的FindviewById就不行
		View parentView = activity.getLayoutInflater().inflate(layoutResId, null);
		//通过id找到这个原始控件，注意这个控件仅仅是从layout中获取的，没有进行代码中的各种设置
		final View orginalView = parentView.findViewById(mSharedElementId);
		if (orginalView == null) {
			Log.e(TAG, "Cann't find the view with id = "+mSharedElementId);
			return null;
		}
		/**
		 * 在这里初始化控件
		 * example:
		 * Button btn = (Button) orginalView.findViewById(R.id.from_button);
		 * btn.setText("button")
		 */
		if (mViewAnimListener != null) {
			mViewAnimListener.onInitAnimationViews(orginalView, mSharedElementId);
		}
		//得到父控件移除要执行动画的控件，将其加入到新的一层中。这样就可以脱离父控件执行动画了
		final ViewGroup viewParent = (ViewGroup) orginalView.getParent();
		viewParent.removeView(orginalView);//必须要先移除控件才能添加到别的view中
		return orginalView;
	}
	
	/**
	 * 开始执行动画的方法
	 * @param activity：当前的activity对象
	 * @param layoutResId：当前activity的布局文件id，如R.layout.xx
	 * @param targetView：要执行动画的控件
	 */
	private static void startSharedElementAnimation(final Activity activity, int layoutResId) {
		final View orginalView = getSharedElementOrginalView(activity, layoutResId);
		if (orginalView == null) {
			return;
		}
		/**
		 * 根据两个activity是否是全屏来计算开始的坐标和偏移量
		 * 从全屏切换到不全屏就会卡顿，系统为了消除卡顿，可能会做个动画。所以不推荐两个不同模式的Activity相互切换
		 */
		final int finalOffsetY;
		boolean isFinalFullScreen = ActivityOptionsCompatICS.isFullScreen(activity);
		if (mIsStartFullScreen == true && isFinalFullScreen == false) {
			finalOffsetY = -ActivityOptionsCompatICS.getStatusBarHeight(activity);
		}
		else {
			finalOffsetY = 0;
		}
		/**
		 * 开始设定view开始时的大小和坐标位置
		 */
		LayoutParams orginalParams = new LayoutParams(mWidth, mHeight);
		ViewGroup rootView = (ViewGroup)(activity.getWindow().getDecorView());
		rootView.addView(orginalView, orginalParams);
		orginalView.setX(mStartX);
		orginalView.setY(mStartY);
		// 只有通过activity的findviewbyid获得的对象才能用post方法得到bounds，用mInflater不行
		final View targetView = activity.findViewById(mSharedElementId);
		
		/**
		 * 已经获得原始的view并且知道了目标的view区域，开始执行动画
		 * 这里可以设置动画的持续时间，开始延迟，添加监听器
		 */
		final ViewAnim anim = new ViewAnim();
		anim.setDuration(mAnimTime);
		anim.setStartDelay(mStartDelay);
		anim.addListener(mViewAnimListener);
		anim.setTimeInterpolator(mInterpolator);
		orginalView.post(new Runnable() {
			
			@Override
			public void run() {
				// 如果目标view现在不在屏幕上那么就不执行动画（必须在显示完成后才能判断是否在屏幕上）
				if (!ActivityOptionsCompatICS.isInScreen(activity, targetView)) {
					targetView.setVisibility(View.VISIBLE);
					((ViewGroup)orginalView.getParent()).removeView(orginalView);
				}else {
					targetView.setVisibility(View.INVISIBLE);
					anim.startViewTweensAnim(orginalView, targetView, 0, finalOffsetY);
				}
			}
		});
	}
	
	/**
	 * 设置结束动画
	 * 可能需要动画的控件已经被销毁或者是不在屏幕上了，这时就不执行动画效果
	 * @param activity
	 * @param layoutResId
	 */
	private static void endSharedElementAnimation(final Activity activity, int layoutResId) {
		final View orginalView = getSharedElementOrginalView(activity, layoutResId);
		if (orginalView == null) {
			return;
		}
		View currentView = activity.findViewById(mSharedElementId);
		currentView.setVisibility(View.INVISIBLE);
		if (!ActivityOptionsCompatICS.isInScreen(activity, currentView)) {
			return;//如果现在这个控件不在屏幕上，那么就不执行动画
		}
		Rect orginalBounds = new Rect();
		orginalBounds.set(Position.getGlobalVisibleRect(currentView));
		/**
		 * 根据两个activity是否是全屏来计算开始的坐标和偏移量
		 * 从全屏切换到不全屏就会卡顿，系统为了消除卡顿，可能会做个动画。所以不推荐两个不同模式的Activity相互切换
		 */
		final int finalOffsetY;
		boolean isFinalFullScreen = ActivityOptionsCompatICS.isFullScreen(activity);
		if (mIsStartFullScreen == false && isFinalFullScreen == true) {
			finalOffsetY = ActivityOptionsCompatICS.getStatusBarHeight(activity);
		}
		else {
			finalOffsetY = 0;
		}
		/**
		 * 开始设定view开始时的大小和坐标位置
		 */
		LayoutParams orginalParams = new LayoutParams(orginalBounds.width(),orginalBounds.height());
		ViewGroup rootView = (ViewGroup)(activity.getWindow().getDecorView());
		rootView.addView(orginalView, orginalParams);
		orginalView.setX(orginalBounds.left);
		orginalView.setY(orginalBounds.top);
		/**
		 * 获得目标view的显示范围
		 */
		final Rect targetBounds = new Rect(mStartX, mStartY, mStartX + mWidth, mStartY + mHeight);
		/**
		 * 已经获得原始的view并且知道了目标的view区域，开始执行动画
		 * 这里可以设置动画的持续时间，开始延迟，添加监听器
		 */
		final ViewAnim anim = new ViewAnim();
		anim.setDuration(mAnimTime);
		anim.setStartDelay(mStartDelay);
		anim.addListener(mViewAnimListener);//设置监听器来监听动画完成
		anim.setTimeInterpolator(mInterpolator);
		orginalView.post(new Runnable() {
			
			@Override
			public void run() {
				anim.startViewTweensAnim(orginalView, targetBounds, 0, finalOffsetY);
			}
		});
	}
	

	/////////////////////////////// 上面已经完成了动画 ///////////////////////////////////
	
	public Bundle getBundle() {
		return mBundle;
	}
	
	/**
	 * 设置动画效果，其实都是一样的，为了便于区做了两个方法，用之前进行设置即可
	 * @param transition
	 */
	public static void setEnterTransition(TransitionAnims transition) {
		mTransitionAnims = transition;
	}
	
	public static void setExitTransition(TransitionAnims animation) {
		setEnterTransition(animation);
	}
	
	/**
	 * 设置动画的持续时间，单位：ms
	 * 在动画开始前进行设置
	 * @param time
	 */
	public static void setAnimDuration(long time) {
		mAnimTime = time;
	}
	
	public static long getAnimDuration() {
		return mAnimTime;
	}
	
	public static void setAnimStartDelay(long delayTime) {
		mStartDelay = delayTime;
	}
	
	public static long getAnimStartDelay() {
		return mStartDelay;
	}
	
	public static void setTimeInterpolator(TimeInterpolator interpolator) {
		mInterpolator = interpolator;
	}
	
	
	
	/**
	 * @author:Jack Tony
	 * @tips  :设置View动画的监听器，这个是用到viewAnim中的，和屏幕动画无关
	 * @date  :2014-11-22
	 */
	public interface ViewAnimationListener{
		public void onInitAnimationViews(View view, int id);
		public void onViewAnimationStart(View view, Animator animator);
		public void onViewAnimationUpdate(View view, ValueAnimator valueAnimator, float progress);
		public void onViewAnimationEnd(View view, Animator animator);
		public void onViewAnimationCancel(View view, Animator animator);
	}
	
	public static void addViewAnimListener(ViewAnimationListener viewAnimListener) {
		mViewAnimListener = viewAnimListener;
	}
	
	/**
	 * @author:Jack Tony
	 * @tips  :设置屏幕动画的监听器
	 * @date  :2014-11-22
	 */
	public interface TransitionListener{
		public void onTransitionStart(Animator animator, Animation animation, boolean isEnter);
		public void onTransitionEnd(Animator animator, Animation animation, boolean isEnter);
		public void onTransitionCancel(Animator animator, Animation animation, boolean isEnter);
	}
	
	public static void addListener(TransitionListener transitionListener) {
		mTransitionListener = transitionListener;
	}
	
	/**
	 * @author:Jack Tony
	 * @tips  :用来判断动画执行的监听器，如果动画在执行中，那么就不会再启动动画了。只有当动画执行完成才能再次启动动画
	 * 这里传入的enter是通过这个类得到的，和监听器无关。因为通过这个类才能准确的得到当前是进入状态还是退出状态
	 * @date  :2014-11-26
	 * @hide
	 */
	private class MyTransitionListener implements TransitionListener{

		@Override
		public void onTransitionStart(Animator animator, Animation animation, boolean e) {
			// TODO 自动生成的方法存根
			if (mTransitionListener != null) {
				mTransitionListener.onTransitionStart(animator, animation, isEnter);
			}
			isPlaying = true;
		}

		@Override
		public void onTransitionEnd(Animator animator, Animation animation, boolean e) {
			// TODO 自动生成的方法存根
			if (mTransitionListener != null) {
				mTransitionListener.onTransitionEnd(animator, animation, isEnter);
			}
			isPlaying = false;
		}

		@Override
		public void onTransitionCancel(Animator animator, Animation animation, boolean e) {
			// TODO 自动生成的方法存根
			if (mTransitionListener != null) {
				mTransitionListener.onTransitionCancel(animator, animation, isEnter);
			}
		}
		
	}
	


	

}
