package com.db.tpm.dao;

import com.db.tpm.tpml.Trade;

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
}
