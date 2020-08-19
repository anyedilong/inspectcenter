package com.java.moudle.system.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.moudle.system.domain.SysRole;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ZhangWei
 * @Description
 * @Date: 2020-03-17 11:06
 **/
public interface SysRoleRepository extends JpaRepository<SysRole, String> {

    //根据名称查询角色
    @Query("from SysRole where roleName = :name")
    SysRole getRoleByName(String name);

    //根据名称查询角色
    @Query("from SysRole where roleCode = :code")
    SysRole getRoleByCode(String code);

    //根据ID删除标本类型
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update sys_role set status = :status  where id = :id", nativeQuery = true)
    void updateStatusById(@Param("id") String id, @Param("status") String status);
}
