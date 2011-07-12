package org.osm.project.xml;

import com.db.tpm.dao.DatabaseStorage;
import com.db.tpm.dao.MongoDatabaseStorage;
import com.db.tpm.dao.StorageException;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;
import org.osm.project.model.Entity;
import org.osm.project.model.Node;
import org.osm.project.model.Relation;
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


    private static long startMillis = 0L;

    private static void startTimer(){
        startMillis = System.currentTimeMillis();
    }

    private static void printEllapsed(String title){
        double seconds = (System.currentTimeMillis() - startMillis)/1000.;
        System.out.printf("%s elapsed in: %f sec.\n", title, seconds);
        startMillis = System.currentTimeMillis();
    }

    public static void main(String[] args) throws RenderException, StorageException {
        ModelRenderer mr = new ModelRenderer();
        File output = new File("E:\\Projects\\mongodb-study\\examples\\out-test.osm");

        DatabaseStorage ds = new MongoDatabaseStorage();
        Datastore mongo = ds.getDatastore();

        List<Entity> forExport = new ArrayList<Entity>();

        startTimer();
        List<Node> busStops = mongo.find(Node.class, "tags.highway", "bus_stop").asList();
        printEllapsed("Found all bus stops");

        // find all ways for all stops
        Set<Way> busWays = new HashSet<Way>();
        for(Node n : busStops){
            busWays.addAll(mongo.createQuery(Way.class).disableValidation()
                    .filter("nodes.$id", n.getId()).asList());
        }
        printEllapsed("Found ways for all bus stops");

        // find all relations for all ways found and adding them to map too
        Set<Relation> busWaysRelations = new HashSet<Relation>();
        for(Way w : busWays){
            Query<Relation> allBusRelations = mongo.createQuery(Relation.class).disableValidation();
            allBusRelations.criteria("members.ref").equal(w.getId()).and()
                    .or(
                        allBusRelations.criteria("tags.type").equal("associatedStreet"),
                        allBusRelations.criteria("tags.type").equal("street")
                    );

            busWaysRelations.addAll(allBusRelations.asList());
        }
        printEllapsed("Found relations for all bus stops ways");

        for(Node n : busStops){
            Query<Relation> allBusStopsRelations = mongo.createQuery(Relation.class).disableValidation();
            allBusStopsRelations.criteria("members.ref").equal(n.getId()).and()
                    .or(
                        allBusStopsRelations.criteria("tags.type").equal("associatedStreet"),
                        allBusStopsRelations.criteria("tags.type").equal("street")
                    );

            busWaysRelations.addAll(allBusStopsRelations.asList());
        }
        printEllapsed("Found relations for all bus stop nodes");


        forExport.addAll(busStops);
        forExport.addAll(busWays);
        forExport.addAll(busWaysRelations);

        mr.buildOsm(forExport, output);
        printEllapsed("Exported");
    }
}
