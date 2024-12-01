package org.jump.soft.animals.core.services;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.jump.soft.animals.core.dto.AnimalDto;
import org.jump.soft.animals.core.dto.AnimalWithDetailsDto;
import org.jump.soft.animals.core.models.Animal;
import org.jump.soft.animals.core.repository.AnimalRepository;
import org.jump.soft.animals.core.services.utils.AnimalServiceUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Transactional
    @Override
    public void addAnimal(AnimalDto animalDto) {
        AnimalServiceUtility.validateAnimal(animalDto);

        if (animalDto.getId() > 0) {
            throw new IllegalArgumentException("Use updateAnimal method for existing animals");
        }

        Animal animal = new Animal();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(animalDto, animal);

        animalRepository.saveAndFlush(animal);
    }

    @Override
    public void removeAnimal(long id) {
        animalRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        animalRepository.deleteById(id);
    }

    @Override
    public AnimalDto getAnimal(long id) {
        Optional<Animal> animalOpt = animalRepository.findById(id);
        if (animalOpt.isPresent()) {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(animalOpt.get(), AnimalDto.class);
        }
        throw new EntityNotFoundException("Animal not found with id: " + id);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void updateAnimal(long id, AnimalDto animalDto) {
        Optional<Animal> animalOpt = animalRepository.findById(id);
        if (animalOpt.isPresent()) {
            Animal animal = animalOpt.get();
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.map(animalDto, animal);
            animalRepository.save(animal);
        }
    }

    @Override
    public List<AnimalDto> getAnimals() {
        List<Animal> animals = animalRepository.findAll();
        return animals.stream()
                .map(animal -> {
                    ModelMapper modelMapper = new ModelMapper();
                    return modelMapper.map(animal, AnimalDto.class);
                })
                .toList();
    }

    @Override
    public List<AnimalWithDetailsDto> getAnimalsWithDetails() {
        return animalRepository.findAllAnimalsWithBreedName();
    }
}