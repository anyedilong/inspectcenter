package com.java.moudle.tripartdock.region.service.impl;

import javax.inject.Named;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.utils.properties.PropertiesUtil;
import com.java.moudle.tripartdock.region.service.RegionOutpatientService;
import com.java.until.http.HttpUtil;


@Named
public class RegionOutpatientServiceImpl implements RegionOutpatientService {

	private final String regionUrl = PropertiesUtil.getRegion("regionUrl");
	

	//查询门诊和住院检查分页
	@Override
	public JSONObject getJcList(JSONObject json) throws Exception {
		String respStr = HttpUtil.doPost(regionUrl + PropertiesUtil.getRegion("getJcPage"), json.toJSONString());
		return JSON.parseObject(respStr);
	}

	//查询门诊或住院检查详情
	@Override
	public JSONObject getJcInfo(JSONObject json) throws Exception {
		String respStr = HttpUtil.doPost(regionUrl + PropertiesUtil.getRegion("getJcInfo"), json.toJSONString());
		return JSON.parseObject(respStr);
	}

	//查询档案基本信息
	@Override
	public JSONObject getJbxxInfo(JSONObject json) throws Exception {
		String respStr = HttpUtil.doPost(regionUrl + PropertiesUtil.getRegion("getInfoBySfzh"), json.toJSONString());
		return JSON.parseObject(respStr);
	}

}
