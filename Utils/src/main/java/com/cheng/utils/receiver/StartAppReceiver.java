package com.cheng.utils.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 启动程序广播接收器
 */
public class StartAppReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        context.startActivity(context.getPackageManager()
                .getLaunchIntentForPackage(context.getPackageName()));
    }

}
