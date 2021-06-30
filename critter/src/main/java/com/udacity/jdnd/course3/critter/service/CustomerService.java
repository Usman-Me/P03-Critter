package com.udacity.jdnd.course3.critter.service;


import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerService {


    @Autowired
    CustomerRepository customerRepository;

    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer getCustomerByPets(Pet pet) {
        return customerRepository.findCustomerByPets(pet);
    }

    public Customer getCustomerById(long customerId) {
        return customerRepository.findOneById(customerId);
    }

    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }


}
