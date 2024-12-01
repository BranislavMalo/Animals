package org.jump.soft.animals.core.services.utils;

import org.jump.soft.animals.core.dto.AnimalDto;
import org.jump.soft.animals.core.repository.BreedRepository;

public class AnimalServiceUtility {

    private AnimalServiceUtility() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void validateAnimalDto(AnimalDto animalDto, BreedRepository breedRepository, boolean isUpdate) {
        if (animalDto == null) {
            throw new IllegalArgumentException("Animal data cannot be null");
        }

        if (animalDto.getName() == null || animalDto.getName().isEmpty()) {
            throw new IllegalArgumentException("Animal name is required");
        }

        if (animalDto.getAge() <= 0) {
            throw new IllegalArgumentException("Animal age must be a positive number");
        }

        if (animalDto.getBreedId() <= 0) {
            throw new IllegalArgumentException("Animal breed must be a positive number");
        }

        if (!breedRepository.existsById(animalDto.getBreedId())) {
            throw new IllegalArgumentException("Breed with ID " + animalDto.getBreedId() + " does not exist.");
        }

        if (animalDto.getGender() == null || animalDto.getGender().isEmpty()) {
            throw new IllegalArgumentException("Animal gender is required");
        }

        if (!isValidGender(animalDto.getGender())) {
            throw new IllegalArgumentException("Animal gender must be either 'MALE' or 'FEMALE'");
        }

        if (isUpdate) {
            if (animalDto.getId() <= 0) {
                throw new IllegalArgumentException("Animal ID is required for update and must be a positive number");
            }
        }
    }

    private static boolean isValidGender(String gender) {
        return "MALE".equals(gender) || "FEMALE".equals(gender);
    }
}