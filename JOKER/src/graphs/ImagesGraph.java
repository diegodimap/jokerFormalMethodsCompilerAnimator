package graphs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.util.RandomLocationTransformer;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.FourPassImageShaper;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.LayeredIcon;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.decorators.DefaultVertexIconTransformer;
import edu.uci.ics.jung.visualization.decorators.EllipseVertexShapeTransformer;
import edu.uci.ics.jung.visualization.decorators.PickableEdgePaintTransformer;
import edu.uci.ics.jung.visualization.decorators.PickableVertexPaintTransformer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.decorators.VertexIconShapeTransformer;
import edu.uci.ics.jung.visualization.picking.PickedState;
import edu.uci.ics.jung.visualization.renderers.BasicVertexRenderer;
import edu.uci.ics.jung.visualization.renderers.Checkmark;
import edu.uci.ics.jung.visualization.renderers.DefaultEdgeLabelRenderer;
import edu.uci.ics.jung.visualization.renderers.DefaultVertexLabelRenderer;
import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;
import gui.JokerWindow;
import java.util.ArrayList;
import xml.XMLNode;

/**
 * This class represents the animation using images. The image that is the node
 * is chosen by the user in the Graphic Options. There is an option to animate
 * through this graphic by clicking in the nodes.
 * @author Diego Henrique Oliveira de Souza
 */
public class ImagesGraph extends JApplet {

    public static final long serialVersionUID = -4332663871914930864L;
    public static int VERTEX_COUNT = 11;
    public static ArrayList<XMLNode> leaves = new ArrayList<XMLNode>();
    public static ArrayList<XMLNode> nox = new ArrayList<XMLNode>();
    public static ArrayList<XMLNode> optXML = new ArrayList<XMLNode>();
    public static ArrayList<Integer> indexes = new ArrayList<Integer>();
    public static ArrayList<Integer> destiny = new ArrayList<Integer>();
    public static HashMap<Integer, ArrayList<Integer>> edges = new HashMap<Integer, ArrayList<Integer>>();
    public static int edgeCounter = 0;
    public static DirectedSparseGraph<Number, Number> graph;
    VisualizationViewer<Number, Number> vv;

    private void createGraph(int vertexCount) {

        for (int i = 0; i < vertexCount; i++) {
            graph.addVertex(i);
        }

//        for (int i = 0; i < edges.size(); i++) {
//            System.out.println("i = " + indexes.get(i));
//
//            for (int j = 0; j < edges.get(indexes.get(i)).size(); j++) {
//                System.out.println("j = " + edges.get(indexes.get(i)).get(j));
//            }
//        }

//        for (int i = 0; i < vertexCount; i++) {
//            graph.addEdge(edgeCounter++, i, i + 1, EdgeType.DIRECTED);
//        }

        if (!edges.isEmpty()) {
            for (int i = 0; i < indexes.size(); i++) {
                ArrayList<Integer> proximos = edges.get(indexes.get(i));
                for (int j = 0; j < proximos.size(); j++) {
//                    System.out.print("FROM = " + leaves.get(indexes.get(i)).getEvent());
//                    System.out.println(" TO " + leaves.get(proximos.get(j)).getEvent());
                    graph.addEdge(edgeCounter++, indexes.get(i), proximos.get(j), EdgeType.DIRECTED);
                }
            }
        }
    }

    public static class PickWithIconListener<V> implements ItemListener {

        DefaultVertexIconTransformer<V> imager;
        Icon checked;

        public PickWithIconListener(DefaultVertexIconTransformer<V> imager) {
            this.imager = imager;
            checked = new Checkmark();
        }

        public void itemStateChanged(ItemEvent e) {
            Icon icon = imager.transform((V) e.getItem());
            if (icon != null && icon instanceof LayeredIcon) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    int index = Integer.parseInt(e.getItem().toString());

                    //System.out.println("NEXT INDEX = " + leaves.get(index).getNext());

                    optXML.clear();
                    destiny.clear();
                    for (XMLNode no : nox) {
                        if (no.getKey().equalsIgnoreCase(leaves.get(index).getNext())) {
                            //System.out.println("NEXT = " + no.getEvent());
                            //no.setEvent((index + 1) + " - " + no.getEvent());
                            leaves.add(no);
                            destiny.add(leaves.indexOf(no));
                        }
                    }

                    indexes.add(index);
                    edges.put(index, new ArrayList<Integer>(destiny));

                    ImagesGraph ig = new ImagesGraph(leaves, nox, 500, 500);
                    JokerWindow.tracePanel.removeAll();
                    JokerWindow.tracePanel.add(ig);
                    JokerWindow.tracePanel.setVisible(true);
                    JokerWindow.tracePanel.updateUI();
//                    ((LayeredIcon) icon).add(checked);
                } else {
                    //System.out.println("DESELECTED = " + e.getItem().toString());
                    ((LayeredIcon) icon).remove(checked);
                }
            }
        }
    }

    public static class VertexStringerImpl<V, S> implements Transformer<V, String> {

        Map<V, String> map = new HashMap<V, String>();
        boolean enabled = true;

        public VertexStringerImpl(Map<V, String> map) {
            this.map = map;
        }

        public String transform(V v) {
            if (isEnabled()) {
                return map.get(v);
            } else {
                return "";
            }
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

    public static class DemoVertexIconTransformer<V> extends DefaultVertexIconTransformer<V>
            implements Transformer<V, Icon> {

        boolean fillImages = true;
        boolean outlineImages = false;

        public boolean isFillImages() {
            return fillImages;
        }

        public void setFillImages(boolean fillImages) {
            this.fillImages = fillImages;
        }

        public boolean isOutlineImages() {
            return outlineImages;
        }

        public void setOutlineImages(boolean outlineImages) {
            this.outlineImages = outlineImages;
        }

        public Icon transform(V v) {
            if (fillImages) {
                return (Icon) iconMap.get(v);
            } else {
                return null;
            }
        }
    }

    public static class DemoVertexIconShapeTransformer<V> extends VertexIconShapeTransformer<V> {

        boolean shapeImages = true;

        public DemoVertexIconShapeTransformer(Transformer<V, Shape> delegate) {
            super(delegate);
        }

        public boolean isShapeImages() {
            return shapeImages;
        }

        public void setShapeImages(boolean shapeImages) {
            shapeMap.clear();
            this.shapeImages = shapeImages;
        }

        public Shape transform(V v) {
            Icon icon = (Icon) iconMap.get(v);

            if (icon != null && icon instanceof ImageIcon) {

                Image image = ((ImageIcon) icon).getImage();

                Shape shape = shapeMap.get(image);
                if (shape == null) {
                    if (shapeImages) {
                        shape = FourPassImageShaper.getShape(image, 30);
                    } else {
                        shape = new Rectangle2D.Float(0, 0,
                                image.getWidth(null), image.getHeight(null));
                    }
                    if (shape.getBounds().getWidth() > 0
                            && shape.getBounds().getHeight() > 0) {
                        int width = image.getWidth(null);
                        int height = image.getHeight(null);
                        AffineTransform transform =
                                AffineTransform.getTranslateInstance(-width / 2, -height / 2);
                        shape = transform.createTransformedShape(shape);
                        shapeMap.put(image, shape);
                    }
                }
                return shape;
            } else {
                return delegate.transform(v);
            }
        }
    }

    class DemoRenderer<V, E> extends BasicVertexRenderer<V, E> {

        public void paintIconForVertex(RenderContext<V, E> rc, V v, Layout<V, E> layout) {

            Point2D p = layout.transform(v);
            p = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, p);
            float x = (float) p.getX();
            float y = (float) p.getY();

            GraphicsDecorator g = rc.getGraphicsContext();
            boolean outlineImages = false;
            Transformer<V, Icon> vertexIconFunction = rc.getVertexIconTransformer();

            if (vertexIconFunction instanceof DemoVertexIconTransformer) {
                outlineImages = ((DemoVertexIconTransformer<V>) vertexIconFunction).isOutlineImages();
            }
            Icon icon = vertexIconFunction.transform(v);
            if (icon == null || outlineImages) {

                Shape s = AffineTransform.getTranslateInstance(x, y).
                        createTransformedShape(rc.getVertexShapeTransformer().transform(v));
                paintShapeForVertex(rc, v, s);
            }
            if (icon != null) {
                int xLoc = (int) (x - icon.getIconWidth() / 2);
                int yLoc = (int) (y - icon.getIconHeight() / 2);
                icon.paintIcon(rc.getScreenDevice(), g.getDelegate(), xLoc, yLoc);
            }
        }
    }

    public ImagesGraph(ArrayList<XMLNode> leavesx, ArrayList<XMLNode> noxx, int w, int h) {
        this.leaves = leavesx;
        this.nox = noxx;

        if (leavesx.size() == 1) {
            this.edgeCounter = 0;
            this.indexes.clear();
            this.edges.clear();
            this.destiny.clear();
        }

        VERTEX_COUNT = leaves.size();

        // create a simple graph for the demo
        graph = new DirectedSparseGraph<Number, Number>();
        createGraph(VERTEX_COUNT);

        // a Map for the labels
        Map<Number, String> map = new HashMap<Number, String>();

        for (int i = 0; i < VERTEX_COUNT; i++) {
            map.put(i, leaves.get(i).getEvent());
        }

        // a Map for the Icons
        Map<Number, Icon> iconMap = new HashMap<Number, Icon>();
        for (int i = 0; i < VERTEX_COUNT; i++) {
            String name = "/img/" + JokerWindow.getOptions().getGraphImg() + ".png";
            try {
                Icon icon =
                        new LayeredIcon(new ImageIcon(ImagesGraph.class.getResource(name)).getImage());
                iconMap.put(i, icon);
            } catch (Exception ex) {
                //System.err.println("You need slashdoticons.jar in your classpath to see the image " + name);
            }
        }

        FRLayout<Number, Number> layout = new FRLayout<Number, Number>(graph);
        layout.setMaxIterations(100);
        layout.setInitializer(new RandomLocationTransformer<Number>(new Dimension(w, h), 0));
        vv = new VisualizationViewer<Number, Number>(layout, new Dimension(w, h));

        // This demo uses a special renderer to turn outlines on and off.
        // you do not need to do this in a real application.
        // Instead, just let vv use the Renderer it already has
        vv.getRenderer().setVertexRenderer(new DemoRenderer<Number, Number>());

        Transformer<Number, Paint> vpf =
                new PickableVertexPaintTransformer<Number>(vv.getPickedVertexState(), Color.white, Color.yellow);
        vv.getRenderContext().setVertexFillPaintTransformer(vpf);
        vv.getRenderContext().setEdgeDrawPaintTransformer(new PickableEdgePaintTransformer<Number>(vv.getPickedEdgeState(), Color.black, Color.cyan));

        vv.setBackground(Color.white);


        final Transformer<Number, String> vertexStringerImpl =
                new VertexStringerImpl<Number, String>(map);
        vv.getRenderContext().setVertexLabelTransformer(vertexStringerImpl);
        vv.getRenderContext().setVertexLabelRenderer(new DefaultVertexLabelRenderer(Color.cyan));
        vv.getRenderContext().setEdgeLabelRenderer(new DefaultEdgeLabelRenderer(Color.cyan));
//        vv.getRenderContext().setEdgeLabelTransformer(new Transformer<Number,String>() {
//        	URL url = getClass().getResource("/images/lightning-s.gif");
//			public String transform(Number input) {
//
//				return "<html><img src="+url+" height=10 width=21>"+input.toString();
//			}});

        // For this demo only, I use a special class that lets me turn various
        // features on and off. For a real application, use VertexIconShapeTransformer instead.
        final DemoVertexIconShapeTransformer<Number> vertexIconShapeTransformer =
                new DemoVertexIconShapeTransformer<Number>(new EllipseVertexShapeTransformer<Number>());

        final DemoVertexIconTransformer<Number> vertexIconTransformer =
                new DemoVertexIconTransformer<Number>();

        vertexIconShapeTransformer.setIconMap(iconMap);
        vertexIconTransformer.setIconMap(iconMap);

        vv.getRenderContext().setVertexShapeTransformer(vertexIconShapeTransformer);
        vv.getRenderContext().setVertexIconTransformer(vertexIconTransformer);

        // un-comment for RStar Tree visual testing
        //vv.addPostRenderPaintable(new BoundingRectanglePaintable(vv.getRenderContext(), vv.getGraphLayout()));

        // Get the pickedState and add a listener that will decorate the
        // Vertex images with a checkmark icon when they are picked
        PickedState<Number> ps = vv.getPickedVertexState();
        ps.addItemListener(new PickWithIconListener<Number>(vertexIconTransformer));

        vv.addPostRenderPaintable(new VisualizationViewer.Paintable() {

            int x;
            int y;
            Font font;
            FontMetrics metrics;
            int swidth;
            int sheight;
            String str = ""; //MESSAGE

            public void paint(Graphics g) {
                Dimension d = vv.getSize();
                if (font == null) {
                    font = new Font(g.getFont().getName(), Font.BOLD, 20);
                    metrics = g.getFontMetrics(font);
                    swidth = metrics.stringWidth(str);
                    sheight = metrics.getMaxAscent() + metrics.getMaxDescent();
                    x = (d.width - swidth) / 2;
                    y = (int) (d.height - sheight * 1.5);
                }
                g.setFont(font);
                Color oldColor = g.getColor();
                g.setColor(Color.lightGray);
                g.drawString(str, x, y);
                g.setColor(oldColor);
            }

            public boolean useTransform() {
                return false;
            }
        });

        // add a listener for ToolTips
        vv.setVertexToolTipTransformer(new ToStringLabeller<Number>());

        Container content = getContentPane();
        final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
        content.add(panel);

        final DefaultModalGraphMouse<Number, Number> graphMouse = new DefaultModalGraphMouse<Number, Number>();
        vv.setGraphMouse(graphMouse);
        vv.addKeyListener(graphMouse.getModeKeyListener());
        final ScalingControl scaler = new CrossoverScalingControl();

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

        JCheckBox shape = new JCheckBox("Shape");
        shape.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                //System.out.println("CLICOU NO ICONE BOY!!!");
                vertexIconShapeTransformer.setShapeImages(e.getStateChange() == ItemEvent.SELECTED);
                vv.repaint();
            }
        });
        shape.setSelected(true);

        JCheckBox fill = new JCheckBox("Fill");
        fill.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                vertexIconTransformer.setFillImages(e.getStateChange() == ItemEvent.SELECTED);
                vv.repaint();
            }
        });
        fill.setSelected(true);

        JCheckBox drawOutlines = new JCheckBox("Outline");
        drawOutlines.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                vertexIconTransformer.setOutlineImages(e.getStateChange() == ItemEvent.SELECTED);
                vv.repaint();
            }
        });

        JComboBox modeBox = graphMouse.getModeComboBox();
        modeBox.setSelectedIndex(1);
        JPanel modePanel = new JPanel();
        modePanel.setBorder(BorderFactory.createTitledBorder("Mouse Mode"));
        modePanel.add(modeBox);

        JPanel scaleGrid = new JPanel(new GridLayout(1, 0));
        scaleGrid.setBorder(BorderFactory.createTitledBorder("Zoom"));
        JPanel labelFeatures = new JPanel(new GridLayout(1, 0));
        labelFeatures.setBorder(BorderFactory.createTitledBorder("Image Effects"));
        JPanel controls = new JPanel();
        scaleGrid.add(plus);
        scaleGrid.add(minus);
        controls.add(scaleGrid);
        labelFeatures.add(shape);
        labelFeatures.add(fill);
        labelFeatures.add(drawOutlines);

        controls.add(labelFeatures);
        controls.add(modePanel);
        content.add(controls, BorderLayout.SOUTH);
    }
}
