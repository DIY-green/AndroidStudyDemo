package com.cheng.animationstudy.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cheng.animationstudy.R;
import com.cheng.animationstudy.customview.zrclistview.SimpleFooter;
import com.cheng.animationstudy.customview.zrclistview.SimpleHeader;
import com.cheng.animationstudy.customview.zrclistview.ZrcListView;
import com.cheng.utils.ViewFinder;

import java.util.ArrayList;
import java.util.List;

public class UiZrcListView extends AppCompatActivity {

    private ZrcListView mZrcListView;

    private Handler mHandler;
    private List<String> mDatas;
    private MyAdapter mAdapter;
    private int pageId = -1;

    private static final String[][] names = new String[][]{
        {"加拿大","瑞典","澳大利亚","瑞士","新西兰","挪威","丹麦","芬兰","奥地利","荷兰","德国","日本","比利时","意大利","英国"},
        {"德国","西班牙","爱尔兰","法国","葡萄牙","新加坡","希腊","巴西","美国","阿根廷","波兰","印度","秘鲁","阿联酋","泰国"},
        {"智利","波多黎各","南非","韩国","墨西哥","土耳其","埃及","委内瑞拉","玻利维亚","乌克兰"},
        {"以色列","海地","中国","沙特阿拉伯","俄罗斯","哥伦比亚","尼日利亚","巴基斯坦","伊朗","伊拉克"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_zrclistview);

        initView();
        initListener();
        initData();
    }

    private void initView() {
        this.mZrcListView = ViewFinder.findViewById(this, R.id.zListView);
        // 设置默认偏移量，主要用于实现透明标题栏功能。（可选）
        float density = getResources().getDisplayMetrics().density;
        this.mZrcListView.setFirstTopOffset((int) (50 * density));

        // 设置下拉刷新的样式（可选，但如果没有Header则无法下拉刷新）
        SimpleHeader header = new SimpleHeader(this);
        header.setTextColor(0xff0066aa);
        header.setCircleColor(0xff33bbee);
        this.mZrcListView.setHeadable(header);

        // 设置加载更多的样式（可选）
        SimpleFooter footer = new SimpleFooter(this);
        footer.setCircleColor(0xff33bbee);
        this.mZrcListView.setFootable(footer);

        // 设置列表项出现动画（可选）
        this.mZrcListView.setItemAnimForTopIn(R.anim.sda_zrc_topitem_in);
        this.mZrcListView.setItemAnimForBottomIn(R.anim.sda_zrc_bottomitem_in);
    }

    private void initListener() {
        // 下拉刷新事件回调（可选）
        this.mZrcListView.setOnRefreshStartListener(new ZrcListView.OnStartListener() {
            @Override
            public void onStart() {
                refresh();
            }
        });

        // 加载更多事件回调（可选）
        this.mZrcListView.setOnLoadMoreStartListener(new ZrcListView.OnStartListener() {
            @Override
            public void onStart() {
                loadMore();
            }
        });
    }

    private void initData() {
        this.mHandler = new Handler();
        this.mAdapter = new MyAdapter();
        this.mZrcListView.setAdapter(mAdapter);
        this.mZrcListView.refresh(); // 主动下拉刷新
    }

    private void refresh(){
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int rand = (int) (Math.random() * 2); // 随机数模拟成功失败。这里从有数据开始。
                if (rand == 0 || pageId == -1) {
                    pageId = 0;
                    mDatas = new ArrayList<String>();
                    for (String name : names[0]) {
                        mDatas.add(name);
                    }
                    mAdapter.notifyDataSetChanged();
                    mZrcListView.setRefreshSuccess("加载成功"); // 通知加载成功
                    mZrcListView.startLoadMore(); // 开启LoadingMore功能
                } else {
                    mZrcListView.setRefreshFail("加载失败");
                }
            }
        }, 2 * 1000);
    }

    private void loadMore(){
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pageId++;
                if (pageId < names.length) {
                    for (String name : names[pageId]) {
                        mDatas.add(name);
                    }
                    mAdapter.notifyDataSetChanged();
                    mZrcListView.setLoadMoreSuccess();
                } else {
                    mZrcListView.stopLoadMore();
                }
            }
        }, 2 * 1000);
    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mDatas==null ? 0 : mDatas.size();
        }
        @Override
        public Object getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView;
            if(convertView==null) {
                textView = (TextView) getLayoutInflater().inflate(android.R.layout.simple_list_item_1, null);
            }else{
                textView = (TextView) convertView;
            }
            textView.setText(mDatas.get(position));
            return textView;
        }
    }

}
