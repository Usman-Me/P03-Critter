package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findByEmployeeId(long id) {
        return employeeRepository.findOneById(id);
    }

    public Employee getEmployeeById(long employeeId) {
        return employeeRepository.findOneById(employeeId);
    }

    public void setEmployeeAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = this.getEmployeeById(employeeId);

        if (employee != null) {
            Set<DayOfWeek> days = employee.getDaysAvailability();
            if (days == null) {
                days = new HashSet<>();
            }
            days.addAll(daysAvailable);
            employee.setDaysAvailability(days);
            this.save(employee);
        }
    }

    public List<Employee> getAllEmployees() {
        Iterable<Employee> iterable = employeeRepository.findAll();
        List<Employee> result = new ArrayList<>();
        iterable.forEach(result::add);
        return result;
    }


    public List<Employee> findEmployeesBySkillAndDate(Set<EmployeeSkill> skills, DayOfWeek dayOfWeek) {


        List<Employee> employees = employeeRepository.findByDaysAvailability(dayOfWeek);
        List<Employee> availableEmployees = new ArrayList<>();

        for (Employee e : employees) {
            if (e.getSkills().containsAll(skills) && e.getDaysAvailability().contains(dayOfWeek)) {
                availableEmployees.add(e);
            }
        }
        return availableEmployees;



    }
}




