package se.iths.labborationjavaee.Flower.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.labborationjavaee.Flower.entity.Flower;
import se.iths.labborationjavaee.Flower.resources.Attributes;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RepositoryTest {
    @InjectMocks
    Repository repository;
    @Mock
    private EntityManager entityManager;
    @Mock
    private Query query;

    Flower flower1 = new Flower().setName("blåsippa").setColor("blå");
    Flower flower2 = new Flower().setName("vitsippa").setColor("vit");

    @Test
    void usingfindAllMethodShouldReturnAListWithCorrectFlowers() {
        List<Flower> expectedFlowers = List.of(flower1, flower2);
        when(query.getResultList()).thenReturn(expectedFlowers);
        when(entityManager.createQuery(Repository.FIND_ALL_FLOWERS_QUERY)).thenReturn(query);

        var actualFlowers = repository.findAll();

        assertThat(actualFlowers).hasSameElementsAs(expectedFlowers);

    }

    @Test
    void usingFindByNameShouldReturnCorrectFlower() {
        var name = "blåsippa";
        List<Flower> expectedFlowers = List.of(flower1);
        when(query.getResultList()).thenReturn(expectedFlowers);
        when(query.setParameter("name", name)).thenReturn(query);
        when(entityManager.createQuery(Repository.FIND_FLOWER_BY_NAME_QUERY)).thenReturn(query);

        var actualFlowers = repository.findByName(name);

        assertThat(actualFlowers).isSameAs(expectedFlowers);

    }

    @Test
    void usingFindByColorReturnsFlowersMatchingTheColor() {
        var color = "vit";
        List<Flower> expectedFlowers = List.of(flower2);
        when(query.getResultList()).thenReturn(expectedFlowers);
        when(query.setParameter("color", color)).thenReturn(query);
        when(entityManager.createQuery(Repository.FIND_FLOWERS_BY_COLOR_QUERY)).thenReturn(query);

        var actualFlowers = repository.findByColor(color);

        assertThat(actualFlowers).isSameAs(expectedFlowers);

    }

    @Test
    void usingInsertFlowerMethodShouldIncreaseIntegerBy1() {
        repository.insertFlower(flower1);

        verify(entityManager).persist(flower1);
    }

    @Test
    void findByIdReturnsCorrectFlower() {
        when(entityManager.find(Flower.class, 1L)).thenReturn(flower1);

        var actualFlower = repository.findById(1L).get();

        assertThat(actualFlower).isEqualTo(flower1);

    }

    @Test
    void usingDeleteRemovesFlower() {
        when(entityManager.find(Flower.class, 1L)).thenReturn(flower1);

        repository.deleteById(1L);

        verify(entityManager).remove(flower1);

    }

    @Test
    void changingNameShouldChangeName() {
        var newName = "gulsippa";
        when(entityManager.find(Flower.class, 1L)).thenReturn(flower1);

        repository.changeAttributes(1L, newName, null, Attributes.NAME);

        assertThat(flower1.getName()).isEqualTo(newName);
    }

    @Test
    void changingColorShouldChangeColor() {
        var newColor = "lila";
        when(entityManager.find(Flower.class, 1L)).thenReturn(flower1);

        repository.changeAttributes(1L, null, newColor, Attributes.COLOR);

        assertThat(flower1.getColor()).isEqualTo(newColor);
    }

    @Test
    void changingBothColorAndNameShouldUpdateFlower() {
        var newName = "Convallaria majalis";
        var newColor = "Vit";
        when(entityManager.find(Flower.class, 1L)).thenReturn(flower1);

        repository.changeAttributes(1L, newName, newColor, Attributes.BOTH);
        var expectedFlower = new Flower().setName(newName).setColor(newColor);

        assertThat(flower1).usingRecursiveComparison().isEqualTo(expectedFlower);
    }

}


