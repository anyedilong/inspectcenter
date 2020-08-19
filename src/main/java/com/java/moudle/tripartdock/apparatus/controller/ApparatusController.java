package com.java.moudle.tripartdock.apparatus.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.tripartdock.apparatus.dto.ApparatusDto;
import com.java.moudle.tripartdock.apparatus.service.ApparatusService;
import com.java.until.StringUtils;
import com.java.until.SysUtil;
import com.java.until.dba.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("apparatus")
public class ApparatusController extends BaseController {

    @Autowired
    private ApparatusService apparatusService;

    //检验仪器分页
    @PostMapping("getApparatusPage")
    public JsonResult getApparatusPage() {
        String param = getParam(request);
        logger.info("检验仪器分页请求参数：" + param);
        ApparatusDto req = JSON.toJavaObject(JSONObject.parseObject(param), ApparatusDto.class);
        if (StringUtils.isBlank(req.getOrgId()))
            req.setOrgId(SysUtil.sysUser(request, response).getOrgId());
        req.setPage(new PageModel(req.getPageNo() == null ? 1 : req.getPageNo(), req.getPageSize() == null ? 10 : req.getPageSize()));
        apparatusService.getApparatusPage(req);
        logger.info("检验仪器分页返回：" + JSON.toJSONString(req.getPage()));
        return jsonResult(req.getPage());
    }

    //检验仪器列表
    @PostMapping("getApparatusList")
    public JsonResult getApparatusList() {
        String param = getParam(request);
        logger.info("检验仪器列表请求参数：" + param);
        ApparatusDto req = JSON.toJavaObject(JSONObject.parseObject(param), ApparatusDto.class);
        if (StringUtils.isBlank(req.getOrgId()))
            req.setOrgId(SysUtil.sysUser(request, response).getOrgId());
        List<ApparatusDto> list = apparatusService.getApparatusList(req);
        logger.info("检验仪器列表返回：" + JSON.toJSONString(list));
        return jsonResult(list);
    }

}
