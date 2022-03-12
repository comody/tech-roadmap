package com.project.techroadmap.repository;

import com.project.techroadmap.entity.Career;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CareerRepository extends JpaRepository<Career, Long> {
    List<Career> findByCareerName(String careerName);

    Optional<Career> findById(Long id);
}
