package com.java.moudle.system.dao;

import java.util.List;

import javax.inject.Named;

import com.java.moudle.system.dao.repository.SysMenuRoleRepository;
import com.java.moudle.system.domain.SysMenuRole;
import com.java.until.dba.BaseDao;

/**
 * @author ZhangWei
 * @Description
 * @Date: 2020-03-17 16:48
 **/
@Named
public class SysMenuRoleDao extends BaseDao<SysMenuRoleRepository, SysMenuRole> {

    public void delByRoleId(String roleId) {
        repository.delByRoleId(roleId);
    }

    public List<SysMenuRole> getMenuByRoleId(String roleId) {
        return repository.getSysMenuRoleOfRoleId(roleId);
    }
}
