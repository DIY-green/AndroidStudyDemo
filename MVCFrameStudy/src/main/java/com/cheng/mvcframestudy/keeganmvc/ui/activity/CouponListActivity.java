/**
 * Copyright (C) 2015. Keegan小钢（http://keeganlee.me）
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cheng.mvcframestudy.keeganmvc.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;
import android.widget.Toast;


import com.cheng.mvcframestudy.R;
import com.cheng.mvcframestudy.keeganmvc.adapter.CouponListAdapter;
import com.cheng.mvcframestudy.keeganmvc.core.ActionCallbackListener;
import com.cheng.mvcframestudy.keeganmvc.model.CouponBO;

import java.util.List;

/**
 * 券列表
 *
 * @version 1.0 创建时间：15/6/28
 */
public class CouponListActivity extends KBaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private CouponListAdapter listAdapter;
    private int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_list);

        initViews();
        getData();

        // TODO 添加上拉加载更多的功能
    }

    private void initViews() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        listView = (ListView) findViewById(R.id.list_view);
        listAdapter = new CouponListAdapter(this);
        listView.setAdapter(listAdapter);
    }

    private void getData() {
        this.appAction.listCoupon(currentPage, new ActionCallbackListener<List<CouponBO>>() {
            @Override
            public void onSuccess(List<CouponBO> data) {
                if (!data.isEmpty()) {
                    if (currentPage == 1) { // 第一页
                        listAdapter.setItems(data);
                    } else { // 分页数据
                        listAdapter.addItems(data);
                    }
                }
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        // 需要重置当前页为第一页，并且清掉数据
        currentPage = 1;
        listAdapter.clearItems();
        getData();
    }
}
