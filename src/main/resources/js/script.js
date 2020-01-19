

$(document).ready(function () {

    const osmLayer = new ol.layer.Tile({
        source: new ol.source.OSM(),
        visible: true
    });

    const map = new ol.Map({
        target: 'mapPlaceholder',
        layers: [
            osmLayer
        ],
        view: new ol.View({
            center: ol.proj.fromLonLat([260.55, 23]),
            zoom: 4
        })
    });

    //Define style, source, and layer to show Airport features
    const pointStyle = new ol.style.Style({
        image: new ol.style.Circle({
            radius: 5,
            fill: null,
            stroke: new ol.style.Stroke({color: 'red', width: 1})
        })
    });
    const airportSource = new ol.source.Vector({
        format: new ol.format.GeoJSON(),
        url: 'http://localhost:8080/voluntaries/'
    });
    const airportLayer = new ol.layer.Vector({
        source: airportSource,
        style: function(feature) {
            return pointStyle;
        }
    });
    map.addLayer(airportLayer);

    //Define style, source, and layer to show Route features
    const lineStyle = new ol.style.Style({
        stroke: new ol.style.Stroke({color: 'green', width: 1})
    });
    const lineStyleFunction = function(feature) {
        return lineStyle;
    }

});