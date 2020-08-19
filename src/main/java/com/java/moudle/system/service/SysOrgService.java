package com.java.moudle.system.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface SysOrgService {
    JSONArray getOrgTree(JSONObject json);
}
