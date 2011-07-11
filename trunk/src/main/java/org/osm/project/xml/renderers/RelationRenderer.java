package org.osm.project.xml.renderers;

import org.osm.project.model.Member;
import org.osm.project.model.Relation;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.Map;

/**
 * @author Maxim Galushka
 * @since 10.07.11
 */
public class RelationRenderer implements Renderer<Relation> {

    @Override
    public void render(XMLStreamWriter writer, Relation model) throws RenderException {
        try {
            writer.writeStartElement("relation");
            String id = Long.toString(model.getId());
            writer.writeAttribute("id", id);
            writer.writeAttribute("visible", "true");

            MemberRenderer mr = new MemberRenderer();
            for(Member member : model.getMembers()){
                mr.render(writer, member);
            }

            TagRenderer tr = new TagRenderer();
            for(Map.Entry<String, String> entry : model.getTags().entrySet()){
                tr.render(writer, new Tag(entry.getKey(), entry.getValue()));
            }

            writer.writeEndElement();
        } catch (XMLStreamException e) {
            e.printStackTrace();
            throw new RenderException(e);
        }
    }
}
