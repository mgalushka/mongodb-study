package org.osm.project.xml.renderers;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 11/07/2011
 */
public class TagRenderer implements Renderer<Tag>{

    @Override
    public void render(XMLStreamWriter writer, Tag model) throws RenderException {
        try {
            writer.writeStartElement("tag");
            writer.writeAttribute("k", model.getName());
            writer.writeAttribute("v", model.getValue());
            writer.writeEndElement();
        } catch (XMLStreamException e) {
            e.printStackTrace();
            throw new RenderException(e);
        }
    }
}
