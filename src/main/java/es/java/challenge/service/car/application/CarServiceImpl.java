package es.java.challenge.service.car.application;

import es.java.challenge.service.car.domain.Car;
import es.java.challenge.service.car.domain.CarRepository;
import es.java.challenge.service.car.domain.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public List<Car> listAllCar() {
        return carRepository.findAll();
    }

    @Override
    public Car getCar(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    @Override
    public Car updateCarStock(Long id, Integer stock) {
        Car car = getCar(id);
        if (null == car) {
            return null;
        }
        Integer currentStock = car.getStock() - stock;
        car.setStock(currentStock);
        return carRepository.save(car);
    }

    @Override
    public Car getCarByType(String type) {
        return carRepository.findCarByType(type);
    }
}
