package com.java.moudle.system.dao;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import com.java.moudle.system.dao.repository.SysUserRepository;
import com.java.moudle.system.domain.SysUser;
import com.java.moudle.system.dto.SysUserDto;
import com.java.until.StringUtils;
import com.java.until.dba.BaseDao;


@Named
public class SysUserDao extends BaseDao<SysUserRepository, SysUser> {

    //查询用户列表（分页）
    public void getUserPage(SysUserDto request) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select u.id,u.username,u.org_id,u.org_name,u.name,u.status, r.id role_id, r.role_name ");
        sql.append(" from sys_user u ");
        sql.append(" left join sys_role_user ru on ru.user_id = u.id ");
        sql.append(" left join sys_role r on r.id = ru.role_id and r.status = '1' ");
        sql.append(" where (u.status = '1' or u.status = '2') ");
        if (StringUtils.isNotBlank(request.getName())) {
            sql.append(" and (u.username like concat('%', concat(:name ,'%')) ");
            sql.append(" or u.name like concat('%', concat(:name ,'%'))) ");
        }
        if (StringUtils.isNotBlank(request.getOrgId())) {
            sql.append(" and u.org_id = :orgId ");
        }

        queryPageList(sql.toString(), request, request.getPage(), SysUserDto.class);
    }

    public SysUser getUserInfoByName(String username) {
        Map<String, Object> map = new HashMap<>();
        StringBuffer sql = new StringBuffer();
        sql.append(" select u.* ");
        sql.append(" from sys_user u ");
        sql.append(" where u.username = :username ");
        map.put("username", username);
        return queryOne(sql.toString(), map, SysUser.class);
    }

    public SysUser queryInfoByCon(String id, String username) {
        Map<String, Object> map = new HashMap<>();
        StringBuffer sql = new StringBuffer();
        sql.append(" select u.* ");
        sql.append(" from sys_user u ");
        sql.append(" where u.status = '1' ");
        if (!StringUtils.isNull(id)) {
            sql.append(" and u.id = :id ");
            map.put("id", id);
        }
        if (!StringUtils.isNull(username)) {
            sql.append(" and u.username = :username ");
            map.put("username", username);
        }
        return queryOne(sql.toString(), map, SysUser.class);
    }

    public SysUserDto getUserInfo(String id) {
        Map<String, Object> map = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append(" select u.id,u.username,u.org_id,u.remark,u.name, r.id role_id, r.role_name ");
        sql.append(" from sys_user u ");
        sql.append(" left join sys_role_user ru on ru.user_id = u.id ");
        sql.append(" left join sys_role r on r.id = ru.role_id ");
        sql.append(" where u.id = :id ");
        map.put("id", id);
        return queryOne(sql.toString(), map, SysUserDto.class);
    }

}
