package com.cheng.improve151suggest.model;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class City implements Comparable<City> {

	// 城市编码
	private String code;
	// 城市名称
	private String name;
	
	public City(String _code,String _name){
		code = _code;
		name = _name;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(City o) {
		// 按照城市名称排序
		return new CompareToBuilder().append(name, o.name).toComparison();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		City city = (City) obj;
		// 根据code判断是否相等
		return new EqualsBuilder()
				.append(code, city.code)
				.isEquals();
	}
}