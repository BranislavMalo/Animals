package org.jump.soft.animals.core.repository;

import org.jump.soft.animals.core.models.Breed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreedRepository extends JpaRepository<Breed, Long> {

    boolean existsById(Long id);
}