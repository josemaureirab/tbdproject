package cl.rollers.tbdproject.SQL.SQL2O.models;

import com.vividsolutions.jts.geom.Geometry;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import javax.persistence.*;
import java.awt.*;
import com.vividsolutions.jts.geom.Point;

@Entity
@Data
@NoArgsConstructor
@Table(name = "voluntary")
public class Voluntary {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VOLUNTARY_SEQ")
    @SequenceGenerator(name = "VOLUNTARY_SEQ", sequenceName = "SEQ_VOLUNTARY", allocationSize = 1)
    private Integer id;

    @NonNull
    @Column(name = "firstname")
    private String name;

    @NonNull
    @Column(name = "lastname")
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
    /*
    @Column(name = "latitude")
    private Float latitude;

    @Column(name = "longitude")
    private Float longitude;
*/
    @NonNull
    @Column(name = "location")
    private Geometry location;
}