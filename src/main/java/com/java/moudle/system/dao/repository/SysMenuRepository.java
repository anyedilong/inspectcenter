package com.java.moudle.system.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.moudle.system.domain.SysMenu;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SysMenuRepository extends JpaRepository<SysMenu, String> {

    //根据名称查询菜单
    @Query("from SysMenu where  name = :name")
    SysMenu getSysMenuByName(String name);

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query("update SysMenu set status = :status where id =:id")
    void updateStatusById(String status, String id);

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query("delete from SysMenu where id = :id")
    void delById(String id);
}
