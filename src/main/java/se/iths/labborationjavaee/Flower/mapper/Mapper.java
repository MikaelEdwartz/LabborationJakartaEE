package se.iths.labborationjavaee.Flower.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import se.iths.labborationjavaee.Flower.dto.FlowerDto;
import se.iths.labborationjavaee.Flower.entity.Flower;

import java.util.List;
import java.util.function.Function;

@ApplicationScoped
public class Mapper {

    public List<FlowerDto> map(List<Flower> flowers) {
        return flowers.stream().map(copyFlowerToDto()).toList();
    }

    public Flower map(FlowerDto flowerDto) {
        return copyDtoToFlower(flowerDto, new Flower());
    }

    public FlowerDto map(Flower flower) {
        return new FlowerDto(flower.getId(), flower.getName(), flower.getColor());
    }

    public static Function<Flower, FlowerDto> copyFlowerToDto() {
        return flower -> new FlowerDto(flower.getId(), flower.getName(), flower.getColor());
    }

    private static Flower copyDtoToFlower(FlowerDto flowerDto, Flower flower) {
        return flower.setId(flowerDto.getId())
                .setName(flowerDto.getName())
                .setColor(flowerDto.getColor());
    }

}
