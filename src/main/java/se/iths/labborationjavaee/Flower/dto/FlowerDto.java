package se.iths.labborationjavaee.Flower.dto;

import se.iths.labborationjavaee.Flower.entity.Flower;

public class FlowerDto {
    private Long id;
    private String name;
    private String color;

    public FlowerDto() {
    }

    public FlowerDto(Long id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public FlowerDto(Long id, String name) {
        this.id = id;
        this.name = name;

    }

    public FlowerDto(Flower flower) {
        this.id = flower.getId();
        this.name = flower.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

}
