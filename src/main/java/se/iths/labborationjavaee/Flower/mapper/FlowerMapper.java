package se.iths.labborationjavaee.Flower.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import se.iths.labborationjavaee.Flower.dto.FlowerDto;
import se.iths.labborationjavaee.Flower.entity.Flower;

import java.util.List;

@ApplicationScoped
public class FlowerMapper {

    public List<FlowerDto> map(List<Flower> flowers) {
        return flowers.stream().map(flower -> new FlowerDto(flower.getId(), flower.getName())).toList();
    }

    public Flower map(FlowerDto flowerDto) {
        Flower flower = new Flower();
        flower.setId(flower.getId());
        flower.setName(flower.getName());
        return flower;
    }
}