package com.java.moudle.tripartdock.region.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.tripartdock.region.service.RegionStatisService;
import com.java.until.StringUtils;
import com.java.until.SysUtil;

/**
 * @ClassName: OutpatientController
 * @Description: 三方对接 查询住院信息
 * @author Administrator
 * @date 2019年9月12日
 */
@RestController
@RequestMapping("/region/statis")
public class RegionStatisController extends BaseController {

	@Inject
	private RegionStatisService statisService;

    /**
	 * @Description: 获取机构工作量charts数据
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getOrgWorkCharts")
	public JsonResult getOrgWorkCharts() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			//参数
			String startTime = paramObj.containsKey("startTime") ? paramObj.getString("startTime") : "";
			String endTime = paramObj.containsKey("endTime") ? paramObj.getString("endTime") : "";
			String orgId = paramObj.containsKey("orgId") ? paramObj.getString("orgId") : SysUtil.sysUser(request, response).getOrgId();
			String itemGroupName = paramObj.containsKey("itemGroupName") ? paramObj.getString("itemGroupName") : "";
			
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("startTime", startTime);
			paramMap.put("endTime", endTime);
			paramMap.put("orgId", orgId);
			paramMap.put("itemGroupName", itemGroupName);
			
			return statisService.getOrgWorkCharts(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}

	/**
	 * @Description: 获取机构工作量的列表
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getOrgWorkList")
	public JsonResult getOrgWorkList() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			
			String startTime = paramObj.containsKey("startTime") ? paramObj.getString("startTime") : "";
			String endTime = paramObj.containsKey("endTime") ? paramObj.getString("endTime") : "";
			String orgId = paramObj.containsKey("orgId") ? paramObj.getString("orgId") : SysUtil.sysUser(request, response).getOrgId();
			String itemGroupName = paramObj.containsKey("itemGroupName") ? paramObj.getString("itemGroupName") : "";
			String pageNo = paramObj.containsKey("pageNo") ? paramObj.getString("pageNo") : "";
			String pageSize = paramObj.containsKey("pageSize") ? paramObj.getString("pageSize") : "";
			
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("startTime", startTime);
			paramMap.put("endTime", endTime);
			paramMap.put("orgId", orgId);
			paramMap.put("itemGroupName", itemGroupName);
			paramMap.put("pageNo", pageNo);
			paramMap.put("pageSize", pageSize);
			return statisService.getOrgWorkList(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 获取项目组别charts数据
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getItemGroupCharts")
	public JsonResult getItemGroupCharts() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			//参数
			String startTime = paramObj.containsKey("startTime") ? paramObj.getString("startTime") : "";
			String endTime = paramObj.containsKey("endTime") ? paramObj.getString("endTime") : "";
			String orgId = paramObj.containsKey("orgId") ? paramObj.getString("orgId") : SysUtil.sysUser(request, response).getOrgId();
			
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("startTime", startTime);
			paramMap.put("endTime", endTime);
			paramMap.put("orgId", orgId);
			
			return statisService.getItemGroupCharts(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}

	/**
	 * @Description: 获取项目组别的列表
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getItemGroupList")
	public JsonResult getItemGroupList() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			
			String startTime = paramObj.containsKey("startTime") ? paramObj.getString("startTime") : "";
			String endTime = paramObj.containsKey("endTime") ? paramObj.getString("endTime") : "";
			String orgId = paramObj.containsKey("orgId") ? paramObj.getString("orgId") : SysUtil.sysUser(request, response).getOrgId();
			String pageNo = paramObj.containsKey("pageNo") ? paramObj.getString("pageNo") : "";
			String pageSize = paramObj.containsKey("pageSize") ? paramObj.getString("pageSize") : "";
			
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("startTime", startTime);
			paramMap.put("endTime", endTime);
			paramMap.put("orgId", orgId);
			paramMap.put("pageNo", pageNo);
			paramMap.put("pageSize", pageSize);
			return statisService.getItemGroupList(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 获取检验申请charts柱状图数据
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getExamApplyBarCharts")
	public JsonResult getExamApplyBarCharts() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			//参数
			String year = paramObj.containsKey("year") ? paramObj.getString("year") : "";
			String month = paramObj.containsKey("month") ? paramObj.getString("month") : "";
			String orgId = paramObj.containsKey("orgId") ? paramObj.getString("orgId") : SysUtil.sysUser(request, response).getOrgId();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			if(StringUtils.isNull(year)) {
				year = sdf.format(new Date());
			}
			if(StringUtils.isNull(month)) {
				month = "0";
			}
			
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("year", year);
			paramMap.put("month", month);
			paramMap.put("orgId", orgId);
			
			return statisService.getExamApplyBarCharts(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 获取检验申请charts饼状图数据
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getExamApplyPieCharts")
	public JsonResult getExamApplyPieCharts() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			//参数
			String year = paramObj.containsKey("year") ? paramObj.getString("year") : "";
			String month = paramObj.containsKey("month") ? paramObj.getString("month") : "";
			String orgId = paramObj.containsKey("orgId") ? paramObj.getString("orgId") : SysUtil.sysUser(request, response).getOrgId();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			if(StringUtils.isNull(year)) {
				year = sdf.format(new Date());
			}
			if(StringUtils.isNull(month)) {
				month = "0";
			}
			
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("year", year);
			paramMap.put("month", month);
			paramMap.put("orgId", orgId);
			
			return statisService.getExamApplyPieCharts(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}

	/**
	 * @Description: 获取检验申请的列表
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getExamApplyList")
	public JsonResult getExamApplyList() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			
			String year = paramObj.containsKey("year") ? paramObj.getString("year") : "";
			String month = paramObj.containsKey("month") ? paramObj.getString("month") : "";
			String orgId = paramObj.containsKey("orgId") ? paramObj.getString("orgId") : SysUtil.sysUser(request, response).getOrgId();
			String pageNo = paramObj.containsKey("pageNo") ? paramObj.getString("pageNo") : "";
			String pageSize = paramObj.containsKey("pageSize") ? paramObj.getString("pageSize") : "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			if(StringUtils.isNull(year)) {
				year = sdf.format(new Date());
			}
			if(StringUtils.isNull(month)) {
				month = "0";
			}
			
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("year", year);
			paramMap.put("month", month);
			paramMap.put("orgId", orgId);
			paramMap.put("pageNo", pageNo);
			paramMap.put("pageSize", pageSize);
			return statisService.getExamApplyList(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 获取标本量charts柱状图数据
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getSampleBarCharts")
	public JsonResult getSampleBarCharts() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			//参数
			String year = paramObj.containsKey("year") ? paramObj.getString("year") : "";
			String month = paramObj.containsKey("month") ? paramObj.getString("month") : "";
			String orgId = paramObj.containsKey("orgId") ? paramObj.getString("orgId") : SysUtil.sysUser(request, response).getOrgId();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			if(StringUtils.isNull(year)) {
				year = sdf.format(new Date());
			}
			if(StringUtils.isNull(month)) {
				month = "0";
			}
			
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("year", year);
			paramMap.put("month", month);
			paramMap.put("orgId", orgId);
			
			return statisService.getSampleBarCharts(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 获取标本量charts饼状图数据
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getSamplePieCharts")
	public JsonResult getSamplePieCharts() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			//参数
			String year = paramObj.containsKey("year") ? paramObj.getString("year") : "";
			String month = paramObj.containsKey("month") ? paramObj.getString("month") : "";
			String orgId = paramObj.containsKey("orgId") ? paramObj.getString("orgId") : SysUtil.sysUser(request, response).getOrgId();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			if(StringUtils.isNull(year)) {
				year = sdf.format(new Date());
			}
			if(StringUtils.isNull(month)) {
				month = "0";
			}
			
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("year", year);
			paramMap.put("month", month);
			paramMap.put("orgId", orgId);
			
			return statisService.getSamplePieCharts(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 获取标本量charts折线图数据
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getSampleLineCharts")
	public JsonResult getSampleLineCharts() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			//参数
			String year = paramObj.containsKey("year") ? paramObj.getString("year") : "";
			String month = paramObj.containsKey("month") ? paramObj.getString("month") : "";
			String orgId = paramObj.containsKey("orgId") ? paramObj.getString("orgId") : SysUtil.sysUser(request, response).getOrgId();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			if(StringUtils.isNull(year)) {
				year = sdf.format(new Date());
			}
			if(StringUtils.isNull(month)) {
				month = "0";
			}
			
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("year", year);
			paramMap.put("month", month);
			paramMap.put("orgId", orgId);
			
			return statisService.getSampleLineCharts(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 获取检验数据charts数据
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getExamDataLineCharts")
	public JsonResult getExamDataLineCharts() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			//参数
			String startTime = paramObj.containsKey("startTime") ? paramObj.getString("startTime") : "";
			String endTime = paramObj.containsKey("endTime") ? paramObj.getString("endTime") : "";
			String orgId = paramObj.containsKey("orgId") ? paramObj.getString("orgId") : SysUtil.sysUser(request, response).getOrgId();
			String itemGroupName = paramObj.containsKey("itemGroupName") ? paramObj.getString("itemGroupName") : "";
			
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("startTime", startTime);
			paramMap.put("endTime", endTime);
			paramMap.put("orgId", orgId);
			paramMap.put("itemGroupName", itemGroupName);
			
			return statisService.getExamDataLineCharts(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}

	/**
	 * @Description: 获取检验数据的列表
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getExamDataList")
	public JsonResult getExamDataList() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			
			String startTime = paramObj.containsKey("startTime") ? paramObj.getString("startTime") : "";
			String endTime = paramObj.containsKey("endTime") ? paramObj.getString("endTime") : "";
			String orgId = paramObj.containsKey("orgId") ? paramObj.getString("orgId") : SysUtil.sysUser(request, response).getOrgId();
			String itemGroupName = paramObj.containsKey("itemGroupName") ? paramObj.getString("itemGroupName") : "";
			String pageNo = paramObj.containsKey("pageNo") ? paramObj.getString("pageNo") : "";
			String pageSize = paramObj.containsKey("pageSize") ? paramObj.getString("pageSize") : "";
			
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("startTime", startTime);
			paramMap.put("endTime", endTime);
			paramMap.put("orgId", orgId);
			paramMap.put("itemGroupName", itemGroupName);
			paramMap.put("pageNo", pageNo);
			paramMap.put("pageSize", pageSize);
			return statisService.getExamDataList(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 获取检验组别统计(大屏)
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getItemGroupStatis")
	public JsonResult getItemGroupStatis() {
		try {
			String paramStr = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(paramStr);
			String orgId = paramObj.containsKey("orgId") ? paramObj.getString("orgId") : "";
			if(StringUtils.isNull(orgId)) {
				orgId = "4B5ABA9B8D94494F94EC2E8450F4ECC8";
			}
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("orgId", orgId);
			return statisService.getItemGroupStatis(paramMap);
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}
	
	/**
	 * @Description: 获取年度检验组别统计(大屏)
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getCurYearItemGroupStatis")
	public JsonResult getCurYearItemGroupStatis() {
		try {
			String paramStr = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(paramStr);
			String orgId = paramObj.containsKey("orgId") ? paramObj.getString("orgId") : "";
			if(StringUtils.isNull(orgId)) {
				orgId = "4B5ABA9B8D94494F94EC2E8450F4ECC8";
			}
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("orgId", orgId);
			return statisService.getCurYearItemGroupStatis(paramMap);
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}
	
	/**
	 * @Description: 获取往年同期年度检验组别统计(大屏)
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getBefYearItemGroupStatis")
	public JsonResult getBefYearItemGroupStatis() {
		try {
			String paramStr = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(paramStr);
			String orgId = paramObj.containsKey("orgId") ? paramObj.getString("orgId") : "";
			if(StringUtils.isNull(orgId)) {
				orgId = "4B5ABA9B8D94494F94EC2E8450F4ECC8";
			}
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("orgId", orgId);
			return statisService.getBefYearItemGroupStatis(paramMap);
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}
	
	/**
	 * @Description: 获取各医院本月标本量统计(大屏)
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getMonthSampleNumStatis")
	public JsonResult getMonthSampleNumStatis() {
		try {
			String paramStr = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(paramStr);
			String orgId = paramObj.containsKey("orgId") ? paramObj.getString("orgId") : "";
			if(StringUtils.isNull(orgId)) {
				orgId = "4B5ABA9B8D94494F94EC2E8450F4ECC8";
			}
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("orgId", orgId);
			return statisService.getMonthSampleNumStatis(paramMap);
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}
	
	/**
	 * @Description: 获取检验结果数据分析(大屏)
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getExamResultStatis")
	public JsonResult getExamResultStatis() {
		try {
			String paramStr = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(paramStr);
			String orgId = paramObj.containsKey("orgId") ? paramObj.getString("orgId") : "";
			if(StringUtils.isNull(orgId)) {
				orgId = "4B5ABA9B8D94494F94EC2E8450F4ECC8";
			}
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("orgId", orgId);
			return statisService.getExamResultStatis(paramMap);
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}
	
	/**
	 * @Description: 获取就诊类型统计(大屏)
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getVisitTypeStatis")
	public JsonResult getVisitTypeStatis() {
		try {
			String paramStr = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(paramStr);
			String orgId = paramObj.containsKey("orgId") ? paramObj.getString("orgId") : "";
			if(StringUtils.isNull(orgId)) {
				orgId = "4B5ABA9B8D94494F94EC2E8450F4ECC8";
			}
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("orgId", orgId);
			return statisService.getVisitTypeStatis(paramMap);
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}
	
	/**
	 * @Description: 获取服务动态数据（大屏）
	 * @param    
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getActionServiceData")
    public JsonResult getActionServiceData(){
    	try {
    		String paramStr = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(paramStr);
			String orgId = paramObj.containsKey("orgId") ? paramObj.getString("orgId") : "";
			if(StringUtils.isNull(orgId)) {
				orgId = "4B5ABA9B8D94494F94EC2E8450F4ECC8";
			}
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("orgId", orgId);
			return statisService.getActionServiceData(paramMap);
    	}catch(Exception e) {
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
    	}
    }
	
}
