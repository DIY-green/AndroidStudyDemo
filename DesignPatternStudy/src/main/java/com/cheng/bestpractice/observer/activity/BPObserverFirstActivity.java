package com.cheng.bestpractice.observer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheng.designpatternstudy.R;
import com.cheng.bestpractice.observer.base.BaseObserverActivity;
import com.cheng.bestpractice.observer.util.EventType;

public class BPObserverFirstActivity extends BaseObserverActivity {

    private TextView mLableTV;
    private ImageView mPicIV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bpobserver_first);
        mLableTV = (TextView) findViewById(R.id.tv_label);
        mPicIV = (ImageView) findViewById(R.id.iv_pic);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bpobserver_first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.item_gootheractivity:
                goActivity(BPObserverOtherActivity.class);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void goActivity(Class<?> activity){
        Intent intent = new Intent(this,activity);
        startActivity(intent);
    }

    @Override
    protected void onChange(String eventType) {
        if(EventType.UPDATE_MAIN == eventType){
            mPicIV.setImageResource(R.mipmap.pic_two);
        }else if(EventType.UPDATE_Text == eventType){
            mLableTV.setText("图片被更新");
        }
    }

    @Override
    protected String[] getObserverEventType() {
        return new String[]{
                EventType.UPDATE_MAIN,
                EventType.UPDATE_Text
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
