package com.cheng.mvpframestudy.themvp.presenter.activity;

import android.view.MenuItem;
import android.widget.Toast;

import com.cheng.mvpframestudy.R;
import com.cheng.mvpframestudy.themvp.frame.presenter.ActivityPresenter;
import com.cheng.mvpframestudy.themvp.ui.delegate.Demo5Delegate;

public class Demo5Activity extends ActivityPresenter<Demo5Delegate> {


    @Override
    protected Class<Demo5Delegate> getDelegateClass() {
        return Demo5Delegate.class;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu) {
            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
