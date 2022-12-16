/*
 * Copyright (C) 2022 Javier Marrero.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package cu.edu.cujae.graphy.tests;

import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;
import cu.edu.cujae.graphy.swing.JGraphView;
import java.awt.Dimension;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Javier Marrero
 */
public class JGraphViewTest
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Graph<Integer> graph = GraphBuilders.makeSimpleGraph(true);
        for (int i = 0; i < 10; i++)
        {
            graph.add(i);
        }

        Random r = new Random();
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 3; ++j)
            {
                graph.connect(i, r.nextInt(10));
            }
        }

        System.out.println(graph);

        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new JFrame("JGraphView test");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                frame.setPreferredSize(new Dimension(800, 600));
                JGraphView jGraphView = new JGraphView();

                frame.add(jGraphView);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

}
