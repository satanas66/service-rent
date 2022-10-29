package es.java.challenge.service.car.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long>, CarResourceRepository<Car> {
}
