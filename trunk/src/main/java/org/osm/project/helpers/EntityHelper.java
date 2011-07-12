package org.osm.project.helpers;

import com.db.tpm.dao.StorageException;
import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.osm.project.model.*;

import java.util.Collection;
import java.util.HashSet;

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

    public Collection<Way> getRelationWays(Relation relation){
        final Collection<Way> output = new HashSet<Way>();
        CollectionUtils.forAllDo(relation.getMembers(), new Closure() {
            @Override
            public void execute(Object o) {
                Member m = (Member) o;
                output.add(mongo.createQuery(Way.class).filter("_id", m.getRef()).get());
            }
        });
        return output;
    }

    public Collection<Node> getRelationNodes(Relation relation){
        final Collection<Node> output = new HashSet<Node>();
        CollectionUtils.forAllDo(relation.getMembers(), new Closure() {
            @Override
            public void execute(Object o) {
                Member m = (Member) o;
                output.add(mongo.createQuery(Node.class).filter("_id", m.getRef()).get());
            }
        });
        return output;
    }

}
