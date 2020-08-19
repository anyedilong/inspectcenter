package com.java.moudle.tripartdock.apparatus.dao.repository;

import com.java.moudle.tripartdock.apparatus.domain.Apparatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApparatusRepository extends JpaRepository<Apparatus, String> {
}
