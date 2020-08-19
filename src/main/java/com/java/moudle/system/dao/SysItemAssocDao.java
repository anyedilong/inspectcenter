package com.java.moudle.system.dao;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import com.java.moudle.system.dao.repository.SysItemAssocRepository;
import com.java.moudle.system.domain.SysItemAssoc;
import com.java.until.dba.BaseDao;


@Named
public class SysItemAssocDao extends BaseDao<SysItemAssocRepository, SysItemAssoc> {

	public Integer isGroupExist(String itemId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(r.id) ");
		sql.append(" from sys_item_assoc r ");
		sql.append(" where r.item_id = :itemId ");
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("itemId", itemId);
		return queryOne(sql.toString(), paramMap, Integer.class);
	}
	
	public void deleteDataByAssocId(String assocId) {
		repository.deleteDataByAssocId(assocId);
	}
	
}
