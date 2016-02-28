package com.cheng.bigtalkdesignpatterns.iterator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.cheng.designpatternstudy.R;

public class BTDPIteratorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btdp_iterator);
    }

    public void onClick(View v) {
        final Aggregat aggregat = new ConcreteAggregat();
        final Iterator iterator = aggregat.createIterator();
        System.out.println(iterator.first());
        while (!iterator.isDone()) {
            System.out.println(iterator.next());
        }
    }

}
