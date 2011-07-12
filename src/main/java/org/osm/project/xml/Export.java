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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Maxim Galushka
 * @since 10.07.11
 */
public class Export {


    public static void main(String[] args) throws RenderException, StorageException {
        ModelRenderer mr = new ModelRenderer();
        File output = new File("E:\\Projects\\mongodb-study\\examples\\out-test.osm");

        DatabaseStorage ds = new MongoDatabaseStorage();
        Datastore mongo = ds.getDatastore();

        List<Entity> forExport = new ArrayList<Entity>();

        List<Node> busStops = mongo.find(Node.class, "tags.highway", "bus_stop").asList();

        Set<Way> busWays = new HashSet<Way>();
        for(Node n : busStops){
            busWays.addAll(mongo.createQuery(Way.class).disableValidation().filter("nodes.$id", n.getId()).asList());
        }

        forExport.addAll(busStops);
        forExport.addAll(busWays);

        mr.buildOsm(forExport, output);
    }
}
