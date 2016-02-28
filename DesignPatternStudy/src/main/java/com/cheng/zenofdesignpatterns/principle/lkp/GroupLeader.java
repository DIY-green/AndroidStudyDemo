package com.cheng.zenofdesignpatterns.principle.lkp;

import java.util.List;

/**
 * 体育委员类
 */
public class GroupLeader {
    private List<Girl> listGirls;

    // 传递全班的女生近来
    public GroupLeader(List<Girl> _list) {
        this.listGirls = _list;
    }

    // 清查女生数量
    public void countGirls() {
        System.out.println("女生数量是：" + listGirls.size());
    }
}
