package com.java.moudle.system.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.domain.SysExamAssoc;
import com.java.moudle.system.service.SysExamAssocService;
import com.java.until.StringUtils;
import com.java.until.UUIDUtil;
import com.java.until.dba.PageModel;


@RestController
@RequestMapping("/sys/examassoc")
public class SysExamAssocController  extends BaseController {
    
	@Inject
    private SysExamAssocService examAssocService;

	/**
	 * @Description: 获取检验项目的列表（分页）
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getExamAssocPage")
	public JsonResult getExamAssocPage() {
		try {
			String param = getParam(request);
			if(StringUtils.isNull(param)) {
				return jsonResult(null, 2009, "参数不能为空！" );
			}
			SysExamAssoc info = JSONObject.parseObject(param, SysExamAssoc.class);
			JSONObject jsonObj = JSONObject.parseObject(param);
			Integer pageSize = jsonObj.containsKey("pageSize") ? jsonObj.getInteger("pageSize") : 10 ;
			Integer pageNo = jsonObj.containsKey("pageNo") ? jsonObj.getInteger("pageNo") : 1 ;
			PageModel page = new PageModel(pageNo, pageSize);
			
			examAssocService.getExamAssocPage(page, info);
			return jsonResult(page);
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}
	
	/**
	 * @Description: 获取检验项目的列表
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getExamAssocList")
	public JsonResult getExamAssocList() {
		try {
			String param = getParam(request);
			if(StringUtils.isNull(param)) {
				return jsonResult("", 2009, "参数不能为空！" );
			}
			SysExamAssoc info = JSONObject.parseObject(param, SysExamAssoc.class);
			
			List<SysExamAssoc> list = examAssocService.getExamAssocList(info);
			return jsonResult(list);
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}
	
	/**
	 * @Description: 查看检验项目组合信息
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("show")
	public JsonResult show() {
		try {
			String param = getParam(request);
			if(StringUtils.isNull(param)) {
				return jsonResult("", 2009, "参数不能为空！" );
			}
			JSONObject jsonObj = JSONObject.parseObject(param);
			String id = jsonObj.getString("id");
			return jsonResult(examAssocService.getDetail(id));
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}
	
	/**
	 * @Description: 保存检验项目信息
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("saveOrUpdate")
	public JsonResult saveOrUpdate() {
		try {
			String param = getParam(request);
			SysExamAssoc info = JSONObject.parseObject(param, SysExamAssoc.class);
			if(StringUtils.isNull(info.getId())) {
				info.setId(UUIDUtil.getUUID());
				info.setCreateTime(new Date());
			}
			//判断项目组合代码是否重复
			int count = examAssocService.isCodeRepeat(info.getId(), info.getAssocCode());
			if(count > 0) {
				return jsonResult(null, 10000, "项目组合代码重复！");
			}
			examAssocService.saveAssocInfo(info);
			return jsonResult();
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}
	
	/**
	 * @Description: 删除检验项目
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("delete")
	public JsonResult delete() {
		try {
			String param = getParam(request);
			if(StringUtils.isNull(param)) {
				return jsonResult("", 2009, "参数不能为空！" );
			}
			JSONObject jsonObj = JSONObject.parseObject(param);
			String id = jsonObj.getString("id");
			examAssocService.deleteAssocInfo(id);
			return jsonResult();
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}
	
}
