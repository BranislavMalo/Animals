package org.jump.soft.animals.core.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import org.jump.soft.animals.core.enumeration.Gender;
import org.springframework.data.annotation.Id;

@Data
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
    @Enumerated(EnumType.STRING)
    private Gender gender;
}