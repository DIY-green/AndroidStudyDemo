package com.cheng.zenofdesignpatterns.patterns.decorator.schoolreport;

/**
 * 四年级的成绩单，这个是我们学校第一次实施，以前没有干过这种“缺德”事。
 */
public class FouthGradeSchoolReport extends SchoolReport {

	// 我的成绩单
	public void report() {
		// 成绩单的格式是这个样子的
		System.out.println("尊敬的XXX家长:");  
		System.out.println("  ......");
		System.out.println("  语文 62  数学65 体育 98  自然  63");
		System.out.println("  .......");
		System.out.println("               家长签名：       ");
	}

	// 家长签名
	public void sign(String name) {
		System.out.println("家长签名为："+name);
	}

}
