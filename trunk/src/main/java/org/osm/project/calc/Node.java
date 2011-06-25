package org.osm.project.calc;

import java.util.*;

/**
 * Node used for calculations for A* algorithm
 *
 * @author Maxim Galushka
 * @since 24.06.11
 */
public class Node {

    private long id;
    private Set<Long> buses = new HashSet<Long>();
    private List<Node> next = new ArrayList<Node>();

    private double lat;
    private double lon;

    private Node previous = null;

    private long costFromStart = 0L;
    private long heuristic = 0L;


    public Node() {
    }

    public Node(long id) {
        this.id = id;
    }

    public Node(long id, double lat, double lon) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Long> getBuses() {
        return buses;
    }

    public void setBuses(Set<Long> buses) {
        this.buses = buses;
    }

    public List<Node> getNext() {
        return next;
    }

    public void setNext(List<Node> next) {
        this.next = next;
    }

    public void setNext(Node... next) {
        this.next = Arrays.asList(next);
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public long getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(long heuristic) {
        this.heuristic = heuristic;
    }

    public long getCostFromStart() {
        return costFromStart;
    }

    public void setCostFromStart(long costFromStart) {
        this.costFromStart = costFromStart;
    }

    public long getPriority(){
        return costFromStart + heuristic;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setCoordinates(double lat, double lon){
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (id != node.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", lat=" + lat +
                ", lon=" + lon +
                "}";
    }
}
