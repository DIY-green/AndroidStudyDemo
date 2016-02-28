package com.cheng.zenofdesignpatterns.patterns.factorymethod.multi;

import com.cheng.zenofdesignpatterns.patterns.factorymethod.nvwa.BlackHuman;
import com.cheng.zenofdesignpatterns.patterns.factorymethod.nvwa.Human;

/**
 * 黑色人种的创建工厂实现
 */
public class BlackHumanFactory extends AbstractHumanFactory {

    @Override
    public Human createHuman() {
        return new BlackHuman();
    }
}
