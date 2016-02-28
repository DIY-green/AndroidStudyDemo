package com.cheng.zenofdesignpatterns.perfectworld.command_chain;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ZoDPCommandChainActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("命令模式 + 责任链模式");
        String content = "小结：\n" +
                "使用命令模式+责任链模式模拟实现搬移UNIX的命令\n\n" +
                "该框架还有一个名称，叫做命令链（Chain of Command）模式，具体来说就是命令模式作为" +
                "责任链模式的排头兵，由命令模式分发具体的消息到责任链模式。";
        mContentTV.setText(content);
    }

    @Override
    public void onClick(View v) {
        // 1. 使用命令模式+责任链模式模拟实现搬移UNIX的命令
        Invoker invoker = new Invoker();
        while(true){
            // unix写的默认提示符号
            System.out.print("#");
            // 捕获输出
            String input = null;
            try {
                input = (new BufferedReader(new InputStreamReader(System.in))).readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 输入quit或exit则退出
            if(input.equals("quit") || input.equals("exit")){
                return;
            }
            System.out.println(invoker.exec(input));
        }
    }
}
