package cl.rollers.tbdproject.SQL.SQL2O.dto;

import cl.rollers.tbdproject.SQL.SQL2O.models.VoluntaryDimension;
import cl.rollers.tbdproject.SQL.SQL2O.models.VoluntaryEmergency;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import lombok.Data;

@Data
public class VoluntaryDto {
    private Integer id;
    private String name;
    private String lastName;
    private String mail;
    private String gender;
    private String rut;
    private Integer age;
    /*private Float latitude;
    private Float longitude;*/
    private Point location;
}