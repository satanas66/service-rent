package es.java.challenge.service.car.application;

import es.java.challenge.service.car.domain.Car;
import es.java.challenge.service.car.domain.CarRepository;
import es.java.challenge.service.car.domain.CarService;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    private CarService carService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        carService = new CarServiceImpl(carRepository);

        Car car = Car.builder()
                .id(10L)
                .type("LAST_GENERATION")
                .price(500.0)
                .stock(20)
                .build();

        Mockito.when(carRepository.findById(10L)).thenReturn(Optional.of(car));
        Mockito.when(carRepository.save(car)).thenReturn(car);
    }

    @Test
    public void whenValidGetId_ThenReturnCar(){
        Car car = carService.getCar(10L);
        Assertions.assertThat(car.getType()).isEqualTo("LAST_GENERATION");
    }

    @Test
    public void whenValidUpdateCarStock_ThenReturNewStock(){
        Car car = carService.updateCarStock(10L, 5);
        Assertions.assertThat(car.getStock()).isEqualTo(15);
    }

}