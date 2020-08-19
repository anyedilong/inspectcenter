package com.java.moudle.system.service.impl;

import javax.inject.Named;

import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.dao.SysMenuRoleDao;
import com.java.moudle.system.domain.SysMenuRole;
import com.java.moudle.system.dto.SysMenuDto;
import com.java.moudle.system.dto.SysRoleDto;
import com.java.moudle.tripartdock.apparatus.dto.ApparatusDto;
import com.java.until.StringUtils;
import com.java.until.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.java.moudle.common.service.impl.BaseServiceImpl;
import com.java.moudle.system.dao.SysRoleDao;
import com.java.moudle.system.domain.SysRole;
import com.java.moudle.system.service.SysRoleService;

import java.util.Date;
import java.util.List;

/**
 * @author ZhangWei
 * @Description
 * @Date: 2020-03-17 11:23
 **/
@Transactional(rollbackFor = Exception.class)
@Named
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleDao, SysRole> implements SysRoleService {

    @Autowired
    private SysMenuRoleDao menuRoleDao;

    @Override
    public SysRole getRoleInfoByUserId(String userId) {
        return dao.getRoleInfoByUserId(userId);
    }

    //查询角色列表（分页）
    @Override
    public void getRolePage(ApparatusDto dto) {
        dao.getRolePage(dto);
    }

    //查询角色列表
    @Override
    public List<SysRole> getRoleList() {
        return dao.getRoleList();
    }

    //保存角色
    @Override
    public JsonResult saveRole(SysRole role) {
        SysRole query = dao.getRoleByCode(role.getRoleCode());
        if (StringUtils.isBlank(role.getId())) {
            //新增角色
            if (query != null)
                return new JsonResult(null, 9001, "角色编码重复，请重新填写。");
            role.setId(UUIDUtil.getUUID());
            role.setCreateTime(new Date());
            role.setStatus("1");
            role.setRoleType("1");
            dao.save(role);
        } else {
            //修改角色
            if (query != null && !role.getId().equals(query.getId()) && role.getRoleCode().equals(query.getRoleCode()))
                return new JsonResult(null, 9001, "角色编码重复，请重新填写。");
            SysRole sysRole = dao.get(role.getId());
            sysRole.setRoleCode(role.getRoleCode());
            sysRole.setRoleName(role.getRoleName());
            sysRole.setRemark(role.getRemark());
            dao.save(sysRole);
        }
        return new JsonResult();
    }

    //查询角色详情
    @Override
    public SysRoleDto getRoleInfo(String id) {
        SysRoleDto sysRole = dao.getRoleInfoById(id);
        sysRole.setMenuList(dao.getMenuIdList(id));
        return sysRole;
    }

    //删除角色
    @Override
    public JsonResult delById(String id) {
        long count = dao.getRoleCount(id);
        if (count > 0)
            return new JsonResult(null, 9001, "该角色已有用户在使用，不能删除！");
        dao.delById(id);
        return new JsonResult();
    }

    //角色授权
    @Override
    public void authorize(SysRoleDto dto) {
        menuRoleDao.delByRoleId(dto.getId());
        List<SysMenuDto> menuList = dto.getMenuList();
        if (menuList != null && menuList.size() > 0) {
            for (SysMenuDto menu : menuList) {
                SysMenuRole menuRole = new SysMenuRole();
                menuRole.setId(UUIDUtil.getUUID());
                menuRole.setRoleId(dto.getId());
                menuRole.setMenuId(menu.getId());
                menuRoleDao.save(menuRole);
            }
        }
    }


}
