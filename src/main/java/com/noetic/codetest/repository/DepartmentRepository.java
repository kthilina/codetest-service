package com.noetic.codetest.repository;

import com.noetic.codetest.domain.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author : Kasun Thilina
 * Date    : 12/28/2019
 * Project : codetest
 **/
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findDepartmentByDepartmentId(final String departmentId);
}
