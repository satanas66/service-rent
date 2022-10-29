package es.java.challenge.service.customerloyalty.domain;

import es.java.challenge.service.customerloyalty.application.CustomerLoyaltyServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class CustomerLoyaltyRepositoryMockTest {

    @Autowired
    private CustomerLoyaltyRepository customerLoyaltyRepository;

    @Test
    public void whenFindAll_thenReturnListCustomerLoyalty(){
        List<CustomerLoyalty> founds = customerLoyaltyRepository.findAll();
        Assertions.assertThat(founds.size()).isNotZero().isEqualTo(3);
    }

    @Test
    public void whenSaveCustomerLoyalty_ThenReturnCustomerLoyaltyNew(){
        CustomerLoyaltyService customerLoyaltyService = new CustomerLoyaltyServiceImpl(customerLoyaltyRepository);
        CustomerLoyalty customerLoyalty = customerLoyaltyService.createCustomerLoyalty("EDUARDO ORTEGA", "09876543E",5);
        Assertions.assertThat(customerLoyalty).isNotNull();
    }



}
