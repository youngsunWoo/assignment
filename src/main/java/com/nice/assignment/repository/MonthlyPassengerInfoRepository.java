package com.nice.assignment.repository;

import com.nice.assignment.entity.MonthlyPassengerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyPassengerInfoRepository extends JpaRepository<MonthlyPassengerInfo, Long> {
}
