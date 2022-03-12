package com.project.techroadmap.controller;

import com.project.techroadmap.dto.CareerDto;
import com.project.techroadmap.dto.CareerRequestDto;
import com.project.techroadmap.dto.ResponseDto;
import com.project.techroadmap.entity.Career;
import com.project.techroadmap.service.CareerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CareeerController {
    private final CareerService careerService;

    @PostMapping(value = "/api/v1/careers/new")
    public ResponseDto<CareerDto> create (@RequestBody CareerRequestDto requestDto) {

        Career career = careerService.create(Career.builder()
                .careerName(requestDto.getCareerName())
                .build());

        return new ResponseDto<>(new CareerDto(career.getId(), career.getCareerName()));
    }

    @GetMapping(value = "/api/v1/careers")
    public ResponseDto<List<CareerDto>> careers () {

        List<Career> findCareers = careerService.findCareers();

        List<CareerDto> collect = findCareers.stream()
                .map(c -> new CareerDto(c.getId(), c.getCareerName()))
                .collect(Collectors.toList());
        return new ResponseDto<>(collect);
    }
}
