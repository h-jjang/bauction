package com.hjjang.backend.domain.university.service;

import org.springframework.stereotype.Service;

import com.hjjang.backend.domain.university.entity.University;
import com.hjjang.backend.domain.university.repository.UniversityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UniversityService {
	private final UniversityRepository universityRepository;

	public University findUniversityByName(String name) {
		return universityRepository.findByName(name).orElseThrow(RuntimeException::new);
	}
}
