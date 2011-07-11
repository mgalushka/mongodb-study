package org.osm.project;

import com.db.tpm.dao.DatabaseStorage;
import com.db.tpm.dao.MongoDatabaseStorage;
import com.db.tpm.dao.StorageException;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.MapreduceType;

/**
 * @author Maxim Galushka
 * @since 28.06.11
 */
public class BusStopsMapReduce {

    public static void main(String[] args) throws StorageException {
        DatabaseStorage ds = new MongoDatabaseStorage();
        Datastore mongo = ds.getDatastore();

        //mongo.mapReduce(MapreduceType.REPLACE, "", "", "", "", );


        ds.close();
    }
}
