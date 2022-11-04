package com.example.hellospring.repository;

import com.withinapi.irs.backoffice.persistence.entity.data.TimezoneTest;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimezoneTestRepository extends JpaRepository<TimezoneTest, Long> {
    List<TimezoneTest> findAllByCreateAtBetween(LocalDateTime start, LocalDateTime end);

}
