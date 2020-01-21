package cl.rollers.tbdproject.SQL.SQL2O.features;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

public class WktHelper {
    public static Geometry wktToGeometry(String wktPoint) {
        WKTReader fromText = new WKTReader();
        try {
            return fromText.read(wktPoint);
        } catch (ParseException e) {
            throw new RuntimeException("Not a WKT string:" + wktPoint);
        }
    }

}
