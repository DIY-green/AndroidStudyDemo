package com.cheng.zenofdesignpatterns.patterns.strategy.calculator;


/**
 *
 */
public class CalculatorContext {

	private ICalculator cal = null;
	
	public CalculatorContext(ICalculator _cal){
		this.cal = _cal;
	}
	
	public int exec(int a,int b,String symbol){
		return this.cal.exec(a, b);
	}
	
}
