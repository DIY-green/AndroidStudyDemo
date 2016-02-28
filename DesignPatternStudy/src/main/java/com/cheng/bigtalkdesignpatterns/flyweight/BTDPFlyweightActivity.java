package com.cheng.bigtalkdesignpatterns.flyweight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.cheng.designpatternstudy.R;

public class BTDPFlyweightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btdp_flyweight);
    }

    public void onClick(View v) {
        WebSiteFactory f = new WebSiteFactory();

        WebSite fx = f.getWebSiteCategory("商品展示");
        fx.use(new User("小菜"));
        WebSite fy = f.getWebSiteCategory("商品展示");
        fy.use(new User("大鸟"));
        WebSite fz = f.getWebSiteCategory("商品展示");
        fz.use(new User("静静"));

        WebSite fl = f.getWebSiteCategory("博客");
        fl.use(new User("老顽童"));
        WebSite fm = f.getWebSiteCategory("博客");
        fm.use(new User("桃谷六仙"));
        WebSite fn = f.getWebSiteCategory("博客");
        fn.use(new User("南海鳄神"));

        System.out.println("得到网站分类总数为：" + f.getWebSiteCount());
    }

}
