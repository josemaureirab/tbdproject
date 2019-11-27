/*
package cl.rollers.tbdproject.SQL.JPA.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name= "emergencies")
public class Emergency {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMERGENCY_SEQ")
    @SequenceGenerator(name = "EMERGENCY_SEQ", sequenceName = "SEQ_EMERGENCY", allocationSize = 1)
    private Integer id;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "description")
    private String description;
}*/
