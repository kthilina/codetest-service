package com.noetic.codetest.service;

import com.noetic.codetest.domain.dto.DepartmentDto;
import com.noetic.codetest.util.CodetestResponse;
import com.noetic.codetest.util.exception.RecordAlreadyExistsException;

/**
 * @author : Kasun Thilina
 * Date    : 12/28/2019
 * Project : codetest
 **/
public interface DepartmentService {
    /**
     * @param departmentDto
     * @return
     * @throws ClassNotFoundException
     */
    CodetestResponse<DepartmentDto, String> create(DepartmentDto departmentDto) throws RecordAlreadyExistsException;
}
