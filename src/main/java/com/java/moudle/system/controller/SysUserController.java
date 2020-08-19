package com.java.moudle.system.controller;

import javax.inject.Inject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.domain.SysUser;
import com.java.moudle.system.dto.SysUserDto;
import com.java.until.StringUtils;
import com.java.until.SysUtil;
import com.java.until.dba.PageModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.domain.SysUser;
import com.java.moudle.system.service.SysUserService;
import com.java.until.StringUtils;
import com.java.until.SysUtil;


@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController {

    @Inject
    private SysUserService userService;

    @RequestMapping("getLoginUserInfo")
    public JsonResult getLoginUserInfo() {
        try {
            SysUser sysUser = SysUtil.sysUser(request, response);
            if(sysUser == null) {
                return jsonResult(null, 1001, "未登录");
            }
            return jsonResult(sysUser);
        }catch(Exception e) {
            e.printStackTrace();
            return jsonResult(null, -1, "系统错误");
        }
    }

    /**
     * 查询用户列表（分页）
     *
     * @return
     * @throws Exception 聂亚威
     */
    @RequestMapping("getUserPage")
    public JsonResult getUserPage() {
        String param = getParam(request);
        logger.info("查询用户列表（分页）请求参数：" + param);
        SysUserDto req = JSON.toJavaObject(JSONObject.parseObject(param), SysUserDto.class);
        req.setPage(new PageModel(req.getPageNo() == null ? 1 : req.getPageNo(), req.getPageSize() == null ? 10 : req.getPageSize()));
        userService.getUserPage(req);
        logger.info("查询用户列表（分页）返回：" + JSON.toJSONString(req.getPage()));
        return jsonResult(req.getPage());
    }

    /**
     * 保存用户
     * 聂亚威
     *
     * @return
     */
    @PostMapping("saveUser")
    public JsonResult saveUser() {
        String param = getParam(request);
        logger.info("保存用户请求参数：" + param);
        SysUser req = JSON.toJavaObject(JSONObject.parseObject(param), SysUser.class);
        if (StringUtils.isBlank(req.getUsername()))
            return new JsonResult(null, 9001, "用户名不能为空！");
        SysUser user = SysUtil.sysUser(request, response);
        req.setCreaterUser(user.getId());
        JsonResult resp = userService.saveUser(req);
        logger.info("保存用户返回：" + JSON.toJSONString(resp));
        return resp;
    }

    /**
     * 查询用户详情
     * 聂亚威
     *
     * @return
     */
    @PostMapping("getUser")
    public JsonResult getUser() {
        String param = getParam(request);
        logger.info("查询用户详情请求参数：" + param);
        SysUser req = JSON.toJavaObject(JSONObject.parseObject(param), SysUser.class);
        if (StringUtils.isBlank(req.getId()))
            return new JsonResult(null, 9001, "用户ID不能为空！");
        SysUserDto resp = userService.getUser(req.getId());
        logger.info("查询用户详情返回：" + JSON.toJSONString(resp));
        return jsonResult(resp);
    }

    /**
     * 修改用户状态
     * 聂亚威
     *
     * @return
     */
    @PostMapping("updateStatus")
    public JsonResult updateStatus() {
        String param = getParam(request);
        logger.info("修改用户状态请求参数：" + param);
        SysUserDto req = JSON.toJavaObject(JSONObject.parseObject(param), SysUserDto.class);
        if (StringUtils.isBlank(req.getId()))
            return new JsonResult(null, 9001, "用户ID不能为空！");
        userService.updateStatus(req);
        return jsonResult();
    }

    /**
     * 重置密码
     * 聂亚威
     *
     * @return
     */
    @PostMapping("reset")
    public JsonResult reset() {
        String param = getParam(request);
        logger.info("重置密码请求参数：" + param);
        SysUserDto req = JSON.toJavaObject(JSONObject.parseObject(param), SysUserDto.class);
        if (StringUtils.isBlank(req.getId()))
            return new JsonResult(null, 9001, "用户ID不能为空！");
        userService.reset(req.getId());
        return jsonResult();
    }

    /**
     * 修改密码
     * 聂亚威
     *
     * @return
     */
    @PostMapping("changePwd")
    public JsonResult changePwd() {
        String param = getParam(request);
        logger.info("修改密码请求参数：" + param);
        SysUserDto req = JSON.toJavaObject(JSONObject.parseObject(param), SysUserDto.class);
        if (StringUtils.isBlank(req.getOldPwd()))
            return new JsonResult(null, 9001, "旧密码不能为空！");
        if (StringUtils.isBlank(req.getPassword()))
            return new JsonResult(null, 9001, "密码不能为空！");
        req.setId(SysUtil.sysUser(request, response).getId());
        JsonResult resp = userService.changePwd(req);
        logger.info("修改密码返回：" + JSON.toJSONString(resp));
        return jsonResult();
    }
}
