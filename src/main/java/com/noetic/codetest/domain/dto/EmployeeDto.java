package com.noetic.codetest.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Kasun Thilina
 * Date    : 12/28/2019
 * Project : codetest
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String employeeId;
    private Long departmentId;

    public EmployeeDto(String firstName, String lastName, String employeeId, Long departmentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeId = employeeId;
        this.departmentId = departmentId;
    }
}
