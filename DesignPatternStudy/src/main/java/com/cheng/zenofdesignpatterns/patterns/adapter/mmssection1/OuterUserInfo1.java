package com.cheng.zenofdesignpatterns.patterns.adapter.mmssection1;

import java.util.Map;

/**
 * 把OuterUser包装成UserInfo
 */
public class OuterUserInfo1 extends OuterUser1 implements IUserInfo1 {
	
	private Map baseInfo = super.getUserBaseInfo();  // 员工的基本信息
	private Map homeInfo = super.getUserHomeInfo(); // 员工的家庭 信息
	private Map officeInfo = super.getUserOfficeInfo(); // 工作信息
	
	/* 
	 * 家庭地址
	 */
	public String getHomeAddress() {
		String homeAddress = (String)this.homeInfo.get("homeAddress");
		System.out.println(homeAddress);
		return homeAddress;
	}

	/* 
	 * 家庭电话号码
	 */
	public String getHomeTelNumber() {
		String homeTelNumber = (String)this.homeInfo.get("homeTelNumber");
		System.out.println(homeTelNumber);
		return homeTelNumber;
	}

	/* 
	 *职位信息
	 */
	public String getJobPosition() {
		String jobPosition = (String)this.officeInfo.get("jobPosition");
		System.out.println(jobPosition);
		return jobPosition;
	}

	/* 
	 * 手机号码
	 */
	public String getMobileNumber() {
		String mobileNumber = (String)this.baseInfo.get("mobileNumber");
		System.out.println(mobileNumber);
		return mobileNumber;
	}

	/* 
	 * 办公电话
	 */
	public String getOfficeTelNumber() {
		String officeTelNumber = (String)this.officeInfo.get("officeTelNumber");
		System.out.println(officeTelNumber);
		return officeTelNumber;
	}

	/* 
	 * 员工的名称
	 */
	public String getUserName() {
		String userName = (String)this.baseInfo.get("userName");
		System.out.println(userName);
		return userName;
	}

}
