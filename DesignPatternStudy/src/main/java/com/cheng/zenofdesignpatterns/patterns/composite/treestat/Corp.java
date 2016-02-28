package com.cheng.zenofdesignpatterns.patterns.composite.treestat;

/**
 * 定义一个公司的人员的抽象类
 */
public abstract class Corp {

	// 公司每个人都有名称
	private String name = "";
	// 公司每个人都职位
	private String position = "";
	// 公司每个人都有薪水
	private int salary =0;
    // 父节点是谁
    private Corp parent = null;
	
	/*
	 * 通过接口的方式传递，我们改变一下习惯，传递进来的参数名以下划线开始
	 * 这个在一些开源项目中非常常见，一般构造函数都是定义的
	 */
	public Corp(String _name,String _position,int _salary){
		this.name = _name;
		this.position = _position;
		this.salary = _salary;
	}
	
	// 获得员工信息
	public String getInfo(){
		String info = "";
		info = "姓名：" + this.name;
		info = info + "\t职位："+ this.position;
		info = info + "\t薪水：" + this.salary;
		return info;
	}

    // 设置父节点
    protected void setParent(Corp _parent){
        this.parent = _parent;
    }

    // 得到父节点
    public Corp getParent(){
        return this.parent;
    }
}
