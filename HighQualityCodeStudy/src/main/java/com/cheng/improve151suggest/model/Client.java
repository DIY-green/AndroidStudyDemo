package com.cheng.improve151suggest.model;

import java.text.NumberFormat;

/**
 * 
 * 建议4：避免带变长参数的方法的重载
 *
 */
public class Client {

	//简单折扣计算
	public void calPrice(int price,int discount){
		float knockdownPrice =price * discount / 100.0F;
		System.out.println("简单折扣后的价格是："+formateCurrency(knockdownPrice));
	}
	
	//复杂多折扣计算
	// 该方法的参数范畴覆盖了calPrice(int price,int discount)的参数范畴，影响可读性
	public void calPrice(int price,int... discounts){
		float knockdownPrice = price;
		for(int discount:discounts){
			knockdownPrice = knockdownPrice * discount / 100;
		}
		System.out.println("复杂折扣后的价格是：" +formateCurrency(knockdownPrice));
	}

	public void methodA(String str, Integer... is) {

	}

	public void methodA(String str, String... strs) {

	}
	
	//格式化成本地货币形式
	private String formateCurrency(float price){
		return NumberFormat.getCurrencyInstance().format(price/100);
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		//499元的货物，打75折
		client.calPrice(49900, 75);
	}
}