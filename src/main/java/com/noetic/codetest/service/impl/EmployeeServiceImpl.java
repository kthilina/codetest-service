package com.noetic.codetest.service.impl;

import com.noetic.codetest.domain.dto.EmployeeDto;
import com.noetic.codetest.domain.model.Department;
import com.noetic.codetest.domain.model.Employee;
import com.noetic.codetest.repository.DepartmentRepository;
import com.noetic.codetest.repository.EmployeeRepository;
import com.noetic.codetest.service.EmployeeService;
import com.noetic.codetest.util.CodetestMapper;
import com.noetic.codetest.util.CodetestResponse;
import com.noetic.codetest.util.CodetestResponseMapper;
import com.noetic.codetest.util.exception.RecordAlreadyExistsException;
import com.noetic.codetest.util.exception.RecordNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Kasun Thilina
 * Date    : 12/28/2019
 * Project : codetest
 **/
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    @Transactional
    public CodetestResponse<EmployeeDto, String> create(EmployeeDto employeeDto) throws RecordNotFoundException, RecordAlreadyExistsException {
        CodetestResponse<EmployeeDto, String> response = new CodetestResponse<>();
        Department department = departmentRepository.findById(employeeDto.getDepartmentId()).orElseThrow(() ->
                new RecordNotFoundException("Department not found for this id :: " + employeeDto.getDepartmentId())
        );
        employeeRepository.findEmployeeByEmployeeId(employeeDto.getEmployeeId()).ifPresent(val -> {
            throw new RecordAlreadyExistsException("Employee already exist for this id :: " + employeeDto.getEmployeeId());
        });

        Employee employee = new Employee();
        CodetestMapper.employeeMapper(employeeDto, employee);
        employee.setDepartmentId(department);
        CodetestMapper.employeeMapper(employeeRepository.save(employee), employeeDto);

        CodetestResponseMapper.build(response, 201, employeeDto, "Employee " + employeeDto.getId() + " created successfully");
        log.info(response.getMessage());
        return response;
    }

    @Override
    public CodetestResponse<EmployeeDto, String> findById(Long id) throws RecordNotFoundException {
        CodetestResponse<EmployeeDto, String> response = new CodetestResponse<>();
        EmployeeDto employeeDto = new EmployeeDto();
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException("Employee not found for this id :: " + id)
        );
        CodetestMapper.employeeMapper(employee, employeeDto);

        CodetestResponseMapper.build(response, 200, employeeDto, "Operation success.");
        log.info(response.getMessage());
        return response;
    }

    @Override
    public CodetestResponse<List<EmployeeDto>, String> findAll() {
        CodetestResponse<List<EmployeeDto>, String> response = new CodetestResponse<>();
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        employeeRepository.findAll().stream().forEach(employee -> {
            EmployeeDto employeeDto = new EmployeeDto();
            CodetestMapper.employeeMapper(employee, employeeDto);
            employeeDtos.add(employeeDto);
        });

        CodetestResponseMapper.build(response, 200, employeeDtos, "Operation success.");
        log.info(response.getMessage());
        return response;
    }

    @Override
    @Transactional
    public CodetestResponse<EmployeeDto, String> update(Long id, EmployeeDto employeeDto) throws RecordNotFoundException {
        CodetestResponse<EmployeeDto, String> response = new CodetestResponse<>();
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException("Employee not found for this id :: " + id)
        );
        CodetestMapper.employeeMapper(employeeDto, employee);
        CodetestMapper.employeeMapper(employeeRepository.save(employee), employeeDto);

        CodetestResponseMapper.build(response, 200, employeeDto, "Employee " + employeeDto.getId() + " update successfully");
        log.info(response.getMessage());
        return response;
    }

    @Override
    @Transactional
    public CodetestResponse<Object, String> delete(Long id) {
        CodetestResponse<Object, String> response = new CodetestResponse<>();
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException("Employee not found for this id :: " + id)
        );
        employeeRepository.delete(employee);

        CodetestResponseMapper.build(response, 200, null, "Employee " + id + " deleted successfully");
        log.info(response.getMessage());
        return response;
    }
}
