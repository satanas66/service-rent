package es.java.challenge.service.rent.domain;

import es.java.challenge.service.car.domain.Car;
import es.java.challenge.service.customerloyalty.domain.CustomerLoyalty;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class RentRepositoryMockTest {

    @Autowired
    private RentRepository rentRepository;

    @Test
    public void whenFindAll_thenReturnListRent(){
        List<Rent> founds = rentRepository.findAll();
        Assertions.assertThat(founds.size()).isNotZero().isEqualTo(4);
    }

    @Test
    public void whenFindCar_thenReturnListRent(){
        Rent rent = Rent.builder()
                .car(Car.builder().id(1L).build())
                .customerLoyalty(CustomerLoyalty.builder().id(21L).build())
                .start_date(new Date())
                .end_date(new Date())
                .payable(300.0)
                .surcharge(0.0)
                .total(300.0).build();
        Rent create = rentRepository.save(rent);
        List<Rent> founds = rentRepository.findByCar(create.getCar());
        Assertions.assertThat(founds.size()).isEqualTo(2);
    }
}
