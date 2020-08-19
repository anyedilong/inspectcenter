package com.java.moudle.system.service;

import com.java.moudle.common.message.JsonResult;
import com.java.moudle.common.service.BaseService;
import com.java.moudle.system.domain.SysMenu;
import com.java.moudle.system.domain.SysUser;
import com.java.moudle.system.dto.SysMenuDto;

import java.util.List;

public interface SysMenuService extends BaseService<SysMenu> {

    void getMenuPage(SysMenuDto menu);

    List<SysMenuDto> getMenuList();

    List<SysMenuDto> getMenuTree(SysUser user);

    JsonResult saveMenu(SysMenuDto menu);

    SysMenu getMenu(String id);

    void updateStatus(SysMenuDto menu);

    void delete(List<String> list);
}
