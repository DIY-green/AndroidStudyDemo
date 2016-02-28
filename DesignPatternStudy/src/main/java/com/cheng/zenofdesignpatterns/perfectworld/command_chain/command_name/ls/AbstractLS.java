package com.cheng.zenofdesignpatterns.perfectworld.command_chain.command_name.ls;

import com.cheng.zenofdesignpatterns.perfectworld.command_chain.command_name.CommandName;

/**
 * 充当Handler
 */
public abstract class AbstractLS extends CommandName {

    // 默认参数
    public final static String DEFAULT_PARAM = "";
    // 参数a
    public final static String A_PARAM = "a";
    // 参数l
    public final static String L_PARAM = "l";

}
