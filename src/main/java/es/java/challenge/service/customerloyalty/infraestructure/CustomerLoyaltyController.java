package es.java.challenge.service.customerloyalty.infraestructure;

import es.java.challenge.service.customerloyalty.domain.CustomerLoyalty;
import es.java.challenge.service.customerloyalty.domain.CustomerLoyaltyService;
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
@RequestMapping(value="customerloyalties")
public class CustomerLoyaltyController {

    @Autowired
    private CustomerLoyaltyService customerLoyaltyService;

    @GetMapping
    public ResponseEntity<List<CustomerLoyalty>> listCars(){
        List<CustomerLoyalty> customerLoyalties = customerLoyaltyService.listAllCustomerLoyalty();
        if(customerLoyalties.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customerLoyalties);
    }

    @GetMapping(value="/{dni}")
    public ResponseEntity<CustomerLoyalty> getCar(@PathVariable("dni") String dni){
        log.info("Searching CustomerLoyalty with dni {}", dni);

        CustomerLoyalty customerLoyalty = customerLoyaltyService.getCustomerLoyaltyByDni(dni);
        if(customerLoyalty == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customerLoyalty);
    }
}
