package es.java.challenge.service.car.domain;

public interface CarResourceRepository<T> {

    T findCarByType(String type);

}
