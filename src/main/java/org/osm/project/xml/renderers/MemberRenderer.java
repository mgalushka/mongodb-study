package org.osm.project.xml.renderers;

import org.osm.project.model.Member;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.Map;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 11/07/2011
 */
public class MemberRenderer implements Renderer<Member>{

    @Override
    public void render(XMLStreamWriter writer, Member model) throws RenderException {
        try {
            writer.writeEmptyElement("member");
            writer.writeAttribute("type", model.getType());
            writer.writeAttribute("ref", Long.toString(model.getRef()));
            writer.writeAttribute("role", model.getRole());
        } catch (XMLStreamException e) {
            e.printStackTrace();
            throw new RenderException(e);
        }
    }
}
