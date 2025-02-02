package org.jump.soft.animals.core.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AnimalDto {
    @Id
    private long id;
    private String name;
    private int age;
    private long breedId;
    private String gender;
}