package se.iths.labborationjavaee.Flower.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Flower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String color;

    public Flower setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Flower setName(String name) {
        this.name = name;
        return this;
    }

    public Flower(Long id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public Flower setColor(String color) {
        this.color = color;
        return this;
    }
}
