package com.cheng.bigtalkdesignpatterns;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.cheng.bigtalkdesignpatterns.listadapter.BigTalkDesignPatternsListAdapter;
import com.cheng.designpatternstudy.R;
import java.util.Arrays;

public class Chapter09Activity extends AppCompatActivity {

    private static final String TAG = "Chapter09Activity";

    private ListView mChapterLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bigtalk_designpatternschapter);
        initView();
        initData();
    }

    private void initView() {
        this.mChapterLV = (ListView) findViewById(R.id.isi_chapter_lv);
    }

    private void initData() {
        String[] suggests = getResources()
                .getStringArray(R.array.btdpchapter9);
        BigTalkDesignPatternsListAdapter adapter = new BigTalkDesignPatternsListAdapter(this, Arrays.asList(suggests));
        mChapterLV.setAdapter(adapter);
    }

}
