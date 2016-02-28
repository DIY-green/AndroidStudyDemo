package com.cheng.animationstudy.activity;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cheng.animationstudy.C;
import com.cheng.animationstudy.R;
import com.cheng.utils.Logger;
import com.cheng.utils.ViewFinder;

/**
 * 仿淘宝购买商品主页后缩
 */
public class UiImitateTaobaoRetraction extends AppCompatActivity {

    private TextView mLogoutTV;
    private View mRootView;
    /**
     * 显示父类的POP 推出
     */
    PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mRootView = LayoutInflater.from(this).inflate(R.layout.ui_imitatetaobaoretraction, null);
        setContentView(mRootView);

        initView();
    }

    private void initView() {
        this.mLogoutTV = ViewFinder.findViewById(this, R.id.exitLogIn);
        this.mLogoutTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shouPop();
            }
        });
    }

    private void shouPop() {
        // Animation scaleAnimation = new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f)
        // 整个屏幕就0.0到1.0的大小//缩放
        // Animation.ZORDER_BOTTOM, 1.0f, Animation.RESTART, 1.0f);
        // scaleAnimation.setDuration(1000);
        // scaleAnimation.setFillAfter(true);
        // mRootView.startAnimation(scaleAnimation);
        final Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.sda_taobaopop_anim);
        scaleAnimation.setDuration(C.Int.ANIM_DURATION * 2);
        scaleAnimation.setFillAfter(true);
        mRootView.startAnimation(scaleAnimation);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.ui_imitatetaobao_logoutpop, null);
        layout.getBackground().setAlpha(220);
        Button sureBtn = ViewFinder.findViewById(layout, R.id.sdi_sure_btn);
        Button cancelBtn = ViewFinder.findViewById(layout, R.id.sdi_cancel_btn);
        sureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow == null) return;
                mPopupWindow.dismiss();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow == null) return;
                mPopupWindow.dismiss();
                mRootView.clearAnimation();
            }
        });
        mPopupWindow = shwoPop(LinearLayout.LayoutParams.MATCH_PARENT, 0, false,
                Gravity.BOTTOM, layout, layout);
    }

    private PopupWindow shwoPop(int witch, int hight_bottom, boolean isDown, int gravity, View... views) {
        PopupWindow popWindow = new PopupWindow(views[0], witch, LinearLayout.LayoutParams.MATCH_PARENT);
        popWindow.setOutsideTouchable(true);
        popWindow.setFocusable(true);
        popWindow.setTouchable(true);
        popWindow.setBackgroundDrawable(new BitmapDrawable());// 点击空白时popupwindow关闭
        int[] location = new int[2];
        try {
            views[1].getLocationOnScreen(location);
            popWindow.showAtLocation(views[1], gravity, 10, hight_bottom);
            popWindow.update();
        } catch (Exception e) {
            Logger.e(e);
        }
        return popWindow;
    }

}
