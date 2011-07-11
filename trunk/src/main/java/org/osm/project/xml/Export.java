package org.osm.project.xml;

import com.db.tpm.dao.DatabaseStorage;
import com.db.tpm.dao.MongoDatabaseStorage;
import com.db.tpm.dao.StorageException;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;
import org.osm.project.model.Entity;
import org.osm.project.model.Node;
import org.osm.project.model.Way;
import org.osm.project.xml.renderers.RenderException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Maxim Galushka
 * @since 10.07.11
 */
public class Export {

    public static void main(String[] args) throws RenderException, StorageException {
        ModelRenderer mr = new ModelRenderer();
        File output = new File("E:\\Projects\\Eclipse\\mongodb-study\\examples\\out-test.osm");

        DatabaseStorage ds = new MongoDatabaseStorage();
        Datastore mongo = ds.getDatastore();

        List<Entity> forExport = new ArrayList<Entity>();

        List<Node> busStops = mongo.find(Node.class, "tags.highway", "bus_stop").asList();
        List<Way> busWays = mongo.createQuery(Way.class).field("tags.highway").exists().limit(1000).asList();

        forExport.addAll(busStops);
        forExport.addAll(busWays);

        mr.buildOsm(forExport, output);
    }
}
