package cl.rollers.tbdproject.SQL.SQL2O.features;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Geometry;

import java.util.Map;
public class Feature {

    private final String type = "Feature";

    @JsonSerialize(using = GeometrySerializer.class)
    private Geometry geometry;
    private Map<String, Object> propierties;

    public Feature(Geometry geometry, Map<String, Object> properties) {
        this.geometry = geometry;
        this.propierties = properties;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Map<String, Object> getData() {
        return propierties;
    }

    public String getType() {
        return type;
    }

    public void getPropierties(Map<String, Object> propierties) {
        this.propierties = propierties;
    }

}
