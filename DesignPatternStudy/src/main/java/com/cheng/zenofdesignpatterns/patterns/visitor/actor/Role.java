package com.cheng.zenofdesignpatterns.patterns.visitor.actor;

/**
 *
 */
public interface Role {
	// 演员要扮演的角色
	void accept(AbsActor actor);
}

