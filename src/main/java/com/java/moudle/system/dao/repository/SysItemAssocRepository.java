package com.java.moudle.system.dao.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java.moudle.system.domain.SysItemAssoc;

public interface SysItemAssocRepository extends JpaRepository<SysItemAssoc, String> {

	
	@Transactional(rollbackOn = Exception.class)
    @Modifying
    @Query(value = "delete from sys_item_assoc r where r.assoc_id = :assocId", nativeQuery = true)
    void deleteDataByAssocId(@Param("assocId") String assocId);
	
}
