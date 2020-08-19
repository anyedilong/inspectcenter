package com.java.moudle.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.java.moudle.system.dao.repository.SysRoleRepository;
import com.java.moudle.system.domain.SysRole;
import com.java.moudle.system.dto.SysMenuDto;
import com.java.moudle.system.dto.SysRoleDto;
import com.java.moudle.tripartdock.apparatus.dto.ApparatusDto;
import com.java.until.StringUtils;
import com.java.until.dba.BaseDao;

/**
 * @author ZhangWei
 * @Description
 * @Date: 2020-03-17 11:06
 **/
@Named
public class SysRoleDao extends BaseDao<SysRoleRepository, SysRole> {

    //查询角色列表（分页）
    public void getRolePage(ApparatusDto dto) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select r.id, r.role_code, r.role_name,r.create_time,r.remark ");
        sql.append(" from sys_role r ");
        sql.append(" where r.status = '1' ");
        if (StringUtils.isNotBlank(dto.getName())) {
            sql.append(" and r.role_name like concat('%', concat(:name ,'%')) ");
        }

        queryPageList(sql.toString(), dto, dto.getPage(), SysRole.class);
    }

    //查询角色列表
    public List<SysRole> getRoleList() {
        StringBuilder sql = new StringBuilder();
        sql.append(" select r.id, r.role_code, r.role_name,r.create_time,r.remark ");
        sql.append(" from sys_role r ");
        sql.append(" where r.status = '1' ");
        return queryList(sql.toString(), null, SysRole.class);
    }

	public SysRole getRoleInfoByUserId(String userId) {
    	StringBuffer sql = new StringBuffer();
        sql.append(" select r.id, r.role_code, r.role_name, r.status, r.role_type ");
        sql.append(" from sys_role_user ur ");
        sql.append(" join sys_role r on r.id = ur.role_id ");
        sql.append(" where r.status = '1' and ur.user_id = :userId ");

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", userId);
        return queryOne(sql.toString(), paramMap, SysRole.class);
    }

    //根据角色ID查询数量
    public Long getRoleCount(String roleId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select r.id ");
        sql.append(" from sys_role r ");
        sql.append(" join sys_role_user ru on ru.role_id = r.id ");
        sql.append(" join sys_user u on u.id = ru.user_id ");
        sql.append(" where r.status = '1' and r.id = :roleId ");
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("roleId", roleId);
        return queryCount(sql.toString(), paramMap);
    }

    //根据角色ID查询菜单列表
    public List<SysMenuDto> getMenuIdList(String roleId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select m.id,m.name ");
        sql.append(" from sys_role r ");
        sql.append(" join sys_menu_role mr on mr.role_id = r.id ");
        sql.append(" join sys_menu m on m.id = mr.menu_id ");
        sql.append(" where r.status = '1' and m.status = '1' and m.menu_level = '2' and r.id = :roleId ");

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("roleId", roleId);
        return queryList(sql.toString(), paramMap, SysMenuDto.class);
    }

    //根据角色ID查询详情
    public SysRoleDto getRoleInfoById(String roleId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select r.id, r.role_code, r.role_name, r.org_id,r.remark,r.create_time ");
        sql.append(" from sys_role r ");
        sql.append(" where r.status = '1' and r.id = :roleId ");

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("roleId", roleId);
        return queryOne(sql.toString(), paramMap, SysRoleDto.class);
    }

    public SysRole getSampleByName(String name) {
        return repository.getRoleByName(name);
    }

    public SysRole getRoleByCode(String code) {
        return repository.getRoleByCode(code);
    }

    public void delById(String id) {
        repository.updateStatusById(id, "3");
    }
    
}
