package com.java.moudle.tripartdock.region.controller;

import javax.inject.Inject;

import com.java.moudle.system.domain.SysUser;
import com.java.until.SysUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.tripartdock.region.service.RegionOutpatientService;
import com.java.until.StringUtils;


/**
 * @author Administrator
 * @ClassName: OutpatientController
 * @Description: 三方对接 查询门诊信息
 * @date 2019年9月12日
 */
@RestController
@RequestMapping("/region/outpatient")
public class RegionOutpatientController extends BaseController {

    @Inject
    private RegionOutpatientService outpatientService;

    /**
     * 查询检验申请分页
     *
     * @return
     * @throws Exception 聂亚威
     */
    @PostMapping("getInspectApplyPage")
    public JsonResult getInspectApplyPage() throws Exception {
        String param = getParam(request);
        logger.info("查询检验申请分页请求参数：" + param);
        JSONObject json = JSONObject.parseObject(param);
        if (StringUtils.isBlank(json.getString("orgId"))) {
            SysUser user = SysUtil.sysUser(request, response);
            json.put("orgId", user.getOrgId());
        }
        json.put("inspectType", "1");
        JSONObject respJson = outpatientService.getJcList(json);
        logger.info("查询检验申请分页返回：" + JSON.toJSONString(respJson));
        return jsonResult(respJson);
    }

    /**
     * 查询样本接受分页
     *
     * @return
     * @throws Exception 聂亚威
     */
    @PostMapping("getSamplePage")
    public JsonResult getSamplePage() throws Exception {
        String param = getParam(request);
        logger.info("查询样本接受分页请求参数：" + param);
        JSONObject json = JSONObject.parseObject(param);
        if (StringUtils.isBlank(json.getString("orgId"))) {
            SysUser user = SysUtil.sysUser(request, response);
            json.put("orgId", user.getOrgId());
        }
        json.put("inspectType", "2");
        JSONObject respJson = outpatientService.getJcList(json);
        logger.info("查询样本接受分页返回：" + JSON.toJSONString(respJson));
        return jsonResult(respJson);
    }

    /**
     * 查询检验报告分页
     *
     * @return
     * @throws Exception 聂亚威
     */
    @PostMapping("getReportPage")
    public JsonResult getReportPage() throws Exception {
        String param = getParam(request);
        logger.info("查询检验报告分页请求参数：" + param);
        JSONObject json = JSONObject.parseObject(param);
        if (StringUtils.isBlank(json.getString("orgId"))) {
            SysUser user = SysUtil.sysUser(request, response);
            json.put("orgId", user.getOrgId());
        }
        json.put("inspectType", "3");
        JSONObject respJson = outpatientService.getJcList(json);
        logger.info("查询检验报告分页返回：" + JSON.toJSONString(respJson));
        return jsonResult(respJson);
    }

    /**
     * 检验报告调阅分页
     *
     * @return
     * @throws Exception 聂亚威
     */
    @PostMapping("getReportReviewPage")
    public JsonResult getReportReviewList() throws Exception {
        String param = getParam(request);
        logger.info("检验报告调阅分页请求参数：" + param);
        JSONObject json = JSONObject.parseObject(param);
        if (StringUtils.isBlank(json.getString("orgId"))) {
            SysUser user = SysUtil.sysUser(request, response);
            json.put("orgId", user.getOrgId());
        }
        json.put("inspectType", "3");
        json.put("isReportReview", "1");
        JSONObject respJson = outpatientService.getJcList(json);
        logger.info("检验报告调阅分页返回：" + JSON.toJSONString(respJson));
        return jsonResult(respJson);
    }

    /**
     * 查询检验详情
     *
     * @return
     * @throws Exception 聂亚威
     */
    @PostMapping("getInspectInfo")
    public JsonResult getJcInfo() throws Exception {
        String param = getParam(request);
        logger.info("查询检验详情请求参数：" + param);
        JSONObject json = JSONObject.parseObject(param);
        if (StringUtils.isBlank(json.getString("id")))
            return new JsonResult(null, 9001, "ID不能为空！");
        JSONObject respJson = outpatientService.getJcInfo(json);
        logger.info("查询检验详情返回：" + JSON.toJSONString(respJson));
        return jsonResult(respJson);
    }

    /**
     * 查询档案基本信息
     *
     * @return
     * @throws Exception 聂亚威
     */
    @PostMapping("getJbxxInfo")
    public JsonResult getJbxxInfo() throws Exception {
        String param = getParam(request);
        logger.info("查询档案基本信息请求参数：" + param);
        JSONObject json = JSONObject.parseObject(param);
        if (StringUtils.isBlank(json.getString("sfzh")))
            return new JsonResult(null, 9001, "身份证号不能为空！");
        JSONObject respJson = outpatientService.getJbxxInfo(json);
        logger.info("查询档案基本信息返回：" + JSON.toJSONString(respJson));
        return jsonResult(respJson);
    }
}
