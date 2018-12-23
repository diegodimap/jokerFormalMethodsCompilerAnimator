package graphs;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import xml.XMLNode;

public class LinkedComp {

    public ArrayList<XMLNode> nox = new ArrayList<XMLNode>();
    private static ArrayList<XMLNode> optXML = new ArrayList<XMLNode>();
    public static LinkedPanel painelPrincipal;

    public LinkedComp(ArrayList<XMLNode> nos) {
        this.nox = nos;

        this.getInitialNodesXML();

        painelPrincipal = new LinkedPanel();
        painelPrincipal.setLayout(null);

        Rectangle r = new Rectangle(40, 40, 40, 40);

        for (int j = 0; j < optXML.size(); j++) {
            addPanel(painelPrincipal, r, optXML.get(j).getEvent() + j);
            r.x += r.width / 3 + 25;
            r.y += r.height + 45;
        }

        //initializes
        PanelMover panelMover = new PanelMover(painelPrincipal, nox);
        painelPrincipal.addMouseListener(panelMover);
        painelPrincipal.addMouseMotionListener(panelMover);
    }

    private void addPanel(Container c, Rectangle bounds, String id) {
        JPanel panel = new JPanel();
        c.add(panel);
        panel.setBounds(bounds);
        panel.add(new JLabel(id, JLabel.CENTER));
        panel.setBorder(BorderFactory.createEtchedBorder());
    }

    private void getInitialNodesXML() {
        optXML.clear();
        for (XMLNode no : nox) {
            if (no.isIsInit()) {
                optXML.add(no);
            }
        }
    }

    public JPanel getContent() {
        return painelPrincipal;
    }
}

class LinkedPanel extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Component[] c = getComponents();
        for (int j = 0; j < c.length; j++) {
            Point2D.Double p1 = getCenter(c[j]);
            for (int k = j + 1; k < c.length; k++) {
                Point2D.Double p2 = getCenter(c[k]);
                g2.draw(new Line2D.Double(p1, p2));
                k = c.length;
            }
        }
    }

    private Point2D.Double getCenter(Component c) {
        Point2D.Double p = new Point2D.Double();
        Rectangle r = c.getBounds();
        p.x = r.getCenterX();
        p.y = r.getCenterY();
        return p;
    }
}

class PanelMover extends MouseInputAdapter {

    LinkedPanel panel;
    Point offset = new Point();
    int selectedIndex;
    boolean dragging = false;
    private static ArrayList<XMLNode> nox = new ArrayList<XMLNode>();
    private static ArrayList<XMLNode> optXML = new ArrayList<XMLNode>();

    PanelMover(LinkedPanel panel, ArrayList<XMLNode> nos) {
        this.nox = nos;
        this.panel = panel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();

        Component[] c = panel.getComponents();
        for (int j = 0; j < c.length; j++) {
            Rectangle r = c[j].getBounds();
            if (r.contains(p)) {
                offset.x = p.x - r.x;
                offset.y = p.y - r.y;
                selectedIndex = j;
                //System.out.println("CLICOU EM " + j);
                this.getNextNodesXML(nox.get(j).getNext());

                dragging = true;
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dragging = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (dragging) {
            int x = e.getX() - offset.x;
            int y = e.getY() - offset.y;
            panel.getComponent(selectedIndex).setLocation(x, y);
            panel.repaint();
        }
    }

    private void getInitialNodesXML() {
        optXML.clear();
        for (XMLNode no : nox) {
            if (no.isIsInit()) {
                optXML.add(no);
            }
        }
    }

    private void getNextNodesXML(String next) {
        optXML.clear();
        for (XMLNode no : nox) {
            if (no.getKey().equals(next)) {
                optXML.add(no);
            }
        }
        getUserActionXML();
    }

    public void getUserActionXML() {
        for (XMLNode no : optXML) {
            final XMLNode node = no;
            final String nexts = no.getNext();
            final String evento = no.getEvent();
            final JButton b;
            final String[] partes;
        }//for opt
    }//user action for XML
}
