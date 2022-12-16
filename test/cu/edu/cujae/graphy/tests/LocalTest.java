package cu.edu.cujae.graphy.tests;

import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;
import java.util.Iterator;

public class LocalTest
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
        graph.connect(0, 4);
        graph.connect(0, 3);
        graph.connect(1, 4);
        graph.connect(1, 2);
        graph.connect(4, 5);

        Iterator<Integer> iter = graph.iterator(0);
        while (iter.hasNext())
        {
            System.out.println(iter.next());
        }
    }

}
