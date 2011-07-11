package org.osm.project.model;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.annotations.Reference;
import com.google.code.morphia.utils.IndexDirection;

/**
 * @author Maxim Galushka
 * @since 26.06.11
 */
@Embedded
public class Member {

    @Reference
    @Indexed
    private long ref;

    private String type;

    @Indexed
    private String role;

    public Member() {
    }

    public Member(long ref, String type, String role) {
        this.ref = ref;
        this.type = type;
        this.role = role;
    }

    public long getRef() {
        return ref;
    }

    public void setRef(long ref) {
        this.ref = ref;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Member{" +
                "ref=" + ref +
                ", type='" + type + '\'' +
                ", role='" + role + '\'' +
                "}\n";
    }
}
