package com.db.tpm.tpml;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 20/06/2011
 */
@Entity(value="trades", noClassnameStored=true)
public class Trade implements Serializable {

    private static final long serialVersionUID = -346474186193162869L;

    @Id
    private ObjectId id;

    private Date timestamp;
    private String sender;
    private String trackingId;

    @Embedded
    private OriginatingSystem originatingSystem;

    @Embedded
    private Book book;

    private List<TradeId> identifiers;

    public Trade() {
    }

    public Trade(Date timestamp, String sender, String trackingId, OriginatingSystem originatingSystem, Book book) {
        this.timestamp = timestamp;
        this.sender = sender;
        this.trackingId = trackingId;
        this.originatingSystem = originatingSystem;
        this.book = book;
    }

    public Trade(Date timestamp, String sender, String trackingId) {
        this.timestamp = timestamp;
        this.sender = sender;
        this.trackingId = trackingId;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public OriginatingSystem getOriginatingSystem() {
        return originatingSystem;
    }

    public void setOriginatingSystem(OriginatingSystem originatingSystem) {
        this.originatingSystem = originatingSystem;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<TradeId> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(List<TradeId> identifiers) {
        this.identifiers = identifiers;
    }
}
