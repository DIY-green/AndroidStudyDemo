/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.diygreen.android6new.newapi;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.IntentFilter;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.service.chooser.ChooserTarget;
import android.service.chooser.ChooserTargetService;

import com.diygreen.android6new.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * <br/>Program Name:
 * <br/>Date: 2016年3月7日
 *
 * @author 李旺成
 * @version 1.0
 */

@TargetApi(Build.VERSION_CODES.M)
public class DirectShareService extends ChooserTargetService {

    @Override
    public List<ChooserTarget> onGetChooserTargets(ComponentName targetActivityName, IntentFilter matchedFilter) {
        ComponentName componentName = new ComponentName(getPackageName(),
                ShareActivity.class.getCanonicalName());
        ArrayList<ChooserTarget> targets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Bundle extras = new Bundle();
            extras.putInt("directsharekey", i);
            targets.add(new ChooserTarget(
                    "name_" + i,
                    Icon.createWithResource(this, R.mipmap.ic_logo),
                    0.5f,
                    componentName,
                    extras));
        }
        return targets;
    }
}
