package com.cheng.animationstudy.customview.baiduread;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.cheng.animationstudy.R;
import com.cheng.animationstudy.ui.UiImitateBaiduReadBubble;
import com.cheng.utils.ViewFinder;

/**
 *
 */
public class ImportMenuView extends RelativeLayout implements RippleLayout.RippleFinishListener, View.OnClickListener {

    private Context mContext = null;

    private RippleLayout mCloseRL;
    private RippleLayout mFirstBallRL;
    private RippleLayout mPCRL;
    private RippleLayout mSDCardRL;
    private View mShadowView;

    public ImportMenuView(Context context) {
        super(context);
    }

    public ImportMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ImportMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.ui_imitatebaiduread_importmenu, this, true);

        mCloseRL = ViewFinder.findViewById(this, R.id.sdi_close_rl);
        mFirstBallRL = ViewFinder.findViewById(this, R.id.sdi_firstball_rl);
        mPCRL = ViewFinder.findViewById(this, R.id.sdi_pc_rl);
        mSDCardRL = ViewFinder.findViewById(this, R.id.sdi_sdcard_rl);
        mShadowView = ViewFinder.findViewById(this, R.id.sdi_shadow);

        mCloseRL.setRippleFinishListener(this);
        mFirstBallRL.setRippleFinishListener(this);
        mPCRL.setRippleFinishListener(this);
        mSDCardRL.setRippleFinishListener(this);
        mShadowView.setOnClickListener(this);

        setVisibility(View.GONE);

        this.mContext = context;
    }

    public void animation(Context context) {
        Animation firstBallAnim = AnimationUtils.loadAnimation(context, R.anim.sda_baiduread_collistionimportqrcodebutton);
        mFirstBallRL.startAnimation(firstBallAnim);

        Animation pcAnim = AnimationUtils.loadAnimation(context, R.anim.sda_baiduread_collistionimportpcbutton);
        mPCRL.startAnimation(pcAnim);

        Animation sdcardAnim = AnimationUtils.loadAnimation(context, R.anim.sda_baiduread_collistionimportsdcardbutton);
        mSDCardRL.startAnimation(sdcardAnim);

        Animation fadeInAnim = AnimationUtils.loadAnimation(context, R.anim.sda_baiduread_fade_in);
        mShadowView.startAnimation(fadeInAnim);
    }

    public void animationOut() {
        Animation firstBallAnim = AnimationUtils.loadAnimation(mContext, R.anim.sda_baiduread_collistionimportqrcordebuttonout);
        mFirstBallRL.startAnimation(firstBallAnim);

        Animation pcAnim = AnimationUtils.loadAnimation(mContext, R.anim.sda_baiduread_collistionimportpcbuttonout);
        mPCRL.startAnimation(pcAnim);

        Animation sdcardAnim = AnimationUtils.loadAnimation(mContext, R.anim.sda_baiduread_collistionimportsdcardbuttonout);
        mSDCardRL.startAnimation(sdcardAnim);

        firstBallAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation fadeInAnim = AnimationUtils.loadAnimation(mContext, R.anim.sda_baiduread_fade_out);
                closeUnVisiableAnimation();
                mShadowView.startAnimation(fadeInAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void closeVisiableAnimation() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mCloseRL, "rotation", 0, -45);
        objectAnimator.setDuration(250);
        objectAnimator.setRepeatCount(0);
        objectAnimator.start();
    }

    public void closeUnVisiableAnimation() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mCloseRL, "rotation", -45, 0);
        objectAnimator.setDuration(200);
        objectAnimator.setRepeatCount(0);
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                UiImitateBaiduReadBubble.mRippleLayout.setVisibility(VISIBLE);
                setVisibility(GONE);
                setEnabled(false);
                setFocusable(false);
                UiImitateBaiduReadBubble.mRippleLayout.bringToFront();
            }
        });
        objectAnimator.start();
    }

    @Override
    public void onClick(View v) {
        animationOut();
    }

    @Override
    public void rippleFinish(int id) {
        switch (id) {
            case R.id.sdi_close_rl:
            case R.id.sdi_firstball_rl:
            case R.id.sdi_pc_rl:
            case R.id.sdi_sdcard_rl:
                animationOut();
                break;
        }
    }
}
