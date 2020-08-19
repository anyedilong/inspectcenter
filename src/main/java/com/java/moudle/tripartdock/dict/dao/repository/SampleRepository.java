package com.java.moudle.tripartdock.dict.dao.repository;

import com.java.moudle.tripartdock.dict.domain.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SampleRepository extends JpaRepository<Sample, String> {
    //根据名称查询标本类型
    @Query("from Sample where  name = :name")
    Sample getSampleByName(String name);

    //根据ID删除标本类型
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query("delete from Sample where id = :id")
    void delById(String id);
}
