package se.iths.labborationjavaee.Flower.repository;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.labborationjavaee.Flower.entity.Flower;
import org.junit.jupiter.api.Test;
import jakarta.persistence.Query;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
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

        assertThat(repository.findAll()).hasSameElementsAs(expectedFlowers);

    }

    @Test
    void usingFindByNameShouldReturnCorrectFlower() {
        var name = "blåsippa";
        List<Flower> expectedFlowers = List.of(flower1);

        when(query.getResultList()).thenReturn(expectedFlowers);
        when(query.setParameter("name", name)).thenReturn(query);
        when(entityManager.createQuery(Repository.FIND_FLOWER_BY_NAME_QUERY)).thenReturn(query);

        assertThat(repository.findByName(name)).isSameAs(expectedFlowers);

    }

    @Test
    void usingFindByColorReturnsFlowersMatchingTheColor() {
        var color = "vit";
        List<Flower> expectedFlowers = List.of(flower2);

        when(query.getResultList()).thenReturn(expectedFlowers);
        when(query.setParameter("color", color)).thenReturn(query);
        when(entityManager.createQuery(Repository.FIND_FLOWERS_BY_COLOR_QUERY)).thenReturn(query);

        assertThat(repository.findByColor(color)).isSameAs(expectedFlowers);

    }

    @Test
    void usingInsertFlowerMethodShouldIncreaseIntegerBy1() {
        repository.insertFlower(flower1);

        verify(entityManager).persist(flower1);
    }

}


