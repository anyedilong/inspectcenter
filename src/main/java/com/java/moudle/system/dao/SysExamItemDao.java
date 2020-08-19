package com.java.moudle.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.java.moudle.system.dao.repository.SysExamItemRepository;
import com.java.moudle.system.domain.SysExamItem;
import com.java.until.StringUtils;
import com.java.until.dba.BaseDao;
import com.java.until.dba.PageModel;


@Named
public class SysExamItemDao extends BaseDao<SysExamItemRepository, SysExamItem> {

	public void getExamItemPage(PageModel page, SysExamItem info) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select r.*, s.name as sampleTypeName ");
		sql.append(" from sys_exam_item r ");
		sql.append(" left join sys_exam_sample s on r.sample_type = s.id ");
		sql.append(" where 1 = 1 ");
		if(!StringUtils.isNull(info.getNameOrCode())) {
			sql.append(" and (r.name like concat('%', concat(:nameOrCode, '%')) or r.item_code like concat('%', concat(:nameOrCode, '%'))) ");
		}
		if(!StringUtils.isNull(info.getItemGroup())) {
			sql.append(" and r.item_group = :itemGroup ");
		}
		queryPageList(sql.toString(), info, page, SysExamItem.class);
	}
	
	public List<SysExamItem> getExamItemList(SysExamItem info){
		StringBuffer sql = new StringBuffer();
		sql.append(" select r.id, r.name, r.item_code  ");
		sql.append(" from sys_exam_item r ");
		sql.append(" where 1 = 1 ");
		if(!StringUtils.isNull(info.getNameOrCode())) {
			sql.append(" and (r.name like concat('%', concat(:nameOrCode, '%')) or r.item_code like concat('%', concat(:nameOrCode, '%'))) ");
		}
		if(!StringUtils.isNull(info.getItemGroup())) {
			sql.append(" and r.item_group = :itemGroup ");
		}
		return queryList(sql.toString(), info, SysExamItem.class);
	}
	
	public SysExamItem getDeltail(String id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select r.* ");
		sql.append(" from sys_exam_item r ");
		sql.append(" where r.id = :id ");
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		return queryOne(sql.toString(), paramMap, SysExamItem.class);
	}
	
	public Integer isCodeRepeat(String id, String code) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) ");
		sql.append(" from sys_exam_item r ");
		sql.append(" where r.id <> :id ");
		sql.append(" and r.item_code = :code ");
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		paramMap.put("code", code);
		return queryOne(sql.toString(), paramMap, Integer.class);
	}
	
	public List<SysExamItem> getListByAssocId(String assocId){
		StringBuffer sql = new StringBuffer();
		sql.append(" select r.name, r.id  ");
		sql.append(" from sys_exam_item r ");
		sql.append(" join sys_item_assoc s on r.id = s.item_id ");
		sql.append(" where s.assoc_id = :assocId ");
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("assocId", assocId);
		return queryList(sql.toString(), paramMap, SysExamItem.class);
	}
}
