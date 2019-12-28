package com.noetic.codetest.service;

import com.noetic.codetest.domain.dto.EmployeeDto;
import com.noetic.codetest.util.CodetestResponse;
import com.noetic.codetest.util.exception.RecordAlreadyExistsException;
import com.noetic.codetest.util.exception.RecordNotFoundException;

import java.util.List;

/**
 * @author : Kasun Thilina
 * Date    : 12/28/2019
 * Project : codetest
 **/
public interface EmployeeService {

    CodetestResponse<EmployeeDto, String> create(EmployeeDto employeeDto) throws RecordNotFoundException, RecordAlreadyExistsException;

    CodetestResponse<EmployeeDto, String> findById(Long id) throws RecordNotFoundException;

    CodetestResponse<List<EmployeeDto>, String> findAll();

    CodetestResponse<EmployeeDto, String> update(Long id, EmployeeDto employeeDto) throws RecordNotFoundException;

    CodetestResponse<Object, String> delete(Long id);
}
