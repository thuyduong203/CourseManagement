package com.liam.be.core.classes.service.impl;

import com.liam.be.core.classes.model.*;
import com.liam.be.core.classes.repository.ClassesRepository;
import com.liam.be.core.classes.repository.PointClassRepository;
import com.liam.be.core.classes.service.ClassesService;
import com.liam.be.entity.*;
import com.liam.be.infrastructure.EmailServiceImpl;
import com.liam.be.infrastructure.constant.RoleUser;
import com.liam.be.infrastructure.constant.StatusEntity;
import com.liam.be.infrastructure.util.DateUtil;
import com.liam.be.infrastructure.util.UserUtil;
import com.liam.be.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Service
public class ClassesServiceImpl implements ClassesService {
    @Autowired
    private ClassesRepository classesRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private PointClassRepository pointRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private LessonClassRepository lessonClassRepository;
    @Autowired
    private EmailServiceImpl emailService;

    @Override
    public Page<ClassesResponse> getAllClasses(Integer page, Integer size, String searchText) {
        Pageable pageable = PageRequest.of(page, size);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByCode(principal.toString().toLowerCase()).orElse(null);
        assert user != null;
        if (user.getAccount().getRole().equals(RoleUser.STUDENT)) {
            return classesRepository.getPageClassStudent(pageable, "%" + searchText + "%", user.getId());
        } else if (user.getAccount().getRole().equals(RoleUser.TEACHER)) {
            return classesRepository.getPageClassTeacher(pageable, "%" + searchText + "%", user.getId());
        } else {
            return classesRepository.getPageClass(pageable, "%" + searchText + "%");
        }
    }

    @Override
    public ClassesRequest addClasses(ClassesRequest request) {
        Course course = courseRepository.findById(request.getCourseId()).orElseThrow();
        User teacher = userRepository.findById(request.getTeacherId()).orElseThrow();
        LocalDateTime timeStart = DateUtil.stringToDate(request.getTimeStart());
        String textCode = genCodeClasses(timeStart);
        Integer maxCode = getMaxCode(textCode);
        Classes classes = new Classes();
        classes.setCourse(course);
        classes.setMaxStudent(request.getMaxStudent());
        classes.setTimeStart(timeStart);
        classes.setStatus(StatusEntity.HOAT_DONG);
        classes.setCode(textCode + (maxCode));
        classes.setTeacher(teacher);
        Set<Schedule> schedules = new HashSet<>();
        for (String day : request.getDayOfWeek()) {
            Schedule schedule = new Schedule(classes,
                    DayOfWeek.valueOf(day),
                    request.getStudy(),
                    timeStart.with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(day)))
                            .toLocalDate()
                            .atTime(getStudyStartTime(request.getStudy())));
            schedules.add(schedule);
        }
        classesRepository.save(classes);
        scheduleRepository.saveAll(schedules);

//        StringBuilder sb = new StringBuilder();
//        Arrays.sort(request.getDayOfWeek(), (day1, day2) -> {
//            DayOfWeek dayOfWeek1 = DayOfWeek.valueOf(day1.toUpperCase());
//            DayOfWeek dayOfWeek2 = DayOfWeek.valueOf(day2.toUpperCase());
//            return dayOfWeek1.compareTo(dayOfWeek2);
//        });
//        for (int i = 0; i < request.getDayOfWeek().length; i++) {
//            sb.append(request.getDayOfWeek()[i]);
//            if (i < request.getDayOfWeek().length - 1) {
//                sb.append(",");
//            }
//        }
//        classes.setDays(sb.toString());
//        classes.setStudy(request.getStudy());
//        Set<Schedule> schedules = new HashSet<>();
//        Integer totalLesson = courseRepository.totalLesson(course.getId());
//        if (totalLesson != null) {
//            while (totalLesson >= 0) {
//                for (String day : request.getDayOfWeek()) {
//                    Schedule schedule = new Schedule(classes,
//                            DayOfWeek.valueOf(day),
//                            request.getStudy(),
//                            timeStart.with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(day)))
//                                    .toLocalDate()
//                                    .atTime(getStudyStartTime(request.getStudy())));
//                    schedules.add(schedule);
//                    timeStart = timeStart.with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(day)));
//                    totalLesson--;
//                }
//            }
//        }
//        scheduleRepository.saveAll(schedules);
//        classesRepository.save(classes);
        return request;
    }


    @Override
    public ClassesResponse getClassesId(Long id) {
        return classesRepository.getClassById(id).orElseThrow();
    }

    @Override
    public Boolean removeClasses(Long id) {
        classesRepository.deleteById(id);
        return true;
    }

    @Override
    public ClassesRequest updateClasses(ClassesRequest request) {
        Course course = courseRepository.findById(request.getCourseId()).orElseThrow();
        User teacher = userRepository.findById(request.getTeacherId()).orElseThrow();
        LocalDateTime timeStart = DateUtil.stringToDate(request.getTimeStart());
        Classes classes = classesRepository.findById(request.getId()).orElseThrow();
        classes.setCourse(course);
        classes.setMaxStudent(request.getMaxStudent());
        classes.setTimeStart(timeStart);
        classes.setTeacher(teacher);
        if (request.getStatus() != null) {
            classes.setStatus(StatusEntity.valueOf(request.getStatus()));
        }
        Set<Schedule> schedules = scheduleRepository.findAllByClassesIdAndTimeStudyAfter(request.getId(), LocalDateTime.now());

        scheduleRepository.deleteAll(schedules);
        schedules.clear();
        for (String day : request.getDayOfWeek()) {
            Schedule schedule = new Schedule(classes,
                    DayOfWeek.valueOf(day),
                    request.getStudy(),
                    LocalDateTime.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(day)))
                            .toLocalDate()
                            .atTime(getStudyStartTime(request.getStudy())));
            schedules.add(schedule);
        }
        scheduleRepository.saveAll(schedules);
        classesRepository.save(classes);
        return request;
//        StringBuilder sb = new StringBuilder();
//        Arrays.sort(request.getDayOfWeek(), (day1, day2) -> {
//            DayOfWeek dayOfWeek1 = DayOfWeek.valueOf(day1.toUpperCase());
//            DayOfWeek dayOfWeek2 = DayOfWeek.valueOf(day2.toUpperCase());
//            return dayOfWeek1.compareTo(dayOfWeek2);
//        });
//        System.out.println(request.getDayOfWeek());
//        for (int i = 0; i < request.getDayOfWeek().length; i++) {
//            sb.append(request.getDayOfWeek()[i]);
//            if (i < request.getDayOfWeek().length - 1) {
//                sb.append(",");
//            }
//        }
//        classes.setDays(sb.toString());
//        classes.setStudy(request.getStudy());
//        Set<Schedule> schedules = scheduleRepository.findAllByClassesIdAndTimeStudyAfter(request.getId(), LocalDateTime.now());
//        Set<Schedule> schedulesBefore = scheduleRepository.findAllByClassesIdAndTimeStudyBefore(request.getId(), LocalDateTime.now());
//        scheduleRepository.deleteAll(schedules);
//        int totalLesson = courseRepository.totalLesson(course.getId()) - schedulesBefore.size();
//        schedules.clear();
//        while (totalLesson >= 0) {
//            for (String day : request.getDayOfWeek()) {
//                Schedule schedule = new Schedule(classes,
//                        DayOfWeek.valueOf(day),
//                        request.getStudy(),
//                        LocalDateTime.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(day)))
//                                .toLocalDate()
//                                .atTime(getStudyStartTime(request.getStudy())));
//                schedules.add(schedule);
//                timeStart = timeStart.with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(day)));
//                totalLesson--;
//            }
//        }
//
//        scheduleRepository.saveAll(schedules);
//        classesRepository.save(classes);
//        return request;
    }

    @Override
    public Map<String, Object> getStudentInClass(String searchText, String code) {
        Map<String, Object> map = new HashMap<>();
        map.put("students", classesRepository.getStudentInClass(searchText, code));
        map.put("teacher", classesRepository.findByCode(code).orElseThrow().getTeacher().getName());
        return map;
    }

    @Override
    public List<ClassStudentResponse> getStudentClass(String searchText, String code) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByCode(principal.toString().toLowerCase()).orElse(null);
        assert user != null;
        if (user.getAccount().getRole().equals(RoleUser.STUDENT)){
            return classesRepository.getStudentClassRoleStudent("",code, user.getId());
        }else {
            return classesRepository.getStudentClass(searchText, code);
        }
    }

    @Override
    public Map<String, Object> getAllScheduleClasses(String code, Integer page, Integer size) {
//        Pageable pageable = PageRequest.of(page, size);
//        LocalDateTime dateStart = LocalDateTime.now();
        Map<String, Object> maps = new HashMap<>();
        maps.put("timeStudy", classesRepository.getClassScheduleByCode(code).orElse(null));
//        if (searchText > 0) {
//            LocalDateTime date = dateStart.plusDays();
//            maps.put("schedule", classesRepository.getScheduleClass(pageable, dateStart, date, code));
//        } else if (searchText < 0) {
//            LocalDateTime date = dateStart.plusDays();
//            maps.put("schedule", classesRepository.getScheduleClassHistory(pageable, dateStart, date, code));
//        } else {
//            maps.put("schedule", classesRepository.getScheduleClassAll(pageable, code));
//        }
        return maps;
    }

    @Override
    public ChangeStatusRequest changeStatus(ChangeStatusRequest request) {
        if (request.getId() == null) {
            LessonClass lessonClass = new LessonClass();
            Lesson lesson = lessonRepository.findById(request.getIdLesson()).orElseThrow();
            Classes classes = classesRepository.findById(request.getIdClass()).orElseThrow();
            lessonClass.setClasses(classes);
            lessonClass.setStatus(request.getStatus());
            lessonClass.setLesson(lesson);
            lessonClassRepository.save(lessonClass);
        } else {
            LessonClass lessonClass = lessonClassRepository.findById(request.getId()).orElseThrow();
            lessonClass.setStatus(request.getStatus());
            lessonClassRepository.save(lessonClass);
        }
        return request;
    }

    @Override
    public Boolean addStudentClass(String codeClass, List<Long> listIdStudent) {
        Classes classes = classesRepository.findByCode(codeClass).orElseThrow();
        Set<User> students = new HashSet<>();
        for (Long idStudent : listIdStudent) {
            User user = userRepository.findById(idStudent).orElseThrow();
            if (user.getAccount() == null) {
                String password = UserUtil.randomPassword(8);
                Account account = new Account();
                account.setRole(RoleUser.STUDENT);
                account.setPassword(passwordEncoder.encode(password));
                user.setAccount(account);
                user.getCourses().remove(classes.getCourse());
                emailService.sendThamGiaLop(user.getEmail(),
                        user.getName(),
                        user.getCode(),
                        password,
                        classes.getCourse().getName(),
                        classes.getCode(),
                        DateUtil.dateToString(classes.getTimeStart()));
            }
            students.add(user);
        }
        userRepository.saveAll(students);
        classes.setStudents(students);
        classesRepository.save(classes);
        Course course = classes.getCourse();
        course.getCourseStudents().removeAll(students);
        courseRepository.save(course);
        return true;
    }

    @Override
    public List<ClassLessonResponse> getAllLessonClasses(String code) {
        return classesRepository.getAllLesson(code);
    }

    @Override
    public Boolean addPoint(AddPointRequest request) {
        Classes classes = classesRepository.findByCode(request.getCodeClass()).orElseThrow();
        Lesson lesson = new Lesson();
        lesson.setId(request.getLessonId());
        User student = new User();
        student.setId(request.getStudentId());
        Point point = pointRepository.findByClassesIdAndLessonIdAndStudentId(classes.getId(), lesson.getId(), student.getId())
                .orElse(new Point(lesson, classes, student, request.getPoint()));
        point.setPoints(request.getPoint());
        pointRepository.save(point);
        return true;
    }

    private String genCodeClasses(LocalDateTime date) {
        String year = String.valueOf(date.getYear()).substring(2);
        int month = date.getMonthValue();
        String codeGen = "";
        if (month <= 3) {
            codeGen = "SP";
        } else if (month <= 6) {
            codeGen = "SU";
        } else if (month <= 9) {
            codeGen = "SU";
        } else {
            codeGen = "WI";
        }
        return codeGen + year;
    }

    private Integer getMaxCode(String codeText) {
        Integer code = classesRepository.getMaxCode(codeText + "%");
        if (code != null) {
            return code + 1;
        }
        return 1;
    }

    private LocalTime getStudyStartTime(Integer study) {
        return switch (study) {
            case 1 -> LocalTime.of(7, 15);
            case 2 -> LocalTime.of(9, 20);
            case 3 -> LocalTime.of(12, 0);
            case 4 -> LocalTime.of(14, 10);
            case 5 -> LocalTime.of(16, 20);
            default -> LocalTime.of(18, 30);
        };
    }

}
