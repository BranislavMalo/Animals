package org.jump.soft.animals.core.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jump.soft.animals.core.enumeration.Gender;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AnimalWithDetailsDto {
    @Id
    private long id;
    private String name;
    private int age;
    private String breedName;
    private Gender gender;
}