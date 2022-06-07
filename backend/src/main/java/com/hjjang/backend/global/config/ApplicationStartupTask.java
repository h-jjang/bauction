package com.hjjang.backend.global.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.hjjang.backend.domain.university.entity.University;
import com.hjjang.backend.domain.university.repository.UniversityRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApplicationStartupTask implements ApplicationListener<ApplicationReadyEvent> {
	private final UniversityRepository universityRepository;
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		if (!universityRepository.existsById(1L)){
			universityRepository.save(new University("empty", "empty"));
		}
		if (!universityRepository.existsById(2L)){
			universityRepository.save(new University("tukorea", "tukorea.ac.kr"));
		}
	}
}
