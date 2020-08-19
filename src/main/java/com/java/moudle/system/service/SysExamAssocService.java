package com.java.moudle.system.service;

import java.util.List;

import com.java.moudle.common.service.BaseService;
import com.java.moudle.system.domain.SysExamAssoc;
import com.java.until.dba.PageModel;

public interface SysExamAssocService extends BaseService<SysExamAssoc> {
   
	//获取检验项目的列表（分页）
	void getExamAssocPage(PageModel page, SysExamAssoc info) throws Exception;
	//获取检验项目的列表
	List<SysExamAssoc> getExamAssocList(SysExamAssoc info) throws Exception;
	//查看检验项目详细
	SysExamAssoc getDetail(String id) throws Exception;
	//判断项目组合代码是否重复
	Integer isCodeRepeat(String id, String assocCode);
	//保存项目组合信息
	void saveAssocInfo(SysExamAssoc info);
	//删除项目组合信息
	void deleteAssocInfo(String id);
	
	
}
