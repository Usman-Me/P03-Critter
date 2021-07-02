package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.service.ConvertService;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Verarbeitet Web Requests für Tiere
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @Autowired
    CustomerService customerService;

    public PetController(PetService petService, CustomerService customerService){
        this.petService=petService;
        this.customerService=customerService;
    }


    /*
    * Ab hier weiter
     */

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Customer customer = null;
        if ((Long) petDTO.getOwnerId() != null) {
            customer = customerService.getCustomerById(petDTO.getOwnerId());
        }
        Pet pet = ConvertService.convertDTOToPetEntity(petDTO);
        pet.setCustomer(customer);

        pet = petService.save(pet);
        petDTO.setId(pet.getId());

        return petDTO;
    }



    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.getPetById(petId);
        PetDTO petDTO = ConvertService.convertEntityToPetDTO(pet);
        petDTO.setOwnerId(pet.getCustomer().getId());
        return petDTO;

        // throw new UnsupportedOperationException();

    }

    @GetMapping
    public List<PetDTO> getPets(){
        //try{
            List<Pet> petList = petService.getAllPet();
            List<PetDTO> petDTOList = petList.stream().map(pet-> ConvertService.convertEntityToPetDTO(pet)).collect(Collectors.toList());
            return petDTOList;
        //}
        //catch (Exception exception){
        // Noch einzubauen    throw new UnsupportedOperationException();
        //}
       // return null;

    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> petList = petService.findAllByCustomerId(ownerId);
        List<PetDTO> petDTOList = petList.stream().map(pet->
                ConvertService.convertEntityToPetDTO(pet)
        ).collect(Collectors.toList());

        List<PetDTO> updatedList = petDTOList.stream().map(petDTO -> {
            petDTO.setOwnerId(ownerId);
            return petDTO;}).collect(Collectors.toList());

        return updatedList;
    }
}
