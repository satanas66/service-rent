package es.java.challenge.service.rent.application;

import es.java.challenge.service.car.domain.Car;
import es.java.challenge.service.car.domain.CarRepository;
import es.java.challenge.service.customerloyalty.domain.CustomerLoyalty;
import es.java.challenge.service.customerloyalty.domain.CustomerLoyaltyRepository;
import es.java.challenge.service.rent.domain.Rent;
import es.java.challenge.service.rent.domain.RentRepository;
import es.java.challenge.service.rent.domain.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentServiceImpl implements RentService {

    private final RentRepository rentRepository;

    private final CarRepository carRepository;

    private final CustomerLoyaltyRepository customerLoyaltyRepository;

    private LocalDate start_date;

    private LocalDate end_date;

    @Override
    public List<Rent> listAllRent() {
        return rentRepository.findAll();
    }

    @Override
    public Rent createRent(String start, String end, String carType, String name, String dni, Integer number_rented_car) {
        Rent rent = null;
        try {
            long days = getDurationDays(start, end);

            Car car = carRepository.findCarByType(carType);
            Double price = days * car.getPrice() * number_rented_car;
            switch (rent.getCar().getType()) {
                case "suv":
                    if (days > 7 && days <= 30) {
                        price = days * car.getPrice() * 0.80 * number_rented_car;
                    }
                    if (days > 30) {
                        price = days * car.getPrice() * 0.50 * number_rented_car;
                    }
                    break;
                case "small":
                    if (days > 7) {
                        price = days * car.getPrice()*0.30 * number_rented_car;
                    }
                    break;
            }

            Integer points = number_rented_car * car.getPoints();
            CustomerLoyalty customerLoyalty = customerLoyaltyRepository.findCustomerLoyaltyByDni(dni);
            if (customerLoyalty == null) {
                customerLoyalty = CustomerLoyalty.builder()
                        .name(name)
                        .dni(dni)
                        .points(points)
                        .build();
            } else {
                customerLoyalty.setPoints(customerLoyalty.getPoints() + points);
            }
            customerLoyalty = customerLoyaltyRepository.save(customerLoyalty);
            rent = Rent.builder()
                    .start_date(Date.from(start_date.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                    .end_date(Date.from(end_date.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                    .payable(price)
                    .total(price)
                    .number_rented_cars(number_rented_car)
                    .car(car)
                    .customerLoyalty(customerLoyalty)
                    .build();

            car.setStock(car.getStock() - number_rented_car);
            carRepository.save(car);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rentRepository.save(rent);
    }

    @Override
    public Rent createDevolution(Long id) {

        Rent rent = rentRepository.findById(id).orElse(null);

        if (rent != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            String current_date = sdf.format(date);
            String start_date = sdf.format(rent.getStart_date());
            String end_date = sdf.format(rent.getEnd_date());

            long idealDays = getDurationDays(start_date, end_date);
            long realDays = getDurationDays(start_date, current_date);
            if (realDays > idealDays) {
                long days = realDays - idealDays;
                Double surcharge = null;
                switch (rent.getCar().getType()) {
                    case "premium":
                        surcharge = rent.getCar().getPrice() + (rent.getCar().getPrice() * 0.20);
                        break;
                    case "suv":
                        surcharge = rent.getCar().getPrice() + (50.0 * 0.60);
                        break;
                    case "small":
                        surcharge = rent.getCar().getPrice() + (rent.getCar().getPrice() * 0.30);
                        break;
                }
                rent.setDevolution_date(date);
                rent.setSurcharge(surcharge * days);
                rent.setTotal(rent.getPayable() + rent.getSurcharge());
                rent = rentRepository.save(rent);

                Car car = rent.getCar();
                car.setStock(car.getStock() + rent.getNumber_rented_cars());
                carRepository.save(car);
            }
        }
        return rent;
    }

    private long getDurationDays(String start, String end) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        start_date = LocalDate.parse(start, formatter);
        end_date = LocalDate.parse(end, formatter);
        Duration duration = Duration.between(start_date.atStartOfDay(), end_date.atStartOfDay());
        return duration.toDays();
    }
}
