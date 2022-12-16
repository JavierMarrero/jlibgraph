package cu.edu.cujae.graphy.tests.algorithms;

import cu.edu.cujae.graphy.algorithms.PathDetection;
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;

public class PathDetectionTest
{

    public static void main(String[] args)
    {
        Graph<Integer> graph = GraphBuilders.makeSimpleGraph(true);

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

        System.out.println(new PathDetection<>(graph, 1, 3).apply().get());
    }
}
