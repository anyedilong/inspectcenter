package com.java.moudle.system.controller;

import javax.inject.Inject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.domain.SysMenu;
import com.java.moudle.system.domain.SysUser;
import com.java.moudle.system.dto.SysMenuDto;
import com.java.until.StringUtils;
import com.java.until.SysUtil;
import com.java.until.ToJavaUtils;
import com.java.until.dba.PageModel;
import com.java.until.validate.ValidateUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.moudle.common.controller.BaseController;
import com.java.moudle.system.service.SysMenuService;

import java.util.List;


@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController {

    @Inject
    private SysMenuService menuService;

    //查询菜单列表（分页）
    @PostMapping("/getMenuPage")
    public JsonResult getMenuPage() {
        String param = getParam(request);
        logger.info("查询菜单列表（分页）请求参数：" + param);
        SysMenuDto req = JSON.toJavaObject(JSONObject.parseObject(param), SysMenuDto.class);
        req.setPage(new PageModel(req.getPageNo() == null ? 1 : req.getPageNo(), req.getPageSize() == null ? 10 : req.getPageSize()));
        menuService.getMenuPage(req);
        logger.info("查询菜单列表（分页）返回：" + JSON.toJSONString(req.getPage()));
        return jsonResult(req.getPage());
    }

    //查询菜单列表
    @PostMapping("/getMenuList")
    public JsonResult getMenuList() {
        logger.info("查询菜单列表！");
        List<SysMenuDto> list = menuService.getMenuList();
        logger.info("查询菜单列表返回：" + JSON.toJSONString(list));
        return jsonResult(list);
    }

    /**
     * 查询菜单树
     * 聂亚威
     *
     * @return
     */
    @PostMapping("/getMenuTree")
    public JsonResult getMenuTree() {
        logger.info("查询菜单树！");
        SysUser user = SysUtil.sysUser(request, response);
        if (user == null)
            return new JsonResult(null, 9001, "未登录！");
        List<SysMenuDto> list = menuService.getMenuTree(user);
        logger.info("查询菜单树返回：" + JSON.toJSONString(list));
        return jsonResult(list);
    }

    //添加/修改菜单
    @PostMapping("/saveMenu")
    public JsonResult saveMenu() {
        String param = getParam(request);
        logger.info("保存菜单请求参数：" + param);
        SysMenuDto menuDto = JSON.toJavaObject(JSONObject.parseObject(param), SysMenuDto.class);
        String msg = ValidateUtil.validateField(menuDto);
        if (StringUtils.isNotBlank(msg))
            return new JsonResult(null, 9001, msg);
        JsonResult resp = menuService.saveMenu(menuDto);
        logger.info("保存菜单返回：" + JSON.toJSONString(resp));
        return resp;
    }

    //根据ID查询菜单
    @PostMapping("/getMenu")
    public JsonResult getMenu() {
        String param = getParam(request);
        logger.info("根据ID查询菜单请求参数：" + param);
        String id = JSONObject.parseObject(param).getString("id");
        if (StringUtils.isBlank(id))
            return new JsonResult(null, 9001, "ID不能为空！");
        SysMenu resp = menuService.getMenu(id);
        logger.info("根据ID查询菜单返回：" + JSON.toJSONString(resp));
        return jsonResult(resp);
    }

    //更改菜单状态
    @PostMapping("/updateStatus")
    public JsonResult updateStatus() {
        String param = getParam(request);
        logger.info("更改菜单状态请求参数：" + param);
        SysMenuDto menuDto = JSON.toJavaObject(JSONObject.parseObject(param), SysMenuDto.class);
        if (StringUtils.isBlank(menuDto.getId()) || StringUtils.isBlank(menuDto.getStatus()))
            return new JsonResult(null, 9001, "ID或状态不能为空！");
        menuService.updateStatus(menuDto);
        return jsonResult();
    }

    //删除菜单
    @PostMapping("/delete")
    public JsonResult delete() {
        String param = getParam(request);
        logger.info("删除菜单请求参数：" + param);
        List<String> idList = JSON.toJavaObject(JSONObject.parseObject(param), SysMenuDto.class).getIdList();
        if (idList == null || idList.size() == 0)
            return new JsonResult(null, 9001, "ID列表不能为空！");
        menuService.delete(idList);
        return jsonResult();
    }
}
