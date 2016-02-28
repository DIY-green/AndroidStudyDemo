package com.cheng.zenofdesignpatterns.perfectworld.command_chain.command;

import com.cheng.zenofdesignpatterns.perfectworld.command_chain.CommandVO;
import com.cheng.zenofdesignpatterns.perfectworld.command_chain.command_name.CommandName;
import com.cheng.zenofdesignpatterns.perfectworld.command_chain.command_name.ls.AbstractLS;

/**
 *
 */
public class LSCommand extends Command {

    public String execute(CommandVO vo) {
        // 返回链表的首节点
        CommandName firstNode = super.buildChain(AbstractLS.class).get(0);
        return firstNode.handleMessage(vo);
    }
}
