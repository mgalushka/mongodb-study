package org.osm.project.model;

import com.google.code.morphia.annotations.Embedded;

/**
 * @author Maxim Galushka
 * @since 27.06.11
 */
@Embedded
public class Location {

    private double lat;
    private double lon;

    public Location() {
    }

    public Location(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "location: {" +
                "lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
