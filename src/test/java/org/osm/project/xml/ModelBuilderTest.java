package org.osm.project.xml;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * @author Maxim Galushka
 * @since 25.06.11
 */
public class ModelBuilderTest {

    private ModelBuilder mb;

    @Before
    public void setUp() throws Exception {
        mb = new ModelBuilder();
    }

    @After
    public void tearDown() throws Exception {
        mb = null;
    }

    @Test
    public void testProcessOsm() throws Exception {
        String path = "E:\\Projects\\mongodb-study\\examples\\map.osm";
        mb.processOsm(new File(path));
    }
}
