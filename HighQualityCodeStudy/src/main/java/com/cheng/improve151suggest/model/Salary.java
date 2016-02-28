package com.cheng.improve151suggest.model;

import java.io.Serializable;

public class Salary implements Serializable{
	private static final long serialVersionUID = 1602636144652644663L;
	//基本工资
	private int basePay;
	//绩效工资
	private int bonus;
	
	public Salary(int _basePay,int _bonus){
		basePay = _basePay;
		bonus = _bonus;
	}
	
	public int getBasePay() {
		return basePay;
	}
	public void setBasePay(int basePay) {
		this.basePay = basePay;
	}
	public int getBonus() {
		return bonus;
	}
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	
}
