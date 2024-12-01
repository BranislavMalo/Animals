package org.jump.soft.animals.core.services.utils;

import org.jump.soft.animals.core.dto.AnimalDto;

public class AnimalServiceUtility {

    private AnimalServiceUtility() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void validateAnimalDto(AnimalDto animalDto, boolean isUpdate) {
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

        if (animalDto.getGender() == null || animalDto.getGender().isEmpty()) {
            throw new IllegalArgumentException("Animal gender is required");
        }

        if (isUpdate) {
            if (animalDto.getId() <= 0) {
                throw new IllegalArgumentException("Animal ID is required for update and must be a positive number");
            }
        }
    }
}