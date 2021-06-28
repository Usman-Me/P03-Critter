package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    PetService petService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = ConvertService.convertDTOToScheduleEntity(scheduleDTO);

        List<Pet> petList = scheduleDTO.getPetIds().stream().map(id->
                petService.getPetById(id)).collect(Collectors.toList());
        List<Employee> employeeList = scheduleDTO.getEmployeeIds().stream().map(id->
                employeeService.findByEmployeeId(id)).collect(Collectors.toList());

        schedule.setPets(petList);
        schedule.setEmployees(employeeList);
        schedule = scheduleService.save(schedule);

        scheduleDTO.setId(schedule.getId());

        return scheduleDTO;

        // noch einbauen: throw new UnsupportedOperationException();
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> allSchedules = scheduleService.findAllSchedules();

        List<ScheduleDTO> scheduleDTOs = convertEntityToDTOList(allSchedules);
      // noch einbauen:  throw new UnsupportedOperationException();
        return scheduleDTOs;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        Pet pet = petService.getPetById(petId);
        if(pet == null)
            return new ArrayList<>();

        List<Schedule> schedules = scheduleService.getScheduleByPet(pet);

        List<ScheduleDTO> scheduleDTOs = convertEntityToDTOList(schedules);
        return scheduleDTOs;
        // noch einbauen:   throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        if(employee == null) return new ArrayList<>();

        List<Schedule> schedules = scheduleService.getScheduleByEmployee(employee);
        List<ScheduleDTO> scheduleDTOs = convertEntityToDTOList(schedules);
        return scheduleDTOs;
        // noch einbauen: throw new UnsupportedOperationException();
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        if(customer == null) return new ArrayList<>();

        List<Schedule> schedules = scheduleService.getScheduleByCustomer(customer);
        List<ScheduleDTO> scheduleDTOs = convertEntityToDTOList(schedules);
        return scheduleDTOs;
        // noch einbauen:   throw new UnsupportedOperationException();
    }

    public List<ScheduleDTO> convertEntityToDTOList(List<Schedule> schedules){
        return schedules.stream().map(schedule ->
                ConvertService.convertEntityToScheduleDTO(schedule))
                .collect(Collectors.toList());
    }
}
