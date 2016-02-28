package com.cheng.zenofdesignpatterns.perfectworld.command_chain.command_name.ls;

import com.cheng.zenofdesignpatterns.perfectworld.command_chain.CommandVO;
import com.cheng.zenofdesignpatterns.perfectworld.command_chain.command_name.os.FileManager;

/**
 * 命令名后面不跟参数
 */
public class LS extends AbstractLS {

    // 最简单的ls命令
    protected String echo(CommandVO vo) {
        return FileManager.ls(vo.formatData());
    }

    // 参数为空
    protected String getOperateParam() {
        return super.DEFAULT_PARAM;
    }

}
