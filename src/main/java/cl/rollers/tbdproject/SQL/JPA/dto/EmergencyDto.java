
package cl.rollers.tbdproject.SQL.JPA.dto;

import com.vividsolutions.jts.geom.Point;
import lombok.Data;

@Data
public class EmergencyDto {
    private Integer id;
    private String name;
    private String description;
    private Point location;
}
