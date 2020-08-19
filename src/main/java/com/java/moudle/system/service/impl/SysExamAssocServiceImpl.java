package com.java.moudle.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.java.moudle.common.service.impl.BaseServiceImpl;
import com.java.moudle.system.dao.SysExamAssocDao;
import com.java.moudle.system.dao.SysExamItemDao;
import com.java.moudle.system.dao.SysItemAssocDao;
import com.java.moudle.system.domain.SysExamAssoc;
import com.java.moudle.system.domain.SysExamItem;
import com.java.moudle.system.domain.SysItemAssoc;
import com.java.moudle.system.service.SysExamAssocService;
import com.java.until.StringUtils;
import com.java.until.UUIDUtil;
import com.java.until.dba.PageModel;


@Named
@Transactional(readOnly=false)
public class SysExamAssocServiceImpl extends BaseServiceImpl<SysExamAssocDao, SysExamAssoc> implements SysExamAssocService {

	@Inject
	private SysItemAssocDao itemAssocDao;
	@Inject
	private SysExamItemDao examItemDao;
	
	@Override
	public void getExamAssocPage(PageModel page, SysExamAssoc info) throws Exception {
		dao.getExamAssocPage(page, info);
		List<SysExamAssoc> list = page.getList();
		if(list != null && list.size() > 0) {
			for(SysExamAssoc se : list) {
				String itemNameStr = "";
				List<SysExamItem> itemList = examItemDao.getListByAssocId(se.getId());
				if(itemList != null && itemList.size() > 0) {
					for(SysExamItem sei : itemList) {
						if(StringUtils.isNull(itemNameStr)) {
							itemNameStr = sei.getName();
						}else {
							itemNameStr += "," + sei.getName();
						}
					}
				}
				se.setItemNameStr(itemNameStr);
			}
		}
	}

	@Override
	public List<SysExamAssoc> getExamAssocList(SysExamAssoc info) throws Exception {
		return dao.getExamAssocList(info);
	}

	@Override
	public SysExamAssoc getDetail(String id) throws Exception {
		SysExamAssoc info = dao.getDetail(id);
		List<SysExamItem> list = examItemDao.getListByAssocId(id);
		if(list == null || list.size() == 0) {
			list = new ArrayList<>();
		}
		info.setExamItemList(list);
		return info;
	}
	
	@Override
	public Integer isCodeRepeat(String id, String assocCode) {
		return dao.isCodeRepeat(id, assocCode);
	}

	@Override
	public void saveAssocInfo(SysExamAssoc info) {
		String itemIdStr = info.getItemIdStr();
		if(!StringUtils.isNull(itemIdStr)) {
			//删除关系表中数据
			itemAssocDao.deleteDataByAssocId(info.getId());
			String[] itemIdArr = itemIdStr.split(",");
			for(int i = 0; i < itemIdArr.length; i++) {
				SysItemAssoc itemAssoc = new SysItemAssoc();
				itemAssoc.setId(UUIDUtil.getUUID());
				itemAssoc.setItemId(itemIdArr[i]);
				itemAssoc.setAssocId(info.getId());
				itemAssocDao.save(itemAssoc);
			}
		}
		dao.save(info);
		
	}

	@Override
	public void deleteAssocInfo(String id) {
		//删除关系表中数据
		itemAssocDao.deleteDataByAssocId(id);
		dao.delete(id);
	}

}
