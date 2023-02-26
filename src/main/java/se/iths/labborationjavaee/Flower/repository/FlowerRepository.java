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

}
