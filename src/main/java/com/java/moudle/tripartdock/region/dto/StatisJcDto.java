package com.java.moudle.tripartdock.region.dto;

import java.io.Serializable;


public class StatisJcDto implements Serializable {

	private static final long serialVersionUID = 19748966L;
	
    private String  id;//机构标识
    private String  name;//机构名称
    private String  ratio;//占比
    private String  level;//机构等级
    //接收统计的数据
    private Integer value;
	private Integer value1;
	private Integer value2;
    
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRatio() {
		return ratio;
	}
	public void setRatio(String ratio) {
		this.ratio = ratio;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public Integer getValue1() {
		return value1;
	}
	public void setValue1(Integer value1) {
		this.value1 = value1;
	}
	public Integer getValue2() {
		return value2;
	}
	public void setValue2(Integer value2) {
		this.value2 = value2;
	}
	
    
}
