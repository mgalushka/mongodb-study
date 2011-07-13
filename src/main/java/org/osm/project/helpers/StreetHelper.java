package org.osm.project.helpers;

import com.db.tpm.dao.StorageException;
import com.google.code.morphia.query.Query;
import org.osm.project.model.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 12/07/2011
 */
public class StreetHelper extends MongoHelper{

    private EntityHelper entityHelper = new EntityHelper();

    public StreetHelper() throws StorageException {
        super();
    }

    /**
     * Retrieves all information related somehow to the street
     *
     * @param name street name
     * @return list of entities representing a street
     * @throws com.db.tpm.dao.StorageException is exception
     */
    public Collection<Entity> findStreet(String name) throws StorageException {
        final Set<Entity> street = new HashSet<Entity>();

        // relations
        List<Relation> relations = mongo.find(Relation.class).field("tags.addr:street").equal(name).asList();

        Query<Relation> byType = mongo.createQuery(Relation.class);
        byType.or(
                byType.criteria("tags.type").equal("street"),
                byType.criteria("tags.type").equal("associatedStreet")
        );
        relations.addAll(byType.asList());

        street.addAll(relations);
        for(Relation r : relations){
            street.addAll(findStreet(r));
        }

        // ways
        Set<Way> ways = new HashSet<Way>();
        ways.addAll(mongo.find(Way.class).field("tags.addr:street").equal(name).asList());

        Query waysQuery = mongo.find(Way.class);
        waysQuery.or(
                byType.criteria("tags.highway").exists(),
                byType.criteria("tags.living_street").exists()
        );
        waysQuery.field("tags.name").equal(name);

        ways.addAll(waysQuery.asList());

        for(Way w : ways){
            street.addAll(w.getNodes());
        }

        street.addAll(ways);

        // nodes
        street.addAll(mongo.find(Node.class).field("tags.addr:street").equal(name).asList());
        for(Way w : ways){
            if(w.getTags().containsKey("highway")){
                street.addAll(entityHelper.findNearestBusStopsForWay(w));
            }
        }
        return street;
    }

    /**
     * Find all street objects related to current relation
     * @param relation relation
     * @return all street objects
     * @throws StorageException is exception
     */
    public Collection<Entity> findStreet(Relation relation) throws StorageException {
        final Set<Node> nodes = new HashSet<Node>();
        final Set<Way> ways = new HashSet<Way>();

        nodes.addAll(entityHelper.getRelationNodes(relation));
        ways.addAll(entityHelper.getRelationWays(relation));

        for(Way w : ways) {
            nodes.addAll(w.getNodes());
        }

        Set<Entity> all = new HashSet<Entity>();
        all.addAll(nodes);
        all.addAll(ways);
        return all;
    }

}
