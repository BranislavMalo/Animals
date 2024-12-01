package org.jump.soft.animals.core.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

@Entity
@Table(name = "ANIMAL")
public class Animal {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @JoinColumn(name = "breed_id", referencedColumnName = "breed_name", nullable = false)
    private long breedId;

    @Column(name = "gender", nullable = false)
    private String gender;

    public Animal() {

    }

    public Animal(long id, String name, int age, long breedId, String gender) {
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

    // toString method for a more readable output
    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", breedId='" + breedId + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}