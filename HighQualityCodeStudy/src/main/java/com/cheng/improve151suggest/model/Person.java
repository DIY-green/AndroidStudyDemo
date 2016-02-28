package com.cheng.improve151suggest.model;

import java.io.IOException;
import java.io.Serializable;

public class Person implements Serializable {
	private static final long serialVersionUID =60407L;
	//姓名
	private String name;
	//薪水
	private transient Salary salary;
	
	public Person(String _name,Salary _salary){
		name=_name;
		salary=_salary;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Salary getSalary() {
		return salary;
	}
	public void setSalary(Salary salary) {
		this.salary = salary;
	}
	
	//序列化时的handler
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		out.writeInt(salary.getBasePay());
	}
	//反序列化时的hanlder
	private void readObject(java.io.ObjectInputStream in) throws IOException,ClassNotFoundException {
		in.defaultReadObject();
		salary = new Salary(in.readInt(),0);
	}
	
	
}