package se.iths.labborationjavaee.Flower.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import se.iths.labborationjavaee.Flower.dto.FlowerDto;
import se.iths.labborationjavaee.Flower.entity.Flower;

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
        var flower = findById(id);
        if (flower.isPresent())
            entityManager.remove(flower.get());
    }

    public void changeFlowerName(Long id, String name) {
        var flower = findById(id);
        if (flower.isPresent())
            flower.get().setName(name);

    }

    public void changeFlowerColor(Long id, String color) {
        var flower = findById(id);
        if (flower.isPresent())
            flower.get().setColor(color);

    }

    public void changeFlower(Long id, String name, String color) {
        var flower = findById(id);
        if (flower.isPresent())
            flower.get().setName(name).setColor(color);

    }

}
