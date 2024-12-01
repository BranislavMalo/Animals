package org.jump.soft.animals.core.repository;

import java.util.List;
import org.jump.soft.animals.core.dto.AnimalWithDetailsDto;
import org.jump.soft.animals.core.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    @Query("SELECT new org.jump.soft.animals.core.dto.AnimalWithDetailsDto(a.id, a.name, a.age, b.breedName, a.gender) " +
            "FROM Animal a " +
            "JOIN Breed b ON a.breedId = b.id")
    List<AnimalWithDetailsDto> findAllAnimalsWithBreedName();
}