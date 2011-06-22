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
     * Saves trade message in database
     *
     * @param trade trade to save
     * @throws StorageException is exception occurs
     */
    public void save(Trade trade) throws StorageException;
}
