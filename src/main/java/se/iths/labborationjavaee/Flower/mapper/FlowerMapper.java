package se.iths.labborationjavaee.Flower.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import se.iths.labborationjavaee.Flower.dto.FlowerDto;
import se.iths.labborationjavaee.Flower.entity.Flower;

import java.util.List;

@ApplicationScoped
public class FlowerMapper {

    public List<FlowerDto> map(List<Flower> flowers) {
        return flowers.stream().map(flower -> new FlowerDto(flower.getId(), flower.getName(), flower.getColor())).toList();
    }

    public Flower map(FlowerDto flowerDto) {
        var flower = new Flower();
        flower.setId(flowerDto.getId())
                .setName(flowerDto.getName())
                .setColor(flowerDto.getColor());
        return flower;
    }

    public FlowerDto map(Flower flower) {
        return new FlowerDto(flower.getId(), flower.getName(), flower.getColor());
    }
}
