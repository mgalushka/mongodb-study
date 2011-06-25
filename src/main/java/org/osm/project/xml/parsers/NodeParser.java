package org.osm.project.xml.parsers;

import org.osm.project.model.Node;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * @author Maxim Galushka
 * @since 26.06.11
 */
public class NodeParser implements Parser<Node> {

    @Override
    public Node parse(XMLStreamReader reader) throws ParseException{
        Long id = Long.parseLong(reader.getAttributeValue("", "id"));
        Double lat = Double.parseDouble(reader.getAttributeValue("", "lat"));
        Double lon = Double.parseDouble(reader.getAttributeValue("", "lon"));

        Node result = new Node(id, lat, lon);

        try {
            while (true) {
                int event = reader.next();
                if (event == XMLStreamConstants.END_ELEMENT) {
                    if("node".equals(reader.getLocalName())){
                        break;
                    }
                }
                if (event == XMLStreamConstants.START_ELEMENT) {
                    if("tag".equals(reader.getLocalName())){
                        result.addTag(reader.getAttributeValue("", "k"),
                                reader.getAttributeValue("", "v"));
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
