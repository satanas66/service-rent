package es.java.challenge.service.rent.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import es.java.challenge.service.car.domain.Car;
import es.java.challenge.service.customerloyalty.domain.CustomerLoyalty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="tbl_rent")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="start_date")
    @Temporal(TemporalType.DATE)
    private Date start_date;

    @Column(name="end_date")
    @Temporal(TemporalType.DATE)
    private Date end_date;

    @Column(name="devolution_date")
    @Temporal(TemporalType.DATE)
    private Date devolution_date;

    @Column(name="payable")
    private Double payable;

    @Column(name="surcharge")
    private Double surcharge;

    @Column(name="total")
    private Double total;

    @Column(name="number_rented_car")
    private Integer number_rented_cars;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_loyalty_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private CustomerLoyalty customerLoyalty;
}
