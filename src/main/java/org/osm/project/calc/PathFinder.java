package org.osm.project.calc;

import java.util.*;

/**
 * @author Maxim Galushka
 * @since 24.06.11
 */
public class PathFinder {

    //private Map<Integer, Node> nodes = new HashMap<Integer, Node>();

    public PathFinder() {
    }

//    public Map<Integer, Node> getNodes() {
//        return nodes;
//    }
//
//    public void setNodes(Map<Integer, Node> nodes) {
//        this.nodes = nodes;
//    }

    public List<Node> findPath(Node s, Node f){
        List<Node> closed = new ArrayList<Node>();
        PriorityQueue<Node> open = new PriorityQueue<Node>(10, new NodeComparator());
        open.add(s);
        while(!open.isEmpty()){
            Node n = open.poll();
            if(n.equals(f)){
                return buildPath(n);
            }
            for(Node next : n.getNext()){
                long newCost = n.getCostFromStart() + calculateBusCost(n, next);
                if((open.contains(next) || closed.contains(next)) && n.getHeuristic() <= newCost){
                    continue;
                }
                next.setPrevious(n);
                next.setCostFromStart(newCost);
                next.setHeuristic(calculateHeuristic(next, f));
                if(closed.contains(next)) closed.remove(next);
                if(!open.contains(next)) open.add(next);
            }
            closed.add(n);
        }
        return null;
    }

    private List<Node> buildPath(Node end){
        List<Node> path = new LinkedList<Node>();

        Node current = end;
        path.add(current);
        while (current.getPrevious() != null){
            current = current.getPrevious();
            path.add(current);
        }

        Collections.reverse(path);
        return path;
    }

    private int calculateBusCost(Node a, Node b){
        double dlat = a.getLat() - b.getLat();
        double dlon = a.getLon() - b.getLon();
        return (int) Math.ceil(Math.sqrt(dlat*dlat + dlon*dlon)/20);
    }

    private int calculateHeuristic(Node current, Node end){
        double dlat = current.getLat() - end.getLat();
        double dlon = current.getLon() - end.getLon();
        return (int) Math.ceil(Math.sqrt(dlat*dlat + dlon*dlon));
    }
}
