package com.db.tpm.dao;

import com.db.tpm.tpml.Trade;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import org.apache.log4j.Logger;

import java.net.UnknownHostException;

/**
 * <p>Mongo DB storage implementation</p>
 *
 * @author Maxim Galushka
 * @since 22/06/2011
 */
public class MongoDatabaseStorage implements DatabaseStorage {

    public static final Logger log = Logger.getLogger(MongoDatabaseStorage.class);

    private String host;
    private int port;

    private String instance;

    public MongoDatabaseStorage() {
    }

    public MongoDatabaseStorage(String host, int port, String instance) {
        this.host = host;
        this.port = port;
        this.instance = instance;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    @Override
    public void save(Trade trade) throws StorageException {
        try {
            log.trace(String.format("Connecting to MongoDB instance: %s:%d/%s",
                    host, port, instance));

            Mongo m = new Mongo(host, port);
            Datastore ds = new Morphia().createDatastore(m, instance);

            log.debug(String.format("Saving object: %s", trade.getTrackingId()));
            ds.save(trade);

        } catch (UnknownHostException e) {
            log.error(String.format("Cannot connect to %s:%d", host, port), e);
            throw new StorageException(e);
        }
    }
}
