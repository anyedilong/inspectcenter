package com.java.moudle.tripartdock.dict.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.domain.SysUser;
import com.java.moudle.tripartdock.apparatus.dto.ApparatusDto;
import com.java.moudle.tripartdock.dict.domain.Sample;
import com.java.moudle.tripartdock.dict.service.SampleService;
import com.java.moudle.tripartdock.region.service.RegionOutpatientService;
import com.java.until.StringUtils;
import com.java.until.SysUtil;
import com.java.until.dba.PageModel;

@RestController
@RequestMapping("sample")
public class SampleController extends BaseController {

    @Autowired
    private SampleService sampleService;

    /**
     * 查询标本类型列表（分页）
     *
     * @return
     * @throws Exception 聂亚威
     */
    @RequestMapping("getSamplePage")
    public JsonResult getSamplePage() {
        String param = getParam(request);
        logger.info("查询标本类型列表（分页）请求参数：" + param);
        ApparatusDto req = JSON.toJavaObject(JSONObject.parseObject(param), ApparatusDto.class);
        req.setPage(new PageModel(req.getPageNo() == null ? 1 : req.getPageNo(), req.getPageSize() == null ? 10 : req.getPageSize()));
        sampleService.getSamplePage(req);
        logger.info("查询标本类型列表（分页）返回：" + JSON.toJSONString(req.getPage()));
        return jsonResult(req.getPage());
    }

    /**
     * 查询标本类型列表
     *
     * @return
     * @throws Exception 聂亚威
     */
    @RequestMapping("getSampleList")
    public JsonResult getSampleList() {
        String param = getParam(request);
        logger.info("查询标本类型列表请求参数：" + param);
        List<Sample> list = sampleService.getSampleList();
        logger.info("查询标本类型列表返回：" + JSON.toJSONString(list));
        return jsonResult(list);
    }

    /**
     * 保存标本类型
     * 聂亚威
     *
     * @return
     */
    @PostMapping("saveSample")
    public JsonResult saveSample() {
        String param = getParam(request);
        logger.info("保存标本类型请求参数：" + param);
        Sample req = JSON.toJavaObject(JSONObject.parseObject(param), Sample.class);
        if (StringUtils.isBlank(req.getName()))
            return new JsonResult(null, 9001, "标本类型不能为空！");
        JsonResult resp = sampleService.saveSample(req);
        logger.info("保存标本类型返回：" + JSON.toJSONString(resp));
        return resp;
    }

    /**
     * 查询标本类型详情
     * 聂亚威
     *
     * @return
     */
    @PostMapping("getSampleInfo")
    public JsonResult getSampleInfo() {
        String param = getParam(request);
        logger.info("查询标本类型详情请求参数：" + param);
        Sample req = JSON.toJavaObject(JSONObject.parseObject(param), Sample.class);
        if (StringUtils.isBlank(req.getId()))
            return new JsonResult(null, 9001, "标本类型ID不能为空！");
        Sample resp = sampleService.getSample(req.getId());
        logger.info("查询标本类型详情返回：" + JSON.toJSONString(resp));
        return jsonResult(resp);
    }

    @Inject
    private RegionOutpatientService outpatientService;

    /**
     * 删除标本类型
     * 聂亚威
     *
     * @return
     */
    @PostMapping("delete")
    public JsonResult delete() {
        try {
        	String param = getParam(request);
            logger.info("删除标本类型请求参数：" + param);
            Sample req = JSON.toJavaObject(JSONObject.parseObject(param), Sample.class);
            if (StringUtils.isBlank(req.getId())) {
                return jsonResult(null, 9001, "标本类型ID不能为空！");
            }
            Sample sample = sampleService.getSample(req.getId());
            JSONObject json = new JSONObject();
            SysUser user = SysUtil.sysUser(request, response);
            json.put("orgId", user.getOrgId());
            json.put("inspectType", "2");
            json.put("sampleType", sample.getName());
            json.put("pageNo", 1);
            json.put("pageSize", 2);
            JSONObject respJson = outpatientService.getJcList(json);
            Integer count = respJson.getInteger("count");
            if(count > 0) {
            	return jsonResult(null, 1001, "该标本类型已有项目在使用，不能删除！");
            }
            sampleService.delById(req.getId());
            return jsonResult();
        }catch(Exception e) {
        	e.printStackTrace();
        	return jsonResult(null, -1, "系统错误");
        }
    }

}
