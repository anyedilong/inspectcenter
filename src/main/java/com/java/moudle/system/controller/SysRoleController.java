package com.java.moudle.system.controller;

import javax.inject.Inject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.domain.SysRole;
import com.java.moudle.system.domain.SysUser;
import com.java.moudle.system.dto.SysRoleDto;
import com.java.moudle.tripartdock.apparatus.dto.ApparatusDto;
import com.java.until.StringUtils;
import com.java.until.SysUtil;
import com.java.until.dba.PageModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.moudle.common.controller.BaseController;
import com.java.moudle.system.service.SysRoleService;

import java.util.List;


@RestController
@RequestMapping("sys/role")
public class SysRoleController extends BaseController {

    @Inject
    private SysRoleService sysRoleService;

    /**
     * 查询角色列表（分页）
     *
     * @return
     * @throws Exception 聂亚威
     */
    @RequestMapping("getRolePage")
    public JsonResult getRolePage() {
        String param = getParam(request);
        logger.info("查询角色列表（分页）请求参数：" + param);
        ApparatusDto req = JSON.toJavaObject(JSONObject.parseObject(param), ApparatusDto.class);
        req.setPage(new PageModel(req.getPageNo() == null ? 1 : req.getPageNo(), req.getPageSize() == null ? 10 : req.getPageSize()));
        sysRoleService.getRolePage(req);
        logger.info("查询角色列表（分页）返回：" + JSON.toJSONString(req.getPage()));
        return jsonResult(req.getPage());
    }

    /**
     * 查询角色列表
     *
     * @return
     * @throws Exception 聂亚威
     */
    @RequestMapping("getRoleList")
    public JsonResult getRoleList() {
        logger.info("查询角色列表。");
        List<SysRole> list = sysRoleService.getRoleList();
        logger.info("查询角色列表返回：" + JSON.toJSONString(list));
        return jsonResult(list);
    }

    /**
     * 保存角色
     * 聂亚威
     *
     * @return
     */
    @PostMapping("saveRole")
    public JsonResult saveRole() {
        String param = getParam(request);
        logger.info("保存角色请求参数：" + param);
        SysRole req = JSON.toJavaObject(JSONObject.parseObject(param), SysRole.class);
        if (StringUtils.isBlank(req.getRoleName()))
            return new JsonResult(null, 9001, "角色名称不能为空！");
        SysUser user = SysUtil.sysUser(request, response);
        req.setOrgId(user.getOrgId());
        JsonResult resp = sysRoleService.saveRole(req);
        logger.info("保存角色返回：" + JSON.toJSONString(resp));
        return resp;
    }

    /**
     * 查询角色详情
     * 聂亚威
     *
     * @return
     */
    @PostMapping("getRoleInfo")
    public JsonResult getRoleInfo() {
        String param = getParam(request);
        logger.info("查询角色详情请求参数：" + param);
        SysRole req = JSON.toJavaObject(JSONObject.parseObject(param), SysRole.class);
        if (StringUtils.isBlank(req.getId()))
            return new JsonResult(null, 9001, "角色ID不能为空！");
        SysRoleDto resp = sysRoleService.getRoleInfo(req.getId());
        logger.info("查询角色详情返回：" + JSON.toJSONString(resp));
        return jsonResult(resp);
    }

    /**
     * 删除角色
     * 聂亚威
     *
     * @return
     */
    @PostMapping("delete")
    public JsonResult delete() {
        String param = getParam(request);
        logger.info("删除角色请求参数：" + param);
        SysRole req = JSON.toJavaObject(JSONObject.parseObject(param), SysRole.class);
        if (StringUtils.isBlank(req.getId()))
            return new JsonResult(null, 9001, "角色ID不能为空！");
        JsonResult resp = sysRoleService.delById(req.getId());
        logger.info("删除角色返回：" + JSON.toJSONString(resp));
        return resp;
    }

    /**
     * 角色授权
     * 聂亚威
     *
     * @return
     */
    @PostMapping("authorize")
    public JsonResult authorize() {
        String param = getParam(request);
        logger.info("角色授权请求参数：" + param);
        SysRoleDto req = JSON.toJavaObject(JSONObject.parseObject(param), SysRoleDto.class);
        if (StringUtils.isBlank(req.getId()))
            return new JsonResult(null, 9001, "角色ID不能为空！");
        sysRoleService.authorize(req);
        return jsonResult();
    }

}
