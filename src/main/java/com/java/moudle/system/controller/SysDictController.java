package com.java.moudle.system.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.dto.InitDictDto;
import com.java.until.DictUtil;


@RestController
@RequestMapping("/sys/dict")
public class SysDictController  extends BaseController {
    
	
	/**
	 * @Description: 从缓存中获取项目组别数据
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getItemGroupList")
	public JsonResult getItemGroupList() {
		try {
			List<InitDictDto> list = DictUtil.getDict("item_group_opt");
			if(list == null || list.size() == 0) {
				list = new ArrayList<>();
			}
			return jsonResult(list);
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}
	
	/**
	 * @Description: 从缓存中获取数据类型别数据
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getDataTypeList")
	public JsonResult getDataTypeList() {
		try {
			List<InitDictDto> list = DictUtil.getDict("data_type");
			if(list == null || list.size() == 0) {
				list = new ArrayList<>();
			}
			return jsonResult(list);
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}

}
