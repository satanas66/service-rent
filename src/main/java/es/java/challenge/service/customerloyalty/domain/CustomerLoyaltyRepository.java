package es.java.challenge.service.customerloyalty.domain;

import es.java.challenge.service.car.domain.CarResourceRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerLoyaltyRepository extends JpaRepository<CustomerLoyalty, Long>, CustomerLoyaltyResourceRepository<CustomerLoyalty> {
}
