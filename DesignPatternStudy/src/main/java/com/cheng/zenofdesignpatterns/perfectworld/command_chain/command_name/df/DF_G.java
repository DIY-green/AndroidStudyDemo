package com.cheng.zenofdesignpatterns.perfectworld.command_chain.command_name.df;

import com.cheng.zenofdesignpatterns.perfectworld.command_chain.CommandVO;
import com.cheng.zenofdesignpatterns.perfectworld.command_chain.command_name.os.DiskManager;

/**
 *
 */
public class DF_G extends AbstractDF{

	// 定义一下自己能处理什么参数
	protected String getOperateParam() {
		return super.G_PARAM;
	}
	
	// 命令处理
	protected String echo(CommandVO vo) {
		return DiskManager.df_g();
	}

}
