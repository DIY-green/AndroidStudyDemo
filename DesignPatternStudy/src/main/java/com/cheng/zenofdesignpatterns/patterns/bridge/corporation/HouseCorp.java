package com.cheng.zenofdesignpatterns.patterns.bridge.corporation;

/**
 *
 */
public class HouseCorp extends Corp {

	// 定义传递一个House产品进来
	public HouseCorp(House house){
		super(house);
	}

	// 房地产公司很High了，赚钱，计算利润
	public void makeMoney(){
		super.makeMoney();
		System.out.println("房地产公司赚大钱了...");
	}

}

