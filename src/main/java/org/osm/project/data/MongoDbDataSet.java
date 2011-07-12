package org.osm.project.data;

import org.openstreetmap.osm.ConfigurationSection;
import org.openstreetmap.osm.data.IDataSet;
import org.openstreetmap.osm.data.Selector;
import org.openstreetmap.osm.data.WayHelper;
import org.openstreetmap.osm.data.coordinates.Bounds;
import org.openstreetmap.osm.data.coordinates.LatLon;
import org.openstreetmap.osmosis.core.domain.v0_6.Node;
import org.openstreetmap.osmosis.core.domain.v0_6.Relation;
import org.openstreetmap.osmosis.core.domain.v0_6.Way;

import java.util.Iterator;

/**
 * @author Maxim Galushka
 * @since 12.07.11
 */
public class MongoDbDataSet implements IDataSet{

    @Override
    public void addWay(Way way) {
        throw new UnsupportedOperationException("MongoDb data set supports readonly operations only");
    }

    @Override
    public boolean containsWay(Way way) {
        return false;
    }

    @Override
    public Iterator<Way> getWays(Bounds bounds) {
        return null;
    }

    @Override
    public void removeWay(Way way) {
        throw new UnsupportedOperationException("MongoDb data set supports readonly operations only");
    }

    @Override
    public WayHelper getWayHelper() {
        return null;
    }

    @Override
    public void addRelation(Relation relation) {

    }

    @Override
    public boolean containsRelation(Relation relation) {
        return false;
    }

    @Override
    public Iterator<Relation> getRelations(Bounds bounds) {
        return null;
    }

    @Override
    public Relation getRelationByID(long l) {
        return null;
    }

    @Override
    public void removeRelation(Relation relation) {
        throw new UnsupportedOperationException("MongoDb data set supports readonly operations only");
    }

    @Override
    public void addNode(Node node) {
        throw new UnsupportedOperationException("MongoDb data set supports readonly operations only");
    }

    @Override
    public boolean containsNode(Node node) {
        return false;
    }

    @Override
    public Iterator<Node> getNodes(Bounds bounds) {
        return null;
    }

    @Override
    public Way getWaysByID(long l) {
        return null;
    }

    @Override
    public Iterator<Way> getWaysByName(String s, Bounds bounds) {
        return null;
    }

    @Override
    public Iterator<Way> getWaysByTag(String s, String s1) {
        return null;
    }

    @Override
    public Iterator<Node> getNodesByTag(String s, String s1) {
        return null;
    }

    @Override
    public Node getNodeByID(long l) {
        return null;
    }

    @Override
    public void removeNode(Node node) {

    }

    @Override
    public Iterator<Way> getWaysForNode(long l) {
        return null;
    }

    @Override
    public Iterator<Node> getNodesByName(String s) {
        return null;
    }

    @Override
    public Node getNearestNode(LatLon latLon, Selector selector) {
        return null;
    }

    @Override
    public void shutdown() {

    }

    @Override
    public ConfigurationSection getSettings() {
        return null;
    }
}
