package com.db.tpm.dao;

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

    private String host = "localhost";
    private int port = 27017;
    private String instance = "test";

    private Mongo mongo;
    private Datastore datastore;

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

    private void init() throws StorageException, UnknownHostException {
        log.trace(String.format("Connecting to MongoDB instance: %s:%d/%s",
                    host, port, instance));

        mongo = new Mongo(host, port);
        datastore = new Morphia().createDatastore(mongo, instance);
        //datastore.ensureIndexes();
    }

    @Override
    public void save(Object trade) throws StorageException {
        try {
            if(datastore == null) init();
            //log.debug(String.format("Saving object: %s", trade.getTrackingId()));
            datastore.save(trade);

        } catch (UnknownHostException e) {
            log.error(String.format("Cannot connect to %s:%d", host, port), e);
            throw new StorageException(e);
        }
    }

    @Override
    public void close(){
        log.trace(String.format("Closing connection to MongoDB instance: %s:%d/%s",
                    host, port, instance));
        mongo.close();
    }

    @Override
    public Datastore getDatastore() throws StorageException {
        try {
            if(datastore == null) init();
        } catch (UnknownHostException e) {
            log.error(String.format("Cannot connect to %s:%d", host, port), e);
            throw new StorageException(e);
        }
        return datastore;
    }
}
