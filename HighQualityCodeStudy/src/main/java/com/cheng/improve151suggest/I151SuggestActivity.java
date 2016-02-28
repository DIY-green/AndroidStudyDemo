package com.cheng.improve151suggest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;
import com.cheng.highqualitycodestudy.R;
import com.cheng.improve151suggest.adapter.I151SuggestListAdapter;

/**
 * 《编写高质量代码：改善java程序的151个建议》
 */
public class I151SuggestActivity extends AppCompatActivity {

    private ListView mChaptersLV;
    private I151SuggestListAdapter mI151SuggestListAdapter;

    private SparseArray<Class> mClazzes;
    private int mClazzesLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i151suggest);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        this.mChaptersLV = (ListView) this.findViewById(R.id.isi_chapters_lv);
    }

    private void initData() {
        String[] chapters = getResources().getStringArray(R.array.i151schapters);
        String[] chapterClazzes = getResources().getStringArray(R.array.i151schapterClazzes);
        int chaptersLen = chapters.length;
        List<String> chaptersList = Arrays.asList(chapters);
        mClazzesLength = chaptersLen;
        mI151SuggestListAdapter = new I151SuggestListAdapter(this, chaptersList);
        mClazzes = new SparseArray<>();
        mChaptersLV.setAdapter(mI151SuggestListAdapter);
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
                Intent intent = new Intent(I151SuggestActivity.this, mClazzes.get(position));
                startActivity(intent);
            }
        });
    }

}
