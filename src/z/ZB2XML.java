package z;

import convert.LTS2XML;
import gui.JokerWindow;
import io.SaveFile;
import io.WriteFile;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import xml.XMLNode;

/**
 * This class is responsible of transforming an array of ZBNode objects to Joker's
 * specified XML format.
 * @author Diego Henrique Oliveira de Souza
 */
public class ZB2XML {
    
    //Z
    private ArrayList<ZBNode> noz = new ArrayList<ZBNode>();
    private static ArrayList<ZBNode> optZ = new ArrayList<ZBNode>();
    //XML
    private ArrayList<XMLNode> nox = new ArrayList<XMLNode>();
    private static ArrayList<XMLNode> optXML = new ArrayList<XMLNode>();

    public ZB2XML(String lts){
        this.animateZ(lts);
    }

    //Z
    public void animateZ(String s) {
        JokerWindow.logIt("ANIMATING Z******************************");
        noz.clear();
        optZ.clear();

        ZBNodes znodes = new ZBNodes(s);
        this.noz = znodes.getNoz();

        //pega os nos iniciais pra gerar o XML
        this.getInitialNodesZ();
        //XML for Z
        this.generateXMLforZ();
    }//animateZ

    public void generateXMLforZ() {
        LTS2XML lts2xml = new LTS2XML();

        nox.clear();
        for (ZBNode no : noz) {
            nox.add(new XMLNode(no.getId() + "", no.getAction(), no.getNext() + ""));
        }

        optXML.clear();
        for (ZBNode no : optZ) {
            optXML.add(new XMLNode(no.getId() + "", no.getAction(), no.getNext() + ""));
        }

        lts2xml.writeXMLforLTS(SaveFile.getNomeArquivo(), nox, optXML);

        //XML tab
        JokerWindow.areaXml.setText(lts2xml.getXml());
        JokerWindow.abas.setTitleAt(3, SaveFile.getNomeArquivo() + ".xml");

        JokerWindow.logIt(SaveFile.getNomeArquivo() + " has " + nox.size() + " states.");

        try {
            WriteFile.escreva(SaveFile.getFolderArquivo() + ".xml", lts2xml.getXml());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error Saving LTS in XML File", JOptionPane.ERROR_MESSAGE);
        }

    }//xml for Z

    private void getInitialNodesZ() {
        optZ.clear();
        for (ZBNode zNode : noz) {
            if (zNode.getId() == 0) {
                optZ.add(zNode);
                JokerWindow.logIt("INITIAL = " + zNode.getId());
                JokerWindow.logIt("NEXT = " + zNode.getNext());
            }
        }
    }

}
