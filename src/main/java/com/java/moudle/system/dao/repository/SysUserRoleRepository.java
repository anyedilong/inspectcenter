package com.java.moudle.system.dao.repository;

import com.java.moudle.system.domain.SysUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SysUserRoleRepository extends JpaRepository<SysUserRole, String> {

    /**
     * @Description 根据用户id删除角色关联表信息
     **/
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query("delete from SysUserRole where userId = :userId")
    void delRoleByUserId(String userId);

    @Query("from SysUserRole where  roleId = :roleId")
    List<SysUserRole> getSysUserRole(String roleId);
}
