package se.iths.labborationjavaee.Flower.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import se.iths.labborationjavaee.Flower.entity.Flower;

@ApplicationScoped
@Transactional
public class FlowerRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void insertFlower(String name, String color) {
        //  var flower = new Flower()
        //  entityManager.persist(new Flower(name, color));
    }

    public void insertFlower(String name) {

    }

    public void insertFlower(Flower flower) {
        entityManager.persist(flower);
    }

    public Flower[] getAll() {
        var flowers = new Flower[2];
        flowers[0] = new Flower();
        flowers[1] = new Flower();
        flowers[0].setId(1L);
        flowers[1].setId(2L);
        flowers[0].setName("doj");
        flowers[1].setName("dojss");
        return flowers;
    }
}
