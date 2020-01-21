package cl.rollers.tbdproject.SQL.SQL2O.features;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
