package com.cheng.zenofdesignpatterns.principle.isp;

/**
 * 星探抽象类
 */
public abstract class AbstractSearcher {
    protected IGoodBodyGirl goodBodyGirl;
    protected IGreatTemperamentGirl greatTemperamentGirl;

    public AbstractSearcher(IGoodBodyGirl _goodBodyGirl) {
        this.goodBodyGirl = _goodBodyGirl;
    }

    public AbstractSearcher(IGreatTemperamentGirl _greatTemperamentGirl) {
        this.greatTemperamentGirl = _greatTemperamentGirl;
    }

    // 搜索美女，列出美女信息
    public abstract void show();
}
