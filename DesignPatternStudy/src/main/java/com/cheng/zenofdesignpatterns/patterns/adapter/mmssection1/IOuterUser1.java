package com.cheng.zenofdesignpatterns.patterns.adapter.mmssection1;

import java.util.Map;

/**
 * 外系统的人员信息
 */
public interface IOuterUser1 {
	
	//基本信息，比如名称，性别，手机号码了等
	Map getUserBaseInfo();
	
	//工作区域信息
	Map getUserOfficeInfo();
	
	//用户的家庭信息
	Map getUserHomeInfo();
	
}
