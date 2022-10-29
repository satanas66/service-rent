package es.java.challenge.service.customerloyalty.application;

import es.java.challenge.service.customerloyalty.domain.CustomerLoyalty;
import es.java.challenge.service.customerloyalty.domain.CustomerLoyaltyRepository;
import es.java.challenge.service.customerloyalty.domain.CustomerLoyaltyService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class CustomerLoyaltyServiceImplTest {

    @Mock
    private CustomerLoyaltyRepository customerLoyaltyRepository;

    private CustomerLoyaltyService customerLoyaltyService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        customerLoyaltyService = new CustomerLoyaltyServiceImpl(customerLoyaltyRepository);
        CustomerLoyalty customerLoyalty = CustomerLoyalty.builder()
                .id(7L)
                .points(3)
                .dni("11111111G")
                .name("MANUEL GONZALEZ")
                .build();
        Mockito.when(customerLoyaltyRepository.findById(7L)).thenReturn(Optional.of(customerLoyalty));
        Mockito.when(customerLoyaltyRepository.save(customerLoyalty)).thenReturn(customerLoyalty);
    }

    @Test
    public void whenValidGetId_ThenReturnCustomerLoyalty(){
        CustomerLoyalty customerLoyalty = customerLoyaltyService.getCustomerLoyalty(7L);
        Assertions.assertThat(customerLoyalty.getPoints()).isEqualTo(3);
        Assertions.assertThat(customerLoyalty.getName()).isEqualTo("MANUEL GONZALEZ");
    }

    @Test
    public void whenValidUpdateCustomerLoyaltyPoints_ThenReturNewPoints(){
        CustomerLoyalty customerLoyalty = customerLoyaltyService.updateCustomerLoyaltyPoints(7L, 5);
        Assertions.assertThat(customerLoyalty.getPoints()).isEqualTo(8);
    }
}