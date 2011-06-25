package org.osm.project.xml.parsers;

import javax.xml.stream.XMLStreamReader;

/**
 * @author Maxim Galushka
 * @since 26.06.11
 */
public interface Parser <T> {

    /**
     * Parses XML to object
     *
     * @param reader XML reader stream to parse from
     * @return parsed object
     * @throws ParseException if exception
     */
    public T parse(XMLStreamReader reader) throws ParseException;
}
