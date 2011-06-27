package org.osm.project.model;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.utils.IndexDirection;

import java.util.Map;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 24/06/2011
 */
@Entity(value="nodes", noClassnameStored=true)
public class Node extends Taggable{

    @Id
    private long id;

    @Indexed(value = IndexDirection.GEO2D)
    @Embedded
    private Location location;

    public Node() {
    }

    public Node(long id) {
        this.id = id;
    }

    public Node(long id, double lat, double lon) {
        this.id = id;
        this.location.setLat(lat);
        this.location.setLon(lon);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLat() {
        return this.location.getLat();
    }

    public void setLat(double lat) {
        this.location.setLat(lat);
    }

    public double getLon() {
        return this.location.getLon();
    }

    public void setLon(double lon) {
        this.location.setLon(lon);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        Map tags = getTags();
        return "Node{" +
                "id=" + id +
                ", location=" + location +
                (tags == null || tags.isEmpty() ? "" : ", tags=" + tags) +
                '}';
    }
}
