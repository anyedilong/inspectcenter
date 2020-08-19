package com.java.moudle.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.utils.properties.PropertiesUtil;
import com.java.moudle.system.service.SysOrgService;
import com.java.until.http.HttpUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysOrgServiceImpl implements SysOrgService {

    private final String regionUrl = PropertiesUtil.getRegion("regionUrl");

    @Override
    public JSONArray getOrgTree(JSONObject json) {
        String respStr = HttpUtil.doPost(regionUrl + PropertiesUtil.getRegion("getOrgTree"), json.toJSONString());
        return JSON.parseArray(respStr);
    }
}
