package com.cheng.zenofdesignpatterns.perfectworld.command_chain.command_name.ls;

import com.cheng.zenofdesignpatterns.perfectworld.command_chain.CommandVO;
import com.cheng.zenofdesignpatterns.perfectworld.command_chain.command_name.os.FileManager;

/**
 * ls -l 命令
 */
public class LS_L extends AbstractLS {

    protected String echo(CommandVO vo) {
        return FileManager.ls_l(vo.formatData());
    }

    // l参数
    protected String getOperateParam() {
        return super.L_PARAM;
    }

}
