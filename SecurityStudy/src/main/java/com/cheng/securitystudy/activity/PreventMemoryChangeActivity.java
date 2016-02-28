package com.cheng.securitystudy.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.cheng.base.BaseActivity;
import com.cheng.securitystudy.R;
import com.cheng.utils.security.EncryptionUtil;

/**
 * APK保护方法之四：防止内存修改
 * 防止”八门神奇”通过内存数据
 */
public class PreventMemoryChangeActivity extends BaseActivity {

    private TextView mShowGoldTV;
    private CheckBox mKeepSafeCB;

    private int mGold;
    private String mGoldMD5; // 变量mGoldMD5用来存放加密后的mGold值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preventmemorychange);

        initView();
        initData();
    }

    private void initView() {
        this.mShowGoldTV = findViewByID(R.id.sdi_showgold_tv);
        this.mKeepSafeCB = findViewByID(R.id.sdi_keepsafe_cb);
    }

    private void initData() {
        this.mGold = 0;
        this.mGoldMD5 = EncryptionUtil.md5Encode(mGold + "");
    }

    public void plusGold(View v) {
        boolean keepSafe = this.mKeepSafeCB.isChecked();
        if (!keepSafe) {
            /*
            未加密
             */
            ++mGold;
            mGoldMD5 = EncryptionUtil.md5Encode(mGold + "");
            mShowGoldTV.setText("Gold : " + mGold);
        } else {
            /*
            加密
            每次使用gold值时都对获取的gold进行md5加密然后与goldMd5进行比较。
            如果不相同，说明gold值被修改了。我们可以判断为**了，
            这里对**的处理是把gold值改为0，并提示**。如果加密数据使用的des算法，
            还可以通过解密goldMd5，来获取真实的gold值
             */
            if (EncryptionUtil.md5Encode(mGold+"").equals(mGoldMD5)) {
                ++mGold;
                mGoldMD5 = EncryptionUtil.md5Encode(mGold + "");
                mShowGoldTV.setText(mGold + "");
            } else {
                mGold = 0;
                mGoldMD5 = EncryptionUtil.md5Encode(mGold + "");
                mShowGoldTV.setText(mGold + "");
                toast("不要作弊...");
            }
        }
    }

}
