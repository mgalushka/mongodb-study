package org.osm.project.xml;


import com.db.tpm.dao.DatabaseStorage;
import com.db.tpm.dao.MongoDatabaseStorage;
import com.db.tpm.dao.StorageException;
import org.osm.project.model.Node;
import org.osm.project.xml.parsers.NodeParser;
import org.osm.project.xml.parsers.WayParser;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;

/**
 * @author Maxim Galushka
 * @since 25.06.11
 */
public class ModelBuilder {

    private DatabaseStorage ds;

    public ModelBuilder() {
        ds = new MongoDatabaseStorage();
    }

    private XMLStreamReader getReader(File osm)
            throws FileNotFoundException, XMLStreamException {

        InputStream in = new BufferedInputStream(new FileInputStream(osm));
        XMLInputFactory factory = XMLInputFactory.newInstance();
        return factory.createXMLStreamReader(in);
    }

    public void processOsm(File osm)
            throws FileNotFoundException, XMLStreamException, StorageException {

        XMLStreamReader reader = getReader(osm);

        while (true) {
            int event = reader.next();
            if (event == XMLStreamConstants.END_DOCUMENT) {
               reader.close();
               break;
            }
            if (event == XMLStreamConstants.START_ELEMENT) {
                if("node".equals(reader.getLocalName())){
                    ds.save(new NodeParser().parse(reader));
                }
                if("way".equals(reader.getLocalName())){
                    ds.save(new WayParser().parse(reader));
                }
            }
        }
        ds.close();
    }
}
