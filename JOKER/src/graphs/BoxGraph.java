package graphs;

import gui.JokerWindow;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;
import xml.XMLNode;

/**
 * This graphic represents the animation in with boxes. These boxes are movable,
 * and are organized in a vertical manner. The top node is the first one, and the
 * node at the bottom is the last one in the trace.
 * @author Diego Henrique Oliveira de Souza
 */
public class BoxGraph {

    private static final Dimension DEFAULT_SIZE = new Dimension(800, 80000);
    private JGraphModelAdapter m_jgAdapter;
    private JGraph jgraph = null;

    /**
     * @see java.applet.Applet#init().
     */
    public void montarGrafico(ArrayList<XMLNode> nos) {
        // create a JGraphT graph
        ListenableGraph g = new ListenableDirectedGraph(DefaultEdge.class);

        // create a visualization using JGraph, via an adapter
        m_jgAdapter = new JGraphModelAdapter(g);
        jgraph = new JGraph(m_jgAdapter);
        adjustDisplaySettings(jgraph);

        // add some sample data (graph manipulated via JGraphT)

        //nos.add(new XMLNode("", "SKIP", ""));

        for (XMLNode no : nos) {
            g.addVertex(no.getEvent());
        }

        g.addVertex("SKIP");

        String predicado = "";
        for (int i = 0; i < nos.size(); i++) {

            for (int j = 0; j < i; j++) {
                predicado += " ";
            }

            if(i == nos.size()-1){
                g.addEdge(nos.get(i).getEvent(), "SKIP");
            }else{
                g.addEdge(nos.get(i).getEvent(), nos.get(i+1).getEvent());
            }
        }

        int x = 50;
        int y = 50;
        for (XMLNode no : nos) {
            positionVertexAt(no.getEvent(), x, y);
            //x += 80;
            y += 90;
        }

        positionVertexAt("SKIP", x, y);

        //this.positionVertexAt(nos.get(nos.size()-1), x, y);

        JokerWindow.tracesScroll.getVerticalScrollBar().setValue(y-180);

        JokerWindow.tracePanel.updateUI();
        JokerWindow.tracePanel.setVisible(true);

    }

    private void adjustDisplaySettings(JGraph jg) {
        jg.setPreferredSize(DEFAULT_SIZE);
        JPanel jp = new JPanel();
        jg.setBackground(jp.getBackground());
    }

    private void positionVertexAt(Object vertex, int x, int y) {
        DefaultGraphCell cell = m_jgAdapter.getVertexCell(vertex);

        Map attr = cell.getAttributes();
        Rectangle2D b = GraphConstants.getBounds(attr);

        //2 = FUNDO
        //5 = FONTE
//        for (Object object : attr.values()) {
//            System.out.println("MAP= " + object.toString());
//        }

        //LARGURA
        //ALTURA
        GraphConstants.setBounds(attr, new Rectangle(x, y, 200, (int) b.getHeight()));

        Map cellAttr = new HashMap();
        cellAttr.put(cell, attr);
        m_jgAdapter.edit(cellAttr, null, null, null);
    }

    public JGraph getJgraph() {
        return jgraph;
    }

    public void setJgraph(JGraph jgraph) {
        this.jgraph = jgraph;
    }
}
