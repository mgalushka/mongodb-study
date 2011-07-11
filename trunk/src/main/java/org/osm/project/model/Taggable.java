package org.osm.project.model;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Indexed;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Maxim Galushka
 * @since 26.06.11
 */
public abstract class Taggable implements Entity{

    @Embedded(concreteClass = java.util.HashMap.class)
    private Map<String,String> tags = new HashMap<String, String>();

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public void addTag(String key, String value){
        tags.put(key, value);
    }
}
