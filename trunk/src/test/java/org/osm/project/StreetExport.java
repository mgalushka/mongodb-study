package org.osm.project;

import com.db.tpm.dao.StorageException;
import org.osm.project.helpers.StreetHelper;
import org.osm.project.model.Entity;
import org.osm.project.xml.ModelRenderer;
import org.osm.project.xml.renderers.RenderException;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 12/07/2011
 */
public class StreetExport {

//    public static final String STREET = "Лесі Українки бульвар";
    public static final String STREET = "Возз'єднання проспект";

    public static void main(String[] args) throws StorageException, RenderException {
        ModelRenderer mr = new ModelRenderer();
        File output = new File("E:\\Projects\\Eclipse\\mongodb-study\\examples\\out-street-test.osm");

        StreetHelper sh = new StreetHelper();

        Collection<Entity> streets = new HashSet<Entity>();
        streets.addAll(sh.findStreet("Возз'єднання проспект"));
        streets.addAll(sh.findStreet("Возз’єднання проспект"));
        streets.addAll(sh.findStreet("Соборності проспект"));
        //streets.addAll(sh.findStreet("Березняківска вулиця"));

        mr.buildOsm(streets, output);
    }
}
