package cu.edu.cujae.graphy.tests.algorithms;

import cu.edu.cujae.graphy.algorithms.PrimMinimalTree;
import cu.edu.cujae.graphy.core.WeightedGraph;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;
import cu.edu.cujae.graphy.core.utility.Weights;

public class PrimTest
{

    // Main Test
    public static void main(String[] args)
    {
        WeightedGraph<Character> graph = GraphBuilders.makeSimpleWeightedGraph(false);

        graph.add(0, 'a');
        graph.add(1, 'b');
        graph.add(2, 'c');
        graph.add(3, 'd');
        graph.add(4, 'e');
        graph.add(5, 'f');
        graph.add(6, 'g');
        graph.add(7, 'h');
        graph.add(8, 'i');

        // Outside
        graph.connect(0, 1, Weights.makeWeight(2));
        graph.connect(1, 2, Weights.makeWeight(3));
        graph.connect(2, 5, Weights.makeWeight(4));
        graph.connect(5, 8, Weights.makeWeight(5));
        graph.connect(8, 7, Weights.makeWeight(3));
        graph.connect(7, 6, Weights.makeWeight(4));
        graph.connect(6, 3, Weights.makeWeight(6));
        graph.connect(3, 0, Weights.makeWeight(1));

        // Crux
        graph.connect(3, 4, Weights.makeWeight(4));
        graph.connect(1, 4, Weights.makeWeight(2));
        graph.connect(5, 4, Weights.makeWeight(1));
        graph.connect(7, 4, Weights.makeWeight(2));

        // FirstInside
        graph.connect(0, 4, Weights.makeWeight(2));
        graph.connect(3, 1, Weights.makeWeight(3));

        // SecondInside
        graph.connect(1, 5, Weights.makeWeight(3));
        graph.connect(2, 4, Weights.makeWeight(4));

        // ThirdInside
        graph.connect(3, 7, Weights.makeWeight(5));
        graph.connect(4, 6, Weights.makeWeight(6));

        // ForthInside
        graph.connect(4, 8, Weights.makeWeight(3));
        graph.connect(5, 7, Weights.makeWeight(3));

        System.out.println(new PrimMinimalTree<>(graph).apply().get().toString());
    }
}
