package se.iths.labborationjavaee.Flower.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import se.iths.labborationjavaee.Flower.entity.Flower;
import se.iths.labborationjavaee.Flower.resources.Attributes;

import java.util.List;
import java.util.Optional;

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

    public List<Flower> findByColor(String color) {
        var query = entityManager.createQuery("SELECT f FROM Flower f WHERE f.color LIKE :color");
        query.setParameter("color", color);
        return query.getResultList();

    }

    public void insertFlower(Flower flower) {
        entityManager.persist(flower);
    }

    public Optional<Flower> findById(long id) {
        return Optional.ofNullable(entityManager.find(Flower.class, id));
    }

    public void deleteById(Long id) {
        findById(id).ifPresent(value -> entityManager.remove(value));
    }

    public void changeAttributes(Long id, String name, String color, Attributes option) {
        var flower = findById(id).get();

        switch (option) {
            case NAME -> flower.setName(name);
            case COLOR -> flower.setColor(color);
            case BOTH -> flower.setName(name).setColor(color);
        }
        entityManager.persist(flower);
    }

}
