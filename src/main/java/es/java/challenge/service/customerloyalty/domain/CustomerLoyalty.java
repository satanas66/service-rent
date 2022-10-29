package es.java.challenge.service.customerloyalty.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="tbl_customer_loyalty")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerLoyalty implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "dni")
    private String dni;

    @Column(name = "points")
    private Integer points;

}
