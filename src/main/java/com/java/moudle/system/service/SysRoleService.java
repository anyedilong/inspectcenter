package com.java.moudle.system.service;

import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.domain.SysRole;
import com.java.moudle.system.dto.SysRoleDto;
import com.java.moudle.tripartdock.apparatus.dto.ApparatusDto;

import java.util.List;

public interface SysRoleService {

    //查询用户的角色信息
    SysRole getRoleInfoByUserId(String userId);

    void getRolePage(ApparatusDto dto);

    List<SysRole> getRoleList();

    JsonResult saveRole(SysRole role);

    SysRoleDto getRoleInfo(String id);

    JsonResult delById(String id);

    void authorize(SysRoleDto dto);

}
