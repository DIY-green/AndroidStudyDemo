package com.cheng.zenofdesignpatterns.principle.srp;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;

public class ZoDPSingleResponsibilityPrincipleActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("单一职责原则");
        String content = "定义：\nThere should never be more than one reason" +
                " for a class to change.\n" +
                "应该有且仅有一个原因引起类的变更。" +
                "\n职责：业务逻辑，或者对象能够承担的责任，并以某种行为方式来执行。" +
                "\n\n单一职责原则的好处：\n" +
                "- 类的复杂性降低，实现什么职责都有清晰明确的定义\n" +
                "- 可读性提高，复杂性降低，那当然可读性提高了\n" +
                "- 可维护性提高，可读性提高，那当然更容易维护了\n" +
                "- 变更引起的风险降低，变更是必不可少的，如果接口的单一职责做得好，" +
                "一个接口修改只对相应的实现类有影响，对其他的接口无影响，这对系统的" +
                "扩展性、维护性都有非常大的帮助\n\n" +
                "注意\n" +
                "单一职责原则提出了一个编写程序的标准，用‘职责’或‘变化原因’来衡量接口" +
                "或类设计得是否优良，但是‘职责’和‘变化原因’，都是不可度量的，因项目而" +
                "异，因环境而异。\n\n" +
                "最佳实践：\n" +
                "对于单一职责原则，我的建议是接口一定要做到单一职责，类的设计尽量做到" +
                "只有一个原因引起变化。";
        mContentTV.setText(content);
    }

    public void onClick(View v) {

    }

    /**
     * 以电话类为例分析单一职责原则
     * 电话通话的时候有4个过程发生：拨号、通话、回应、挂机
     * 涉及的职责：
     * 一个是协议管理，一个是数据传输
     */

}
