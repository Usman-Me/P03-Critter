package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;


    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> findAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleByPet(Pet pet) {
        return scheduleRepository.getAllByPetsContains(pet);
    }

    public List<Schedule> getScheduleByEmployee(Employee employee) {
        return scheduleRepository.getAllByEmployeesContains(employee);
    }

    public List<Schedule> getScheduleByCustomer(Customer customer) {
        List<Pet> pets = customer.getPets();
        return scheduleRepository.getAllByPetsIn(pets);
    }
}
