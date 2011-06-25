package org.osm.project.calc;

import java.util.Comparator;

/**
 * @author Maxim Galushka
 * @since 25.06.11
 */
public class NodeComparator implements Comparator<Node>{

    @Override
    public int compare(Node first, Node second) {
        if(first.getPriority() == second.getPriority()) return 0;
        return first.getPriority() - second.getPriority() > 0 ? 1 : -1;
    }
}
