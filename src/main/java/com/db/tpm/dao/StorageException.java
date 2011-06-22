package com.db.tpm.dao;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 22/06/2011
 */
public class StorageException extends Exception{
    private static final long serialVersionUID = -4602658054559532299L;

    public StorageException() {
        super();
    }

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }

    public StorageException(Throwable cause) {
        super(cause);
    }
}
