package com.java.moudle.tripartdock.region.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface RegionQualityService {

    JSONObject getQualityPage(JSONObject json) throws Exception;

    JSONArray getResultList(JSONObject json) throws Exception;

}
