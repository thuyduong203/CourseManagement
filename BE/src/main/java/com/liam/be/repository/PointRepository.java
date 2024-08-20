package com.liam.be.repository;

import com.liam.be.entity.Lesson;
import com.liam.be.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
}
