package com.java.moudle.system.service;

import com.java.moudle.common.message.JsonResult;
import com.java.moudle.common.service.BaseService;
import com.java.moudle.system.domain.SysUser;
import com.java.moudle.system.dto.SysUserDto;

public interface SysUserService extends BaseService<SysUser> {

    SysUser getUserInfoByName(String username);

    SysUser queryInfoByCon(String id, String username);

    void getUserPage(SysUserDto request);

    JsonResult saveUser(SysUser user);

    SysUserDto getUser(String id);

    void updateStatus(SysUserDto request);

    void reset(String id);

    JsonResult changePwd(SysUserDto user);

}
