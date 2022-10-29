package es.java.challenge.service.rent.domain;

import es.java.challenge.service.car.domain.Car;
import es.java.challenge.service.customerloyalty.domain.CustomerLoyalty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long> {

    public List<Rent> findByCar(Car car);

    public List<Rent> findByCustomerLoyalty(CustomerLoyalty customerLoyalty);

}
