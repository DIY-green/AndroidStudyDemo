package com.cheng.animationstudy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cheng.animationstudy.C;
import com.cheng.animationstudy.R;
import com.cheng.utils.Logger;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

public class NineOldAndroidsActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout mRootViewRL;
    private Button mMenuBtn;
    private Button mItemBtn1;
    private Button mItemBtn2;
    private Button mItemBtn3;
    private Button mItemBtn4;
    private Button mItemBtn5;

    private boolean mIsMenuOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootViewRL = (RelativeLayout) getLayoutInflater().inflate(R.layout.activity_nineoldandroids, null);
        setContentView(mRootViewRL);

        Logger.TAG = "NineOldAndroidsActivity";
        initView();
        initListener();
    }

    private void initView() {
        this.mMenuBtn = (Button) this.findViewById(R.id.btn_menu);
        this.mItemBtn1 = (Button) this.findViewById(R.id.btn_item1);
        this.mItemBtn2 = (Button) this.findViewById(R.id.btn_item2);
        this.mItemBtn3 = (Button) this.findViewById(R.id.btn_item3);
        this.mItemBtn4 = (Button) this.findViewById(R.id.btn_item4);
        this.mItemBtn5 = (Button) this.findViewById(R.id.btn_item5);
    }

    private void initListener() {
        this.mMenuBtn.setOnClickListener(this);
        this.mItemBtn1.setOnClickListener(this);
        this.mItemBtn2.setOnClickListener(this);
        this.mItemBtn3.setOnClickListener(this);
        this.mItemBtn4.setOnClickListener(this);
        this.mItemBtn5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mMenuBtn) {
            if (!mIsMenuOpen) {
                openMenu();
            } else {
                closeMenu();
            }
        } else {
            Toast.makeText(this.getApplicationContext(), "你点击了按钮：" + v, Toast.LENGTH_SHORT).show();
        }
    }

    private void openMenu() {
        mIsMenuOpen = true;
        doAnimateOpen(mItemBtn1, 0, 5, C.Int.MENU_DURATION);
        doAnimateOpen(mItemBtn2, 1, 5, C.Int.MENU_DURATION);
        doAnimateOpen(mItemBtn3, 2, 5, C.Int.MENU_DURATION);
        doAnimateOpen(mItemBtn4, 3, 5, C.Int.MENU_DURATION);
        doAnimateOpen(mItemBtn5, 4, 5, C.Int.MENU_DURATION);
    }

    private void closeMenu() {
        mIsMenuOpen = false;
        doAnimateClose(mItemBtn1, 0, 5, C.Int.MENU_DURATION);
        doAnimateClose(mItemBtn2, 1, 5, C.Int.MENU_DURATION);
        doAnimateClose(mItemBtn3, 2, 5, C.Int.MENU_DURATION);
        doAnimateClose(mItemBtn4, 3, 5, C.Int.MENU_DURATION);
        doAnimateClose(mItemBtn5, 4, 5, C.Int.MENU_DURATION);
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

}
