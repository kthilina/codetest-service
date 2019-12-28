package com.noetic.codetest.util;

import com.noetic.codetest.domain.dto.DepartmentDto;
import com.noetic.codetest.domain.dto.EmployeeDto;
import com.noetic.codetest.domain.model.Department;
import com.noetic.codetest.domain.model.Employee;
import org.springframework.beans.BeanUtils;

/**
 * @author : Kasun Thilina
 * Date    : 12/28/2019
 * Project : codetest
 **/
public class CodetestMapper {
    /**
     * Map department dto data into department model
     *
     * @param departmentDto
     * @param department
     */
    public static void departmentMapper(DepartmentDto departmentDto, Department department) {
        BeanUtils.copyProperties(departmentDto, department);
    }

    /**
     * Map department model data into department dto
     *
     * @param department
     * @param departmentDto
     */
    public static void departmentMapper(Department department, DepartmentDto departmentDto) {
        BeanUtils.copyProperties(department, departmentDto);
    }

    /**
     * Map employee dto data into employee model
     *
     * @param employeeDto
     * @param employee
     */
    public static void employeeMapper(EmployeeDto employeeDto, Employee employee) {
        BeanUtils.copyProperties(employeeDto, employee);
    }

    /**
     * Map employee model data into employee dto
     *
     * @param employee
     * @param employeeDto
     */
    public static void employeeMapper(Employee employee, EmployeeDto employeeDto) {
        BeanUtils.copyProperties(employee, employeeDto);
    }
}
