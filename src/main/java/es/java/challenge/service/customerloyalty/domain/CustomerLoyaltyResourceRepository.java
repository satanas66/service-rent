package es.java.challenge.service.customerloyalty.domain;

public interface CustomerLoyaltyResourceRepository<T> {

    T findCustomerLoyaltyByDni(String dni);
}
