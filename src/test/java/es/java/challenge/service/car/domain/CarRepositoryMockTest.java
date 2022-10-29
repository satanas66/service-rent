package es.java.challenge.service.car.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class CarRepositoryMockTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    public void whenFindAll_thenReturnListCar(){
        List<Car> founds = carRepository.findAll();
        Assertions.assertThat(founds.size()).isNotZero().isEqualTo(3);
    }
}
