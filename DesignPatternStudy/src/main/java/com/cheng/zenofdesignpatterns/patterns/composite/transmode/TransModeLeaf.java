package com.cheng.zenofdesignpatterns.patterns.composite.transmode;

import java.util.ArrayList;

/**
 * 叶子构件
 */
public class TransModeLeaf extends TransModeComponent {

	@Deprecated
	public void add(TransModeComponent component) throws UnsupportedOperationException{
		// 空实现,直接抛弃一个“不支持请求”异常
		throw new UnsupportedOperationException();
	}
	
	@Deprecated
	public void remove(TransModeComponent component)throws UnsupportedOperationException{
		// 空实现
		throw new UnsupportedOperationException();
	}
	
	@Deprecated
	public ArrayList<TransModeComponent> getChildren()throws UnsupportedOperationException{
		// 空实现
		throw new UnsupportedOperationException();		
	}

    /**
     * 叶子对象，其下再也没有其他的分支，也就是遍历的最小单位
     */
}
