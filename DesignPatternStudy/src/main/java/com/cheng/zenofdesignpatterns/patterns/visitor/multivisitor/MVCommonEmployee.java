package com.cheng.zenofdesignpatterns.patterns.visitor.multivisitor;

/**
 * 普通员工，也就是最小的小兵
 */
public class MVCommonEmployee extends MVEmployee {
	
	// 工作内容，这个非常重要，以后的职业规划就是靠这个了
	private String job;

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
	// 我允许访问者过来访问
	@Override
	public void accept(IVisitor visitor){
		visitor.visit(this);
	}
	
}
