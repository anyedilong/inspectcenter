package com.java.moudle.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.service.SysOrgService;
import com.java.until.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("org")
public class SysOrgController extends BaseController {

    @Autowired
    private SysOrgService sysOrgService;

    @RequestMapping("getOrgTree")
    public JsonResult getOrgTree() {
        String param = getParam(request);
        logger.info("查询机构树请求参数：" + param);
        JSONObject json = JSONObject.parseObject(param);
        if (StringUtils.isBlank(json.getString("orgId")))
            return new JsonResult(null, 9001, "机构ID不能为空！");
        JSONArray array = sysOrgService.getOrgTree(json);
        logger.info("查询机构树返回：" + JSON.toJSONString(array));
        return jsonResult(array);
    }

}
