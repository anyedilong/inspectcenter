package com.java.moudle.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import com.java.moudle.system.dao.repository.SysMenuRepository;
import com.java.moudle.system.domain.SysMenu;
import com.java.moudle.system.domain.SysUser;
import com.java.moudle.system.dto.SysMenuDto;
import com.java.until.dba.BaseDao;


@Named
public class SysMenuDao extends BaseDao<SysMenuRepository, SysMenu> {

    public void getMenuPage(SysMenuDto dto) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select m.id,m.name,mm.name parent_name,m.url,m.status,m.order_num ");
        sql.append(" from sys_menu m ");
        sql.append(" left join sys_menu mm on mm.id = m.parent_id ");
        sql.append(" where (m.status = '1' or m.status = '2') ");
        if (StringUtils.isNotBlank(dto.getName())) {
            sql.append(" and (m.name like concat('%', concat(:name ,'%'))) ");
        }
        sql.append(" order by to_number(m.order_num) ");
        queryPageList(sql.toString(), dto, dto.getPage(), SysMenuDto.class);
    }

    //查询菜单列表
    public List<SysMenuDto> getMenuList(SysMenuDto dto) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select m.id,m.name,m.url,m.status,m.order_num ");
        sql.append(" from sys_menu m ");
        sql.append(" where m.status = '1' ");
        if (StringUtils.isNotBlank(dto.getMenuLevel())) {
            sql.append(" and m.menu_level = :menuLevel ");
        }
        if (StringUtils.isNotBlank(dto.getParentId())) {
            sql.append(" and m.parent_id = :parentId ");
        }
        sql.append(" order by to_number(m.order_num)  ");
        return queryList(sql.toString(), dto, SysMenuDto.class);
    }

    //查询用户菜单列表
    public List<SysMenuDto> getMenuTree(SysMenuDto dto) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select distinct(m.id),m.name,m.url,m.status,m.order_num ");
        sql.append(" from sys_menu m ");
        sql.append(" join sys_menu_role mr on mr.menu_id = m.id ");
        sql.append(" where m.status = '1' ");
        if (StringUtils.isNotBlank(dto.getMenuLevel())) {
            sql.append(" and m.menu_level = :menuLevel ");
        }
        if (StringUtils.isNotBlank(dto.getParentId())) {
            sql.append(" and m.parent_id = :parentId ");
        }
        if (StringUtils.isNotBlank(dto.getRoleId())) {
            sql.append(" and mr.role_id = :roleId ");
        }
        sql.append(" order by to_number(m.order_num)  ");
        return queryList(sql.toString(), dto, SysMenuDto.class);
    }


    public SysMenu getSysMenuByName(String id, String name, String orderNum) {
        Map<String, Object> paramMap = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append(" select m.* ");
        sql.append(" from sys_menu m");
        sql.append(" where (m.status = '1' or m.status = '2')  ");
        if (StringUtils.isNotBlank(id)) {
            sql.append(" and m.id != :id ");
            paramMap.put("id", id);
        }
        if (StringUtils.isNotBlank(name)) {
            sql.append(" and m.name = :name ");
            paramMap.put("name", name);
        }
        if (StringUtils.isNotBlank(orderNum)) {
            sql.append(" and m.order_num = :orderNum ");
            paramMap.put("orderNum", orderNum);
        }
        return queryOne(sql.toString(), paramMap, SysMenu.class);
    }

    public void updateStatusById(String status, String id) {
        repository.updateStatusById(status, id);
    }

    public void delById(String id) {
        repository.delById(id);
    }
}
