package cl.rollers.tbdproject.SQL.SQL2O.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import javax.persistence.*;
import java.awt.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "voluntary")
public class Voluntary {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VOLUNTARY_SEQ")
    @SequenceGenerator(name = "VOLUNTARY_SEQ", sequenceName = "SEQ_VOLUNTARY", allocationSize = 1)
    private Integer id;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "last_Name")
    private String lastName;

    @NonNull
    @Column(name = "mail")
    private String mail;

    @NonNull
    @Column(name = "gender")
    private String gender;

    @NonNull
    @Column(name = "rut")
    private String rut;

    @NonNull
    @Column(name = "age")
    private Integer age;

    @Column(name = "latitude")
    private Float latitude;

    @Column(name = "longitude")
    private Float longitude;

    @NonNull
    @Column(columnDefinition = "Geometry", name = "location")
    private Point location;
}