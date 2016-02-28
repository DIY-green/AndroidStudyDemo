package com.cheng.bigtalkdesignpatterns;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import com.cheng.designpatternstudy.R;
import com.cheng.bigtalkdesignpatterns.flyweight.BTDPFlyweightActivity;
import com.cheng.bigtalkdesignpatterns.listadapter.BigTalkDesignPatternsListAdapter;

import java.util.Arrays;

public class Chapter26Activity extends AppCompatActivity {

    private static final String TAG = "Chapter26Activity";

    private Button mListHeaderBtn;
    private ListView mChapterLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bigtalk_designpatternschapter);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        this.mListHeaderBtn = new Button(this);
        this.mChapterLV = (ListView) findViewById(R.id.isi_chapter_lv);
        this.mListHeaderBtn.setLayoutParams(new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        this.mListHeaderBtn.setText("示例");
    }

    private void initData() {
        String[] suggests = getResources().getStringArray(R.array.btdpchapter1);
        BigTalkDesignPatternsListAdapter adapter = new BigTalkDesignPatternsListAdapter(this, Arrays.asList(suggests));
        mChapterLV.setAdapter(adapter);
        mChapterLV.addHeaderView(mListHeaderBtn);
    }

    private void initListener() {
        this.mListHeaderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chapter26Activity.this, BTDPFlyweightActivity.class);
                startActivity(intent);
            }
        });
    }
}
