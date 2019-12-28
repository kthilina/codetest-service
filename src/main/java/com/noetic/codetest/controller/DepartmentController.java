package com.noetic.codetest.controller;

import com.noetic.codetest.domain.dto.DepartmentDto;
import com.noetic.codetest.service.DepartmentService;
import com.noetic.codetest.util.CodetestUri;
import com.noetic.codetest.util.exception.RecordAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Kasun Thilina
 * Date    : 12/28/2019
 * Project : codetest
 **/
@RestController
@RequestMapping(CodetestUri.DEPARTMENT_HEAD)
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    /**
     * Create new department according to the requested details.
     *
     * @param departmentDto
     * @return
     * @throws ClassNotFoundException
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody DepartmentDto departmentDto) throws RecordAlreadyExistsException {
        return new ResponseEntity<>(departmentService.create(departmentDto), HttpStatus.CREATED);
    }
}
