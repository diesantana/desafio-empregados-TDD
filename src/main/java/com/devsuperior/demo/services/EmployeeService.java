package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.EmployeeDTO;
import com.devsuperior.demo.entities.Department;
import com.devsuperior.demo.entities.Employee;
import com.devsuperior.demo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository repository;
    
    @Transactional(readOnly = true)
    public Page<EmployeeDTO> findAll(Pageable pageable) {
        Page<Employee> employeePage = repository.findAll(pageable);
        return employeePage.map(x -> new EmployeeDTO(x));
    }
    
    @Transactional
    public EmployeeDTO insert(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(new Department(dto.getDepartmentId(), null));
        
        employee = repository.save(employee);
        return new EmployeeDTO(employee);
    }
}
