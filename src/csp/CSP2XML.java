package csp;

import convert.LTS2XML;
import gui.JokerWindow;
import io.SaveFile;
import io.WriteFile;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import xml.XMLNode;

/**
 * This class is responsible for converting an array of CSPNode objects to
 * an array of XMLNode objects.
 * @author Diego Henrique Oliveira de Souza
 */
public class CSP2XML {
    
    //CSP
    private ArrayList<CSPNode> nos = new ArrayList<CSPNode>();
    private ArrayList<CSPNode> optCSP = new ArrayList<CSPNode>();
    //XML
    private ArrayList<XMLNode> nox = new ArrayList<XMLNode>();
    private static ArrayList<XMLNode> optXML = new ArrayList<XMLNode>();

    public CSP2XML(String lts){
        this.animateCSP(lts);
    }

    public void animateCSP(String s) {
        nos.clear();
        optCSP.clear();

        CSPNodes cspNodes = new CSPNodes(s);
        this.nos = cspNodes.getNos();

        //pega nos iniciais para por no XML
        this.getInitialNodesCSP();
        //XML for CSP
        this.generateXMLforCSP();
    } //animate CSP

    private void getInitialNodesCSP() {
        optCSP.clear();

        String next = "";
        for (CSPNode no : nos) {
            if (no.getKey().equals("root")) {
                next = no.getNext();
                JokerWindow.logIt("INITIAL = " + no.getKey());
                JokerWindow.logIt("NEXT = " + no.getNext());
            }
        }

        for (CSPNode no : nos) {
            if (no.getKey().equals(next)) {
                optCSP.add(no);
            }
        }
    }

    private void cspnode2xmlnode() {
        nox.clear();
        for (CSPNode no : nos) {
            nox.add(new XMLNode(no.getKey(), no.getContent(), no.getNext()));
        }
    }

    private void generateXMLforCSP() {
        LTS2XML lts2xml = new LTS2XML();

        this.cspnode2xmlnode();

        optXML.clear();
        for (CSPNode no : optCSP) {
            optXML.add(new XMLNode(no.getKey(), no.getContent(), no.getNext()));
        }


        lts2xml.writeXMLforLTS(SaveFile.getNomeArquivo(), nox, optXML);

        JokerWindow.logIt(SaveFile.getNomeArquivo() + " has " + nox.size() + " states.");

        //XML tab
        JokerWindow.areaXml.setText(lts2xml.getXml());
        JokerWindow.abas.setTitleAt(3, SaveFile.getNomeArquivo() + ".xml");

        try {
            WriteFile.escreva(SaveFile.getFolderArquivo() + ".xml", lts2xml.getXml());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error Saving LTS in XML File", JOptionPane.ERROR_MESSAGE);
        }
    }
}
