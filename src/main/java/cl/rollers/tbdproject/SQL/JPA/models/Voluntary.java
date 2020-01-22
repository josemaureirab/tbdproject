package cl.rollers.tbdproject.SQL.JPA.models;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "voluntary")
public class Voluntary {

    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VOLUNTARY_SEQ")
    @SequenceGenerator(name = "VOLUNTARY_SEQ", sequenceName = "SEQ_VOLUNTARY", allocationSize = 1)
    private Integer id;

    @NonNull
    @Column(name = "name")
    private String firstName;

    @NonNull
    @Column(name = "last_Name")
    private String lastName;

    @NonNull
    @Column(name = "mail")
    private String mail;

    @NonNull
    @Column(name = "gender")
    private String gender;

    @Column(name = "rut")
    private String rut;
    
    @Column(name = "age")
    private Integer age;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column( name = "location", nullable = true)
    @JsonDeserialize(contentUsing = GeometryDeserializer.class)
    @JsonSerialize(using = GeometrySerializer.class)
    private Point location;
}