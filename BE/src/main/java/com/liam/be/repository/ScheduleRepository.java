package com.liam.be.repository;

import com.liam.be.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Set<Schedule> findAllByClassesIdAndTimeStudyAfter(Long classId, LocalDateTime dateTime);
    Set<Schedule> findAllByClassesIdAndTimeStudyBefore(Long classId, LocalDateTime dateTime);

    Set<Schedule> findAllByTimeStudyBetween(LocalDateTime startYesterday, LocalDateTime endYesterday);
}
