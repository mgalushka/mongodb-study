package org.osm.project;

import com.db.tpm.dao.DatabaseStorage;
import com.db.tpm.dao.MongoDatabaseStorage;
import com.db.tpm.dao.StorageException;
import org.osm.project.model.Way;

/**
 * @author Maxim Galushka
 * @since 27.06.11
 */
public class RetrieveWay {

    public static void main(String[] args) throws StorageException {
        DatabaseStorage ds = new MongoDatabaseStorage();

        Way w = ds.getDatastore().find(Way.class, "_id", 50805884L).get();

        System.out.printf("Way: %s\n", w);
    }
}
