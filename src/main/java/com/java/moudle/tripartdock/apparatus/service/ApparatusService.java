package com.java.moudle.tripartdock.apparatus.service;

import com.java.moudle.tripartdock.apparatus.dto.ApparatusDto;

import java.util.List;

public interface ApparatusService {

    void getApparatusPage(ApparatusDto dto);

    List<ApparatusDto> getApparatusList(ApparatusDto dto);

}
