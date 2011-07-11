package org.osm.project.model;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Maxim Galushka
 * @since 26.06.11
 */
@Entity(value="relations", noClassnameStored=true)
public class Relation extends Taggable{

    @Id
    private long id;

    @Embedded
    private List<Member> members = new ArrayList<Member>();

    public Relation() {
    }

    public Relation(long id) {
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

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public void addMember(Member member) {
        this.members.add(member);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Relation relation = (Relation) o;

        if (id != relation.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        Map tags = getTags();
        return "Relation{" +
                "id=" + id +
                ", members=" + members +
                (tags == null || tags.isEmpty() ? "" : ", tags=" + tags) +
                "}\n";
    }
}
