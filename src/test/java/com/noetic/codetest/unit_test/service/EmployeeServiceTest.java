package com.noetic.codetest.unit_test.service;

import com.noetic.codetest.domain.dto.EmployeeDto;
import com.noetic.codetest.domain.model.Department;
import com.noetic.codetest.domain.model.Employee;
import com.noetic.codetest.repository.DepartmentRepository;
import com.noetic.codetest.repository.EmployeeRepository;
import com.noetic.codetest.service.EmployeeService;
import com.noetic.codetest.util.CodetestResponse;
import com.noetic.codetest.util.exception.RecordAlreadyExistsException;
import com.noetic.codetest.util.exception.RecordNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @author : Kasun Thilina
 * Date    : 12/28/2019
 * Project : codetest
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeeServiceTest {
    @Autowired
    private EmployeeService employeeService;
    @MockBean
    private EmployeeRepository employeeRepository;
    @MockBean
    private DepartmentRepository departmentRepository;

    @Before
    public void init() {
        /*
        Response data
         */
        Department department = new Department(1L, "IS", "IS100", new ArrayList<>());
        Employee resFirstEmp = new Employee(1L, "Kasun", "Thilina", "A520", department);
        Employee resSecondEmp = new Employee(2L, "Manoj", "Ranga", "A521", department);

        /*
        Request data
         */
        Employee reqFirstEmp = new Employee("Manoj", "Ranga", "A521", department);

        /*
          Mock repository response according to the request
         */
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(resFirstEmp));
        when(employeeRepository.findEmployeeByEmployeeId("A520")).thenReturn(Optional.of(resFirstEmp));
        when(employeeRepository.save(reqFirstEmp)).thenReturn(resSecondEmp);

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
    }

    /**
     * Test the happy scenarios when the employee is found
     */
    @Test
    public void findById_happy_test() {
        CodetestResponse<EmployeeDto, String> response = employeeService.findById(1L);
        assertTrue("Check whether the error code is correct", response.getCode() == HttpStatus.OK.value());
        assertTrue("Check whether the response employeeId is correct", response.getData().getEmployeeId().equals("A520"));
    }

    /**
     * Test whether the exception is correct when the employee is found
     */
    @Test(expected = RecordNotFoundException.class)
    public void findById_should_throw_RecordNotFoundException_test() {
        employeeService.findById(4L);
    }

    /**
     * Test the happy scenarios when the employee is created
     */
    @Test
    public void create_happy_test() {
        CodetestResponse<EmployeeDto, String> response = employeeService.create(new EmployeeDto("Manoj", "Ranga", "A521", 1L));
        assertTrue("Check whether the error code is correct", response.getCode() == HttpStatus.CREATED.value());
        assertTrue("Check whether the response employeeId is correct", response.getData().getEmployeeId().equals("A521"));
    }

    /**
     * Test whether the exception is correct when the employee is created
     */
    @Test(expected = RecordAlreadyExistsException.class)
    public void create_should_throw_RecordAlreadyExistsException_test() {
        employeeService.create(new EmployeeDto("Manoj", "Ranga", "A520", 1L));
    }
}
