package com.cheng.animationstudy.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.IntEvaluator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.Toast;

import com.cheng.animationstudy.C;
import com.cheng.animationstudy.R;
import com.cheng.utils.Logger;

public class UiPropertyAnim extends AppCompatActivity {

    private View mAnimView;

    private Button mMenuBtn;
    private Button mItemBtn1;
    private Button mItemBtn2;
    private Button mItemBtn3;
    private Button mItemBtn4;
    private Button mItemBtn5;

    private Button mTestTranslateBtn;
    private Button mTestRotateBtn;
    private Button mTestScaleBtn;
    private Button mTestAlphaBtn;
    private Button mTestKeyFrameBtn;
    private Button mTestPropertyValuesHolderBtn;

    private boolean mIsMenuOpen = false;
    private boolean mTestSwitch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_propertyanim);
        Logger.TAG = "UiPropertyAnim";
        initView();
    }

    private void initView() {
        this.mAnimView = this.findViewById(R.id.sdi_anim_view);
        this.mMenuBtn = (Button) this.findViewById(R.id.sdi_menu_btn);
        this.mItemBtn1 = (Button) this.findViewById(R.id.sdi_item1_btn);
        this.mItemBtn2 = (Button) this.findViewById(R.id.sdi_item2_btn);
        this.mItemBtn3 = (Button) this.findViewById(R.id.sdi_item3_btn);
        this.mItemBtn4 = (Button) this.findViewById(R.id.sdi_item4_btn);
        this.mItemBtn5 = (Button) this.findViewById(R.id.sdi_item5_btn);
        this.mTestTranslateBtn = (Button) this.findViewById(R.id.sdi_testtranslate_btn);
        this.mTestRotateBtn = (Button) this.findViewById(R.id.sdi_testrotate_btn);
        this.mTestScaleBtn = (Button) this.findViewById(R.id.sdi_testscale_btn);
        this.mTestAlphaBtn = (Button) this.findViewById(R.id.sdi_testalpha_btn);
        this.mTestKeyFrameBtn = (Button) this.findViewById(R.id.sdi_testkeyframe_btn);
        this.mTestPropertyValuesHolderBtn = (Button) this.findViewById(R.id.sdi_testpropertyvaluesholder_btn);

        logValueBtnProperty(mTestTranslateBtn.getId());
    }

    public void onClick(View v) {
        if (v == mMenuBtn) {
            if (!mIsMenuOpen) {
                mIsMenuOpen = true;
                doAnimateOpen(mItemBtn1, 0, 5, C.Int.MENU_DURATION);
                doAnimateOpen(mItemBtn2, 1, 5, C.Int.MENU_DURATION);
                doAnimateOpen(mItemBtn3, 2, 5, C.Int.MENU_DURATION);
                doAnimateOpen(mItemBtn4, 3, 5, C.Int.MENU_DURATION);
                doAnimateOpen(mItemBtn5, 4, 5, C.Int.MENU_DURATION);
            } else {
                mIsMenuOpen = false;
                doAnimateClose(mItemBtn1, 0, 5, C.Int.MENU_DURATION);
                doAnimateClose(mItemBtn2, 1, 5, C.Int.MENU_DURATION);
                doAnimateClose(mItemBtn3, 2, 5, C.Int.MENU_DURATION);
                doAnimateClose(mItemBtn4, 3, 5, C.Int.MENU_DURATION);
                doAnimateClose(mItemBtn5, 4, 5, C.Int.MENU_DURATION);
            }
        } else {
            Toast.makeText(this.getApplicationContext(), "你点击了按钮：" + v, Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 打开菜单的动画
     * @param view 执行动画的view
     * @param index view在动画序列中的顺序
     * @param total 动画序列的个数
     * @param radius 动画半径
     */
    private void doAnimateOpen(View view, int index, int total, int radius) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        double degree = Math.PI * index / ((total - 1) * 2);
        int translationX = (int) (radius * Math.cos(degree));
        int translationY = (int) (radius * Math.sin(degree));
        Logger.d(String.format("degree = %f, translationX = %d, translationY = %d", degree, translationX, translationY));
        AnimatorSet set = new AnimatorSet();
        // 包含平移、缩放和透明度动画
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 0, translationX),
                ObjectAnimator.ofFloat(view, "translationY", 0, translationY),
                ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f),
                ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        );
        // 动画周期为600ms
        set.setDuration(C.Int.MENU_DURATION * 2).start();
    }

    /**
     * 关闭菜单的动画
     * @param view 执行动画的view
     * @param index view在动画序列中的顺序
     * @param total 动画序列的个数
     * @param radius 动画半径
     */
    private void doAnimateClose(final View view, int index, int total,
                                int radius) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        double degree = Math.PI * index / ((total - 1) * 2);
        int translationX = (int) (radius * Math.cos(degree));
        int translationY = (int) (radius * Math.sin(degree));
        Logger.d(String.format("degree = %f, translationX = %d, translationY = %d", degree, translationX, translationY));
        AnimatorSet set = new AnimatorSet();
        // 包含平移、缩放和透明度动画
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", translationX, 0),
                ObjectAnimator.ofFloat(view, "translationY", translationY, 0),
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f),
                ObjectAnimator.ofFloat(view, "scaleXY", 1f, 0f),
                ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        );
        // 为动画加上事件监听，当动画结束的时候，把当前veiw隐藏
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                Logger.d("onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Logger.d("onAnimationEnd");
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                Logger.d("onAnimationCancel");
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                Logger.d("onAnimationRepeat");
            }
        });
        set.setDuration(C.Int.MENU_DURATION * 2).start();
    }

    public void showSimpleUse(View v) {
        /*
        ObjectAnimator是有条件限制的
        1. 对象应该有一个setter方法：set<PropertyName>（驼峰命名法）（提供一个属性）
        2. 该对象要有相应属性的 getter方法：get<PropertyName>，如果有getter方法，其应返回值类型应与相应的setter方法的参数类型一致
        如果不满足上述条件，解决方法：
        1. 给你的对象加上get和set方法，如果有权限的话
        2. 用一个类来包装原始对象，间接为其提供get和set方法
        3. 采用ValueAnimator，监听动画过程，自己实现属性的改变
         */

        // 像ofFloat之类的工场方法，第一个参数为对象名(这个也就是我们的view类了)，
        // 第二个为属性名，后面的参数为可变参 数，如果values…
        // 参数只设置了一个值的话，那么会假定为目的值，属性值的变化范围为当前值到目的值
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(v, "alpha", 1.0f, 0.1f);
        objectAnimator.setDuration(C.Int.ANIM_DURATION);
        objectAnimator.start();
    }

    public void toNext(View v) {
        Intent intent = new Intent(UiPropertyAnim.this, UiPropertyAnimAdvanced.class);
        startActivity(intent);
    }

    public void toNineOldAndroids(View v) {
        Intent intent = new Intent(UiPropertyAnim.this, UiNineOldAndroids.class);
        startActivity(intent);
    }

    public void testTranslation(View v) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTestTranslateBtn,"translationX", 0f,100.0f);
        showPropertyAnim01(objectAnimator, v.getId());
    }

    public void testRotate(View v) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTestRotateBtn,"rotation", 0f,360f);
        showPropertyAnim01(objectAnimator, v.getId());
    }

    public void testScale(View v) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTestScaleBtn,"scaleX", 0f,1.0f);
        showPropertyAnim01(objectAnimator, v.getId());
    }

    public void testAlpha(View v) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTestAlphaBtn,"alpha", 0f,1.0f);
        showPropertyAnim01(objectAnimator, v.getId());
    }

    public void testColor(View v) {
        ValueAnimator colorAnim = ObjectAnimator.ofInt(v, "backgroundColor", /*Red*/0xFFFF8080, /*Blue*/0xFF8080FF);
        colorAnim.setDuration(C.Int.ANIM_DURATION * 3);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();
    }

    public void testAnimSet(View v) {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(v, "rotationX", 0, 360),
                ObjectAnimator.ofFloat(v, "rotationY", 0, 180),
                ObjectAnimator.ofFloat(v, "rotation", 0, -90),
                ObjectAnimator.ofFloat(v, "translationX", 0, 90),
                ObjectAnimator.ofFloat(v, "translationY", 0, 90),
                ObjectAnimator.ofFloat(v, "scaleX", 1, 1.5f),
                ObjectAnimator.ofFloat(v, "scaleY", 1, 0.5f),
                ObjectAnimator.ofFloat(v, "alpha", 1, 0.25f, 1)
        );
        set.setDuration(C.Int.ANIM_DURATION * 5).start();
    }

    public void testKeyFrame(View v) {
        if (mTestSwitch) {
            /*
            keyFrame是一个 时间/值 对，通过它可以定义一个在特定时间的特定状态，
            而且在两个keyFrame之间可以定义不同的Interpolator，就相当多个动画的拼接，
            第一个动画的结束点是第二个动画的开始点。KeyFrame是抽象类，
            要通过ofInt(),ofFloat(),ofObject()获得适当的 KeyFrame，
            然后通过PropertyValuesHolder.ofKeyframe获得PropertyValuesHolder对象
             */
            Keyframe kf0 = Keyframe.ofInt(0, 400);
            Keyframe kf1 = Keyframe.ofInt(0.25f, 200);
            Keyframe kf2 = Keyframe.ofInt(0.5f, 400);
            Keyframe kf3 = Keyframe.ofInt(0.75f, 100);
            Keyframe kf4 = Keyframe.ofInt(1f, 300);
            PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofKeyframe("width", kf0, kf1, kf2, kf3, kf4);
            ObjectAnimator widthAnim = ObjectAnimator.ofPropertyValuesHolder(mTestKeyFrameBtn, propertyValuesHolder);
            widthAnim.setDuration(C.Int.ANIM_DURATION * 2);
            widthAnim.start();
            /*
            上述代码的意思为：设置 valueBtn 对象的width属性值使其：
            开始时 Width=400
            动画开始1/4时 Width=200
            动画开始1/2时 Width=400
            动画开始3/4时 Width=100
            动画结束时 Width=500
            第一个参数为时间百分比，第二个参数是在第一个参数的时间时的属性值。
            定义了一些Keyframe后，通过PropertyValuesHolder类的方法ofKeyframe封装，
            然后通过ObjectAnimator.ofPropertyValuesHolder获得Animator
             */
        } else {
           // 设置在动画开始时，旋转角度为0度
            Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
            // 设置在动画执行50%时，旋转角度为360度
            Keyframe kf1 = Keyframe.ofFloat(0.5f, 360f);
            // 设置在动画结束时，旋转角度为0度
            Keyframe kf2 = Keyframe.ofFloat(1.0f, 0f);
            // 使用PropertyValuesHolder进行属性名称和值集合的封装
            PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2);
            // 通过ObjectAnimator进行执行
            ObjectAnimator.ofPropertyValuesHolder(v, pvhRotation)
                    .setDuration(C.Int.ANIM_DURATION * 2)
                    .start();
        }
        mTestSwitch = !mTestSwitch;
    }

    public void testPropertyValuesHolder(View v) {
        if (mTestSwitch) {
            PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("x", 50f);
            PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("y", 600f);
            // 同时进行X轴和Y轴的动画
            ObjectAnimator xyAnimator = ObjectAnimator.ofPropertyValuesHolder(mTestPropertyValuesHolderBtn, pvhX, pvhY);
            xyAnimator.setDuration(C.Int.ANIM_DURATION * 2);
            xyAnimator.start();
        } else {
            int left = v.getLeft();
            int right = v.getRight();
            // 将view左边增加10像素
            PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("left", left, left + 10);
            // 将view右边减少10像素
            PropertyValuesHolder pvhRight = PropertyValuesHolder.ofInt("right", right, right - 10);
            // 在X轴缩放从原始比例1f,缩小到最小0f,再放大到原始比例1f
            PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 0f, 1f);
            // 在Y轴缩放从原始比例1f,缩小到最小0f,再放大到原始比例1f
            PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 0f, 1f);
            // 将PropertyValuesHolder交付给ObjectAnimator进行构建
            ObjectAnimator customAnim = ObjectAnimator.ofPropertyValuesHolder(v, pvhLeft, pvhRight, pvhScaleX, pvhScaleY);
            customAnim.setDuration(C.Int.ANIM_DURATION * 2);
            customAnim.start();
        }
        mTestSwitch = !mTestSwitch;
    }

    public void testWrapperforProperty(View v) {
        ViewWrapper wrapper = new ViewWrapper(v);
        // 5s内让Button的宽度增加到500px
        ObjectAnimator.ofInt(wrapper, "width", v.getWidth(), 500).setDuration(C.Int.ANIM_DURATION * 5).start();
    }

    public void testValueAnimator(final View v) {
        performAnimate(v, v.getWidth(), 500, mTestSwitch);
        mTestSwitch = !mTestSwitch;
    }

    public void testObjectAnimator(View v) {
        if (v.getId() == R.id.sdi_objectanimator_btn) {
            // 简单示例：View的横向移动
            ObjectAnimator.ofFloat(mAnimView, "translationX", 0.0f, -200.0f)
                    .setDuration(C.Int.ANIM_DURATION * 2)
                    .start();
        } else {
            // 复合示例：View弹性落下然后弹起，执行一次
            ObjectAnimator yBouncer = ObjectAnimator.ofFloat(mAnimView, "y", mAnimView.getY(), 400.0f);
            yBouncer.setDuration(C.Int.ANIM_DURATION * 2);
            // 设置插值器(用于调节动画执行过程的速度)
            yBouncer.setInterpolator(new BounceInterpolator());
            // 设置重复次数(缺省为0,表示不重复执行)
            yBouncer.setRepeatCount(1);
            // 设置重复模式(RESTART或REVERSE),重复次数大于0或INFINITE生效
            yBouncer.setRepeatMode(ValueAnimator.REVERSE);
            // 设置动画开始的延时时间(200ms)
            yBouncer.setStartDelay(200);
            yBouncer.start();
        }
    }

    public void testAnimatorSet(View v) {
        /*
        AnimatorSet bouncer = new AnimatorSet();
        bouncer.play(bounceAnim).before(squashAnim1);
        bouncer.play(squashAnim1).with(squashAnim2);
        bouncer.play(squashAnim1).with(stretchAnim1);
        bouncer.play(squashAnim1).with(stretchAnim2);
        bouncer.play(bounceBackAnim).after(stretchAnim2);
        ValueAnimator fadeAnim = ObjectAnimator.ofFloat(newBall, "alpha", 1f, 0f);
        fadeAnim.setDuration(250);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(bouncer).before(fadeAnim);
        animatorSet.start();
         */
    }

    public void testAnimatorUpdateListener(final View v) {
        if (mTestSwitch) {
            // 1. 在回调中手动更新View对应属性：
            ValueAnimator.AnimatorUpdateListener listener = new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    // 当前的分值范围为0.0f -> 1.0f
                    // 分度值是动画执行的百分比，区别于AnimatedValue
                    float fraction = animation.getAnimatedFraction();
                    // 以下的效果为View从完全透明到不透明
                    v.setAlpha(fraction);
                    // Y方向向下移动300px的距离
                    v.setTranslationY(fraction * 300.0f);
                }
            };
            ValueAnimator animator = ValueAnimator.ofFloat(0f, 1.0f);
            animator.addUpdateListener(listener);
            animator.setDuration(C.Int.ANIM_DURATION * 2);
            animator.start();
        } else {
            MyAnimationView myAnimationView = new MyAnimationView(UiPropertyAnim.this);
            ValueAnimator animator = ValueAnimator.ofFloat(0f, 1.0f);
            animator.addUpdateListener(myAnimationView);
            animator.setDuration(C.Int.ANIM_DURATION * 2);
            animator.start();
        }
        mTestSwitch = !mTestSwitch;
    }

    // 2. 在自定义View内部用于引发重绘
    private class MyAnimationView extends View implements
            ValueAnimator.AnimatorUpdateListener {
        public MyAnimationView(Context context) {
            super(context);
        }
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            // 手动触发界面重绘
            invalidate();
        }
    }

    public void testAnimatorListener(final View v) {
        // 1. 实现AnimatorListener中的方法可在动画执行全程进行其他任务的回调执行。
        // 2. 也可以添加AnimatorListener的实现类AnimatorListenerAdapter，仅重写需要的监听即可
        // 将View透明度从当前的1.0f更新为0.5f，在动画结束时移除该view
        ObjectAnimator anim = ObjectAnimator.ofFloat(v, "alpha", 0.5f);
        anim.setDuration(C.Int.ANIM_DURATION * 3);
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                // 动画开始时调用
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // 动画结束时调用
                ViewGroup parent = (ViewGroup) v.getParent();
                if (parent != null) {
                    parent.removeView(v);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // 动画取消时调用
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // 动画重复时调用
            }
        });
        anim.start();
    }

    public void testAnimatorInfalter(View v) {
        // 加载xml动画
        Animator animator = AnimatorInflater.loadAnimator(UiPropertyAnim.this, R.anim.sda_animatorset_anim);
        animator.setTarget(mTestSwitch?v:mAnimView);
        animator.start();
        mTestSwitch = !mTestSwitch;
    }

    public void testTypeEvaluator(final View v) {
        // 类型估值 - 抛物线示例
        TypeEvaluator<PointF> typeEvaluator = new TypeEvaluator<PointF>() {
            @Override
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                float time = fraction * 3;
                Logger.e(time + "");
                // x方向120px/s，y方向0.5 * 200 * t * t
                PointF pointF = new PointF();
                pointF.x = 120 * time;
                pointF.y = 0.5f * 200 * time * time;
                return pointF;
            }
        };
        ValueAnimator valueAnimator = ValueAnimator.ofObject(typeEvaluator, new PointF(0, 0));
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(C.Int.ANIM_DURATION * 3);
        valueAnimator.start();

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                v.setX(pointF.x);
                v.setY(pointF.y);
            }
        });
    }

    public void testTimeInterpolator(View v) {
        /*
        1. 几种常见的插值器：
        Interpolator对象                              资源ID                                                        功能作用
        AccelerateDecelerateInterpolator              @android:anim/accelerate_decelerate_interpolator             先加速再减速
        AccelerateInterpolator                        @android:anim/accelerate_interpolator                        加速
        AnticipateInterpolator                        @android:anim/anticipate_interpolator                        先回退一小步然后加速前进
        AnticipateOvershootInterpolator               @android:anim/anticipate_overshoot_interpolator              在上一个基础上超出终点一小步再回到终点
        BounceInterpolator                            @android:anim/bounce_interpolator                            最后阶段弹球效果
        CycleInterpolator                             @android:anim/cycle_interpolator                             周期运动
        DecelerateInterpolator                        @android:anim/decelerate_interpolator                        减速
        LinearInterpolator                            @android:anim/linear_interpolator                            匀速
        OvershootInterpolator                         @android:anim/overshoot_interpolator                         快速到达终点并超出一小步最后回到终点
        2. 自定义插值器
        a.实现Interpolator(TimeInterpolator)接口;
        b.重写接口函数float getInterpolation(floatinput)。
         */
    }

    private void showPropertyAnim01(ObjectAnimator objectAnimator, final int btnId) {
        objectAnimator.setDuration(C.Int.ANIM_DURATION);
        objectAnimator.start();
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                logValueBtnProperty(btnId);
            }
        });
    }

    private void logValueBtnProperty(int btnId) {
        switch (btnId) {
            case R.id.sdi_testtranslate_btn:
                Logger.i("mTestTranslateBtn.getLeft()=" + mTestTranslateBtn.getLeft());
                Logger.i("mTestTranslateBtn.getX()=" + mTestTranslateBtn.getX());
                Logger.i("mTestTranslateBtn.getTranslationX()=" + mTestTranslateBtn.getTranslationX());
                break;
            case R.id.sdi_testrotate_btn:
                Logger.i("mTestRotateBtn.getRotation()=" + mTestRotateBtn.getRotation());
                Logger.i("mTestRotateBtn.getX()=" + mTestRotateBtn.getX());
                Logger.i("mTestRotateBtn.getRotationX()=" + mTestRotateBtn.getRotationX());
                break;
            case R.id.sdi_testscale_btn:

                break;
            case R.id.sdi_testalpha_btn:

                break;
        }
    }

    private void performAnimate(final View target, final int start, final int end, boolean flag) {
        if (flag) {
            ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 100);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                // 持有一个IntEvaluator对象，方便下面估值的时候使用
                private IntEvaluator mEvaluator = new IntEvaluator();

                @Override
                public void onAnimationUpdate(ValueAnimator animator) {
                    // 获得当前动画的进度值，整型，1-100之间
                    int currentValue = (int) animator.getAnimatedValue();
                    Logger.d("Current value: " + currentValue);
                    // 计算当前进度占整个动画过程的比例，浮点型，0-1之间
                    float fraction = currentValue / 100f;
                    // 直接调用整型估值器通过比例计算出宽度，然后再设给Button
                    target.getLayoutParams().width = mEvaluator.evaluate(fraction, start, end);
                    target.requestLayout();
                }
            });
            valueAnimator.setDuration(C.Int.ANIM_DURATION * 5);
            valueAnimator.start();
        } else {
            ValueAnimator animator = ValueAnimator.ofFloat(0f, 200.0f);
            // 设置作用对象
            animator.setTarget(target);
            animator.setDuration(C.Int.ANIM_DURATION * 2);
            // 添加动画更新监听
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    // 获取当前值
                    float currentValue = (float) animation.getAnimatedValue();
                    // 设置横向偏移量
                    target.setTranslationX(currentValue);
                    target.setTranslationY(currentValue);
                }
            });
            animator.start();
        }
    }

    /**
     * 一个包装类用来包装原始对象，间接为其提供get和set方法
     */
    private static class ViewWrapper {
        private View mTarget;

        public ViewWrapper(View target) {
            this.mTarget = target;
        }

        public int getWidth() {
            return mTarget.getLayoutParams().width;
        }

        public void setWidth(int width) {
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }
    }

    /*
    在Property Animation中，改变的是对象的实际属性，
    而且Property Animation不止可以应用于View，还可以应用于任何对象
    ValueAnimator类就是这个的包含Property Animation的动画所以核心功能，
    如动画的时间，开始，结束的属性值等等，
    对于ValueAnimator来说我们一般都不会直接使用这个，
    我们都是直接使用ValueAnimator的子类就是ObjectAnimator

    可以监听动画的开始和结束，可以使用AnimatorListener监听
    objectAnimator.addListener(new AnimatorListener(){}}
    也可以使用AnimatorListenerAdapter这个适配器来进行监听
    objectAnimator.addListener(new AnimatorListenerAdapter(){}}

    想要同时实现很多个动画就需要AnimatorSet这个类来实现
    AnimatorSet提供了before,with,after方法

    经常使用一些动画属性
    1. translationX,translationY,x,y
    这些 translationX和x，TranslationY和y是有区别的
    无论啥样应用动画，getLeft的值是不会变的，而TranslationX的值
    是为最终位置于布局时初始位置的差，即“最终位置－getLeft()",
    而x为最终位置之和，即”getLeft()+getTranslationX()“,
    所以当getLeft为０的时候就会发现两个值是相等的
    2. rotation,totationX,rotationY
    旋转，rotation用于2D旋转角度，3D中用到后两个
    3. scaleX,scaleY
    缩放
    4. alpha
    透明度
     */

}
