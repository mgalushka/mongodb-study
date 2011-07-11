package org.osm.project.xml;

import com.db.tpm.dao.DatabaseStorage;
import com.db.tpm.dao.MongoDatabaseStorage;
import com.db.tpm.dao.StorageException;
import com.google.code.morphia.Datastore;
import org.osm.project.model.Entity;
import org.osm.project.model.Node;
import org.osm.project.xml.renderers.RenderException;

import java.io.File;
import java.util.List;

/**
 * @author Maxim Galushka
 * @since 10.07.11
 */
public class Export {

    public static void main(String[] args) throws RenderException, StorageException {
        ModelRenderer mr = new ModelRenderer();
        File output = new File("E:\\Projects\\mongodb-study\\examples\\test.osm");

        DatabaseStorage ds = new MongoDatabaseStorage();
        Datastore mongo = ds.getDatastore();

        List<Node> busStops = mongo.find(Node.class, "tags.highway", "bus_stop").asList();

        mr.buildOsm(busStops, output);
    }
}
