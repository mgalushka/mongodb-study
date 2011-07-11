package org.osm.project.xml;

import com.db.tpm.dao.StorageException;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Usefull utility to import full Kiev osm map file to Mongo DB
 *
 * @author Maxim Galushka
 * @since 09.07.11
 */
public class Import {

    public static void main(String[] args)
            throws StorageException, XMLStreamException, FileNotFoundException {

        ModelBuilder mb = new ModelBuilder();
        String path = "E:\\Projects\\mongodb-study\\examples\\full-kiev.osm";
        mb.processOsm(new File(path));
    }
}
