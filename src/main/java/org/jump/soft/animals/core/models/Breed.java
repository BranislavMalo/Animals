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
import lombok.Data;

@Data
@Entity
@Table(name = "BREED")
public class Breed {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "breed_name", nullable = false, unique = true)
    private String breedName;

    @OneToMany(mappedBy = "breedId", cascade = CascadeType.ALL)
    private Set<Animal> animals = new HashSet<>();
}