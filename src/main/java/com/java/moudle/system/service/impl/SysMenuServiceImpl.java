package com.java.moudle.system.service.impl;

import javax.inject.Named;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.domain.SysUser;
import com.java.moudle.system.dto.SysMenuDto;
import com.java.until.StringUtils;
import com.java.until.ToJavaUtils;
import com.java.until.UUIDUtil;
import org.springframework.transaction.annotation.Transactional;

import com.java.moudle.common.service.impl.BaseServiceImpl;
import com.java.moudle.system.dao.SysMenuDao;
import com.java.moudle.system.domain.SysMenu;
import com.java.moudle.system.service.SysMenuService;

import java.util.ArrayList;
import java.util.List;


@Named
@Transactional(readOnly = false)
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {

    //分页查询菜单
    @Override
    public void getMenuPage(SysMenuDto menu) {
        dao.getMenuPage(menu);
    }

    //查询菜单列表
    @Override
    public List<SysMenuDto> getMenuList() {
        SysMenuDto req = new SysMenuDto();
        req.setMenuLevel("1");
        List<SysMenuDto> parentList = dao.getMenuList(req);
        if (parentList != null) {
            for (SysMenuDto parentMenu : parentList) {
                SysMenuDto request = new SysMenuDto();
                request.setParentId(parentMenu.getId());
                List<SysMenuDto> childrenList = dao.getMenuList(request);
                parentMenu.setChildrenList(childrenList);
                if (childrenList != null && childrenList.size() > 0) {
                    JSONArray array = JSONArray.parseArray(JSON.toJSONString(childrenList));
                    parentMenu.setChildrenList(array);
                } else {
                    parentMenu.setChildrenList(new ArrayList());
                }
            }
        }
        return parentList;
    }

    //查询菜单树
    @Override
    public List<SysMenuDto> getMenuTree(SysUser user) {
        SysMenuDto req = new SysMenuDto();
        req.setMenuLevel("1");
        req.setRoleId(user.getRole().getId());
        List<SysMenuDto> parentList = dao.getMenuTree(req);
        if (parentList != null) {
            for (SysMenuDto parentMenu : parentList) {
                SysMenuDto request = new SysMenuDto();
                request.setParentId(parentMenu.getId());
                request.setRoleId(user.getRole().getId());
                List<SysMenuDto> childrenList = dao.getMenuTree(request);
                parentMenu.setChildrenList(childrenList);
                if (childrenList != null && childrenList.size() > 0) {
                    JSONArray array = JSONArray.parseArray(JSON.toJSONString(childrenList));
                    parentMenu.setChildrenList(array);
                } else {
                    parentMenu.setChildrenList(new ArrayList());
                }
            }
        }
        return parentList;
    }

    //添加/修改菜单
    @Override
    public JsonResult saveMenu(SysMenuDto menu) {
        SysMenu query = dao.getSysMenuByName(menu.getId(), menu.getName(), "");
        if (query != null)
            return new JsonResult(null, 9001, "菜单名称已存在");
        SysMenu query1 = dao.getSysMenuByName(menu.getId(), "", menu.getOrderNum());
        if (query1 != null)
            return new JsonResult(null, 9001, "菜单排序号已存在");
        if (StringUtils.isBlank(menu.getId()))
            menu.setId(UUIDUtil.getUUID());
        if ("0".equals(menu.getParentId())) {
            menu.setMenuLevel("1");
        } else {
            menu.setMenuLevel("2");
        }
        SysMenu sysMenu = new SysMenu();
        ToJavaUtils.copyFields(menu, sysMenu);
        dao.save(sysMenu);
        return new JsonResult();
    }

    //根据ID查询菜单
    @Override
    public SysMenu getMenu(String id) {
        return dao.get(id);
    }

    //更改菜单状态
    @Override
    public void updateStatus(SysMenuDto menu) {
        dao.updateStatusById(menu.getStatus(), menu.getId());
    }

    //删除菜单
    @Override
    public void delete(List<String> list) {
        for (String id : list) {
            dao.updateStatusById("3", id);
        }
    }


}
