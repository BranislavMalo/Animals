package org.jump.soft.animals.core.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class AnimalDto {
    @Id
    private long id;
    private String name;
    private int age;
    private long breedId;
    private String gender;

    public AnimalDto() {

    }

    public AnimalDto(long id, String name, int age, long breedId, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.breedId = breedId;
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

    public long getBreedId() {
        return breedId;
    }

    public void setBreedId(long breedId) {
        this.breedId = breedId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalDto animalDto = (AnimalDto) o;
        return Objects.equals(age, animalDto.age) &&
                Objects.equals(name, animalDto.name) &&
                Objects.equals(breedId, animalDto.breedId) &&
                Objects.equals(gender, animalDto.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, breedId, gender);
    }
}