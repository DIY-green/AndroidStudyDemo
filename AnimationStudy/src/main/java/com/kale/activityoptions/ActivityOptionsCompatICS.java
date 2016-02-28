package com.kale.activityoptions;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.kale.activityoptions.util.Position;

public class ActivityOptionsCompatICS {
	
	/**
	 * 传递的结果码，用来判断
	 */
	public final static int RESULT_CODE = 1314;
	
	/**
	 * 结果码的KEY
	 */
	public static String KEY_RESULT_CODE = "kale:resultCode";
	
    /**
     * Type of animation that arguments specify.
     * 设置动画的类型，在启动activity时用到
     * @hide
     */
    public static final String KEY_ANIM_TYPE = "kale:animType";
    
    /**
     * Custom enter animation resource ID.
     * 进入动画的id
     */
    public static final String KEY_ANIM_ENTER_RES_ID = "kale:animEnterRes";

    /**
     * Custom exit animation resource ID.
     * 退出动画的id
     */
    public static final String KEY_ANIM_EXIT_RES_ID = "kale:animExitRes";

    /**
     * Bitmap for thumbnail animation.
     * 进行拉伸效果的bitmap
     */
    public static final String KEY_ANIM_THUMBNAIL = "kale:animThumbnail";
    
    /**
     * Start X position of thumbnail animation.
     * 设置view开始的X坐标，用于绘制控件的原始位置
     */
    public static final String KEY_ANIM_START_X = "kale:animStartX";
    
    /**
     * Start Y position of thumbnail animation.
     * 设置view开始的Y坐标，用于绘制控件的原始位置
     */
    public static final String KEY_ANIM_START_Y = "kale:animStartY";
    
    /**
     * Initial width of the animation.
     * 设置view开始的的宽度，用户绘制控件的原始宽度
     */
    public static final String KEY_ANIM_WIDTH = "kale:animWidth";
    
    /**
     * Initial height of the animation.
     * 设置view开始的高度，用于绘制控件开始的高度
     */
    public static final String KEY_ANIM_HEIGHT = "kale:animHeight";

    /**
     * 判断现在view是否在屏幕上显示
     */
    public static final String KEY_IS_IN_THE_SCREEN = "kale:isInTheScreen";
    
    /**
     * 判断当前屏幕是否是全屏
     */
    public static final String KEY_IS_START_FULL_SCREEN = "kale:isFullScreen";
    
    /**
     * 判断当前屏幕是否是竖屏
     */
    public static final String KEY_IS_VERTICAL_SCREEN = "kale:isVerticalScreen";
    
	/**
	 * 分享元素的id list
	 */
	public static String kEY_SHARED_ELEMENT_ID_LIST = "kale:sharedElementIdList";
	
	/**
	 * 分享元素显示区域的Rect list
	 */
	public static String kEY_SHARED_ELEMENT_BOUNDS_LIST = "kale:sharedElementBoundsList";
	
	/**
	 * 分享元素显示区域的Rect list
	 */
	public static String kEY_IS_IN_THE_SCREEN_ARR= "kale:isInTheScreenArr";
    
    /** 
     * 没有动画 
     */
    public static final int ANIM_NONE = 0;
    
    /** 
     * 自定义动画：makeCustomAnimation	
     */
    public static final int ANIM_CUSTOM = 1;
    
    /** 
     * 拉伸动画：makeScaleUpAnimation	
     */
    public static final int ANIM_SCALE_UP = 2;
    
    /** 
     * Bitmap的动画效果，从bitmap渐变到activity
     */
    public static final int ANIM_THUMBNAIL_SCALE_UP = 3;
    
    /** 
     * 屏幕动画效果：makeSceneTransitionAnimation	
     */
    public static final int ANIM_SCENE_TRANSITION = 5;

    /**
     * 默认的动画效果，等于没进行设置
     */
    public static final int ANIM_DEFAULT = 6;
    
    private int mAnimationType = ANIM_NONE;
    private int mCustomEnterResId;
    private int mCustomExitResId;
    private Bitmap mThumbnail;
    
    private int mStartX;
    private int mStartY;
    private int mWidth;
    private int mHeight;
    // 分享元素的id list
    private ArrayList<Integer> mSharedElementIds;
    // 分享元素的绘制区域
    private ArrayList<Rect> mSharedElementBounds;
    // 分享元素是否在屏幕上的boolean数组
    private boolean[] mIsInTheScreenArr;
    // 当前是否是竖屏
    private boolean mIsVerticalScreen;
    // 当前是否是全屏
    private boolean mIsStartFullScreen;
    // 当前view是否在屏幕上
    private boolean mIsInTheScreen;

    
    /**
     * Create an ActivityOptions specifying a custom animation to run when
     * the activity is displayed.
     *
     * @param context Who is defining this.  This is the application that the
     * animation resources will be loaded from.
     * @param enterResId A resource ID of the animation resource to use for
     * the incoming activity.  Use 0 for no animation.
     * @param exitResId A resource ID of the animation resource to use for
     * the outgoing activity.  Use 0 for no animation.
     * @return Returns a new ActivityOptions object that you can use to
     * supply these options as the options Bundle when starting an activity.
     */
    public static ActivityOptionsCompatICS makeCustomAnimation(Context context,
    		int enterResId, int exitResId) {
    	
    	ActivityOptionsCompatICS opts = new ActivityOptionsCompatICS();
    	// 设置动画类型和动画资源的id
    	opts.mAnimationType = ANIM_CUSTOM;
        opts.mCustomEnterResId = enterResId;
        opts.mCustomExitResId = exitResId;
        return opts;
    }
    
    /**
     * @param source：新activity开始动画的view，通过startX，startY来定义区域
	 * @param startX：动画开始的X坐标，相对于source左上角的X坐标
	 * @param startY：动画开始的Y坐标，相对于source左上角的Y坐标
	 * @param width：新activity的起始宽度
	 * @param height：新activity的起始高度
	 * 
     * Create an ActivityOptions specifying an animation where the new activity is
     * scaled from a small originating area of the screen to its final full
     * representation.
     * <p/>
     * If the Intent this is being used with has not set its
     * {@link android.content.Intent#setSourceBounds(android.graphics.Rect)},
     * those bounds will be filled in for you based on the initial bounds passed
     * in here.
     *
     * @param source The View that the new activity is animating from. This
     * defines the coordinate space for startX and startY.
     * @param startX The x starting location of the new activity, relative to
     * source.
     * @param startY The y starting location of the activity, relative to source.
     * @param startWidth The initial width of the new activity.
     * @param startHeight The initial height of the new activity.
     * @return Returns a new ActivityOptions object that you can use to supply
     * these options as the options Bundle when starting an activity.
     * 
     */
	public static ActivityOptionsCompatICS makeScaleUpAnimation(View source,
			int startX, int startY, int width, int height) {
		Activity activity = (Activity) source.getContext();
		ActivityOptionsCompatICS opts = new ActivityOptionsCompatICS();
		// 设置动画类型
		opts.mAnimationType = ANIM_SCALE_UP;
		// 判断当前是否是竖屏
		opts.mIsVerticalScreen = isVerticalScreen(activity);
		// 判断view是否在屏幕上，如果在就执行动画，否则不执行动画
		opts.mIsInTheScreen = isInScreen(activity, source);
		
		int[] pts = new int[2];//ps = position，目的得到当前view相对于屏幕的坐标
	    source.getLocationOnScreen(pts);
		// 设置起始坐标和起始宽高
		opts.mStartX = pts[0] + startX;
		opts.mStartY = pts[1] + startY;
		opts.mWidth = width;
		opts.mHeight = height;
		
		return opts;
	}

	
	/**
     * @param source：view
     * @param thumbnail：开始渐变效果的bitmap
     * @param startX：动画开始的X坐标，相对于source
     * @param startY：动画开始的Y坐标，相对于source
     * 
     * Create an ActivityOptions specifying an animation where a thumbnail
     * is scaled from a given position to the new activity window that is
     * being started.
     *
     * <p>If the Intent this is being used with has not set its
     * {@link android.content.Intent#setSourceBounds Intent.setSourceBounds},
     * those bounds will be filled in for you based on the initial
     * thumbnail location and size provided here.
     *
     * @param source The View that this thumbnail is animating from.  This
     * defines the coordinate space for <var>startX</var> and <var>startY</var>.
     * @param thumbnail The bitmap that will be shown as the initial thumbnail
     * of the animation.
     * @param startX The x starting location of the bitmap, relative to <var>source</var>.
     * @param startY The y starting location of the bitmap, relative to <var>source</var>.
     * @return Returns a new ActivityOptions object that you can use to
     * supply these options as the options Bundle when starting an activity.
     * 
     */
    public static ActivityOptionsCompatICS makeThumbnailScaleUpAnimation(View source,
            Bitmap thumbnail, int startX, int startY) {
    	Activity activity = (Activity) source.getContext();
    	ActivityOptionsCompatICS opts = new ActivityOptionsCompatICS();
        opts.mAnimationType = ANIM_THUMBNAIL_SCALE_UP;
        opts.mThumbnail = thumbnail;
        // 判断当前是否是竖屏
     	opts.mIsVerticalScreen = isVerticalScreen(activity);
     	// 判断当前activity是否是全屏模式
     	opts.mIsStartFullScreen = isFullScreen(activity);
     	// 判断view是否在屏幕上，如果在就执行动画，否则不执行动画
     	opts.mIsInTheScreen = isInScreen(activity, source);
     		
        int[] pts = new int[2];
        source.getLocationOnScreen(pts);
        opts.mStartX = pts[0] + startX;
        opts.mStartY = pts[1] + startY;
        opts.mWidth = thumbnail.getWidth();
        opts.mHeight = thumbnail.getHeight();
        return opts;
    }
	
	/**
	 * 这个方法必须放在view加载完后再执行，否则获取不到view的宽度和坐标
	 * @param activity：执行动画的activity
	 * @param sharedElement：执行动画的元素
	 * @param sharedElementId：新的activity中要接收动画效果的元素的id，这样就得到了两个view之间的联系
	 * 通过两点一线的方式来进行补间动画。
	 * 
     * Create an ActivityOptions to transition between Activities using cross-Activity scene
     * animations. This method carries the position of one shared element to the started Activity.
     * The position of <code>sharedElement</code> will be used as the epicenter for the
     * exit Transition. The position of the shared element in the launched Activity will be the
     * epicenter of its entering Transition.
     *
     * <p>This requires {@link android.view.Window#FEATURE_ACTIVITY_TRANSITIONS} to be
     * enabled on the calling Activity to cause an exit transition. The same must be in
     * the called Activity to get an entering transition.</p>
     * @param activity The Activity whose window contains the shared elements.
     * @param sharedElement The View to transition to the started Activity.
     * @param sharedElementId The shared element id as used in the target Activity. This
     *                          must not be null.
     * @return Returns a new ActivityOptions object that you can use to
     *         supply these options as the options Bundle when starting an activity.
     * @see android.transition.Transition#setEpicenterCallback(
     *          android.transition.Transition.EpicenterCallback)
     *          
     */
	@SuppressWarnings("unchecked")
	public static ActivityOptionsCompatICS makeSceneTransitionAnimation(Activity activity, 
			final View sharedElement,int sharedElementId) {
		return makeSceneTransitionAnimation(activity, Pair.create(sharedElement, sharedElementId));
	}
	
	
	/**
	 * 这个方法必须放在view加载完后再执行，否则获取不到view的宽度和坐标
	 * @param activity：执行动画的activity
	 * @param sharedElement：执行动画的元素
	 * @param sharedElementId：新的activity中要接收动画效果的元素的id，这样就得到了两个view之间的联系
	 * 通过两点一线的方式来进行补间动画。
	 * 
     * 传入多个view——id对，将这些信息传递到新的activity中。
	 * 这里的Rect实现了Parcelable接口，所以可以直接传递
	 * 
     * Create an ActivityOptions to transition between Activities using cross-Activity scene
     * animations. This method carries the position of multiple shared elements to the started
     * Activity. The position of the first element in sharedElements
     * will be used as the epicenter for the exit Transition. The position of the associated
     * shared element in the launched Activity will be the epicenter of its entering Transition.
     *
     * <p>This requires {@link android.view.Window#FEATURE_ACTIVITY_TRANSITIONS} to be
     * enabled on the calling Activity to cause an exit transition. The same must be in
     * the called Activity to get an entering transition.</p>
     * @param activity The Activity whose window contains the shared elements.
     * @param sharedElements The names of the shared elements to transfer to the called
     *                       Activity and their associated Views. The Views must each have
     *                       a unique shared element name.
     * @return Returns a new ActivityOptions object that you can use to
     *         supply these options as the options Bundle when starting an activity.
     * @see android.transition.Transition#setEpicenterCallback(
     *          android.transition.Transition.EpicenterCallback)
     *          
     */
	public static ActivityOptionsCompatICS makeSceneTransitionAnimation(Activity activity,
            Pair<View, Integer>... sharedElements) {
		
		ActivityOptionsCompatICS opts = new ActivityOptionsCompatICS();
		// 设置动画的类型
		opts.mAnimationType = ANIM_SCENE_TRANSITION;
		// 判断当前是否是竖屏
		opts.mIsVerticalScreen = isVerticalScreen(activity);
		// 判断当前activity是否是全屏模式
		opts.mIsStartFullScreen = isFullScreen(activity);
		// init
		opts.mSharedElementIds = new ArrayList<Integer>();
		opts.mSharedElementBounds = new ArrayList<Rect>();
		
		if (sharedElements != null) {
			opts.mIsInTheScreenArr = new boolean[sharedElements.length];
		}else {
			throw new RuntimeException("Shared Elements... must not be null");
		}
		
		//得到view+id的list，一个个取出来
        for (int i = 0; i < sharedElements.length; i++) {
        	//sharedElement是单个的一个元素对
            Pair<View, Integer> sharedElement = sharedElements[i];
            Integer sharedElementId = sharedElement.second;
            
            View view = sharedElement.first;
            if (view == null) {
                throw new IllegalArgumentException("Shared element must not be null");
            }
            opts.mSharedElementIds.add(sharedElementId);
            Rect bounds = getBounds(view);
            opts.mSharedElementBounds.add(bounds);
            opts.mIsInTheScreenArr[i] =	isInScreen(activity, view);
        }
		return opts;
	}

	/**
	 * 将各种坐标和参数放入bundle中传递
	 * 
     * Returns the created options as a Bundle, which can be passed to
     * {@link android.content.Context#startActivity(android.content.Intent, android.os.Bundle)
     * Context.startActivity(Intent, Bundle)} and related methods.
     * Note that the returned Bundle is still owned by the ActivityOptions
     * object; you must not modify it, but can supply it to the startActivity
     * methods that take an options Bundle.
     */
	public Bundle toBundle() {
		if (mAnimationType == ANIM_DEFAULT) {
            return null;
        }
		Bundle bundle = new Bundle();
        bundle.putInt(KEY_ANIM_TYPE, mAnimationType);
        switch (mAnimationType) {
        
            case ANIM_CUSTOM:
            	bundle.putInt(KEY_ANIM_ENTER_RES_ID, mCustomEnterResId);
                bundle.putInt(KEY_ANIM_EXIT_RES_ID, mCustomExitResId);
                break;
                
            case ANIM_SCALE_UP:
        		bundle.putBoolean(KEY_IS_VERTICAL_SCREEN, mIsVerticalScreen);
        		bundle.putBoolean(KEY_IS_IN_THE_SCREEN, mIsInTheScreen);
        		
        		bundle.putInt(KEY_ANIM_WIDTH, mWidth);
        		bundle.putInt(KEY_ANIM_HEIGHT, mHeight);
        		bundle.putInt(KEY_ANIM_START_X, mStartX);
        		bundle.putInt(KEY_ANIM_START_Y, mStartY);
                break;
                
            case ANIM_THUMBNAIL_SCALE_UP:
            	bundle.putBoolean(KEY_IS_START_FULL_SCREEN, mIsStartFullScreen);
            	bundle.putBoolean(KEY_IS_VERTICAL_SCREEN, mIsVerticalScreen);
        		bundle.putBoolean(KEY_IS_IN_THE_SCREEN, mIsInTheScreen);
                bundle.putParcelable(KEY_ANIM_THUMBNAIL, mThumbnail);
                bundle.putInt(KEY_ANIM_START_X, mStartX);
                bundle.putInt(KEY_ANIM_START_Y, mStartY);
                bundle.putInt(KEY_ANIM_WIDTH, mWidth);
                bundle.putInt(KEY_ANIM_HEIGHT, mHeight);
                break;
                
            case ANIM_SCENE_TRANSITION:
        		bundle.putBoolean(KEY_IS_VERTICAL_SCREEN, mIsVerticalScreen);
        		bundle.putBoolean(KEY_IS_START_FULL_SCREEN, mIsStartFullScreen);
        		
        		bundle.putBooleanArray(kEY_IS_IN_THE_SCREEN_ARR, mIsInTheScreenArr);
        		bundle.putIntegerArrayList(kEY_SHARED_ELEMENT_ID_LIST, mSharedElementIds);
        		bundle.putParcelableArrayList(kEY_SHARED_ELEMENT_BOUNDS_LIST, mSharedElementBounds);
                break;
        }

		return bundle;
	}

	
	/**
	 * view当前是否显示在屏幕上，包括被遮挡，显示不全的状态
	 * @param activity
	 * @param rect
	 * @return
	 */
	public static boolean isInScreen(Activity activity, View view) {
		Rect bounds = new Rect();
		//只要有一部分显示在屏幕内，就是true，不考虑遮挡情况
		boolean isInScreen = view.getGlobalVisibleRect(bounds);
		if (isInScreen) {
			if (bounds.width() < view.getWidth() * 0.3f || bounds.height() < view.getHeight() * 0.3f) {
				return false;
			}else {
				return true;
			}
		}else {
			return false;
		}
	}

	/**
	 * @param view
	 * @return 目标view的实际大小和实际的显示坐标
	 */
	public static Rect getBounds(View view) {
		Rect bounds = new Rect();
		//view.getGlobalVisibleRect(bounds);
		bounds.set(Position.getGlobalVisibleRect(view));
		return bounds;
	}

	/**
	 * @param activity
	 * @return 屏幕的宽度
	 */
	public static int getScreenWidth(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;//宽度
	}
	
	/**
	 * @param activity
	 * @return 屏幕的高度
	 */
	public static int getScreenHeight(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels ;//高度
	}

	/**
	 * @param activity
	 * @return 判断当前手机是否是全屏
	 */
	public static boolean isFullScreen(Activity activity) {
		int flag = activity.getWindow().getAttributes().flags;  
        if((flag & WindowManager.LayoutParams.FLAG_FULLSCREEN) 
        		== WindowManager.LayoutParams.FLAG_FULLSCREEN) {
        	return true;
        }else {
			return false;
		}
	}
	
	/**
	 * 判断当前屏幕是否是横屏
	 * @param activity
	 * @return
	 */
	public static boolean isVerticalScreen(Activity activity) {
		int flag = activity.getResources().getConfiguration().orientation;
		if (flag == 0) {
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * @param activity
	 * @param isActivity
	 * @return 得到actionbar的高度，这个必须在view绘制完成后才能的到值，否则返回0
	 */
/*	public static int getActionBarHeight(Activity activity,boolean isActivity) {
		if (isActivity) {
			if (activity.getActionBar() != null) {
				return activity.getActionBar().getHeight();
			}
		}else {
			if (((ActionBarActivity)activity).getSupportActionBar() != null) {
				return ((ActionBarActivity)activity).getSupportActionBar().getHeight();
			}
		}
		return 0;
	}
	*/
	/** 
     * 获取状态栏高度 
     *  
     * @return 
     */ 
    public static int getStatusBarHeight(Context context) {  
        Class<?> c = null;  
        Object obj = null;  
        java.lang.reflect.Field field = null;  
        int x = 0;  
        int statusBarHeight = 0;  
        try {  
            c = Class.forName("com.android.internal.R$dimen");  
            obj = c.newInstance();  
            field = c.getField("status_bar_height");  
            x = Integer.parseInt(field.get(obj).toString());  
            statusBarHeight = context.getResources().getDimensionPixelSize(x);  
            return statusBarHeight;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return statusBarHeight;  
    }



}
