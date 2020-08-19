package com.java.moudle.system.service;

import java.util.List;

import com.java.moudle.common.service.BaseService;
import com.java.moudle.system.domain.SysExamItem;
import com.java.until.dba.PageModel;

public interface SysExamItemService extends BaseService<SysExamItem> {
   
	//获取检验项目的列表（分页
	void getExamItemPage(PageModel page, SysExamItem info) throws Exception;
	//获取检验项目的列表
	List<SysExamItem> getExamItemList(SysExamItem info) throws Exception;
	//查看检验项目详细
	SysExamItem getDeltail(String id) throws Exception;
	//判断项目代码是否重复
	Integer isCodeRepeat(String id, String code) throws Exception;
	//判断项目是否为组合项目中的一个
	Integer isGroupExist(String itemId) throws Exception;
	//判断项目是否已经有检验数据
	Integer isExamDataExist(String itemCode, String name) throws Exception;
}
