package es.java.challenge.service.customerloyalty.application;

import es.java.challenge.service.customerloyalty.domain.CustomerLoyalty;
import es.java.challenge.service.customerloyalty.domain.CustomerLoyaltyRepository;
import es.java.challenge.service.customerloyalty.domain.CustomerLoyaltyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerLoyaltyServiceImpl implements CustomerLoyaltyService {

    private final CustomerLoyaltyRepository customerLoyaltyRepository;

    @Override
    public List<CustomerLoyalty> listAllCustomerLoyalty() {
        return customerLoyaltyRepository.findAll();
    }

    @Override
    public CustomerLoyalty getCustomerLoyalty(Long id) {
        return customerLoyaltyRepository.findById(id).orElse(null);
    }

    @Override
    public CustomerLoyalty updateCustomerLoyaltyPoints(Long id, Integer points) {
        CustomerLoyalty customerLoyalty = getCustomerLoyalty(id);
        if (null == customerLoyalty) {
            return null;
        }
        Integer currentPoints = customerLoyalty.getPoints() + points;
        customerLoyalty.setPoints(currentPoints);
        return customerLoyaltyRepository.save(customerLoyalty);
    }

    @Override
    public CustomerLoyalty createCustomerLoyalty(String name, String dni, Integer points) {

        CustomerLoyalty customerLoyalty = CustomerLoyalty.builder()
                .name(name)
                .dni(dni)
                .points(points)
                .build();
        customerLoyalty = customerLoyaltyRepository.save(customerLoyalty);
        return customerLoyalty;
    }

    @Override
    public CustomerLoyalty getCustomerLoyaltyByDni(String dni) {
        return customerLoyaltyRepository.findCustomerLoyaltyByDni(dni);
    }
}
