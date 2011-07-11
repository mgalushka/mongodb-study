package org.osm.project.model;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.annotations.Reference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Maxim Galushka
 * @since 26.06.11
 */
@Entity(value="ways", noClassnameStored=true)
public class Way extends Taggable{

    @Id
    private long id;

    // TODO: to investigate if we require lazy loading here
    @Reference(ignoreMissing = true/*, lazy = true*/)
    @Indexed
    private List<Node> nodes = new ArrayList<Node>();

    public Way() {
    }

    public Way(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void addNode(Node node){
        nodes.add(node);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Way way = (Way) o;

        if (id != way.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        Map tags = getTags();
        return "Way{" +
                "id=" + id +
                ", nodes=" + nodes +
                (tags == null || tags.isEmpty() ? "" : ", tags=" + tags) +
                "}\n";
    }
}
