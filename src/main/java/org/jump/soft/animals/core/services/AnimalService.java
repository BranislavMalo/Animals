package org.jump.soft.animals.core.services;

import java.util.List;
import org.jump.soft.animals.core.dto.AnimalDto;
import org.jump.soft.animals.core.dto.AnimalWithDetailsDto;

public interface AnimalService {

    void addAnimal(AnimalDto animalDto);

    void removeAnimal(long id);

    AnimalDto getAnimal(long id);

    void updateAnimal(long id, AnimalDto animalDto);

    List<AnimalDto> getAnimals();

    List<AnimalWithDetailsDto> getAnimalsWithDetails();
}