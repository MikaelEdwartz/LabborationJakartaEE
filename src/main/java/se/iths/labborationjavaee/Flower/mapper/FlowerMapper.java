package se.iths.labborationjavaee.Flower.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import se.iths.labborationjavaee.Flower.dto.FlowerDto;
import se.iths.labborationjavaee.Flower.entity.Flower;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class FlowerMapper {

    public List<FlowerDto> map(List<Flower> flowers) {
        return flowers.stream().map(flower -> new FlowerDto(flower.getId(), flower.getName(), flower.getColor())).toList();
    }

    public Flower map(FlowerDto flowerDto) {
        return new Flower(flowerDto.getId(), flowerDto.getName(), flowerDto.getColor());
    }

    public FlowerDto map(Flower flower) {
        return new FlowerDto(flower.getId(), flower.getName(), flower.getColor());
    }
}
