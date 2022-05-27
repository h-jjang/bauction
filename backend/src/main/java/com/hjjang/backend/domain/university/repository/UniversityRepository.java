package com.hjjang.backend.domain.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hjjang.backend.domain.university.entity.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {
}
