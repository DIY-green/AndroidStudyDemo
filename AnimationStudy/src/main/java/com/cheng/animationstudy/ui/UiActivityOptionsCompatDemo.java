package com.cheng.animationstudy.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheng.animationstudy.C;
import com.cheng.animationstudy.R;
import com.cheng.utils.ViewFinder;
import com.kale.activityoptions.ActivityCompatICS;
import com.kale.activityoptions.ActivityOptionsCompatICS;

public class UiActivityOptionsCompatDemo extends AppCompatActivity {

    public static boolean isSceneAnim = false;

    private ImageView mOriginalIV;
    private TextView mOriginalTV;
    private ImageView mChromeIV;

    private String mSwitchName = "testSwitch";
    private boolean mTestSwitch = false;
    private Intent mIntent;
    // 通过handler来相应动画操作，进行动画元素的恢复
    public static InnerHandler sHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_activityoptionscompatdemo);

        initData();
        initView();
    }

    private void initData() {
        SharedPreferences preferences = getSharedPreferences(C.kAPP_NAME, MODE_PRIVATE);
        mTestSwitch = preferences.getBoolean(mSwitchName, false);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(mSwitchName, !mTestSwitch);
        editor.commit();
        sHandler = new InnerHandler();
        this.mIntent = new Intent(UiActivityOptionsCompatDemo.this, UiActivityOptionsCompatDemoTarget.class);
    }

    private void initView() {
        this.mOriginalIV = ViewFinder.findViewById(this, R.id.sdi_original_iv);
        this.mOriginalTV = ViewFinder.findViewById(this, R.id.sdi_original_tv);
        this.mChromeIV = ViewFinder.findViewById(this, R.id.sdi_chrome_iv);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sdi_custom_btn:
                showCustomAnim();
                break;
            case R.id.sdi_scaleUp_btn:
                showScaleUpAnim(mOriginalIV);
                break;
            case R.id.sdi_thumbnail_btn:
                showThumbNailScaleAnim(mChromeIV);
                break;
            case R.id.sdi_scene_btn:
                screenTransitAnimByPair(
                        Pair.create((View) mOriginalIV, R.id.target_imageView),
                        Pair.create((View) mOriginalTV, R.id.target_textView),
                        Pair.create((View) mChromeIV, R.id.target_chrome_imageView)
                );
                break;
        }
    }

    /**
     * 简单做一个定制的动画，这个参数很简单，传入一个进入的动画的id，和移除动画的id即可
     */
    private void showCustomAnim() {
        if (mTestSwitch) {
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeCustomAnimation(
                    UiActivityOptionsCompatDemo.this,
                    R.anim.slide_bottom_in,
                    R.anim.slide_bottom_out);
            ActivityCompat.startActivity(
                    UiActivityOptionsCompatDemo.this,
                    mIntent,
                    optionsCompat.toBundle());
        } else {
            ActivityOptionsCompatICS optionsCompatICS
                    = ActivityOptionsCompatICS.makeCustomAnimation(
                    UiActivityOptionsCompatDemo.this,
                    R.anim.slide_bottom_in,
                    R.anim.slide_bottom_out);
            ActivityCompatICS.startActivity(
                    UiActivityOptionsCompatDemo.this,
                    mIntent,
                    optionsCompatICS.toBundle());
        }
    }

    /**
     * 这个在4.x上有用，可以实现新的Activity从某个固定的坐标以某个大小扩大至全屏
     */
    private void showScaleUpAnim(View view) {
        if (mTestSwitch) {
            ActivityOptionsCompat optionsCompat
                    = ActivityOptionsCompat.makeScaleUpAnimation(
                    view,
                    0, 0, // 拉伸开始的坐标
                    view.getMeasuredWidth(),
                    view.getMeasuredHeight()
            );
            ActivityCompat.startActivity(
                    UiActivityOptionsCompatDemo.this,
                    mIntent,
                    optionsCompat.toBundle());
        } else {
            ActivityOptionsCompatICS optionsCompatICS
                    = ActivityOptionsCompatICS.makeScaleUpAnimation(
                    view,
                    0, 0, // 拉伸开始的坐标
                    view.getMeasuredWidth(),
                    view.getMeasuredHeight()
            );
            ActivityCompatICS.startActivity(
                    UiActivityOptionsCompatDemo.this,
                    mIntent,
                    optionsCompatICS.toBundle());
        }
    }

    /**
     * 这个方法可以用于4.x上，是将一个小块的Bitmpat进行拉伸的动画
     */
    private void showThumbNailScaleAnim(ImageView view) {
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = view.getDrawingCache();
        if (mTestSwitch) {
            ActivityOptionsCompat optionsCompat
                    = ActivityOptionsCompat.makeThumbnailScaleUpAnimation(
                    view,
                    bitmap,
                    0, 0
            );
            ActivityCompat.startActivity(
                    UiActivityOptionsCompatDemo.this,
                    mIntent,
                    optionsCompat.toBundle());
        } else {
            ActivityOptionsCompatICS optionsCompatICS
                    = ActivityOptionsCompatICS.makeThumbnailScaleUpAnimation(
                    view,
                    bitmap,
                    0, 0
            );
            ActivityCompatICS.startActivity(
                    UiActivityOptionsCompatDemo.this,
                    mIntent,
                    optionsCompatICS.toBundle());
            // view.setDrawingCacheEnabled(false);
        }
    }

    /**
     * 当你需要当前界面中的某个元素和新界面中的元素有关时，你可以使用这个动画
     */
    public void showSceneAnim(View view) {
        isSceneAnim = true;
        if (mTestSwitch) {
            ActivityOptionsCompat optionsCompat
                    = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    UiActivityOptionsCompatDemo.this,
                    view,
                    view.toString()
            );
            ActivityCompat.startActivity(
                    UiActivityOptionsCompatDemo.this,
                    mIntent,
                    optionsCompat.toBundle());
        } else {
            ActivityOptionsCompatICS optionsCompatICS
                    = ActivityOptionsCompatICS.makeSceneTransitionAnimation(
                    UiActivityOptionsCompatDemo.this,
                    view,
                    view.getId()
            );
            ActivityCompatICS.startActivity(
                    UiActivityOptionsCompatDemo.this,
                    mIntent,
                    optionsCompatICS.toBundle());
        }
    }

    /**
     * 这里可以用多个元素或者是单个元素进行动画，这里用了多个元素。为了保证动画效果，这里进行了渐变操作，
     * 在handle中进行了恢复操作，这样让动画看起来平滑了很多
     * @param views
     */
    private void screenTransitAnimByPair(Pair<View, Integer>... views) {
        isSceneAnim = true;
        if (mTestSwitch) {
            Pair<View, String>[] tViews = new Pair[views.length];
            int i = 0;
            for (Pair<View, Integer> view : views) {
                tViews[i] = new Pair<>(view.first, view.second.toString());
                i++;
            }
            ActivityOptionsCompat optionsCompat
                    = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    UiActivityOptionsCompatDemo.this,
                    tViews
            );
            ActivityCompat.startActivity(
                    UiActivityOptionsCompatDemo.this,
                    mIntent,
                    optionsCompat.toBundle()
            );
        } else {
            ActivityOptionsCompatICS optionsCompatICS
                    = ActivityOptionsCompatICS.makeSceneTransitionAnimation(
                    UiActivityOptionsCompatDemo.this,
                    views
            );
            ActivityCompatICS.startActivity(
                    UiActivityOptionsCompatDemo.this,
                    mIntent,
                    optionsCompatICS.toBundle()
            );
        }
    }

    class InnerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 123) {
                mOriginalIV.setVisibility(View.INVISIBLE);
                mOriginalTV.setVisibility(View.INVISIBLE);
                mChromeIV.setVisibility(View.INVISIBLE);
            } else if (msg.what == 321) {
                mOriginalIV.setVisibility(View.VISIBLE);
                mOriginalTV.setVisibility(View.VISIBLE);
                mChromeIV.setVisibility(View.VISIBLE);
                isSceneAnim = false;
            }
        }
    }

}
