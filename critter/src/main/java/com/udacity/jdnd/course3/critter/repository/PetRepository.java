package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    Pet save(Pet pet);

    Pet findOneById(long id);

    List<Pet> findAll();

    List<Pet> findAllByCustomerId(long ownerId);
}
