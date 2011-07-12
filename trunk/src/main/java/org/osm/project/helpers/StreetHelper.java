package org.osm.project.helpers;

import com.db.tpm.dao.DatabaseStorage;
import com.db.tpm.dao.MongoDatabaseStorage;
import com.db.tpm.dao.StorageException;
import com.google.code.morphia.Datastore;
import com.mongodb.Mongo;
import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.osm.project.model.Entity;
import org.osm.project.model.Node;
import org.osm.project.model.Relation;
import org.osm.project.model.Way;

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
        final Set<Node> nodes = new HashSet<Node>();
        final Set<Way> ways = new HashSet<Way>();
        final Set<Relation> relations = new HashSet<Relation>();

        // relations
        relations.addAll(mongo.find(Relation.class).field("tags.addr:street").equal(name).asList());
        CollectionUtils.forAllDo(relations, new Closure() {
            @Override
            public void execute(Object o) {
                ways.addAll(entityHelper.getRelationWays((Relation) o));
            }
        });
        CollectionUtils.forAllDo(relations, new Closure() {
            @Override
            public void execute(Object o) {
                nodes.addAll(entityHelper.getRelationNodes((Relation) o));
            }
        });

        // ways
        ways.addAll(mongo.find(Way.class).field("tags.addr:street").equal(name).asList());
        CollectionUtils.forAllDo(ways, new Closure() {
            @Override
            public void execute(Object o) {
                nodes.addAll(((Way)o).getNodes());
            }
        });

        // nodes
        nodes.addAll(mongo.find(Node.class).field("tags.addr:street").equal(name).asList());

        Set<Entity> all = new HashSet<Entity>();
        all.addAll(nodes);
        all.addAll(ways);
        all.addAll(relations);

        return all;
    }
}
