package com.project.techroadmap.service;

import com.project.techroadmap.entity.Career;
import com.project.techroadmap.repository.CareerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CareerService {

    @Autowired
    CareerRepository careerRepository;

    @Transactional
    public Career create(Career career) {
        validateDuplicateCareer(career);

        careerRepository.save(career);

        return career;
    }

    private void validateDuplicateCareer(Career career) {
        List<Career> findCareers =
                careerRepository.findByCareerName(career.getCareerName());
        if (!findCareers.isEmpty()) {
            throw new IllegalStateException("동일한 이름의 직업이 존재합니다.");
        }
    }

    public List<Career> findCareers() {
        return careerRepository.findAll();
    }

    public Career findOne(Long careerId) {
        return careerRepository.getById(careerId);
    }
}
