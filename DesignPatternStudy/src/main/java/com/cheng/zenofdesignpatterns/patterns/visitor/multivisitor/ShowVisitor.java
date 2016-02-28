package com.cheng.zenofdesignpatterns.patterns.visitor.multivisitor;

/**
 * 展示报表，该访问者的工作就是看到什么数据展示什么数据
 */
public class ShowVisitor implements IShowVisitor {

	private String info = "";
	
	// 打印出报表
	public void report() {
		System.out.println(this.info);
	}

	// 访问普通员工，组装信息
	public void visit(MVCommonEmployee commonEmployee) {
		this.info = this.info + this.getBasicInfo(commonEmployee)+ "工作："+commonEmployee.getJob()+"\t\n";
	}

	// 访问经理，然后组装信息
	public void visit(MVManager manager) {
		this.info = this.info + this.getBasicInfo(manager) +  "业绩："+manager.getPerformance() + "\t\n";
	}
	
	// 组装出基本信息
	private String getBasicInfo(MVEmployee employee){
		String info = "姓名：" + employee.getName() + "\t";
		info = info + "性别：" + (employee.getSex() == MVEmployee.FEMALE?"女":"男") + "\t";
		info = info + "薪水：" + employee.getSalary()  + "\t";
		return info;
	}

}
