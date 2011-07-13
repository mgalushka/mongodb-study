package org.osm.project;

import com.db.tpm.dao.DatabaseStorage;
import com.db.tpm.dao.MongoDatabaseStorage;
import com.db.tpm.dao.StorageException;
import com.google.code.morphia.Datastore;
import org.osm.project.helpers.StreetHelper;
import org.osm.project.model.Entity;
import org.osm.project.model.Node;
import org.osm.project.xml.ModelRenderer;
import org.osm.project.xml.renderers.RenderException;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 12/07/2011
 */
public class StreetExport {

//    public static final String STREET = "Лесі Українки бульвар";

    private static final String DEFAULT_FILE = "E:\\Projects\\mongodb-study\\examples\\out-street-test.osm";
    public static final String STREET = "Возз'єднання проспект";

    public static void main(String[] args) throws StorageException, RenderException {
        ModelRenderer mr = new ModelRenderer();

        File output = new File((args.length > 0) ? args[0] : DEFAULT_FILE);
        StreetHelper sh = new StreetHelper();

        Collection<Entity> streets = new HashSet<Entity>();
        streets.addAll(sh.findStreet("Возз'єднання проспект"));
        streets.addAll(sh.findStreet("Возз’єднання проспект"));
        streets.addAll(sh.findStreet("Соборності проспект"));
        streets.addAll(sh.findStreet("Березняківска вулиця"));
        streets.addAll(sh.findStreet("Березняковская"));


        DatabaseStorage ds = new MongoDatabaseStorage();
        Datastore mongo = ds.getDatastore();

//        List<Node> busStops = mongo.find(Node.class, "tags.highway", "bus_stop").limit(1).asList();
//        streets.addAll(mongo.find(Node.class, "tags.highway", "bus_stop").asList());

        mr.buildOsm(streets, output);
    }
}
