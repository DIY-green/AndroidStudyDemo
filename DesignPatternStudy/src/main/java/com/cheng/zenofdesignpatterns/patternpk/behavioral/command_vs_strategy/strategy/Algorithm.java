package com.cheng.zenofdesignpatterns.patternpk.behavioral.command_vs_strategy.strategy;

/**
 * 抽象压缩算法
 */
public interface Algorithm {

	// 压缩算法
	boolean compress(String source,String to);

	// 解压缩算法
	boolean uncompress(String source,String to);

}
