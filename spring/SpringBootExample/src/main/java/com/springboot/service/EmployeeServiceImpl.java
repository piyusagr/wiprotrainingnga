
package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.bean.Employee;
import com.springboot.repo.EmployeeRepository;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository empRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        return empRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return empRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return empRepository.findById(id).orElse(null);
    }

    @Override
    public Employee updateEmployee(Integer id, Employee employee) {
        Employee existing = empRepository.findById(id).orElse(null);

        if (existing == null) return null;

        existing.setName(employee.getName());
        return empRepository.save(existing);
    }

    @Override
    public void deleteEmployee(Integer id) {
        empRepository.deleteById(id);
    }
}
