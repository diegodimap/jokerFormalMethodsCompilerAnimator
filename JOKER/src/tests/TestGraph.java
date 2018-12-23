package tests;

import graphs.BoxGraph;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jgraph.JGraph;

/**
 * Class for testing the several graphics used along the development.
 * @author Diego Henrique Oliveira de Souza
 */
public class TestGraph {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(500, 500);
        JPanel p = new JPanel();
        p.setSize(500, 500);

        BoxGraph g = new BoxGraph();
        //g.montarGrafico();

        JGraph jg = g.getJgraph();

        p.add(jg);
        
        f.add(p);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
