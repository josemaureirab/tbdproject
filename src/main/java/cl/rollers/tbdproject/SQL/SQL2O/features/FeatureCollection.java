package cl.rollers.tbdproject.SQL.SQL2O.features;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jdk.nashorn.internal.codegen.ObjectClassGenerator;

import java.util.ArrayList;
import java.util.List;

@JsonSerialize(using = GeoJSONSerializer.class)
public class FeatureCollection {

    private List<Feature> features = new ArrayList<>();

    public FeatureCollection() {
    }

    public FeatureCollection(List<Feature> features) {
        this.features = features;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void addFeature(Feature feature) {
        this.features.add(feature);
    }

}
