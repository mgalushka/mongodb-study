package org.osm.project;

import com.db.tpm.dao.DatabaseStorage;
import com.db.tpm.dao.MongoDatabaseStorage;
import com.db.tpm.dao.StorageException;
import org.osm.project.model.*;

/**
 * @author Maxim Galushka
 * @since 28.06.11
 */
public class RetrieveRelation {

    public static void main(String[] args) throws StorageException {
        DatabaseStorage ds = new MongoDatabaseStorage();

        Relation r = ds.getDatastore().find(Relation.class, "_id", 422376L).get();

        System.out.printf("Relation: %s\n", r);

        for(Member m : r.getMembers()){
            Taggable next = null;
            if("node".equals(m.getType())){
                next = ds.getDatastore().find(Node.class, "_id", m.getRef()).get();
            }
            if("way".equals(m.getType())){
                next = ds.getDatastore().find(Way.class, "_id", m.getRef()).get();
            }
            System.out.printf("Member: %s\n", next);
        }
    }
}
