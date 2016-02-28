package com.cheng.zenofdesignpatterns.patterns.visitor.actor;

/**
 *
 */
public class KungFuRole implements Role {
	// 武功天下第一的角色
	public void accept(AbsActor actor){
		actor.act(this);
	}
}
