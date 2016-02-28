package com.cheng.zenofdesignpatterns.patterns.adapter.mmssection2;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class OuterUserBaseInfo implements IOuterUserBaseInfo {

	/* 
	 * 用户的基本信息
	 */
	public Map getUserBaseInfo() {
		HashMap baseInfoMap = new HashMap();
		
		baseInfoMap.put("userName", "这个员工叫混世魔王....");
		baseInfoMap.put("mobileNumber", "这个员工电话是....");
		
		return baseInfoMap;
	}

}
