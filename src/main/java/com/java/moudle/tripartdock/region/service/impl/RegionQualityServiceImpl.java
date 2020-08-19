package com.java.moudle.tripartdock.region.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.utils.properties.PropertiesUtil;
import com.java.moudle.tripartdock.region.service.RegionQualityService;
import com.java.until.http.HttpUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegionQualityServiceImpl implements RegionQualityService {
    private final String regionUrl = PropertiesUtil.getRegion("regionUrl");

    //查询质控数据列表（分页）
    @Override
    public JSONObject getQualityPage(JSONObject json) throws Exception {
        String respStr = HttpUtil.doPost(regionUrl + PropertiesUtil.getRegion("getQualityPage"), json.toJSONString());
        return JSON.parseObject(respStr);
    }

    //查询质控数据结果
    @Override
    public JSONArray getResultList(JSONObject json) throws Exception {
        String respStr = HttpUtil.doPost(regionUrl + PropertiesUtil.getRegion("getResultList"), json.toJSONString());
        return JSON.parseArray(respStr);
    }
}
