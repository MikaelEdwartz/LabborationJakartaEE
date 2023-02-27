package se.iths.labborationjavaee.Flower.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import se.iths.labborationjavaee.Flower.entity.Flower;

import java.util.List;

@ApplicationScoped
@Transactional
public class FlowerRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<Flower> findAll() {
        var query = entityManager.createQuery("SELECT f FROM Flower f");
        return query.getResultList();
    }

    public List<Flower> findByName(String name) {
        var query = entityManager.createQuery("SELECT f FROM Flower f WHERE f.name LIKE :name");
        query.setParameter("name", name);
        return query.getResultList();
    }

    public void insertFlower(Flower flower) {
        entityManager.persist(flower);
    }

}
