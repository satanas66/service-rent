package es.java.challenge.service.rent.infraestructure;

import es.java.challenge.service.car.domain.Car;
import es.java.challenge.service.rent.domain.Rent;
import es.java.challenge.service.rent.domain.RentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "rents")
public class RentContoller {

    @Autowired
    private RentService rentService;

    @GetMapping
    public ResponseEntity<List<Rent>> listCars(){
        List<Rent> rents = rentService.listAllRent();
        if(rents.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rents);
    }


    @PostMapping("/create" )
    public ResponseEntity<Rent> createRent(@RequestParam String fecha_inicio, @RequestParam String fecha_fin, @RequestParam String tipo_coche,
                                           @RequestParam String nombre, @RequestParam String dni, @RequestParam Integer number_rented_car
                                           ){
        log.info("Creating Rent by {}", tipo_coche);
        Rent rent = rentService.createRent(fecha_inicio, fecha_fin, tipo_coche, nombre, dni, number_rented_car);
        if(rent == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rent);
    }

    @GetMapping(value="/devolution/{id}")
    public ResponseEntity<Rent> getCar(@PathVariable("id") Long id){
        log.info("Searching Rent with id {}", id);

        Rent rent = rentService.createDevolution(id);
        if(rent == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rent);
    }
}
