package se.iths.labborationjavaee.Flower.mapper;

import org.junit.jupiter.api.Test;
import se.iths.labborationjavaee.Flower.dto.FlowerDto;
import se.iths.labborationjavaee.Flower.entity.Flower;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MapperTest {

    Mapper mapper = new Mapper();

    @Test
    void mappingAListOfFlowersReturnsListOfFlowerDto() {
        var flower = new Flower();
        var flower2 = new Flower();
        List<Flower> flowers = List.of(flower, flower2);

        var actualDtoList = mapper.map(flowers);
        var expected = List.of(mapper.map(flower), mapper.map(flower2));

        assertThat(actualDtoList).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void mappingADTOtoFlowerCorrectlyMaps() {
        var flowerDto = new FlowerDto(1L, "Kaktus", "grön");
        var expectedResult = new Flower().setId(1L).setName("Kaktus").setColor("grön");

        var actualResult = mapper.map(flowerDto);

        assertThat(actualResult).usingRecursiveComparison().isEqualTo(expectedResult);
    }

}
