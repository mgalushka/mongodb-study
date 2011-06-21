package com.db.tpm.tpml;

import com.google.code.morphia.annotations.Embedded;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 20/06/2011
 */
@Embedded
public class TradeId implements Serializable{

    private static final long serialVersionUID = -7479758723885327454L;

    private String system;
    private String id;
    private Integer version;

    public TradeId() {
    }

    public TradeId(String system, String id) {
        this.system = system;
        this.id = id;
    }

    public TradeId(String system, String id, Integer version) {
        this.system = system;
        this.id = id;
        this.version = version;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "TradeId{" +
                "system='" + system + '\'' +
                ", id='" + id + '\'' +
                ", version=" + version +
                '}';
    }
}
