package org.jump.soft.animals.core.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.jump.soft.animals.core.enumeration.Gender;

@Entity
public class AnimalWithDetailsDto {
    @Id
    private long id;
    private String name;
    private int age;
    private String breedName;
    private Gender gender;

    public AnimalWithDetailsDto() {

    }

    public AnimalWithDetailsDto(long id, String name, int age, String breedName, Gender gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.breedName = breedName;
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}