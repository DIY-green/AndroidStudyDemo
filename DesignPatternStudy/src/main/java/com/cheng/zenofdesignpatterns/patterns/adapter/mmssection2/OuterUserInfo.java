package com.cheng.zenofdesignpatterns.patterns.adapter.mmssection2;

import java.util.Map;

/**
 * 把OuterUser包装成UserInfo
 */
public class OuterUserInfo implements IUserInfo {
    /**
     * 这是所谓的对象适配器
     * 把类的继承关系变更为关联关系
     */
	// 源目标对象
	private IOuterUserBaseInfo baseInfo = null;  //员工的基本信息
	private IOuterUserHomeInfo homeInfo = null; //员工的家庭 信息
	private IOuterUserOfficeInfo officeInfo = null; //工作信息
	
	// 数据处理
	private Map baseMap = null;
	private Map homeMap = null;
	private Map officeMap = null;
	
	// 构造函数传递对象
	public OuterUserInfo(IOuterUserBaseInfo _baseInfo,IOuterUserHomeInfo _homeInfo,IOuterUserOfficeInfo _officeInfo){
		this.baseInfo = _baseInfo;
		this.homeInfo = _homeInfo;
		this.officeInfo = _officeInfo;
		
		//数据处理
		this.baseMap = this.baseInfo.getUserBaseInfo();
		this.homeMap = this.homeInfo.getUserHomeInfo();
		this.officeMap = this.officeInfo.getUserOfficeInfo();
	}
	
	 // 家庭地址
	public String getHomeAddress() {
		String homeAddress = (String)this.homeMap.get("homeAddress");
		System.out.println(homeAddress);
		return homeAddress;
	}

	 // 家庭电话号码
	public String getHomeTelNumber() {
		String homeTelNumber = (String)this.homeMap.get("homeTelNumber");
		System.out.println(homeTelNumber);
		return homeTelNumber;
	}

	 // 职位信息
	public String getJobPosition() {
		String jobPosition = (String)this.officeMap.get("jobPosition");
		System.out.println(jobPosition);
		return jobPosition;
	}

	 // 手机号码
	public String getMobileNumber() {
		String mobileNumber = (String)this.baseMap.get("mobileNumber");
		System.out.println(mobileNumber);
		return mobileNumber;
	}

	 // 办公电话
	public String getOfficeTelNumber() {
		String officeTelNumber = (String)this.officeMap.get("officeTelNumber");
		System.out.println(officeTelNumber);
		return officeTelNumber;
	}
	
	// 员工的名称
	public String getUserName() {
		String userName = (String)this.baseMap.get("userName");
		System.out.println(userName);
		return userName;
	}

}
