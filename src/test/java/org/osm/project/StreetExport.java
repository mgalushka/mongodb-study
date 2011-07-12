package org.osm.project;

import com.db.tpm.dao.StorageException;
import org.osm.project.helpers.StreetHelper;
import org.osm.project.xml.ModelRenderer;
import org.osm.project.xml.renderers.RenderException;

import java.io.File;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 12/07/2011
 */
public class StreetExport {

    public static final String STREET = "Лесі Українки бульвар";

    public static void main(String[] args) throws StorageException, RenderException {
        ModelRenderer mr = new ModelRenderer();
        File output = new File("E:\\Projects\\Eclipse\\mongodb-study\\examples\\out-street-test.osm");

        mr.buildOsm(new StreetHelper().findStreet(STREET), output);
    }
}
