package org.jump.soft.animals.core.controllers;

import java.util.List;
import org.jump.soft.animals.core.dto.AnimalDto;
import org.jump.soft.animals.core.dto.AnimalWithDetailsDto;
import org.jump.soft.animals.core.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping("/add-animal")
    public void addAnimal(@RequestBody AnimalDto animalDto) {
        animalService.addAnimal(animalDto);
    }

    @DeleteMapping("/remove-animal/{id}")
    public void removeAnimal(@PathVariable long id) {
        animalService.removeAnimal(id);
    }

    @GetMapping("/{id}")
    public AnimalDto getAnimal(@PathVariable long id) {
        return animalService.getAnimal(id);
    }

    @PutMapping("/update-animal/{id}")
    public void updateAnimal(@PathVariable long id, @RequestBody AnimalDto animalDto) {
        animalService.updateAnimal(id, animalDto);
    }

    @GetMapping("/all")
    public List<AnimalDto> getAnimals() {
        return animalService.getAnimals();
    }

    @GetMapping("/all-with-details")
    public List<AnimalWithDetailsDto> getAnimalsWithDetails() {
        return animalService.getAnimalsWithDetails();
    }
}