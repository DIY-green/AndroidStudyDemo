package com.cheng.zenofdesignpatterns.patterns.adapter.mmssection1;

/**
 *
 * 用户信息对象
 */
public interface IUserInfo1 {
	
	// 获得用户姓名
	String getUserName();
	
	// 获得家庭地址
	String getHomeAddress();
	
	// 手机号码，这个太重要，手机泛滥呀
	String getMobileNumber();
	
	// 办公电话，一般式座机
	String getOfficeTelNumber();
	
	// 这个人的职位是啥
	String getJobPosition();
	
	// 获得家庭电话，这个有点缺德，我是不喜欢打家庭电话讨论工作
	String getHomeTelNumber();
}
