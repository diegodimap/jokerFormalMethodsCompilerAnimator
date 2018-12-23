package graphs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;

import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.functors.ConstantTransformer;

import edu.uci.ics.jung.algorithms.layout.BalloonLayout;
import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Forest;
import edu.uci.ics.jung.graph.DelegateForest;
import edu.uci.ics.jung.graph.DelegateTree;
import edu.uci.ics.jung.graph.Tree;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.VisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalLensGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.layout.LayoutTransition;
import edu.uci.ics.jung.visualization.transform.LensSupport;
import edu.uci.ics.jung.visualization.transform.MutableTransformer;
import edu.uci.ics.jung.visualization.transform.MutableTransformerDecorator;
import edu.uci.ics.jung.visualization.transform.shape.HyperbolicShapeTransformer;
import edu.uci.ics.jung.visualization.transform.shape.ViewLensSupport;
import edu.uci.ics.jung.visualization.util.Animator;
import gui.JokerWindow;
import java.util.ArrayList;
import xml.XMLNode;

/**
 * This class is responsible for generating the graphic in the animation using
 * red balloons as a representation of the nodes. The nodes are movable. It allows
 * zoom in and zoom out. There are normal and hyperbolic view associated with this
 * graphic.
 * @author Diego Henrique Oliveira de Souza
 */
@SuppressWarnings("serial")
public class BalloonGraph extends JApplet {

    private ArrayList<XMLNode> nos = new ArrayList<XMLNode>();
    /*
     * the graph
     */
    Forest<String, Integer> graph;
    Factory<DirectedGraph<String, Integer>> graphFactory =
            new Factory<DirectedGraph<String, Integer>>() {

                public DirectedGraph<String, Integer> create() {
                    return new DirectedSparseMultigraph<String, Integer>();
                }
            };
    Factory<Tree<String, Integer>> treeFactory =
            new Factory<Tree<String, Integer>>() {

                public Tree<String, Integer> create() {
                    return new DelegateTree<String, Integer>(graphFactory);
                }
            };
    Factory<Integer> edgeFactory = new Factory<Integer>() {

        int i = 0;

        public Integer create() {
            return i++;
        }
    };
    Factory<String> vertexFactory = new Factory<String>() {

        int i = 0;

        public String create() {
            return "V" + i++;
        }
    };
    /*
     * the visual component and renderer for the graph
     */
    VisualizationViewer<String, Integer> vv;
    VisualizationServer.Paintable rings;
    String root;
    TreeLayout<String, Integer> layout;
    BalloonLayout<String, Integer> radialLayout;
    /*
     * provides a Hyperbolic lens for the view
     */
    LensSupport hyperbolicViewSupport;

    public BalloonGraph(ArrayList<XMLNode> nodes, int w, int h) {
        this.nos = nodes;
        // create a simple graph for the demo
        graph = new DelegateForest<String, Integer>();

        createTree();

        layout = new TreeLayout<String, Integer>(graph);
        radialLayout = new BalloonLayout<String, Integer>(graph);
        radialLayout.setSize(new Dimension(w, h));
        vv = new VisualizationViewer<String, Integer>(layout, new Dimension(w, h));
        vv.setBackground(Color.white);
        vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line());
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        // add a listener for ToolTips
        vv.setVertexToolTipTransformer(new ToStringLabeller());
        vv.getRenderContext().setArrowFillPaintTransformer(new ConstantTransformer(Color.lightGray));
        rings = new Rings(radialLayout);

        Container content = getContentPane();
        final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
        content.add(panel);


        final DefaultModalGraphMouse graphMouse = new DefaultModalGraphMouse();

        vv.setGraphMouse(graphMouse);
        vv.addKeyListener(graphMouse.getModeKeyListener());

        hyperbolicViewSupport =
                new ViewLensSupport<String, Integer>(vv, new HyperbolicShapeTransformer(vv,
                vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW)),
                new ModalLensGraphMouse());


        graphMouse.addItemListener(hyperbolicViewSupport.getGraphMouse().getModeListener());

        JComboBox modeBox = graphMouse.getModeComboBox();
        modeBox.addItemListener(graphMouse.getModeListener());
        graphMouse.setMode(ModalGraphMouse.Mode.PICKING);

        final ScalingControl scaler = new CrossoverScalingControl();

        vv.scaleToLayout(scaler);

        JButton plus = new JButton("+");
        plus.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                scaler.scale(vv, 1.1f, vv.getCenter());
            }
        });
        JButton minus = new JButton("-");
        minus.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                scaler.scale(vv, 1 / 1.1f, vv.getCenter());
            }
        });

        JToggleButton radial = new JToggleButton("Balloon");
        radial.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    //System.out.println("CLICOU NA BOLINHA 1");

                    LayoutTransition<String, Integer> lt =
                            new LayoutTransition<String, Integer>(vv, layout, radialLayout);
                    Animator animator = new Animator(lt);
                    animator.start();
                    vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).setToIdentity();
                    vv.addPreRenderPaintable(rings);
                } else {
                    //System.out.println("CLICOU NA BOLINHA 2");
                    LayoutTransition<String, Integer> lt =
                            new LayoutTransition<String, Integer>(vv, radialLayout, layout);
                    Animator animator = new Animator(lt);
                    animator.start();
                    vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).setToIdentity();
                    vv.removePreRenderPaintable(rings);
                }
                vv.repaint();

                JokerWindow.tracePanel.updateUI();
                JokerWindow.tracePanel.setVisible(true);
            }
        });
        final JRadioButton hyperView = new JRadioButton("Hyperbolic View");
        hyperView.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                //System.out.println("CLICOU NA BOLINHA 5");

                hyperbolicViewSupport.activate(e.getStateChange() == ItemEvent.SELECTED);
            }
        });

        JPanel scaleGrid = new JPanel(new GridLayout(1, 0));
        scaleGrid.setBorder(BorderFactory.createTitledBorder("Zoom"));

        JPanel controls = new JPanel();
        scaleGrid.add(plus);
        scaleGrid.add(minus);
        controls.add(radial);
        controls.add(scaleGrid);
        controls.add(modeBox);
        controls.add(hyperView);
        content.add(controls, BorderLayout.SOUTH);
    }

    class Rings implements VisualizationServer.Paintable {

        BalloonLayout<String, Integer> layout;

        public Rings(BalloonLayout<String, Integer> layout) {
            this.layout = layout;
        }

        public void paint(Graphics g) {
            g.setColor(Color.gray);

            Graphics2D g2d = (Graphics2D) g;

            Ellipse2D ellipse = new Ellipse2D.Double();
            for (String v : layout.getGraph().getVertices()) {
                Double radius = layout.getRadii().get(v);
                if (radius == null) {
                    continue;
                }
                Point2D p = layout.transform(v);
                ellipse.setFrame(-radius, -radius, 2 * radius, 2 * radius);
                AffineTransform at = AffineTransform.getTranslateInstance(p.getX(), p.getY());
                Shape shape = at.createTransformedShape(ellipse);

                MutableTransformer viewTransformer =
                        vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW);

                if (viewTransformer instanceof MutableTransformerDecorator) {
                    //System.out.println("CLICOU NA BOLINHA 3");

                    shape = vv.getRenderContext().getMultiLayerTransformer().transform(shape);
                } else {
                    //System.out.println("CLICOU NA BOLINHA 4");

                    shape = vv.getRenderContext().getMultiLayerTransformer().transform(Layer.LAYOUT, shape);
                }

                g2d.draw(shape);

                JokerWindow.ap.updateUI();
                JokerWindow.ap.setVisible(true);
                JokerWindow.tracePanel.updateUI();
                JokerWindow.tracePanel.setVisible(true);
            }
        }

        public boolean useTransform() {
            return true;
        }
    }

    private void createTree() {
        
//        for (String no : nos) {
//            graph.addVertex(no);
//        }

        for (int i = 0; i < nos.size(); i++) {
            if(i == nos.size()-1){
                graph.addEdge(edgeFactory.create(), nos.get(i).getEvent(), "SKIP");
            }else{
                graph.addEdge(edgeFactory.create(), nos.get(i).getEvent(), nos.get(i+1).getEvent());
            }
        }
        
    }

    /**
     * a driver for this demo
     */
//    public static void main(String[] args) {
//        JPanel frame = new JPanel();
//        frame.add(new BalloonGraph());
//
//        JFrame janela = new JFrame();
//
//        janela.add(frame);
//
//        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        janela.pack();
//        janela.setVisible(true);
//
//    }
}
