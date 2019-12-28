package com.noetic.codetest.domain.dto;

import com.noetic.codetest.domain.model.Employee;
import lombok.Data;

import java.util.List;

/**
 * @author : Kasun Thilina
 * Date    : 12/28/2019
 * Project : codetest
 **/
@Data
public class DepartmentDto {
    private Long id;
    private String name;
    private String departmentId;
    private List<Employee> employeeList;
}
