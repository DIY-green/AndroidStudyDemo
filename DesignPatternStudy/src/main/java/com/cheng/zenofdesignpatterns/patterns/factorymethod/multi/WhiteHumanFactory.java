package com.cheng.zenofdesignpatterns.patterns.factorymethod.multi;

import com.cheng.zenofdesignpatterns.patterns.factorymethod.nvwa.Human;
import com.cheng.zenofdesignpatterns.patterns.factorymethod.nvwa.WhiteHuman;

/**
 * 白色人种的创建工厂实现
 */
public class WhiteHumanFactory extends AbstractHumanFactory {

    @Override
    public Human createHuman() {
        return new WhiteHuman();
    }
}
