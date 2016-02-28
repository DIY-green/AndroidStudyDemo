package com.cheng.animationstudy.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheng.animationstudy.R;
import com.cheng.utils.ViewFinder;

/**
 * 仿蘑菇街购物车动画
 * @category 模仿蘑菇街购物车动画效果 使用Tween动画
 * @issue 1、第一次执行动画效果图片放大效果明显，之后放大效果不明显，蘑菇街也有这样的问题。
 *        2、弹出的popubWindow变形
 */
public class UiImitateMogujieCart extends AppCompatActivity {

    private ImageView mCartAnimationIV;
    private TextView mGoodsNumTV;
    private Animation mAnimation;
    private int mGoodsNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_imitatemogujiecart);

        initView();
        initListener();
    }

    private void initView() {
        mCartAnimationIV = ViewFinder.findViewById(this, R.id.cart_anim_icon);
        mGoodsNumTV = ViewFinder.findViewById(this, R.id.detail_shopping_new);

        mAnimation = AnimationUtils.loadAnimation(this, R.anim.sda_mogujie_cartanim);
        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mGoodsNum++;
                mGoodsNumTV.setText(mGoodsNum+"");
                mCartAnimationIV.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mCartAnimationIV.setVisibility(View.VISIBLE);
                mCartAnimationIV.startAnimation(mAnimation);
            }
        }, 1500);
    }

    private void initListener() {
        mGoodsNumTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCartAnimationIV.setVisibility(View.VISIBLE);
                mCartAnimationIV.startAnimation(mAnimation);
            }
        });
    }

}
