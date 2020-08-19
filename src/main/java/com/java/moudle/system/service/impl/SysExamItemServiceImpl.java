package com.java.moudle.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.java.moudle.common.service.impl.BaseServiceImpl;
import com.java.moudle.common.utils.properties.PropertiesUtil;
import com.java.moudle.system.dao.SysExamItemDao;
import com.java.moudle.system.dao.SysItemAssocDao;
import com.java.moudle.system.domain.SysExamItem;
import com.java.moudle.system.service.SysExamItemService;
import com.java.until.dba.PageModel;
import com.java.until.http.HttpRequest;


@Named
@Transactional(readOnly=false)
public class SysExamItemServiceImpl extends BaseServiceImpl<SysExamItemDao, SysExamItem> implements SysExamItemService {

	private final String regionUrl = PropertiesUtil.getRegion("regionUrl");
	
	@Inject
	private SysItemAssocDao itemAssocDao;
	
	@Override
	public void getExamItemPage(PageModel page, SysExamItem info) throws Exception {
		dao.getExamItemPage(page, info);
		
	}

	@Override
	public List<SysExamItem> getExamItemList(SysExamItem info) throws Exception {
		return dao.getExamItemList(info);
	}

	@Override
	public SysExamItem getDeltail(String id) throws Exception {
		return dao.getDeltail(id);
	}
	
	@Override
	public Integer isCodeRepeat(String id, String code) throws Exception {
		return dao.isCodeRepeat(id, code);
	}

	@Override
	public Integer isGroupExist(String itemId) throws Exception {
		return itemAssocDao.isGroupExist(itemId);
	}

	@Override
	public Integer isExamDataExist(String itemCode, String name) throws Exception {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("itemCode", itemCode);
		paramMap.put("itemName", name);
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("isExamDataExist"), paramMap);
		return JSON.parseObject(sendPost, Integer.class);
	}


}
