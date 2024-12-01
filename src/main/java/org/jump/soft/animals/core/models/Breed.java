package org.jump.soft.animals.core.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "BREED")
public class Breed {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "breed_name", nullable = false, unique = true)
    private String breedName;

    @OneToMany(mappedBy = "breedId", cascade = CascadeType.ALL)
    private Set<Animal> animals = new HashSet<>();

    public Breed() {

    }

    public Breed(long id, String breedName) {
        this.id = id;
        this.breedName = breedName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", breedName='" + breedName + '\'' +
                '}';
    }
}