package com.db.tpm.tpml;

import com.google.code.morphia.annotations.Embedded;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 21/06/2011
 */
@Embedded
public class Book implements Serializable {

    private static final long serialVersionUID = -7019564589676188620L;

    private String name;
    private String location;

    public Book() {
    }

    public Book(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
