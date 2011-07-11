package org.osm.project.router;

import org.openstreetmap.osm.data.MemoryDataSet;
import org.openstreetmap.osm.data.NodeHelper;
import org.openstreetmap.osm.data.coordinates.LatLon;
import org.openstreetmap.osm.io.FileLoader;
//import org.openstreetmap.osmosis.core.domain.v0_6.Node;
import org.openstreetmap.travelingsalesman.routing.IRouter;
import org.openstreetmap.travelingsalesman.routing.Route;
import org.openstreetmap.travelingsalesman.routing.routers.DepthFirstRouter;
import org.openstreetmap.travelingsalesman.routing.routers.DijkstraRouter;
import org.openstreetmap.travelingsalesman.routing.routers.TurnRestrictedMultiTargetDijkstraRouter;
import org.openstreetmap.travelingsalesman.routing.selectors.Motorcar;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Maxim Galushka
 * @since 30.06.11
 */
public class AstarRouter {

    public static void main(String[] args) throws MalformedURLException {

        MemoryDataSet map = new FileLoader(
                        new File(new URL("file:///E:/Projects/mongodb-study/examples/ukraine.osm").getFile()))
                            .parseOsm();

//        MemoryDataSet map = (new org.openstreetmap.osm.io.BoundingBoxDownloader(lat0, lon0, lat1, lon1)).parseOsm();

        LatLon startCoord = new LatLon(50.4311977, 30.5948412);
//        Node startNode = NodeHelper.findNearestNode(map, startCoord);

        LatLon targetCoord = new LatLon(50.4351546, 30.613116);

//        Node targetNode = NodeHelper.findNearestNode(map, targetCoord);

        IRouter router = new DijkstraRouter();
//        Route theRoute = router.route(map, targetNode, startNode, new Motorcar());

//        for(Route.RoutingStep rs: theRoute.getRoutingSteps()){
//            System.out.println(String.format("Coordinates: %f - %f, %f metres",
//                    rs.getStartNode().getLatitude(), rs.getStartNode().getLongitude(), rs.distanceInMeters()));
//        }

    }
}
