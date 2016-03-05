package com.cheng.animationstudy.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.cheng.animationstudy.R;
import com.cheng.animationstudy.customview.layoutanim.FixedGridLayout;

/**
 * 当Layout改变时应用动画
 */
public class LayoutAnimActivity extends AppCompatActivity {

    private int mNumButtons = 1;
    private ViewGroup mContainerVG = null;
    private ViewGroup mParentVG;
    private Button mAddNewBtn;
    private CheckBox mCustomAnimCB;
    private CheckBox mAppearingCB;
    private CheckBox mDisappearingCB;
    private CheckBox mChangingAppearingCB;
    private CheckBox mChangingDisappearingCB;

    // 默认的动画
    private Animator mDefaultAppearingAnim, mDefaultDisappearingAnim;
    private Animator mDefaultChangingAppearingAnim, mDefaultChangingDisappearingAnim;

    // 自定义动画
    private Animator mCustomAppearingAnim, mCustomDisappearingAnim;
    private Animator mCustomChangingAppearingAnim, mCustomChangingDisappearingAnim;

    // 当前动画
    private Animator mCurrentAppearingAnim, mCurrentDisappearingAnim;
    private Animator mCurrentChangingAppearingAnim, mCurrentChangingDisappearingAnim;

    private CompoundButton.OnCheckedChangeListener mCheckboxListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layoutanim);

        initView();
        initListener();
    }

    private void initView() {
        this.mContainerVG = new FixedGridLayout(this);
        this.mContainerVG.setClipChildren(false);
        ((FixedGridLayout) mContainerVG).setCellHeight(90);
        ((FixedGridLayout) mContainerVG).setCellWidth(100);
        this.mParentVG = (ViewGroup) findViewById(R.id.vg_parent);
        this.mAddNewBtn = (Button) findViewById(R.id.btn_addnew);
        // 几个多选按钮的事件都一样
        this.mCustomAnimCB = (CheckBox) findViewById(R.id.cb_customanim);
        // Check for disabled animations
        this.mAppearingCB = (CheckBox) findViewById(R.id.cb_appearing);
        this.mDisappearingCB = (CheckBox) findViewById(R.id.cb_disappearing);
        this.mChangingAppearingCB = (CheckBox) findViewById(R.id.cb_changingAppearing);
        this.mChangingDisappearingCB = (CheckBox) findViewById(R.id.cb_changingDisappearing);
    }

    private void initListener() {
        // ViewGroup布局变换动画
        final LayoutTransition transitioner = new LayoutTransition();

        // 把LayoutTransition对象transitioner设置给这个外层容器
        this.mContainerVG.setLayoutTransition(transitioner);

        // 默认系列
        this.mDefaultAppearingAnim = transitioner
                .getAnimator(LayoutTransition.APPEARING);
        this.mDefaultDisappearingAnim = transitioner
                .getAnimator(LayoutTransition.DISAPPEARING);
        this.mDefaultChangingAppearingAnim = transitioner
                .getAnimator(LayoutTransition.CHANGE_APPEARING);
        this.mDefaultChangingDisappearingAnim = transitioner
                .getAnimator(LayoutTransition.CHANGE_DISAPPEARING);

        // 创建自定义动画
        createCustomAnimations(transitioner);

        // 先将默认的作为当前的
        this.mCurrentAppearingAnim = mDefaultAppearingAnim;
        this.mCurrentDisappearingAnim = mDefaultDisappearingAnim;
        this.mCurrentChangingAppearingAnim = mDefaultChangingAppearingAnim;
        this.mCurrentChangingDisappearingAnim = mDefaultChangingDisappearingAnim;

        // 加入自定义的FixedGridLayout
        this.mParentVG.addView(mContainerVG);
        this.mParentVG.setClipChildren(false);

        this.mCheckboxListener = new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                setupTransition(transitioner);
            }
        };

        this.mAddNewBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Add Button按钮点击事件：新增按钮，按钮的点击事件是移除自身
                Button newButton = new Button(LayoutAnimActivity.this);
                newButton.setText(String.valueOf(mNumButtons++));
                newButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        mContainerVG.removeView(v);
                    }
                });
                // Math.min(1, mContainerVG.getChildCount()的使用：
                // addView的index:最开始是0，后来都是1
                // 即第一个按钮是加在index为0位置，后续添加的按钮都是插入地加在index为1，即第二的位置
                mContainerVG.addView(newButton,
                        Math.min(1, mContainerVG.getChildCount()));
            }
        });

        this.mCustomAnimCB.setOnCheckedChangeListener(mCheckboxListener);
        this.mAppearingCB.setOnCheckedChangeListener(mCheckboxListener);
        this.mDisappearingCB.setOnCheckedChangeListener(mCheckboxListener);
        this.mChangingAppearingCB.setOnCheckedChangeListener(mCheckboxListener);
        this.mChangingDisappearingCB.setOnCheckedChangeListener(mCheckboxListener);
    }

    // 多选按钮的选择变换后执行的事件方法
    // 根据多选按钮的状态来设置LayoutTransition transition的四个Animator
    private void setupTransition(LayoutTransition transition) {
        // 首先，把多选按钮全都获取一遍
        CheckBox customAnimCB = (CheckBox) findViewById(R.id.cb_customanim);
        CheckBox appearingCB = (CheckBox) findViewById(R.id.cb_appearing);
        CheckBox disappearingCB = (CheckBox) findViewById(R.id.cb_disappearing);
        CheckBox changingAppearingCB = (CheckBox) findViewById(R.id.cb_changingAppearing);
        CheckBox changingDisappearingCB = (CheckBox) findViewById(R.id.cb_changingDisappearing);

        // 然后，根据多选按钮的选择状态来setAnimator

        // 解释一下第一个：
        // mAppearingCB.isChecked()?
        // / \
        // true false -> transition.setAnimator(LayoutTransition.APPEARING,null)
        // |
        // mCustomAnimCB.isChecked() ?
        // / \
        // true false ->
        // transition.setAnimator(LayoutTransition.APPEARING,mDefaultAppearingAnim)
        // |-->transition.setAnimator(LayoutTransition.APPEARING,mCustomAppearingAnim)
        transition.setAnimator(LayoutTransition.APPEARING, appearingCB
                .isChecked() ? (customAnimCB.isChecked() ? mCustomAppearingAnim
                : mDefaultAppearingAnim) : null);

        // 后面三个依次类推
        transition
                .setAnimator(
                        LayoutTransition.DISAPPEARING,
                        disappearingCB.isChecked() ? (customAnimCB.isChecked() ? mCustomDisappearingAnim
                                : mDefaultDisappearingAnim)
                                : null);
        transition
                .setAnimator(
                        LayoutTransition.CHANGE_APPEARING,
                        changingAppearingCB.isChecked() ? (customAnimCB
                                .isChecked() ? mCustomChangingAppearingAnim
                                : mDefaultChangingAppearingAnim) : null);
        transition
                .setAnimator(
                        LayoutTransition.CHANGE_DISAPPEARING,
                        changingDisappearingCB.isChecked() ? (customAnimCB
                                .isChecked() ? mCustomChangingDisappearingAnim
                                : mDefaultChangingDisappearingAnim) : null);
    }

    // 创建自定义动画
    private void createCustomAnimations(LayoutTransition transition) {
        // Changing while Adding
        // 多个属性同时动画
        PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("left", 0, 1);
        PropertyValuesHolder pvhTop = PropertyValuesHolder.ofInt("top", 0, 1);
        PropertyValuesHolder pvhRight = PropertyValuesHolder.ofInt("right", 0,
                1);
        PropertyValuesHolder pvhBottom = PropertyValuesHolder.ofInt("bottom",
                0, 1);
        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat("scaleX",
                1f, 0f, 1f);
        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat("scaleY",
                1f, 0f, 1f);

        // 自定义的ChangingAppearing动画
        // 感觉就是从无到有地缩放了一遍
        mCustomChangingAppearingAnim = ObjectAnimator.ofPropertyValuesHolder(
                this, pvhLeft, pvhTop, pvhRight, pvhBottom, pvhScaleX,
                pvhScaleY).setDuration(
                transition.getDuration(LayoutTransition.CHANGE_APPEARING));
        mCustomChangingAppearingAnim.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setScaleX(1f);
                view.setScaleY(1f);
            }
        });

        // 自定义的ChangingDisappearing动画
        // 有一个平面旋转
        // Changing while Removing
        Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
        Keyframe kf1 = Keyframe.ofFloat(.9999f, 360f);
        Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
        PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe(
                "rotation", kf0, kf1, kf2);
        mCustomChangingDisappearingAnim = ObjectAnimator
                .ofPropertyValuesHolder(this, pvhLeft, pvhTop, pvhRight,
                        pvhBottom, pvhRotation)
                .setDuration(
                        transition
                                .getDuration(LayoutTransition.CHANGE_DISAPPEARING));
        mCustomChangingDisappearingAnim
                .addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator anim) {
                        View view = (View) ((ObjectAnimator) anim).getTarget();
                        view.setRotation(0f);
                    }
                });

        // 自定义的Appearing动画：纵向旋转（以Y为轴翻转）出现
        // Adding
        mCustomAppearingAnim = ObjectAnimator
                .ofFloat(null, "rotationY", 90f, 0f).setDuration(
                        transition.getDuration(LayoutTransition.APPEARING));
        mCustomAppearingAnim.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setRotationY(0f);
            }
        });

        // 自定义的Disappearing动画：横向旋转（以X为轴翻转）消失
        // Removing
        mCustomDisappearingAnim = ObjectAnimator.ofFloat(null, "rotationX", 0f,
                90f).setDuration(
                transition.getDuration(LayoutTransition.DISAPPEARING));
        mCustomDisappearingAnim.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setRotationX(0f);
            }
        });
    }

    /*
    ViewGroup中的子元素可以通过setVisibility使其Visible、Invisible或Gone，当有子元素可见性改变时(VISIBLE、GONE)，可以向其应用动画，通过LayoutTransition类应用此类动画：

    transition.setAnimator(LayoutTransition.DISAPPEARING, mCustomDisappearingAnim);
    通过setAnimator应用动画，第一个参数表示应用的情境，可以以下4种类型：

    APPEARING　　　　　　　　当一个元素在其父元素中变为Visible时对这个元素应用动画
    CHANGE_APPEARING　　　 当一个元素在其父元素中变为Visible时，因系统要重新布局有一些元素需要移动，对这些要移动的元素应用动画
    DISAPPEARING　　　　　　 当一个元素在其父元素中变为GONE时对其应用动画
    CHANGE_DISAPPEARING　  当一个元素在其父元素中变为GONE时，因系统要重新布局有一些元素需要移动，这些要移动的元素应用动画.
    第二个参数为一Animator

    mTransitioner.setStagger(LayoutTransition.CHANGE_APPEARING, 30);
    此函数设置动画延迟时间，参数分别为类型与时间
     */
}
