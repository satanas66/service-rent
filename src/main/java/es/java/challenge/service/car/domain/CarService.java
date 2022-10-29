package es.java.challenge.service.car.domain;

import java.util.List;

public interface CarService {

    public List<Car> listAllCar();

    public Car getCar(Long id);

    public Car updateCarStock(Long id, Integer stock);

    public Car getCarByType(String type);

}
