package org.osm.project.helpers;

import com.db.tpm.dao.DatabaseStorage;
import com.db.tpm.dao.MongoDatabaseStorage;
import com.db.tpm.dao.StorageException;
import com.google.code.morphia.Datastore;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 12/07/2011
 */
public abstract class MongoHelper {

    protected Datastore mongo;

    public MongoHelper() throws StorageException {
        DatabaseStorage ds = new MongoDatabaseStorage();
        this.mongo = ds.getDatastore();
    }

    protected Datastore getMongo() {
        return mongo;
    }
}
