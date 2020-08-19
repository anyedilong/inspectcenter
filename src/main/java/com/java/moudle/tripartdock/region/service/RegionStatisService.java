package com.java.moudle.tripartdock.region.service;

import java.util.Map;

import com.java.moudle.common.message.JsonResult;

public interface RegionStatisService {

    
	//获取机构工作量charts数据
	JsonResult getOrgWorkCharts(Map<String, String> paramMap) throws Exception;
	//获取机构工作量的列表
	JsonResult getOrgWorkList(Map<String, String> paramMap) throws Exception;
	
	//获取项目组别charts数据
	JsonResult getItemGroupCharts(Map<String, String> paramMap) throws Exception;
	//获取项目组别的列表
	JsonResult getItemGroupList(Map<String, String> paramMap) throws Exception;
	
	//获取检验申请charts柱状图数据
	JsonResult getExamApplyBarCharts(Map<String, String> paramMap) throws Exception;
	//获取检验申请charts饼状图数据
	JsonResult getExamApplyPieCharts(Map<String, String> paramMap) throws Exception;
	//获取检验申请的列表
	JsonResult getExamApplyList(Map<String, String> paramMap) throws Exception;
	
	//获取标本量charts柱状图数据
	JsonResult getSampleBarCharts(Map<String, String> paramMap) throws Exception;
	//获取标本量charts饼状图数据
	JsonResult getSamplePieCharts(Map<String, String> paramMap) throws Exception;
	//获取标本量charts折线图数据
	JsonResult getSampleLineCharts(Map<String, String> paramMap) throws Exception;
	
	//获取检验数据charts统计
	JsonResult getExamDataLineCharts(Map<String, String> paramMap) throws Exception;
	//获取检验数据列表统计
	JsonResult getExamDataList(Map<String, String> paramMap) throws Exception;
		
	//获取检验组别统计（大屏）
	JsonResult getItemGroupStatis(Map<String, String> paramMap) throws Exception;
	//获取年度检验组别统计（大屏）
	JsonResult getCurYearItemGroupStatis(Map<String, String> paramMap) throws Exception;
	//获取往年同期年度检验组别统计（大屏）
	JsonResult getBefYearItemGroupStatis(Map<String, String> paramMap) throws Exception;
	//获取各医院本月标本量统计（大屏）
	JsonResult getMonthSampleNumStatis(Map<String, String> paramMap) throws Exception;
	//获取检验结果数据分析（大屏）
	JsonResult getExamResultStatis(Map<String, String> paramMap) throws Exception;
	//获取就诊类型统计（大屏）
	JsonResult getVisitTypeStatis(Map<String, String> paramMap) throws Exception;
	//获取服务动态数据（大屏）
	JsonResult getActionServiceData(Map<String, String> paramMap) throws Exception;
}
