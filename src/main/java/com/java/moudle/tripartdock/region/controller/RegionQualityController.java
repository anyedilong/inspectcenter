package com.java.moudle.tripartdock.region.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.domain.SysUser;
import com.java.moudle.tripartdock.region.service.RegionQualityService;
import com.java.until.StringUtils;
import com.java.until.SysUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//质控数据相关接口

@RestController
@RequestMapping("/quality")
public class RegionQualityController extends BaseController {

    @Autowired
    private RegionQualityService qualityService;

    /**
     * 查询质控数据列表（分页）
     *
     * @return
     * @throws Exception 聂亚威
     */
    @PostMapping("getQualityPage")
    public JsonResult getQualityPage() throws Exception {
        String param = getParam(request);
        logger.info("查询质控数据列表（分页）请求参数：" + param);
        JSONObject json = JSONObject.parseObject(param);
        if (StringUtils.isBlank(json.getString("orgId"))) {
            SysUser user = SysUtil.sysUser(request, response);
            json.put("orgId", user.getOrgId());
        }
        JSONObject respJson = qualityService.getQualityPage(json);
        logger.info("查询质控数据列表（分页）返回：" + JSON.toJSONString(respJson));
        return jsonResult(respJson);
    }

    /**
     * 查询质控数据结果
     *
     * @return
     * @throws Exception 聂亚威
     */
    @PostMapping("getResultList")
    public JsonResult getResultList() throws Exception {
        String param = getParam(request);
        logger.info("查询质控数据结果请求参数：" + param);
        JSONObject json = JSONObject.parseObject(param);
        if (StringUtils.isBlank(json.getString("apparatusName"))) {
            return jsonResult(null, 9001, "仪器名称[apparatusName]不能为空！");
        }
        JSONArray respJson = qualityService.getResultList(json);
        logger.info("查询质控数据结果返回：" + JSON.toJSONString(respJson));
        return jsonResult(respJson);
    }

}
