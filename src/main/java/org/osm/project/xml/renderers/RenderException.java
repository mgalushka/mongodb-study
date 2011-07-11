package org.osm.project.xml.renderers;

/**
 * @author Maxim Galushka
 * @since 10.07.11
 */
public class RenderException extends Exception {
    private static final long serialVersionUID = -1448302608736056364L;

    public RenderException() {
        super();
    }

    public RenderException(String message) {
        super(message);
    }

    public RenderException(String message, Throwable cause) {
        super(message, cause);
    }

    public RenderException(Throwable cause) {
        super(cause);
    }
}
