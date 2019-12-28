package com.noetic.codetest.service.impl;

import com.noetic.codetest.domain.dto.DepartmentDto;
import com.noetic.codetest.domain.model.Department;
import com.noetic.codetest.repository.DepartmentRepository;
import com.noetic.codetest.service.DepartmentService;
import com.noetic.codetest.util.CodetestMapper;
import com.noetic.codetest.util.CodetestResponse;
import com.noetic.codetest.util.CodetestResponseMapper;
import com.noetic.codetest.util.exception.RecordAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : Kasun Thilina
 * Date    : 12/28/2019
 * Project : codetest
 **/
@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    @Transactional
    public CodetestResponse<DepartmentDto, String> create(DepartmentDto departmentDto) throws RecordAlreadyExistsException {
        CodetestResponse<DepartmentDto, String> response = new CodetestResponse<>();
        departmentRepository.findDepartmentByDepartmentId(departmentDto.getDepartmentId()).ifPresent(val -> {
            throw new RecordAlreadyExistsException("Department already exist for this id :: " + departmentDto.getDepartmentId());
        });

        Department department = new Department();
        CodetestMapper.departmentMapper(departmentDto, department);
        CodetestMapper.departmentMapper(departmentRepository.save(department), departmentDto);

        CodetestResponseMapper.build(response, 201, departmentDto, "Department " + departmentDto.getId() + " created successfully");
        log.info(response.getMessage());
        return response;
    }
}
