package com.java.moudle.tripartdock.region.service.impl;

import java.util.Map;

import javax.inject.Named;

import com.alibaba.fastjson.JSON;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.common.utils.properties.PropertiesUtil;
import com.java.moudle.tripartdock.region.service.RegionStatisService;
import com.java.until.http.HttpRequest;



@Named
public class RegionStatisServiceImpl implements RegionStatisService {

	private final String regionUrl = PropertiesUtil.getRegion("regionUrl");

	@Override
	public JsonResult getOrgWorkCharts(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getOrgWorkCharts"), paramMap);
		return JSON.parseObject(sendPost, JsonResult.class);
	}

	@Override
	public JsonResult getOrgWorkList(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getOrgWorkList"), paramMap);
		return JSON.parseObject(sendPost, JsonResult.class);
	}

	@Override
	public JsonResult getItemGroupCharts(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getItemGroupCharts"), paramMap);
		return JSON.parseObject(sendPost, JsonResult.class);
	}

	@Override
	public JsonResult getItemGroupList(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getItemGroupList"), paramMap);
		return JSON.parseObject(sendPost, JsonResult.class);
	}

	@Override
	public JsonResult getExamApplyBarCharts(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getExamApplyBarCharts"), paramMap);
		return JSON.parseObject(sendPost, JsonResult.class);
	}

	@Override
	public JsonResult getExamApplyPieCharts(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getExamApplyPieCharts"), paramMap);
		return JSON.parseObject(sendPost, JsonResult.class);
	}

	@Override
	public JsonResult getExamApplyList(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getExamApplyList"), paramMap);
		return JSON.parseObject(sendPost, JsonResult.class);
	}

	@Override
	public JsonResult getSampleBarCharts(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getSampleBarCharts"), paramMap);
		return JSON.parseObject(sendPost, JsonResult.class);
	}

	@Override
	public JsonResult getSamplePieCharts(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getSamplePieCharts"), paramMap);
		return JSON.parseObject(sendPost, JsonResult.class);
	}

	@Override
	public JsonResult getSampleLineCharts(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getSampleLineCharts"), paramMap);
		return JSON.parseObject(sendPost, JsonResult.class);
	}

	@Override
	public JsonResult getExamDataLineCharts(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getExamDataLineCharts"), paramMap);
		return JSON.parseObject(sendPost, JsonResult.class);
	}

	@Override
	public JsonResult getExamDataList(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getExamDataList"), paramMap);
		return JSON.parseObject(sendPost, JsonResult.class);
	}
	
	@Override
	public JsonResult getItemGroupStatis(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getItemGroupStatis"), paramMap);
		return JSON.parseObject(sendPost, JsonResult.class);
	}
	
	@Override
	public JsonResult getCurYearItemGroupStatis(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getCurYearItemGroupStatis"), paramMap);
		return JSON.parseObject(sendPost, JsonResult.class);
	}
	
	@Override
	public JsonResult getBefYearItemGroupStatis(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getBefYearItemGroupStatis"), paramMap);
		return JSON.parseObject(sendPost, JsonResult.class);
	}
	
	@Override
	public JsonResult getMonthSampleNumStatis(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getMonthSampleNumStatis"), paramMap);
		return JSON.parseObject(sendPost, JsonResult.class);
	}
	
	@Override
	public JsonResult getExamResultStatis(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getExamResultStatis"), paramMap);
		return JSON.parseObject(sendPost, JsonResult.class);
	}
	
	@Override
	public JsonResult getVisitTypeStatis(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getVisitTypeStatis"), paramMap);
		return JSON.parseObject(sendPost, JsonResult.class);
	}

	@Override
	public JsonResult getActionServiceData(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getActionServiceData"), paramMap);
		return JSON.parseObject(sendPost, JsonResult.class);
	}
	

}
