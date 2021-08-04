package com.tenniscourts.schedules;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("select * from schedule where s.tennisCourtId order by StartDateTime")
    List<Schedule> findByTennisCourt_IdOrderByStartDateTime(Long id);

    @Query("select * from schedule s where s.endDateTime<(:endDate) and s.startDate > (:startDate)")
    List<Schedule> findSchedulesBetweenDates( LocalDateTime startDate, LocalDateTime endDate);
}