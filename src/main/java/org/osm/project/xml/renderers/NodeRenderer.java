package org.osm.project.xml.renderers;

import org.osm.project.model.Node;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * @author Maxim Galushka
 * @since 10.07.11
 */
public class NodeRenderer implements Renderer<Node> {

    @Override
    public void render(XMLStreamWriter writer, Node model) throws RenderException {
        try {
            writer.writeStartElement("node");
            //id="10980673" lat="50.4347936" lon="30.5048661" user="yurkoy" uid="79838" visible="true" version="8" changeset="4015721" timestamp="2010-03-02T10:49:04Z"
            String id = Long.toString(model.getId());
            writer.writeAttribute("id", id);
            writer.writeAttribute("lat", Double.toString(model.getLat()));
            writer.writeAttribute("lon", Double.toString(model.getLon()));
//            writer.writeAttribute("user", "mgalushka");
//            writer.writeAttribute("uid", id);
            writer.writeAttribute("visible", "true");
            writer.writeAttribute("version", "1");
//            writer.writeAttribute("changeset", "1");
//            writer.writeAttribute("timestamp", "2010-03-02T10:49:04Z");

            writer.writeEndElement();
        } catch (XMLStreamException e) {
            e.printStackTrace();
            throw new RenderException(e);
        }
    }
}
