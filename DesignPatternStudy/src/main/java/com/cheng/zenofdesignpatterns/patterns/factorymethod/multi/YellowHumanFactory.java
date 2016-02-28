package com.cheng.zenofdesignpatterns.patterns.factorymethod.multi;

import com.cheng.zenofdesignpatterns.patterns.factorymethod.nvwa.Human;
import com.cheng.zenofdesignpatterns.patterns.factorymethod.nvwa.WhiteHuman;
import com.cheng.zenofdesignpatterns.patterns.factorymethod.nvwa.YellowHuman;

/**
 * 黄色人种的创建工厂实现
 */
public class YellowHumanFactory extends AbstractHumanFactory {

    @Override
    public Human createHuman() {
        return new YellowHuman();
    }
}
