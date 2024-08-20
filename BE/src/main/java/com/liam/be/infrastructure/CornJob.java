package com.liam.be.infrastructure;

import com.liam.be.entity.Schedule;
import com.liam.be.infrastructure.util.DateUtil;
import com.liam.be.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Component
public class CornJob {
//    @Autowired
//    private ScheduleRepository scheduleRepository;
//    @Scheduled(cron = "* * * * * *")
//    public void scheduleTaskWithFixedRate() {
//        // Lấy ra ngày hôm qua
//        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
//
//        // Đặt thời gian bắt đầu của ngày hôm qua là 00:00:00
//        LocalDateTime startOfYesterday = yesterday.with(LocalTime.MIN);
//
//        // Đặt thời gian kết thúc của ngày hôm qua là 23:59:59
//        LocalDateTime endOfYesterday = yesterday.with(LocalTime.MAX);
//
//        Set<Schedule> schedulesFromYesterday = scheduleRepository.findAllByTimeStudyBetween(startOfYesterday, endOfYesterday);
//
//
//    }
}
