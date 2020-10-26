package com.nice.assignment.metro.repository;

import com.nice.assignment.metro.entity.PassengerCount;
import com.nice.assignment.metro.entity.MonthlyMetroPassengerInfo;
import com.nice.assignment.metro.entity.PassengerDiffrentCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MonthlyPassengerInfoRepository extends JpaRepository<MonthlyMetroPassengerInfo, Long> {

    @Query("SELECT " +
            "new com.nice.assignment.metro.entity.PassengerCount(m.stationName, sum(m.passengerCount)) " +
            "FROM " +
            "MonthlyMetroPassengerInfo m where m.year = :year group by m.stationName")
    List<PassengerCount> getYearlyCount(Long year);


    @Query( "SELECT " +
            "new com.nice.assignment.metro.entity.PassengerDiffrentCount(m.stationName, max(m.passengerCount) , min (m.passengerCount)) " +
            "FROM " +
            "MonthlyMetroPassengerInfo m where m.year = :year  group by m.stationName")
    List<PassengerDiffrentCount> getDiffMaxAndMin(Long year);

}
