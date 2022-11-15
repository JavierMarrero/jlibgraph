package cu.edu.cujae.graphy.tests.algorithms;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import cu.edu.cujae.graphy.algorithms.PathDetection;
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;

public class PathDetectionTest {

    private Graph<Integer> graph;

    private void initialize(){
        graph = GraphBuilders.makeSimpleGraph(true);

        graph.add(0);
        graph.add(1);
        graph.add(2);
        graph.add(3);
        graph.add(4);
        graph.add(5);

        graph.connect(0, 1);
        graph.connect(0, 3);
        graph.connect(0, 4);
        graph.connect(1, 2);
        graph.connect(1, 4);
        graph.connect(4, 5);
    }

    @Test
    public void testTruePath(){
        initialize();
        assertTrue(new PathDetection<>(graph, 0, 4).apply().get());
    }

    @Test
    public void testDeadNode(){
        initialize();
        assertFalse(new PathDetection<>(graph, 3, 4).apply().get());
    }

    @Test 
    public void testBackTracking(){
        initialize();
        assertTrue(new PathDetection<>(graph, 0, 5).apply().get());
    }
}
