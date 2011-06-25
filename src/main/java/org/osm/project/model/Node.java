package org.osm.project.model;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

import java.util.HashMap;
import java.util.Map;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 24/06/2011
 */
@Entity(value="nodes", noClassnameStored=true)
public class Node {

    @Id
    private long id;
    private double lat;
    private double lon;

    @Embedded(concreteClass = java.util.HashMap.class)
    private Map<String,String> tags = new HashMap<String, String>();

    public Node() {
    }

    public Node(long id) {
        this.id = id;
    }

    public Node(long id, double lat, double lon) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public void addTag(String key, String value){
        tags.put(key, value);
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", lat=" + lat +
                ", lon=" + lon +
                (tags == null || tags.isEmpty() ? "" : ", tags=" + tags) +
                '}';
    }
}
