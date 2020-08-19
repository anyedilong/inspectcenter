package com.java.moudle.tripartdock.dict.service;

import com.java.moudle.common.message.JsonResult;
import com.java.moudle.tripartdock.apparatus.dto.ApparatusDto;
import com.java.moudle.tripartdock.dict.domain.Sample;

import java.util.List;

public interface SampleService {

    void getSamplePage(ApparatusDto dto);

    List<Sample> getSampleList();

    JsonResult saveSample(Sample sample);

    Sample getSample(String id);

    void delById(String id);
}
