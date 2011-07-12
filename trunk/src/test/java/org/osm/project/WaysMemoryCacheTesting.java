package org.osm.project;

import com.db.tpm.dao.DatabaseStorage;
import com.db.tpm.dao.MongoDatabaseStorage;
import com.db.tpm.dao.StorageException;
import com.mongodb.DB;
import com.mongodb.Mongo;
import org.osm.project.model.Way;

/**
 * @author Maxim Galushka
 * @since 12.07.11
 */
public class WaysMemoryCacheTesting {

    public static void main(String[] args) throws StorageException {
        final DatabaseStorage ds = new MongoDatabaseStorage();

        Mongo mongo = ds.getDatastore().getMongo();
        DB db = mongo.getDB("test");

        Listmongo.createQuery(Way.class).asList();


    }
}
