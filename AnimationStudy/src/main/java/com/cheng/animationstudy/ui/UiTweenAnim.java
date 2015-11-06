package com.cheng.animationstudy.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ViewFlipper;

import com.cheng.animationstudy.C;
import com.cheng.animationstudy.R;
import com.cheng.animationstudy.customview.TweenAnimView;
import com.cheng.utils.AnalogSignal;
import com.cheng.utils.ViewFinder;

/**
 * Tween Animation
 * 在View Animation（Tween Animation）中，其改变的是View的绘制效果，真正的View的属性保持不变
 */
public class UiTweenAnim extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ImageView mAnimImgIV;
    private TweenAnimView mCustomAnimTAV;
    private CheckBox mKeepAnimStatusCB;
    private CheckBox mLoadAnimByXmlCB;

    private ViewFlipper mFlipper;
    private Spinner mFlipperSpinner;
    private Spinner mInterpolatorSpinner;
    private View mInterpolatorTarget;
    private View mInterpolatorTargetParent;

    private Animation mAlphaAnimation;
    private Animation mScaleAnimation;
    private Animation mTranslateAnimation;
    private Animation mRotateAnimation;

    private String[] mStrings = {
            "Push up", "Push left", "Cross fade", "Hyperspace"};
    private static final String[] INTERPOLATORS = {
            "Accelerate", "Decelerate", "Accelerate/Decelerate",
            "Anticipate", "Overshoot", "Anticipate/Overshoot",
            "Bounce"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_tweenanim);

        initView();
    }

    private void initView() {
        this.mAnimImgIV = ViewFinder.findViewById(this, R.id.sdi_animimg_iv);
        this.mCustomAnimTAV = ViewFinder.findViewById(this, R.id.sdi_customanim_tav);
        this.mKeepAnimStatusCB = ViewFinder.findViewById(this, R.id.sdi_keepanimstatus_cb);
        this.mLoadAnimByXmlCB = ViewFinder.findViewById(this, R.id.sdi_loadanimbyxml_cb);
        this.mFlipper = ViewFinder.findViewById(this, R.id.sdi_showflipperanim_vf);
        this.mFlipperSpinner = ViewFinder.findViewById(this, R.id.sdi_showflipperanim_spinner);
        this.mInterpolatorSpinner = ViewFinder.findViewById(this, R.id.sdi_interpolatoranim_spinner);
        this.mInterpolatorTarget = ViewFinder.findViewById(this, R.id.sdi_interploatoranim_target);
        this.mInterpolatorTargetParent = (View) mInterpolatorTarget.getParent();

        mCustomAnimTAV.setFocusable(true);
        mCustomAnimTAV.findFocus();
        mFlipper.startFlipping();
        ArrayAdapter<String> flipperAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mStrings);
        flipperAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mFlipperSpinner.setAdapter(flipperAdapter);
        mFlipperSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> interpolatorSadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, INTERPOLATORS);
        interpolatorSadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mInterpolatorSpinner.setAdapter(interpolatorSadapter);
        mInterpolatorSpinner.setOnItemSelectedListener(this);
    }

    /*
    透明度动画
     */
    public void showAlphaAnim(View v) {
        boolean isLoadFromXml = this.mLoadAnimByXmlCB.isChecked();
        boolean isKeepAnimStatus = this.mKeepAnimStatusCB.isChecked();
        mAlphaAnimation = null;
        if (isLoadFromXml) {
            // 初始化
            mAlphaAnimation = AnimationUtils.loadAnimation(UiTweenAnim.this, R.anim.sda_alpha_anim);
        } else {
            // 初始化
            // AlphaAnimation(float fromAlpha,float toAlpha)
            // fromAlpha表示的是动画初始时的透明度，
            // toAlpha表示的是动画结束时的透明度，
            // 这个取值范围是０～1，0表示的是完全透明，１表示的是完全不透明
            mAlphaAnimation = new AlphaAnimation(1.0f, 0f);
            // 设置动画时间
            mAlphaAnimation.setDuration(C.Int.ANIM_DURATION * 3);
        }
        mAlphaAnimation.setFillAfter(isKeepAnimStatus);
        // 开始动画
        this.mAnimImgIV.startAnimation(mAlphaAnimation);
    }

    /*
    缩放动画
     */
    public void showScaleAnim(View v) {
        boolean isLoadFromXml = this.mLoadAnimByXmlCB.isChecked();
        boolean isKeepAnimStatus = this.mKeepAnimStatusCB.isChecked();
        mScaleAnimation = null;
        if (isLoadFromXml) {
            // 初始化
            mScaleAnimation = AnimationUtils.loadAnimation(UiTweenAnim.this, R.anim.sda_scale_anim);
        } else {
            // 初始化
            // 第一个参数fromX ,第二个参数toX:分别是动画起始、结束时X坐标上的伸缩尺寸
            // 第三个参数fromY ,第四个参数toY:分别是动画起始、结束时Y坐标上的伸缩尺寸
            // 还可以设置伸缩模式pivotXType、pivotYType， 伸缩动画相对于x,y 坐标的开始位置pivotXValue、pivotYValue等
            // 这里有三个值：
            // Animation.ABSOLUTE：这个表示的是绝对坐标
            // Animation.RELATIVE_TO_SELF：相对于自己的坐标
            // Animation.RELATIVE_TO_PARENT：相对于父控件
            // Animation.RELATIVE_TO_SELF这个就相对于自己的坐标，就是说这个坐标原始坐标是在你设置view的左上角，Animation.RELATIVE_TO_PARENT相对于父控件的坐标，这个大多数指的就是手机上的坐标原点。Animation.ABSOLUTE绝对坐标说的就是具体相对哪个一个点，比如(100,200)，就是表示相对坐标点在(100,200)这个点来进行动画。
            // pivotXValue和pivotYValue值就是相对上面的值设置的，
            // 表示的是相对于哪一个点来进行放大缩小的动画，
            // 对于Animation.RELATIVE_TO_SELF和Animation.RELATIVE_TO_PARENT
            // (0,0)表示的是原点，（0.5f,0.5f）表示的中间点
            // 这个对于Animation.RELATIVE_TO_SELF相对于view控件的中间点，
            // Animation.RELATIVE_TO_PARENT就是值该view上父控件的中间点，
            // (1,1)表示的就是右下角的坐标
            // mScaleAnimation = new ScaleAnimation(1.0f, 0.1f, 1.0f, 0.1f);
            mScaleAnimation = new ScaleAnimation(1.0f, 0.1f, 1.0f, 0.1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            // 设置动画时间
            mScaleAnimation.setDuration(C.Int.ANIM_DURATION * 3);
        }
        mScaleAnimation.setFillAfter(isKeepAnimStatus);
        // 开始动画
        this.mAnimImgIV.startAnimation(mScaleAnimation);
    }

    /*
    位移动画
     */
    public void showTranslateAnim(View v) {
        boolean isLoadFromXml = this.mLoadAnimByXmlCB.isChecked();
        boolean isKeepAnimStatus = this.mKeepAnimStatusCB.isChecked();
        mTranslateAnimation = null;
        if (isLoadFromXml) {
            // 初始化
            mTranslateAnimation = AnimationUtils.loadAnimation(UiTweenAnim.this, R.anim.sda_translate_anim);
        } else {
            // 初始化
            // 第一个参数fromXDelta ,第二个参数toXDelta:分别是动画起始、结束时X坐标
            // （0，0）这个表示的就是当前view的坐标
            // 第三个参数fromYDelta ,第四个参数toYDelta:分别是动画起始、结束时Y坐标
            // 如果只是X轴移动或者Y轴移动，那么可以把对应不移动的坐标设置为０
            mTranslateAnimation = new TranslateAnimation(0.1f, 300.0f,0.1f,-300.0f);
            // 设置动画时间
            mTranslateAnimation.setDuration(C.Int.ANIM_DURATION * 2);
        }
        mTranslateAnimation.setFillAfter(isKeepAnimStatus);
        // 开始动画
        this.mAnimImgIV.startAnimation(mTranslateAnimation);
    }

    /*
    旋转动画
     */
    public void showRotateAnim(View v) {
        boolean isLoadFromXml = this.mLoadAnimByXmlCB.isChecked();
        boolean isKeepAnimStatus = this.mKeepAnimStatusCB.isChecked();
        mRotateAnimation = null;
        if (isLoadFromXml) {
            // 初始化
            mRotateAnimation = AnimationUtils.loadAnimation(UiTweenAnim.this, R.anim.sda_rotate_anim);
        } else {
            // 初始化
            mRotateAnimation = new RotateAnimation(0f, 360f);
            // 设置动画时间
            mRotateAnimation.setDuration(C.Int.ANIM_DURATION * 3);
        }
        mRotateAnimation.setFillAfter(isKeepAnimStatus);
        // 开始动画
        this.mAnimImgIV.startAnimation(mRotateAnimation);
    }

    /*
    动画集1
     */
    public void showAnimSet01(View v) {
        boolean isLoadFromXml = this.mLoadAnimByXmlCB.isChecked();
        boolean isKeepAnimStatus = this.mKeepAnimStatusCB.isChecked();
        if (isLoadFromXml) {
            mTranslateAnimation = AnimationUtils.loadAnimation(UiTweenAnim.this, R.anim.sda_translate_anim);
            mAlphaAnimation = AnimationUtils.loadAnimation(UiTweenAnim.this, R.anim.sda_alpha_anim);
        } else {
            mTranslateAnimation = new TranslateAnimation(0.1f, 100.0f, 0.1f, 100.0f);
            mAlphaAnimation = new AlphaAnimation(0.1f, 1.0f);
        }

        // 动画集
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(mTranslateAnimation);
        set.addAnimation(mAlphaAnimation);

        // 设置动画时间（作用到每个动画）
        set.setDuration(C.Int.ANIM_DURATION);
        set.setFillAfter(isKeepAnimStatus);
        this.mAnimImgIV.startAnimation(set);
    }

    /*
    动画集2
     */
    public void showAnimSet02(View v) {
        boolean isLoadFromXml = this.mLoadAnimByXmlCB.isChecked();
        boolean isKeepAnimStatus = this.mKeepAnimStatusCB.isChecked();
        if (isLoadFromXml) {
            mRotateAnimation = AnimationUtils.loadAnimation(UiTweenAnim.this, R.anim.sda_rotate_anim);
            mScaleAnimation = AnimationUtils.loadAnimation(UiTweenAnim.this, R.anim.sda_scale_anim);
            mTranslateAnimation = AnimationUtils.loadAnimation(UiTweenAnim.this, R.anim.sda_translate_anim);
        } else {
            mRotateAnimation = AnimationUtils.loadAnimation(UiTweenAnim.this, R.anim.sda_rotate_anim);
            mScaleAnimation = new ScaleAnimation(1.0f, 0.1f, 1.0f, 0.1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            mTranslateAnimation = new TranslateAnimation(0.1f, 100.0f, 0.1f, 100.0f);
        }

        // 动画集
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(mRotateAnimation);
        set.addAnimation(mScaleAnimation);
        set.addAnimation(mTranslateAnimation);

        // 设置动画时间（作用到每个动画）
        set.setDuration(C.Int.ANIM_DURATION * 2);
        set.setFillAfter(isKeepAnimStatus);
        this.mAnimImgIV.startAnimation(set);
    }

    /*
    动画集3
     */
    public void showAnimSet03(View v) {
        boolean isKeepAnimStatus = this.mKeepAnimStatusCB.isChecked();
        // 动画集
        Animation anim = AnimationUtils.loadAnimation(UiTweenAnim.this, R.anim.sda_animationset_anim);

        // 设置动画时间（作用到每个动画）
        anim.setDuration(C.Int.ANIM_DURATION * 2);
        anim.setFillAfter(isKeepAnimStatus);
        this.mAnimImgIV.startAnimation(anim);
    }

    public void showSharkAnim(View v) {
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.sda_shake_anim);
        v.startAnimation(shake);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent == mFlipperSpinner) {
            changeFlipperAnim(position);
        } else {
            changeInterpolator(position);
        }
    }

    private void changeFlipperAnim(int position) {
        switch (position) {
            case 0:
                mFlipper.setInAnimation(AnimationUtils.loadAnimation(this,
                        R.anim.sda_push_up_in));
                mFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,
                        R.anim.sda_push_up_out));
                break;
            case 1:
                mFlipper.setInAnimation(AnimationUtils.loadAnimation(this,
                        R.anim.sda_push_left_in));
                mFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,
                        R.anim.sda_push_left_out));
                break;
            case 2:
                mFlipper.setInAnimation(AnimationUtils.loadAnimation(this,
                        android.R.anim.fade_in));
                mFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,
                        android.R.anim.fade_out));
                break;
            default:
                mFlipper.setInAnimation(AnimationUtils.loadAnimation(this,
                        R.anim.sda_hyperspace_in));
                mFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,
                        R.anim.sda_hyperspace_out));
                break;
        }
    }

    private void changeInterpolator(int position) {
        Animation animation = new TranslateAnimation(0.0f,
                mInterpolatorTargetParent.getWidth()-mInterpolatorTarget.getWidth()-mInterpolatorTargetParent.getPaddingLeft()-mInterpolatorTargetParent.getPaddingRight(),
                0.0f, 0.0f);
        animation.setDuration(1000);
        animation.setStartOffset(300);
        animation.setRepeatMode(Animation.RESTART);
        animation.setRepeatCount(Animation.INFINITE);
        switch (position) {
            case 0:
                animation.setInterpolator(AnimationUtils.loadInterpolator(this,
                        android.R.anim.accelerate_interpolator));
                break;
            case 1:
                animation.setInterpolator(AnimationUtils.loadInterpolator(this,
                        android.R.anim.decelerate_interpolator));
                break;
            case 2:
                animation.setInterpolator(AnimationUtils.loadInterpolator(this,
                        android.R.anim.accelerate_decelerate_interpolator));
                break;
            case 3:
                animation.setInterpolator(AnimationUtils.loadInterpolator(this,
                        android.R.anim.anticipate_interpolator));
                break;
            case 4:
                animation.setInterpolator(AnimationUtils.loadInterpolator(this,
                        android.R.anim.overshoot_interpolator));
                break;
            case 5:
                animation.setInterpolator(AnimationUtils.loadInterpolator(this,
                        android.R.anim.anticipate_overshoot_interpolator));
                break;
            case 6:
                animation.setInterpolator(AnimationUtils.loadInterpolator(this,
                        android.R.anim.bounce_interpolator));
                break;
        }
        mInterpolatorTarget.startAnimation(animation);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /*
    常用方法说明：
    1. setDuration(long durationMillis)
    设置动画的显示时间，就是这个动画从初始状态到结束状态所需要的时间，durationMillis参数为动画显示时间的长短，单位是毫秒
    2. setStartOffset(long startOffset)
    动画的开始时间，在startAnimation之后可能不希望立即取执行这个动画，需要等待一会再进行动画就可以使用这个
    3. setFillAfter(boolean fillAfter)
    动画结束之后是否保留结束的位置，这个值默认是flase表示动画结束之后不保留动画的位置，
    就是说在我们进行动画效果结束之后又会自动恢复到原始的状态，true表示就是保留动画结束时的状态，这个值一般都是要设置为true的
    4. setFillBefore(boolean fillBefore)
    当设置为true ，该动画转化在动画开始前被应用
    5. setInterpolator(Interpolator i)
    设置动画的变化速度，这里android提供很多类型的Interpolator类型的变化器
    5.1 new AccelerateInterpolator(float factor)
    加速，factor参数可以设置为加速的倍数，数值越大动画的速度越快，当然也可以是默认的new AccelerateInterpolator（）默认这个参数为１
    5.2 new AccelerateDecelerateInterpolator()
    先加速后减速
    5.3 new DecelerateInterpolator(float factor)
    减速，factor和上面的加速参数是一个意思，只不过作用是相反的，也可以不带参数 new DecelerateInterpolator ()
    5.4 new CycleInterpolator()
    动画循环播放特定次数，速率改变沿着正弦曲线
    5.5 new LinearInterpolator()
    匀速
    5.6 new OvershootInterpolator()
    超越，最后超出目的值然后缓慢改变到目的值
    5.7 new BounceInterpolator()
    跳跃，快到目的值时值会跳跃，如目的值100，后面的值可能依次为85，77，70，80，90，100
    5.8 new AnticipateOvershootInterpolator()
    反向加超越，先向相反方向改变，再加速播放，会超出目的值然后缓慢移动至目的值
    5.9 new AnticipateInterpolator()
    反向 ，先向相反方向改变一段再加速播放
    5.10 自定义
    写一个类继承于 Interpolator 接口，并且去实现getInterpolation()方法，在该方法里面做相应的运算
    6. zAdjustment(int z)
    定义动画的Z Order的改变	0：保持Z Order不变 1：保持在最上层 -1：保持在最下层

    动画集
    可以使用AnimationSet这个类实现多个动画叠加效果
    AnimationSet(boolean shareInterpolator);
    这个参数设为false表示可以在每个添加到AnimationSet中的Animation都使用Interpolator，
    且效果都能清楚的观察。设置为true如果在添加到AnimationSet中的Animation设置Interpolator
    将无效果，通过设置AnimationSet的Interpolator可以设置所有动画的Interpolator且所有动画的
    Interpolator都一样

    监听动画的开始和结束
    AnimationListener

    注意：
    tween Animation动画改变的是view绘制， 而没有改变View对象本身，比如，
    你有一个Button，坐标 （100,100），Width:200,Height:50，
    而你有一个动画使其变为Width：100，Height：100，你会发现动画过程中
    触发按钮点击的区域仍是(100,100)-(300,150)
     */

    /*
    下面使用模拟按键的方式试图触发下面的事件，使TweenAnimView执行相应动画
    说明：未达到设定目标，TweenAnimView没有抢到焦点，被其他按钮响应了
    备注：TweenAnimView的问题以后再说，其他基本动画都演示清楚了
     */
    public void keyUp(View v) {
        AnalogSignal.KeyEventSignal.up();
    }

    public void keyDown(View v) {
        AnalogSignal.KeyEventSignal.down();
    }

    public void keyLeft(View v) {
        AnalogSignal.KeyEventSignal.left();
    }

    public void keyRight(View v) {
        AnalogSignal.KeyEventSignal.right();
    }

    public void keyCenter(View v) {
        AnalogSignal.KeyEventSignal.center();
    }

}
