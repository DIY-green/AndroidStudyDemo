package com.cheng.zenofdesignpatterns.patternpk.behavioral.command_vs_strategy.strategy;

/**
 * Zip压缩解压算法
 */
public class Zip implements Algorithm {

	// zip格式的压缩算法
	public boolean compress(String source, String to) {
		System.out.println(source + " --> " +to + " ZIP压缩成功!");
		return true;
	}
	
	// zip格式的解压缩算法
	public boolean uncompress(String source,String to){
		System.out.println(source + " --> " +to + " ZIP解压缩成功!");
		return true;
	}

}
