package es.java.challenge.service.customerloyalty.domain;

import java.util.List;

public interface CustomerLoyaltyService {

    public List<CustomerLoyalty> listAllCustomerLoyalty();

    public CustomerLoyalty getCustomerLoyalty(Long id);

    public CustomerLoyalty updateCustomerLoyaltyPoints(Long id, Integer points);

    public CustomerLoyalty createCustomerLoyalty(String name, String dni, Integer points);

    public CustomerLoyalty getCustomerLoyaltyByDni(String dni);
}
