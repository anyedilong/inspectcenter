package com.java.moudle.tripartdock.region.service;

import com.alibaba.fastjson.JSONObject;

public interface RegionOutpatientService {


	//查询门诊和住院检查分页
	JSONObject getJcList(JSONObject json) throws Exception;

	//查询门诊或住院检查详情
	JSONObject getJcInfo(JSONObject json) throws Exception;

	JSONObject getJbxxInfo(JSONObject json) throws Exception;
}
