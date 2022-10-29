package es.java.challenge.service.car.infraestructure;

import es.java.challenge.service.car.domain.Car;
import es.java.challenge.service.car.domain.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value="cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> listCars(){
      List<Car> cars = carService.listAllCar();
      if(cars.isEmpty()){
          return ResponseEntity.noContent().build();
      }
      return ResponseEntity.ok(cars);
    }

    @GetMapping(value="/{type}")
    public ResponseEntity<Car> getCar(@PathVariable("type") String type){
        log.info("Searching Carr with type {}", type);

        Car car = carService.getCarByType(type);
        if(car == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(car);
    }

}
