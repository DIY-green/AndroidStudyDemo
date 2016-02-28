package com.cheng.zenofdesignpatterns.perfectworld.command_chain.command_name.ls;

import com.cheng.zenofdesignpatterns.perfectworld.command_chain.CommandVO;
import com.cheng.zenofdesignpatterns.perfectworld.command_chain.command_name.os.FileManager;

/**
 *
 */
public class LS_A extends AbstractLS {

    // ls -a命令
    protected String echo(CommandVO vo) {
        return FileManager.ls_a(vo.formatData());
    }

    protected String getOperateParam() {
        return super.A_PARAM;
    }

}
