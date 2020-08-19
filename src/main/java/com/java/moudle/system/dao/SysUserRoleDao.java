package com.java.moudle.system.dao;

import com.java.moudle.system.dao.repository.SysUserRoleRepository;
import com.java.moudle.system.domain.SysUserRole;
import com.java.until.dba.BaseDao;

import javax.inject.Named;
import java.util.List;

@Named
public class SysUserRoleDao extends BaseDao<SysUserRoleRepository, SysUserRole> {

    public void delRoleByUserId(String userId) {
        repository.delRoleByUserId(userId);
    }

    public List<SysUserRole> getSysUserRole(String roleId) {

        return repository.getSysUserRole(roleId);
    }
}
