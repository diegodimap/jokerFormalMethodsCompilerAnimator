package animation;

import graphs.BoxGraph;
import convert.XML2OBJ;
import graphs.BalloonGraph;
import graphs.ImagesGraph;
import gui.JokerWindow;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import org.jgraph.JGraph;
import xml.XMLNode;


/**
 * This class is responsible of presenting the buttons to the user. These buttons
 * represent the choices at a determined moment, provided by the animation of
 * a specific specification.
 * @author Diego Henrique Oliveira de Souza
 *
 */
public class AnimationPanel extends javax.swing.JPanel {

    private int counter = 1;
    //TREE
    private ArrayList<XMLNode> leaves = new ArrayList<XMLNode>(); //nós da árvore gráfica
    //XML
    public static ArrayList<XMLNode> nox = new ArrayList<XMLNode>();
    private static ArrayList<XMLNode> optXML = new ArrayList<XMLNode>();

    public AnimationPanel() {
        initComponents();
        this.setSize(300, 100);
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        this.updateUI();
    }//GEN-LAST:event_formMouseMoved
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private void montarArvore(XMLNode no) {
        XMLNode node = new XMLNode(no.getKey(), counter + " - " + no.getEvent(), no.getNext());
        counter++;
        JokerWindow.tracePanel.removeAll();

        JPanel p = new JPanel();

        leaves.add(node);

        if (JokerWindow.getOptions().getGraphType().equals("Boxes")) {
            BoxGraph g = new BoxGraph();
            g.montarGrafico(leaves);
            JGraph jg = g.getJgraph();
            p.add(jg);
        } else if (JokerWindow.getOptions().getGraphType().equals("Images")) {
            ImagesGraph v = new ImagesGraph(leaves, nox, 500, 500);
            p.add(v);
        } else {
            BalloonGraph gb = new BalloonGraph(leaves, 500, 500);
            p.add(gb);
        }

        JokerWindow.tracePanel.add(p);
        JokerWindow.tracePanel.setVisible(true);
        JokerWindow.tracePanel.updateUI();
    }

    public void animateXML() {
        JokerWindow.logIt("ANIMATING XML******************************");
        XML2OBJ xml = new XML2OBJ();
        xml.readXMLConvert2Obj(JokerWindow.areaXml.getText());

        nox = xml.getNox();

        StringBuilder logger = new StringBuilder("");
        for (XMLNode no : nox) {
            logger.append("NOX****************************** \n");
            logger.append("INIT:  ").append(no.isIsInit()).append("\n");
            logger.append("KEY:   ").append(no.getKey()).append("\n");
            logger.append("EVENT: ").append(no.getEvent()).append("\n");
            logger.append("NEXT:  ").append(no.getNext()).append("\n\n");
        }

        JokerWindow.logIt(logger.toString());

        //pega os nos iniciais
        getInitialNodesXML();

        //interage com o usuario
        getUserActionXML();

    }

    private void getInitialNodesXML() {
        optXML.clear();
        for (XMLNode no : nox) {
            if (no.isIsInit()) {
                optXML.add(no);
                JokerWindow.logIt("INITIAL = " + no.getKey());
                JokerWindow.logIt("NEXT = " + no.getNext());
            }
        }
    }

    private void getNextNodesXML(String next) {
        optXML.clear();
        for (XMLNode no : nox) {
            if (no.getKey().equals(next)) {
                optXML.add(no);
                JokerWindow.logIt("NEXT = " + no.getNext());
            }
        }

        getUserActionXML();
    }

    public void getUserActionXML() {
        JButton test = new JButton("ONE OPTION HERE!!!");
        //this.setLayout(new GridLayout(optXML.size(), 1));
        //this.setBorder(new TitledBorder("CHOICE"));
        this.removeAll();

        for (XMLNode no : optXML) {
            final XMLNode node = no;
            final String nexts = no.getNext();

            final String evento = no.getEvent();

            final JButton b;

            final String[] partes;
            if (evento.contains("-->")) {
                partes = evento.split("-->");
                b = new JButton(partes[0]);
            } else {
                b = new JButton(evento);
            }

            b.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JokerWindow.getAp().removeAll();

                    JokerWindow.areaOut.setText(JokerWindow.areaOut.getText() + "\n" + evento);
                    montarArvore(node);
                    getNextNodesXML(nexts);
                    updateUI();
                }
            });

            if (evento.equalsIgnoreCase("skip")) {
                b.setEnabled(false);
            }

            if (evento.equalsIgnoreCase("stop")) {
                b.setEnabled(false);
            }

            if (evento.equalsIgnoreCase("exit")) {
                b.setEnabled(false);
            }

            if (evento.equalsIgnoreCase("noexit")) {
                b.setEnabled(false);
            }

            this.add(b);

        }//for opt
        //this.setSize(test.getWidth(), optXML.size()*test.getHeight());
        this.updateUI();
    }//user action for XML
}//class
