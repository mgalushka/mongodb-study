package org.osm.project.model;

import com.google.code.morphia.annotations.Embedded;

/**
 * @author Maxim Galushka
 * @since 25.06.11
 */
@Embedded
@Deprecated
public class Tag {

    private String name;
    private String value;

    public Tag() {
    }

    public Tag(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
