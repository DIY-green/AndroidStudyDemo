package com.cheng.animationstudy.ui;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.cheng.animationstudy.C;
import com.cheng.animationstudy.R;
import com.kale.activityoptions.transition.TransitionAnims;
import com.kale.activityoptions.transition.TransitionCompat;
import com.kale.activityoptions.transition.TransitionListenerAdapter;

public class UiActivityOptionsCompatDemoTarget extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_activityoptionscompatdemotarget);
        /**
         * view动画的监听器，比如thumbNailScaleAnim，screenTransitAnim这样的动画就会在这里得到监听
         *
         * 这里的值是说明动画进行到什么时候，原始的view开始显示
         * 设置方式是：动画时间越长，可以设置的越精细，越靠近1，动画时间越短设置为0.95就差不多了
         * 这里的值请自行根据你的动画长度进行调整，如果调整不好可能会出现动画结束后相应元素不见的问题。
         * 这里测试是如果动画是2000ms，那么用0.998较为合适
         */
        addViewAnimListener();
    }

    private void addViewAnimListener() {
        final float fraction = 0.9f;
        TransitionCompat.addViewAnimListener(new TransitionCompat.ViewAnimationListener() {

            boolean isShowed = false;

            @Override
            public void onInitAnimationViews(View view, int id) {

            }

            @Override
            public void onViewAnimationStart(View view, Animator animator) {
                if (UiActivityOptionsCompatDemo.isSceneAnim && TransitionCompat.isEnter) {
                    UiActivityOptionsCompatDemo.sHandler.sendEmptyMessage(123);
                }
            }

            @Override
            public void onViewAnimationUpdate(View view, ValueAnimator valueAnimator, float progress) {
                // 判断当前是否是进入的状态，如果是进入的那么 isEnter=true
                if (UiActivityOptionsCompatDemo.isSceneAnim
                        && !TransitionCompat.isEnter
                        && progress >= fraction
                        && isShowed) {
                    UiActivityOptionsCompatDemo.sHandler.sendEmptyMessage(321);
                    isShowed = true;
                }
            }

            @Override
            public void onViewAnimationEnd(View view, Animator animator) {
                if (!TransitionCompat.isEnter && !isShowed) {
                    UiActivityOptionsCompatDemo.sHandler.sendEmptyMessage(321);
                    isShowed = true;
                }
            }

            @Override
            public void onViewAnimationCancel(View view, Animator animator) {

            }
        });
        /**
         * 屏幕（场景）动画的监听器，这里用了适配器模式。可以传入完整的接口实现类
         */
        TransitionCompat.addListener(new TransitionListenerAdapter() {
            @Override
            public void onTransitionEnd(Animator animator, Animation animation, boolean isEnter) {
                super.onTransitionEnd(animator, animation, isEnter);
                // TODO
            }
        });

//		TransitionCompat.setEnterTransition(new SceneFade(this, true));// use to scale Up animation
//		TransitionCompat.setAnimDuration(300);// default
//		TransitionCompat.setAnimStartDelay(0);// default
//		TransitionCompat.setTimeInterpolator(new AccelerateDecelerateInterpolator());// default
//		TransitionCompat.setAnimDuration(300);
        // 这段代码必须放在ActivityOptionsCompat各种设置之后
        TransitionCompat.startTransition(this, R.layout.ui_activityoptionscompatdemotarget);
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
//        TransitionCompat.setExitTransition(new MySceneAnim(this));//a test anim.Should not be use with customAnimation
//        TransitionCompat.setAnimStartDelay(0);// default
//        TransitionCompat.setAnimDuration(300);// default
//        TransitionCompat.setTimeInterpolator(new AccelerateDecelerateInterpolator());// default
//        TransitionCompat.finishAfterTransition(
//                UiActivityOptionsCompatDemoTarget.this,
//                R.anim.slide_bottom_in,
//                R.anim.slide_bottom_out);// custom animation
        // 这段代码必须放在ActivityOptionsCompat各种设置之后
        TransitionCompat.finishAfterTransition(this);
    }

    private class MySceneAnim extends TransitionAnims {

        public MySceneAnim(Activity activity) {
            super(activity);
            getActivity(); // 得到要启动动画的Activity
            getAnimsDuration(); // 得到通过transitionCompatICS设置的动画持续时间
            getAnimsInterpolator(); // 得到通过transitionCompatICS设置的动画效果
            getBackground(); // 得到当前Activity默认的背景图片，这个是开源项目中默认设置的，是一个#eeeeee的drawable。仅仅用于收尾操作
            getAnimsStartDelay(); // 得到通过transitionCompatICS设置的动画延迟时间
            getSceneRoot(); // 重要：执行动画的View对象
        }

        @Override
        public void playScreenEnterAnims() {

        }

        @Override
        public void playScreenExitAnims() {
            View sceneRoot = getSceneRoot();
            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_bottom_out);
            animation.setDuration(C.Int.ANIM_DURATION * 2);
            animation.setAnimationListener(new TransitionAnimsListener() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    super.onAnimationEnd(animation);
                    exitAnimsEnd();;
                }
            });
            sceneRoot.startAnimation(animation);
        }
    }
}
