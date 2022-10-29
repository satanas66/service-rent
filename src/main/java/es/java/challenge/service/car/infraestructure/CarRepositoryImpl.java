package es.java.challenge.service.car.infraestructure;

import es.java.challenge.service.car.domain.Car;
import es.java.challenge.service.car.domain.CarRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public abstract class CarRepositoryImpl implements CarRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Car findCarByType(String type) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
        Root<Car> root = criteriaQuery.from(Car.class);
        criteriaQuery.select(root);
        criteriaQuery.where(
                criteriaBuilder.equal(root.get("type"), type)
        );
        return em.createQuery(criteriaQuery).getSingleResult();
    }
}
