package org.osm.project.xml.parsers;

import org.osm.project.model.Member;
import org.osm.project.model.Relation;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * @author Maxim Galushka
 * @since 26.06.11
 */
public class RelationParser implements Parser<Relation>{

    @Override
    public Relation parse(XMLStreamReader reader) throws ParseException {
        Long id = Long.parseLong(reader.getAttributeValue("", "id"));

        Relation result = new Relation(id);

        try {
            while (true) {
                int event = reader.next();
                if (event == XMLStreamConstants.END_ELEMENT) {
                    if("relation".equals(reader.getLocalName())){
                        break;
                    }
                }
                if (event == XMLStreamConstants.START_ELEMENT) {
                    if("member".equals(reader.getLocalName())){
                        result.addMember(new Member(
                                Long.parseLong(reader.getAttributeValue("", "ref")),
                                reader.getAttributeValue("", "role")));
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
