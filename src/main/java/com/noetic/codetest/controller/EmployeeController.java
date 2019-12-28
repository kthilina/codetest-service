package com.noetic.codetest.controller;

import com.noetic.codetest.domain.dto.EmployeeDto;
import com.noetic.codetest.service.EmployeeService;
import com.noetic.codetest.util.CodetestUri;
import com.noetic.codetest.util.exception.RecordAlreadyExistsException;
import com.noetic.codetest.util.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Kasun Thilina
 * Date    : 12/28/2019
 * Project : codetest
 **/
@RestController
@RequestMapping(CodetestUri.EMPLOYEE_HEAD)
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EmployeeDto employeeDto) throws RecordNotFoundException, RecordAlreadyExistsException {
        return ResponseEntity.ok(employeeService.create(employeeDto));
    }

    @GetMapping(CodetestUri.EMPLOYEE_FIND_BY_ID)
    public ResponseEntity<?> findById(@PathVariable Long id) throws RecordNotFoundException {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @DeleteMapping(CodetestUri.EMPLOYEE_DELETE)
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(employeeService.delete(id));
    }

    @PutMapping(CodetestUri.EMPLOYEE_UPDATE)
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody EmployeeDto employeeDto) throws RecordNotFoundException {
        return ResponseEntity.ok(employeeService.update(id, employeeDto));
    }
}
