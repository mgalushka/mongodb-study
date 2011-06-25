package org.osm.project.model;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Maxim Galushka
 * @since 26.06.11
 */
@Entity(value="ways", noClassnameStored=true)
public class Way {

    @Id
    private long id;

    @Reference(ignoreMissing = true)
    private List<Node> nodes = new ArrayList<Node>();

    @Embedded(concreteClass = java.util.HashMap.class)
    private Map<String,String> tags = new HashMap<String, String>();

    public Way() {
    }

    public Way(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public void addNode(Node node){
        nodes.add(node);
    }

    public void addTag(String key, String value){
        tags.put(key, value);
    }

    @Override
    public String toString() {
        return "Way{" +
                "id=" + id +
                ", nodes=" + nodes +
                ", tags=" + tags +
                '}';
    }
}
