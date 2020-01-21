package cl.rollers.tbdproject.SQL.SQL2O.features;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
public class GeoJSONSerializer extends StdSerializer<FeatureCollection> {

    public GeoJSONSerializer() {
        this(null);
    }

    public GeoJSONSerializer(Class<FeatureCollection> t) {
        super(t);
    }

    @Autowired
    private GeometrySerializer geometrySerializer = new GeometrySerializer();

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void serialize(FeatureCollection value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("type", "FeatureCollection");
        gen.writeObjectField("features", value.getFeatures());
        gen.writeEndObject();
    }

    public void setGeometrySerializer(GeometrySerializer geometrySerializer) {
        this.geometrySerializer = geometrySerializer;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

}
