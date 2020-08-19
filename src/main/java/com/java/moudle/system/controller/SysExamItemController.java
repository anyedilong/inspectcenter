package com.java.moudle.system.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.domain.SysExamItem;
import com.java.moudle.system.service.SysExamItemService;
import com.java.until.StringUtils;
import com.java.until.UUIDUtil;
import com.java.until.dba.PageModel;


@RestController
@RequestMapping("/sys/examitem")
public class SysExamItemController  extends BaseController {
    
	@Inject
    private SysExamItemService examItemService;

	/**
	 * @Description: 获取检验项目的列表（分页）
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getExamItemPage")
	public JsonResult getExamItemPage() {
		try {
			String param = getParam(request);
			if(StringUtils.isNull(param)) {
				return jsonResult(null, 2009, "参数不能为空！" );
			}
			SysExamItem info = JSONObject.parseObject(param, SysExamItem.class);
			JSONObject jsonObj = JSONObject.parseObject(param);
			Integer pageSize = jsonObj.getInteger("pageSize");
			Integer pageNo = jsonObj.getInteger("pageNo");
			PageModel page = new PageModel(pageNo, pageSize);
			
			examItemService.getExamItemPage(page, info);
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
	@RequestMapping("getExamItemList")
	public JsonResult getExamItemList() {
		try {
			String param = getParam(request);
			if(StringUtils.isNull(param)) {
				return jsonResult("", 2009, "参数不能为空！" );
			}
			SysExamItem info = JSONObject.parseObject(param, SysExamItem.class);
			
			List<SysExamItem> list = examItemService.getExamItemList(info);
			return jsonResult(list);
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}
	
	/**
	 * @Description: 查看检验项目信息
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
			return jsonResult(examItemService.getDeltail(id));
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
			SysExamItem info = JSONObject.parseObject(param, SysExamItem.class);
			if(StringUtils.isNull(info.getId())) {
				info.setId(UUIDUtil.getUUID());
				info.setCreateTime(new Date());
			}
			//判断项目代码是否重复
			int count = examItemService.isCodeRepeat(info.getId(), info.getItemCode());
			if(count > 0) {
				return jsonResult(null, 10000, "项目代码重复！");
			}
			examItemService.save(info);
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
			//判断项目是否为组合项目中的一个
			int count = examItemService.isGroupExist(id);
			if(count > 0) {
				return jsonResult(null, 10000, "该项目已经被组合项目使用！");
			}
			SysExamItem info = examItemService.get(id);
			//判断项目是否已经有检验数据
			int num = examItemService.isExamDataExist(info.getItemCode(), info.getName());
			if(num > 0) {
				return jsonResult(null, 10000, "该项目已经有检验数据！");
			}else if(num == -1) {
				return jsonResult(null, 10000, "查询检验数据保存！");
			}
			examItemService.delete(id);
			return jsonResult();
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}

}
