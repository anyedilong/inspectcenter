package com.java.moudle.system.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.java.moudle.system.domain.SysMenuRole;

/**
 * @author ZhangWei
 * @Description
 * @Date: 2020-03-17 16:48
 **/
public interface SysMenuRoleRepository extends JpaRepository<SysMenuRole, String> {

    /**
     * @Author zw
     * @Description 根据角色id删除权限
     * @Date 17:04 2020-03-17
     * @Param
     * @return void
     **/
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query("DELETE from SysMenuRole where roleId = :roleId")
    void delByRoleId(@Param("roleId") String roleId);

    /**
     * @Author zw
     * @Description 查询角色关联表
     * @Date 17:28 2020-03-17
     * @Param
     * @return java.util.List<com.java.moudle.system.domain.SysRoleFunction>
     **/
    @Query("from SysMenuRole where roleId = :roleId")
    List<SysMenuRole> getSysMenuRoleOfRoleId(String roleId);
}
