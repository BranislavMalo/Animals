package org.jump.soft.animals.core.controllers;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import org.jump.soft.animals.core.dto.AnimalDto;
import org.jump.soft.animals.core.dto.AnimalWithDetailsDto;
import org.jump.soft.animals.core.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> addAnimal(@RequestBody AnimalDto animalDto) {
        try {
            animalService.addAnimal(animalDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error-message", e.getMessage()));
        }
    }

    @DeleteMapping("/remove-animal/{id}")
    public ResponseEntity<?> removeAnimal(@PathVariable long id) {
        try {
            animalService.removeAnimal(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error-message", e.getMessage()));
        }
    }

    @GetMapping("/get-animal/{id}")
    public ResponseEntity<?> getAnimal(@PathVariable long id) {
        try {
            AnimalDto entity = animalService.getAnimal(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(entity);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error-message", e.getMessage()));
        }
    }

    @PutMapping("/update-animal/{id}")
    public ResponseEntity<?> updateAnimal(@PathVariable long id, @RequestBody AnimalDto animalDto) {
        try {
            animalService.updateAnimal(id, animalDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error-message", e.getMessage()));
        }
    }

    @GetMapping("/get-animals")
    public ResponseEntity<?> getAnimals() {
        List<AnimalDto> entities = animalService.getAnimals();
        if (entities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/get-animals-with-details")
    public ResponseEntity<?> getAnimalsWithDetails() {
        List<AnimalWithDetailsDto> entities = animalService.getAnimalsWithDetails();
        if (entities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(entities);
    }
}