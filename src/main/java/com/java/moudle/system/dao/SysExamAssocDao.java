package com.java.moudle.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.java.moudle.system.dao.repository.SysExamAssocRepository;
import com.java.moudle.system.domain.SysExamAssoc;
import com.java.until.StringUtils;
import com.java.until.dba.BaseDao;
import com.java.until.dba.PageModel;


@Named
public class SysExamAssocDao extends BaseDao<SysExamAssocRepository, SysExamAssoc> {

	
	public void getExamAssocPage(PageModel page, SysExamAssoc info) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select r.*, s.name as sampleTypeName ");
		sql.append(" from sys_exam_assoc r ");
		sql.append(" left join sys_exam_sample s on r.sample_type = s.id ");
		sql.append(" where 1 = 1 ");
		if(!StringUtils.isNull(info.getName())) {
			sql.append(" and r.name like concat('%', concat(:name, '%')) ");
		}
		if(!StringUtils.isNull(info.getAssocCode())) {
			sql.append(" and r.assoc_code like concat('%', concat(:assocCode, '%')) ");
		}
		if(!StringUtils.isNull(info.getItemGroup())) {
			sql.append(" and r.item_group = :itemGroup ");
		}
		queryPageList(sql.toString(), info, page, SysExamAssoc.class);
	}
	
	public List<SysExamAssoc> getExamAssocList(SysExamAssoc info){
		StringBuffer sql = new StringBuffer();
		sql.append(" select r.*, s.name as sampleTypeName ");
		sql.append(" from sys_exam_assoc r ");
		sql.append(" left join sys_exam_sample s on r.sample_type = s.id ");
		sql.append(" where 1 = 1 ");
		if(!StringUtils.isNull(info.getName())) {
			sql.append(" and r.name like concat('%', concat(:name, '%')) ");
		}
		if(!StringUtils.isNull(info.getAssocCode())) {
			sql.append(" and r.assoc_code = :assocCode ");
		}
		if(!StringUtils.isNull(info.getItemGroup())) {
			sql.append(" and r.item_group = :itemGroup ");
		}
		return queryList(sql.toString(), info, SysExamAssoc.class);
	}
	
	public SysExamAssoc getDetail(String id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select r.* ");
		sql.append(" from sys_exam_assoc r ");
		sql.append(" where r.id = :id ");
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		return queryOne(sql.toString(), paramMap, SysExamAssoc.class);
	}
	
	public Integer isCodeRepeat(String id, String code) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) ");
		sql.append(" from sys_exam_assoc r ");
		sql.append(" where r.id <> :id ");
		sql.append(" and r.assoc_code = :code ");
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		paramMap.put("code", code);
		return queryOne(sql.toString(), paramMap, Integer.class);
	}
	
}
