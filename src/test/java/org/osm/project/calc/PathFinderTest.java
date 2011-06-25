package org.osm.project.calc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author Maxim Galushka
 * @since 25.06.11
 */
public class PathFinderTest {

    private PathFinder pf;

    private Node root;

    @Before
    public void setUp() throws Exception {
        pf = new PathFinder();
        root = new Node(1);
        root.setCoordinates(0,0);

        Node n_10 = new Node(2);
        n_10.setCoordinates(1,0);

        Node n_20 = new Node(3);
        n_20.setCoordinates(2,0);

        Node n_11 = new Node(4);
        n_11.setCoordinates(1,1);

        Node n_21 = new Node(5);
        n_21.setCoordinates(2,1);

        Node n_22 = new Node(6);
        n_22.setCoordinates(2,2);

        root.setNext(n_10);
        n_10.setNext(n_11, n_20, n_21);
        n_11.setNext(n_21);
        n_20.setNext(n_21);
        n_21.setNext(n_22);

    }

    @After
    public void tearDown() throws Exception {
         pf = null;
    }

    @Test
    public void testFindPath() throws Exception {
        System.out.println(pf.findPath(root, new Node(6, 2, 2)));
    }
}
