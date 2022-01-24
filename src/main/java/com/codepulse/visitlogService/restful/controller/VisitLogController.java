package com.codepulse.visitlogService.restful.controller;

import com.codepulse.visitlogService.restful.repository.VisitLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class VisitLogController {

    private final VisitLogRepository visitLogRepository;
    private final ModelMapper modelMapper;


}
