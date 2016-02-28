package com.cheng.zenofdesignpatterns;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cheng.designpatternstudy.R;
import com.cheng.zenofdesignpatterns.listadapter.ZenofDesignPatternsListAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * 《设计模式之禅》
 */
public class ZenofDesignPatternsActivity extends AppCompatActivity {

    private ListView mPartsLV;
    private ZenofDesignPatternsListAdapter mZoDPListAdapter;

    private String[] mParts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zenof_designpatterns);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        this.mPartsLV = (ListView) this.findViewById(R.id.zoi_parts_lv);
    }

    private void initData() {
        mParts = getResources().getStringArray(R.array.zodpparts);
        List<String> partsList = Arrays.asList(mParts);
        mZoDPListAdapter = new ZenofDesignPatternsListAdapter(this, partsList);
        mPartsLV.setAdapter(mZoDPListAdapter);
    }

    private void initListener() {
        mPartsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ZenofDesignPatternsActivity.this, ZoDPPartsActivity.class);
                intent.putExtra("partname", mParts[position]);
                intent.putExtra("partnum", position);
                startActivity(intent);
            }
        });
    }

}
