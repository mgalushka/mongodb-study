package org.osm.project.xml;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.osm.project.model.Entity;
import org.osm.project.xml.renderers.RenderException;
import org.osm.project.xml.renderers.Renderer;
import org.osm.project.xml.renderers.RendererFactory;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Used for creating osm xml files build from domain model
 *
 * @author Maxim Galushka
 * @since 10.07.11
 */
public class ModelRenderer {

    private static final String MIN_LAT = "50.4048000";
    private static final String MIN_LON = "30.5036000";
    private static final String MAX_LAT = "50.4453000";
    private static final String MAX_LON = "30.5678000";


    private XMLStreamWriter getWriter(File file) throws IOException, XMLStreamException {
        XMLOutputFactory xmlof = XMLOutputFactory.newInstance();
        return xmlof.createXMLStreamWriter(new FileWriter(file));
    }

    @SuppressWarnings("unchecked")
    public void buildOsm(List<? extends Entity> model, File output) throws RenderException {
        try {
            final XMLStreamWriter xmlw = getWriter(output);
            xmlw.writeStartDocument();
            // Now start with root element
            xmlw.writeStartElement("osm");

            xmlw.writeAttribute("version","0.6");
            xmlw.writeAttribute("generator","mongodb-study-1.0");

            xmlw.writeEmptyElement("bounds");
            xmlw.writeAttribute("minlat", MIN_LAT);
            xmlw.writeAttribute("minlon", MIN_LON);
            xmlw.writeAttribute("maxlat", MAX_LAT);
            xmlw.writeAttribute("maxlon", MAX_LON);

            final RendererFactory rf = new RendererFactory();
            CollectionUtils.forAllDo(model, new Closure() {
                @Override
                public void execute(Object input) {
                    Entity model = (Entity) input;
                    Renderer r = rf.getRenderer(model);
                    try {
                        r.render(xmlw, model);
                    } catch (RenderException e) {
                        e.printStackTrace();
                    }
                }
            });

            xmlw.writeEndDocument();
            // Close the writer to flush the output
            xmlw.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RenderException(e);
        }
    }
}
