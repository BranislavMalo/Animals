package org.jump.soft.animals.core.dto;

public class AnimalDto {
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
}