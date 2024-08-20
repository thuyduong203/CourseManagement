package com.liam.be.core.classes.repository;

import com.liam.be.entity.Point;
import com.liam.be.repository.PointRepository;

import java.util.Optional;

public interface PointClassRepository extends PointRepository {

    Optional<Point> findByClassesIdAndLessonIdAndStudentId(Long classes_id, Long lesson_id, Long student_id);
}
