package com.cheng.zenofdesignpatterns.patterns.visitor.actor;

/**
 *
 */
public class YoungActor extends AbsActor {

	// 年轻演员最喜欢演功夫戏
	public void act(KungFuRole role){
		System.out.println("最喜欢演功夫角色");
	}

}
