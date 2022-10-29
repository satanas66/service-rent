package es.java.challenge.service.rent.domain;

import java.util.List;

public interface RentService {

    public List<Rent> listAllRent();

    Rent createRent(String start, String end, String carType, String name, String dni, Integer number_rented_car);

    public Rent createDevolution(Long id);
}
