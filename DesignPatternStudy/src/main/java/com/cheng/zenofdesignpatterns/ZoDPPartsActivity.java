package com.cheng.zenofdesignpatterns;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.cheng.designpatternstudy.R;
import com.cheng.zenofdesignpatterns.listadapter.ZenofDesignPatternsListAdapter;

import java.util.Arrays;
import java.util.List;

public class ZoDPPartsActivity extends AppCompatActivity {

    private TextView mTitleTV;
    private ListView mChaptersLV;

    private int mPartNum;
    private int[] mPartArrayIDs = {
            R.array.zodppart1, R.array.zodppart2,
            R.array.zodppart3, R.array.zodppart4,
            R.array.zodppart5};
    private int[] mPartChapterArrayIDs = {
            R.array.zodppart1chapterClazzes,
            R.array.zodppart2chapterClazzes,
            R.array.zodppart3chapterClazzes,
            R.array.zodppart4chapterClazzes,
            R.array.zodppart5chapterClazzes};
    private Class[] mChapterClazzes;
    private int mClazzesSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zodp_parts);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        this.mTitleTV = (TextView) this.findViewById(R.id.zoi_title_tv);
        this.mChaptersLV = (ListView) this.findViewById(R.id.zoi_chapters_lv);
    }

    private void initData() {
        String partname = this.getIntent().getStringExtra("partname");
        mPartNum = this.getIntent().getIntExtra("partnum", 0);
        this.mTitleTV.setText(partname);
        String[] chapters = getResources().getStringArray(mPartArrayIDs[mPartNum]);
        List<String> chaptersList = Arrays.asList(chapters);
        ZenofDesignPatternsListAdapter adapter = new ZenofDesignPatternsListAdapter(this, chaptersList);
        this.mChaptersLV.setAdapter(adapter);
        // 通过读取Array中的配置调整
        String[] chapterClazzStrs = getResources().getStringArray(mPartChapterArrayIDs[mPartNum]);
        int size = chapterClazzStrs.length;
        mClazzesSize = size;
        mChapterClazzes = new Class[size];
        for (int i = 0; i < size; i++) {
            try {
                mChapterClazzes[i] = Class.forName(chapterClazzStrs[i]);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void initListener() {
        this.mChaptersLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mClazzesSize <= position) return;
                if (mChapterClazzes[position] == null) return;
                Intent intent = new Intent(ZoDPPartsActivity.this, mChapterClazzes[position]);
                startActivity(intent);
            }
        });
    }

}
