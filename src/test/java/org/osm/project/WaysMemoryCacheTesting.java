package org.osm.project;

import com.db.tpm.dao.DatabaseStorage;
import com.db.tpm.dao.MongoDatabaseStorage;
import com.db.tpm.dao.StorageException;
import com.google.code.morphia.Datastore;
import org.osm.project.model.Node;
import org.osm.project.model.Relation;
import org.osm.project.model.Way;

import java.util.List;

/**
 * @author Maxim Galushka
 * @since 12.07.11
 */
public class WaysMemoryCacheTesting {

    /*
     * -Xmx512m is OK
     */
    public static void main(String[] args) throws StorageException {
        final DatabaseStorage ds = new MongoDatabaseStorage();

        Datastore mongo = ds.getDatastore();

        // full dump in memory
        List<Way> allWays = mongo.find(Way.class).asList();
        List<Node> allNodes = mongo.find(Node.class).asList();
        List<Relation> allRelations = mongo.find(Relation.class).asList();
    }
}
