package org.osm.project.helpers;

import com.db.tpm.dao.StorageException;
import com.google.code.morphia.query.Query;
import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.osm.project.model.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 12/07/2011
 */
public class EntityHelper extends MongoHelper{

    public EntityHelper() throws StorageException {
        super();
    }

    public Collection<Way> findWaysForNode(Node node){
        return mongo.createQuery(Way.class).disableValidation()
                            .filter("nodes.$id", node.getId()).asList();
    }

    public Collection<Relation> findRelationsForNode(Node node){
        return mongo.createQuery(Relation.class).disableValidation()
                            .filter("members.ref", node.getId()).asList();
    }

    public Collection<Relation> findRelationsForWay(Way way){
        return mongo.createQuery(Relation.class).disableValidation()
                            .filter("members.ref", way.getId()).asList();
    }

    public Collection<Node> findNearestBusStopsForWay(Way way){
        Set<Node> nearestBusStops = new HashSet<Node>();
        for(Node n : way.getNodes()){
            if("bus_stop".equals(n.getTags().get("highway"))){
                nearestBusStops.add(n);
            }
            else{
                Query<Node> nearestQuery = mongo.createQuery(Node.class).disableValidation();
                nearestQuery.and(
                        nearestQuery.criteria("location").near(n.getLat(), n.getLon()),
                        nearestQuery.criteria("tags.highway").equal("bus_stop")
                        );
                // TODO: limit=5 is test parameter and can be optimized
                nearestBusStops.addAll(nearestQuery.limit(5).asList());
            }
        }
        return nearestBusStops;
    }

    public Collection<Way> getRelationWays(Relation relation){
        final Collection<Way> output = new HashSet<Way>();
        for(Member m : relation.getMembers()){
            if("way".equals(m.getRole())){
                output.add(mongo.createQuery(Way.class).filter("_id", m.getRef()).get());
            }
        }
        return output;
    }

    public Collection<Node> getRelationNodes(Relation relation){
        final Collection<Node> output = new HashSet<Node>();
        for(Member m : relation.getMembers()){
            if("node".equals(m.getRole())){
                output.add(mongo.createQuery(Node.class).filter("_id", m.getRef()).get());
            }
        }
        return output;
    }

    public Collection<Entity> getRelationEntities(Relation relation){
        final Collection<Entity> output = new HashSet<Entity>();
        for(Member m : relation.getMembers()){
            if("way".equals(m.getRole())){
                output.add(mongo.createQuery(Way.class).filter("_id", m.getRef()).get());
            }
            if("node".equals(m.getRole())){
                output.add(mongo.createQuery(Node.class).filter("_id", m.getRef()).get());
            }
        }
        return output;
    }

}
