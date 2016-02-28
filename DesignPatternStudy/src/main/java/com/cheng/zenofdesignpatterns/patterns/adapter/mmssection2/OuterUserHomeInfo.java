package com.cheng.zenofdesignpatterns.patterns.adapter.mmssection2;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class OuterUserHomeInfo implements IOuterUserHomeInfo {

	/* 
	 * 员工的家庭信息
	 */
	public Map getUserHomeInfo() {
		HashMap homeInfo = new HashMap();
		
		homeInfo.put("homeTelNumbner", "员工的家庭电话是....");
		homeInfo.put("homeAddress", "员工的家庭地址是....");
		
		return homeInfo;
	}
}
