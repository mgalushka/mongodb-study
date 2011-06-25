package org.osm.project.xml.parsers;

/**
 * @author Maxim Galushka
 * @since 26.06.11
 */
public class ParseException extends RuntimeException{
    private static final long serialVersionUID = -3101416089828549119L;

    public ParseException() {
        super();
    }

    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseException(Throwable cause) {
        super(cause);
    }
}
