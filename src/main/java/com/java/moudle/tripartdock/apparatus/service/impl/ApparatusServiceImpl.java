package com.java.moudle.tripartdock.apparatus.service.impl;

import com.java.moudle.tripartdock.apparatus.dao.ApparatusDao;
import com.java.moudle.tripartdock.apparatus.dto.ApparatusDto;
import com.java.moudle.tripartdock.apparatus.service.ApparatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ApparatusServiceImpl implements ApparatusService {

    @Autowired
    private ApparatusDao apparatusDao;

    //检验仪器分页
    @Override
    public void getApparatusPage(ApparatusDto dto) {
        apparatusDao.getApparatusPage(dto);
    }

    //检验仪器列表
    @Override
    public List<ApparatusDto> getApparatusList(ApparatusDto dto) {
        return apparatusDao.getApparatusList(dto);
    }
}
