package com.cheng.animationstudy.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheng.animationstudy.R;
import com.cheng.utils.ViewFinder;
import com.kale.activityoptions.ActivityCompatICS;
import com.kale.activityoptions.ActivityOptionsCompatICS;

public class UiActivityOptionsCompatDemo extends AppCompatActivity {

    public static boolean isSceneAnim = false;

    private ImageView mOriginalIV;
    private TextView mOriginalTV;
    private ImageView mChromeIV;

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

    private void showCustomAnim() {
        ActivityOptionsCompatICS optionsCompatICS
                = ActivityOptionsCompatICS.makeCustomAnimation(
                this,
                R.anim.slide_bottom_in,
                R.anim.slide_bottom_out);
        ActivityCompatICS.startActivity(
                UiActivityOptionsCompatDemo.this,
                mIntent,
                optionsCompatICS.toBundle());
    }

    private void showScaleUpAnim(View view) {
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

    private void showThumbNailScaleAnim(ImageView view) {
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = view.getDrawingCache();
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

    public void showSceneAnim(View view) {
        isSceneAnim = true;
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

    /**
     * 这里可以用多个元素或者是单个元素进行动画，这里用了多个元素。为了保证动画效果，这里进行了渐变操作，
     * 在handle中进行了恢复操作，这样让动画看起来平滑了很多
     * @param views
     */
    private void screenTransitAnimByPair(Pair<View, Integer>... views) {
        isSceneAnim = true;
        ActivityOptionsCompatICS optionsCompatICS
                = ActivityOptionsCompatICS.makeSceneTransitionAnimation(
                UiActivityOptionsCompatDemo.this,
                views
        );
        ActivityCompatICS.startActivity(
                UiActivityOptionsCompatDemo.this,
                mIntent,
                optionsCompatICS.toBundle());
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
