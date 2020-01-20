
package cl.rollers.tbdproject.SQL.JPA.dto;

import com.vividsolutions.jts.geom.Point;
import lombok.Data;

@Data
public class VoluntaryDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String mail;
    private String gender;
    private String rut;
    private Integer age;
    private Point location;
}