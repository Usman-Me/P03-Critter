package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee findByEmployeeId(long id){
        return employeeRepository.findOneById(id);
    }

    public Employee getEmployeeById(long employeeId) {
        return employeeRepository.findOneById(employeeId);
    }

    public void setEmployeeAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = this.getEmployeeById(employeeId);

        if(employee != null) {
            Set<DayOfWeek> days = employee.getDaysAvailability();
            if (days == null) {
                days = new HashSet<>();
            }

            days.addAll(daysAvailable);
            employee.setDaysAvailability(days);
            this.save(employee);
        }
    }


    public List<Employee> findEmployeesBySkillAndDate(Set<EmployeeSkill> skills, LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        List<Employee> resultEmployee = new ArrayList<>();

        for(EmployeeSkill skill : skills){
            List<Employee> employeeBySkill = employeeRepository.getAllBySkills(skill);

            for(Employee e: employeeBySkill){
                if(!resultEmployee.contains(e) && e.getDaysAvailability().contains(dayOfWeek) && e.getSkills().containsAll(skills)){
                    resultEmployee.add(e);
                }
            }
        }

        return resultEmployee;
    }



}
