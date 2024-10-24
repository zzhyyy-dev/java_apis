package com.example.vritual.repository;

import com.example.vritual.entities.SchoolClass;
import com.example.vritual.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {
    List<SchoolClass> findByTeacher(Teacher teacher);
}
