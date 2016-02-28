package com.cheng.bigtalkdesignpatterns;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.cheng.designpatternstudy.R;
import com.cheng.bigtalkdesignpatterns.listadapter.BigTalkDesignPatternsListAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * 《大话设计模式》
 */
public class BigTalkDesignPatternsActivity extends AppCompatActivity {
    
    private ListView mChaptersLV;
    private BigTalkDesignPatternsListAdapter mBTDPListAdapter;

    private SparseArray<Class> mClazzes;
    private int mClazzesLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bigtalk_designpatterns);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        this.mChaptersLV = (ListView) this.findViewById(R.id.bti_chapters_lv);
    }

    private void initData() {
        String[] chapters = getResources().getStringArray(R.array.btdpchapters);
        String[] chapterClazzes = getResources().getStringArray(R.array.btdpchapterClazzes);
        int chaptersLen = chapters.length;
        List<String> chaptersList = Arrays.asList(chapters);
        mClazzesLength = chaptersLen;
        mBTDPListAdapter = new BigTalkDesignPatternsListAdapter(this, chaptersList);
        mClazzes = new SparseArray<>();
        mChaptersLV.setAdapter(mBTDPListAdapter);
        try {
            for (int i = 0; i < chaptersLen; i++) {
                mClazzes.put(i, Class.forName(chapterClazzes[i]));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initListener() {
        mChaptersLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mClazzesLength <= position) return;
                if (mClazzes.get(position) == null) return;
                Intent intent = new Intent(BigTalkDesignPatternsActivity.this, mClazzes.get(position));
                startActivity(intent);
            }
        });
    }
}
