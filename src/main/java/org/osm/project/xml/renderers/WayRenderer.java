package org.osm.project.xml.renderers;

import org.osm.project.model.Node;
import org.osm.project.model.Way;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.Map;

/**
 * @author Maxim Galushka
 * @since 10.07.11
 */
public class WayRenderer implements Renderer<Way> {

    @Override
    public void render(XMLStreamWriter writer, Way model) throws RenderException {
        try {
            writer.writeStartElement("way");
            String id = Long.toString(model.getId());
            writer.writeAttribute("id", id);
            writer.writeAttribute("visible", "true");
            writer.writeAttribute("version", "1");

            for(Node node : model.getNodes()){
                writer.writeEmptyElement("nd");
                writer.writeAttribute("ref", Long.toString(node.getId()));
            }

            TagRenderer tr = new TagRenderer();
            for(Map.Entry<String, String> entry : model.getTags().entrySet()){
                tr.render(writer, new Tag(entry.getKey(), entry.getValue()));
            }

            writer.writeEndElement();

            //TODO: render all nodes for ways - may be duplications!!!
            NodeRenderer nr = new NodeRenderer();
            for(Node node : model.getNodes()){
                nr.render(writer, node);
            }

        } catch (XMLStreamException e) {
            e.printStackTrace();
            throw new RenderException(e);
        }
    }
}
