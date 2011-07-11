package org.osm.project.xml.renderers;

import org.osm.project.model.*;

/**
 * @author Maxim Galushka
 * @since 10.07.11
 */
public class RendererFactory {

    public Renderer getRenderer(Entity model){
        if(model instanceof Node) return new NodeRenderer();
        if(model instanceof Way) return new WayRenderer();
        if(model instanceof Relation) return new RelationRenderer();
        throw new RuntimeException("Cannot create renderer");
    }
}
