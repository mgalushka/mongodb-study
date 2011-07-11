package com.db.tpm.dao;

import com.db.tpm.tpml.Trade;
import com.google.code.morphia.Datastore;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 22/06/2011
 */
public interface DatabaseStorage {

    /**
     * Saves object in database
     *
     * @param object object to save
     * @throws StorageException if exception occurs
     */
    public void save(Object object) throws StorageException;

    /**
     * Closes storage
     *
     * @throws StorageException if exception occurs
     */
    public void close() throws StorageException;

    /**
     * @return datastore object for search purposes
     * @throws StorageException if exception occurs
     */
    public Datastore getDatastore() throws StorageException;
}
