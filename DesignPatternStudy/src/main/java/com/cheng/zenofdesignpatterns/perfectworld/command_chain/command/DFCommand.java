package com.cheng.zenofdesignpatterns.perfectworld.command_chain.command;

import com.cheng.zenofdesignpatterns.perfectworld.command_chain.CommandVO;
import com.cheng.zenofdesignpatterns.perfectworld.command_chain.command_name.df.AbstractDF;

/**
 *
 */
public class DFCommand extends Command {

    public String execute(CommandVO vo) {
        return super.buildChain(AbstractDF.class).get(0).handleMessage(vo);
    }

}
