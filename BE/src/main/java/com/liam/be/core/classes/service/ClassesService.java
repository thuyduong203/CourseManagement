package com.liam.be.core.classes.service;

import com.liam.be.core.classes.model.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ClassesService {
    Page<ClassesResponse> getAllClasses(Integer page, Integer size, String searchText);

    ClassesRequest addClasses(ClassesRequest request);

    ClassesResponse getClassesId(Long id);

    Boolean removeClasses(Long id);

    ClassesRequest updateClasses(ClassesRequest request);

    Map<String, Object> getStudentInClass(String searchText, String code);

    Boolean addStudentClass(String codeClass, List<Long> listIdStudent);

    List<ClassLessonResponse> getAllLessonClasses(String code);

    Boolean addPoint(AddPointRequest request);

    List<ClassStudentResponse> getStudentClass(String searchText, String code);

    Map<String, Object> getAllScheduleClasses(String code,Integer page, Integer size);

    ChangeStatusRequest changeStatus(ChangeStatusRequest request);
}
