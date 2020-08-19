package com.java.moudle.tripartdock.apparatus.dao;

import com.java.moudle.tripartdock.apparatus.dao.repository.ApparatusRepository;
import com.java.moudle.tripartdock.apparatus.domain.Apparatus;
import com.java.moudle.tripartdock.apparatus.dto.ApparatusDto;
import com.java.until.StringUtils;
import com.java.until.dba.BaseDao;

import javax.inject.Named;
import java.util.List;

@Named
public class ApparatusDao extends BaseDao<ApparatusRepository, Apparatus> {

    //检验仪器分页
    public void getApparatusPage(ApparatusDto dto) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.* ");
        sql.append(" from sys_exam_apparatus a ");
        sql.append(" where 1=1 ");
        if (StringUtils.isNotBlank(dto.getOrgId())) {
            sql.append(" and a.org_id = :orgId ");
        }
        if (StringUtils.isNotBlank(dto.getOrgCode())){
            sql.append(" and a.org_code like concat(:orgCode ,'%') ");
        }
        if (StringUtils.isNotBlank(dto.getName())) {
            sql.append(" and a.name like concat('%', concat(:name ,'%')) ");
        }
        if (StringUtils.isNotBlank(dto.getModel())) {
            sql.append(" and a.model like concat('%', concat(:model ,'%')) ");
        }

        queryPageList(sql.toString(), dto, dto.getPage(), ApparatusDto.class);
    }

    //检验仪器列表
    public List<ApparatusDto> getApparatusList(ApparatusDto dto) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.* ");
        sql.append(" from sys_exam_apparatus a ");
        sql.append(" where 1=1 ");
        if (StringUtils.isNotBlank(dto.getOrgId())) {
            sql.append(" and a.org_id = :orgId ");
        }
        if (StringUtils.isNotBlank(dto.getOrgCode())){
            sql.append(" and a.org_code like concat(:orgCode ,'%') ");
        }
        if (StringUtils.isNotBlank(dto.getName())) {
            sql.append(" and a.name like concat('%', concat(:name ,'%')) ");
        }
        if (StringUtils.isNotBlank(dto.getModel())) {
            sql.append(" and a.model like concat('%', concat(:model ,'%')) ");
        }

        return queryList(sql.toString(), dto, ApparatusDto.class);
    }
}
