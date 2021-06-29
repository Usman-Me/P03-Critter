package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    Pet save(Pet pet);

    Pet findOneById(long id);

    List<Pet> findAll();

    List<Pet> findAllByCustomerId(long ownerId);
}
