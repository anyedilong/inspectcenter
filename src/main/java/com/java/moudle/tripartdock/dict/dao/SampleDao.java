package com.java.moudle.tripartdock.dict.dao;

import com.java.moudle.tripartdock.apparatus.dto.ApparatusDto;
import com.java.moudle.tripartdock.dict.dao.repository.SampleRepository;
import com.java.moudle.tripartdock.dict.domain.Sample;
import com.java.until.StringUtils;
import com.java.until.dba.BaseDao;
import com.java.until.dba.PageModel;


import javax.inject.Named;
import java.util.List;

@Named
public class SampleDao extends BaseDao<SampleRepository, Sample> {

    //查询标本类型分页
    public void getSamplePage(ApparatusDto dto) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select s.* ");
        sql.append(" from sys_exam_sample s ");
        sql.append(" where 1=1 ");
        if (StringUtils.isNotBlank(dto.getName())) {
            sql.append(" and s.name like concat('%', concat(:name ,'%')) ");
        }
        if (StringUtils.isNotBlank(dto.getSpell())) {
            sql.append(" and s.spell like concat('%', concat(:spell ,'%')) ");
        }

        queryPageList(sql.toString(), dto, dto.getPage(), Sample.class);
    }

    //查询标本列表
    public List<Sample> getSampleList() {
        StringBuilder sql = new StringBuilder();
        sql.append(" select s.* ");
        sql.append(" from sys_exam_sample s ");
        sql.append(" where 1=1 ");
        return queryList(sql.toString(), null, Sample.class);
    }

    public Sample getSampleByName(String name) {
        return repository.getSampleByName(name);
    }

    public void delById(String id) {
        repository.delById(id);
    }
}
