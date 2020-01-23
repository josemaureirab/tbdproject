
package cl.rollers.tbdproject.SQL.JPA.models;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Point;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name= "emergency")
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

    @Column( name = "location", nullable = true)
    @JsonDeserialize(contentUsing = GeometryDeserializer.class)
    @JsonSerialize(using = GeometrySerializer.class)
    private Point location;

}
