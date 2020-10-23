package com.nice.assignment.repository;

import com.nice.assignment.entity.MetroPassengerCount;
import com.nice.assignment.entity.MonthlyMetroPassengerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthlyPassengerInfoRepository extends JpaRepository<MonthlyMetroPassengerInfo, Long> {

    @Query("SELECT " +
            "new com.nice.assignment.entity.MetroPassengerCount(m.stationName, sum(m.passengerCount)) " +
            "FROM " +
            "MonthlyMetroPassengerInfo m where m.year = :year group by m.stationName")
    List<MetroPassengerCount> getYearlyCount(Long year);

}
