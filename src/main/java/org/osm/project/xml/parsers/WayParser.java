package org.osm.project.xml.parsers;

import org.osm.project.model.Node;
import org.osm.project.model.Tag;
import org.osm.project.model.Way;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * @author Maxim Galushka
 * @since 26.06.11
 */
public class WayParser implements Parser<Way> {

    @Override
    public Way parse(XMLStreamReader reader) throws ParseException{
        Long id = Long.parseLong(reader.getAttributeValue("", "id"));

        Way result = new Way(id);

        try {
            while (true) {
                int event = reader.next();
                if (event == XMLStreamConstants.END_ELEMENT) {
                    if("way".equals(reader.getLocalName())){
                        break;
                    }
                }
                if (event == XMLStreamConstants.START_ELEMENT) {
                    if("nd".equals(reader.getLocalName())){
                        result.addNode(new Node(
                                Long.parseLong(reader.getAttributeValue("", "ref"))));
                    }
                    if("tag".equals(reader.getLocalName())){
                        if("tag".equals(reader.getLocalName())){
                            result.addTag(reader.getAttributeValue("", "k"),
                                reader.getAttributeValue("", "v"));
                        }
                    }
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
            throw new ParseException(e);
        }

        return result;
    }
}
