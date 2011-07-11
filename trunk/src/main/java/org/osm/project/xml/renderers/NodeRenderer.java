package org.osm.project.xml.renderers;

import org.osm.project.model.Node;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.Map;

/**
 * @author Maxim Galushka
 * @since 10.07.11
 */
public class NodeRenderer implements Renderer<Node> {

    @Override
    public void render(XMLStreamWriter writer, Node model) throws RenderException {
        try {
            writer.writeStartElement("node");
            String id = Long.toString(model.getId());
            writer.writeAttribute("id", id);
            writer.writeAttribute("lat", Double.toString(model.getLat()));
            writer.writeAttribute("lon", Double.toString(model.getLon()));
            writer.writeAttribute("visible", "true");
            writer.writeAttribute("version", "1");

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
