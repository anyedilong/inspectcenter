package com.java.moudle.tripartdock.dict.service.impl;

import com.java.moudle.common.message.JsonResult;
import com.java.moudle.tripartdock.apparatus.dto.ApparatusDto;
import com.java.moudle.tripartdock.dict.dao.SampleDao;
import com.java.moudle.tripartdock.dict.domain.Sample;
import com.java.moudle.tripartdock.dict.service.SampleService;
import com.java.until.StringUtils;
import com.java.until.UUIDUtil;
import com.java.until.dba.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SampleServiceImpl implements SampleService {

    @Autowired
    private SampleDao sampleDao;

    //查询标本类型分页
    @Override
    public void getSamplePage(ApparatusDto dto) {
        sampleDao.getSamplePage(dto);
    }

    //查询标本列表
    @Override
    public List<Sample> getSampleList() {
        return sampleDao.getSampleList();
    }

    //保存标本类型
    @Override
    public JsonResult saveSample(Sample sample) {
        Sample query = sampleDao.getSampleByName(sample.getName());
        if (StringUtils.isBlank(sample.getId())) {
            if (query != null)
                return new JsonResult(null, 9001, "标本类型重复，请重新填写。");
            sample.setId(UUIDUtil.getUUID());
            sample.setCreateDate(new Date());
        } else if (query != null && !sample.getId().equals(query.getId()) && sample.getName().equals(query.getName()))
            return new JsonResult(null, 9001, "标本类型重复，请重新填写。");

        sampleDao.save(sample);
        return new JsonResult();
    }

    //根据ID查询标本类型
    @Override
    public Sample getSample(String id) {
        return sampleDao.get(id);
    }

    //根据ID删除
    @Override
    public void delById(String id) {
        sampleDao.delById(id);
    }

}
