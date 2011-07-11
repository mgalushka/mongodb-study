package org.osm.project;

import com.db.tpm.dao.DatabaseStorage;
import com.db.tpm.dao.MongoDatabaseStorage;
import com.db.tpm.dao.StorageException;
import com.google.code.morphia.Datastore;
import org.osm.project.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Maxim Galushka
 * @since 28.06.11
 */
public class BusStopsPlaying {

    public static void main(String[] args) throws StorageException {
        DatabaseStorage ds = new MongoDatabaseStorage();
        Datastore mongo = ds.getDatastore();

        List<Node> busStops = mongo.find(Node.class, "tags.highway", "bus_stop").limit(1).asList();
        Map<Node, Way> ways = new HashMap<Node, Way>(busStops.size()*2);

        for(Node stop : busStops){
            Way w = mongo.find(Way.class).disableValidation().filter("nodes.$id", stop.getId()).get();
            ways.put(stop, w);
            System.out.printf("Stop: %s:\nWay: %s\n", stop, w);

            Relation r = mongo.find(Relation.class).disableValidation().filter("members.ref", w.getId()).get();
            System.out.printf("Stop: %s:\nRelation: %s\n", stop, r);

            // find all ways for current relation
            for(Member m : r.getMembers()){
                Taggable next;
                if("node".equals(m.getType())){
                    next = ds.getDatastore().find(Node.class, "_id", m.getRef()).get();
                    System.out.printf("Node: %s\n",  next);
                }
                if("way".equals(m.getType())){
                    next = ds.getDatastore().find(Way.class, "_id", m.getRef()).get();
                    System.out.printf("Way: %s\n",  next);
                }
            }
        }



        ds.close();
    }
}
