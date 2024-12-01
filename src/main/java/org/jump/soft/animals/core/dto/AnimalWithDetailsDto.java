package org.jump.soft.animals.core.dto;

public class AnimalWithDetailsDto {
    private long id;
    private String name;
    private int age;
    private String breedName;
    private String gender;

    public AnimalWithDetailsDto() {

    }

    public AnimalWithDetailsDto(long id, String name, int age, String breedName, String gender) {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}