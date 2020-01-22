package cl.rollers.tbdproject.SQL.SQL2O.features;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
public class Feature {

    private final String type = "Feature";
    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(contentUsing = GeometryDeserializer.class)
    private Point geometry;

    private Map<String, Object> properties;

    public Feature(Point geometry, Map<String, Object> properties) {
        this.geometry = geometry;
        this.properties = properties;
    }

    public Feature() {

    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Point geometry) {
        this.geometry = geometry;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public String getType() {
        return type;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }


}
