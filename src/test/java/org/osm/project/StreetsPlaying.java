package org.osm.project;

import com.db.tpm.dao.DatabaseStorage;
import com.db.tpm.dao.MongoDatabaseStorage;
import com.db.tpm.dao.StorageException;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.osm.project.model.Member;
import org.osm.project.model.Node;
import org.osm.project.model.Relation;
import org.osm.project.model.Way;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Maxim Galushka
 * @since 08.07.11
 */
public class StreetsPlaying {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws StorageException {
        final DatabaseStorage ds = new MongoDatabaseStorage();

        //Way w = ds.getDatastore().getMongo().find(Way.class).get();
        Mongo mongo = ds.getDatastore().getMongo();
        DB db = mongo.getDB("test");

        HashSet<String> streets = new HashSet<String>();

        DBCollection nodes = db.getCollection("nodes");
        streets.addAll(nodes.distinct("tags.addr:street"));

        DBCollection ways = db.getCollection("ways");
        streets.addAll(ways.distinct("tags.addr:street"));

        DBCollection relations = db.getCollection("relations");
        streets.addAll(relations.distinct("tags.addr:street"));

        //System.out.printf("Streets: %s\n", streets);

        final String street = "Лесі Українки бульвар";
        System.out.printf("Nodes: %s\n", ds.getDatastore().find(Node.class).field("tags.addr:street").equal(street).asList());
        System.out.printf("Ways: %s\n", ds.getDatastore().find(Way.class).field("tags.addr:street").equal(street).asList());

        Relation rel = ds.getDatastore().find(Relation.class).field("tags.addr:street").equal(street).get();
//        System.out.printf("Relations: %s\n", rel);

        // all relations with role=street
        //System.out.printf("role=street: %s\n", ds.getDatastore().find(Relation.class).field("members.role").equal("street").asList());

        // filter out only street relations
        CollectionUtils.filter(rel.getMembers(), new Predicate() {
            @Override
            public boolean evaluate(Object o) {
                if(!(o instanceof Member)) return false;
                Member m = (Member) o;
                return "street".equals(m.getRole());
            }
        });
        System.out.printf("Relations: %s\n", rel);

        // display all ways for the relation
        CollectionUtils.forAllDo(rel.getMembers(), new Closure() {
            @Override
            public void execute(Object input) {
                if(!(input instanceof Member)) return;
                Member m = (Member) input;
                if(!"way".equals(m.getType())) return;
                try {
                    System.out.printf("w: %s\n", ds.getDatastore().find(Way.class).field("_id").equal(m.getRef()).get());
                } catch (StorageException e) {
                    e.printStackTrace();
                }
            }
        });

//        Set<String> rolesForStreet = new HashSet<String>();
//        for(Member m : rel.getMembers()){
//            rolesForStreet.add(m.getRole());
//        }
//        System.out.printf("Distinct roles: %s\n", rolesForStreet);

    }
}
