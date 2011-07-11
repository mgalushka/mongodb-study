package org.osm.project.xml.renderers;

import javax.xml.stream.XMLStreamWriter;

/**
 * @author Maxim Galushka
 * @since 10.07.11
 */
public interface Renderer <T> {

    /**
     * Renders domain object and writes it to XML writer
     *
     * @param writer XML writer stream to render to
     * @param model object to render to stream
     * @throws RenderException if exception
     */
    public void render(XMLStreamWriter writer, T model) throws RenderException;
}
