package org.jump.soft.animals.core.controllers;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import org.jump.soft.animals.core.dto.AnimalDto;
import org.jump.soft.animals.core.dto.AnimalWithDetailsDto;
import org.jump.soft.animals.core.exceptions.DuplicateAnimalException;
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
public final class AnimalController {

    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    /**
     * Adds a new animal to the system via a POST request.
     *
     * <p>This endpoint processes an incoming animal data transfer object (DTO)
     * and attempts to add the animal to the system.</p>
     *
     * @param animalDto The data transfer object containing animal information to be added
     * @return ResponseEntity representing the result of the animal addition
     *         <ul>
     *         <li>HTTP 200 OK if the animal is successfully added</li>
     *         <li>HTTP 400 Bad Request if there are validation errors or the animal already exists</li>
     *         </ul>
     * @throws IllegalArgumentException if the animal data is invalid
     * @throws DuplicateAnimalException if an animal with similar identifying information already exists
     */
    @PostMapping("/add-animal")
    public ResponseEntity<?> addAnimal(@RequestBody final AnimalDto animalDto) {
        try {
            animalService.addAnimal(animalDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (IllegalArgumentException | DuplicateAnimalException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error-message", e.getMessage()));
        }
    }

    /**
     * Removes an animal from the system via a DELETE request.
     *
     * <p>This endpoint attempts to remove an animal from the system based on its ID.</p>
     *
     * @param id The unique identifier of the animal to be removed
     * @return ResponseEntity representing the result of the animal removal
     *         <ul>
     *         <li>HTTP 200 OK if the animal is successfully removed</li>
     *         <li>HTTP 404 Not Found if the animal with the specified ID does not exist</li>
     *         </ul>
     * @throws EntityNotFoundException if no animal is found with the specified ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAnimal(@PathVariable final long id) {
        try {
            animalService.removeAnimal(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error-message", e.getMessage()));
        }
    }

    /**
     * Retrieves a specific animal from the system via a GET request.
     *
     * <p>This endpoint returns detailed information about a single animal based on its ID.</p>
     *
     * @param id The unique identifier of the animal to retrieve
     * @return ResponseEntity containing the animal data
     *         <ul>
     *         <li>HTTP 200 OK with the animal data if found</li>
     *         <li>HTTP 404 Not Found if the animal with the specified ID does not exist</li>
     *         </ul>
     * @throws EntityNotFoundException if no animal is found with the specified ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getAnimal(@PathVariable final long id) {
        try {
            AnimalDto entity = animalService.getAnimal(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(entity);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error-message", e.getMessage()));
        }
    }

    /**
     * Updates an existing animal in the system via a PUT request.
     *
     * <p>This endpoint processes updated animal information and modifies the existing record.</p>
     *
     * @param id The unique identifier of the animal to update
     * @param animalDto The data transfer object containing updated animal information
     * @return ResponseEntity representing the result of the update operation
     *         <ul>
     *         <li>HTTP 200 OK if the animal is successfully updated</li>
     *         <li>HTTP 404 Not Found if the animal with the specified ID does not exist</li>
     *         <li>HTTP 400 Bad Request if the updated data is invalid</li>
     *         </ul>
     * @throws EntityNotFoundException if no animal is found with the specified ID
     * @throws IllegalArgumentException if the updated animal data is invalid
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAnimal(@PathVariable final long id, @RequestBody final AnimalDto animalDto) {
        try {
            animalService.updateAnimal(id, animalDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error-message", e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error-message", e.getMessage()));
        }
    }

    /**
     * Retrieves all animals from the system via a GET request.
     *
     * <p>This endpoint returns a list of all animals in the system with basic information.</p>
     *
     * @return ResponseEntity containing a list of all animals
     *         <ul>
     *         <li>HTTP 200 OK with the list of animals if any exist</li>
     *         <li>HTTP 204 No Content if no animals are found in the system</li>
     *         </ul>
     */
    @GetMapping("/all-animals")
    public ResponseEntity<?> getAnimals() {
        List<AnimalDto> entities = animalService.getAnimals();
        if (entities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(entities);
    }

    /**
     * Retrieves all animals with additional details from the system via a GET request.
     *
     * <p>This endpoint returns a list of all animals in the system with extended information.</p>
     *
     * @return ResponseEntity containing a list of all animals with detailed information
     *         <ul>
     *         <li>HTTP 200 OK with the list of detailed animal information if any exist</li>
     *         <li>HTTP 204 No Content if no animals are found in the system</li>
     *         </ul>
     */
    @GetMapping("/all-animals-with-details")
    public ResponseEntity<?> getAnimalsWithDetails() {
        List<AnimalWithDetailsDto> entities = animalService.getAnimalsWithDetails();
        if (entities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(entities);
    }
}
