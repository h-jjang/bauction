package com.hjjang.backend.domain.university.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hjjang.backend.domain.university.entity.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {
	Optional<University> findByName(String name);

	Boolean existsByName(String name);
}
