package convert;

import gui.JokerWindow;
import java.util.ArrayList;
import xml.XMLNode;

/*
 * This class is responsible for converting a generated LTS to Joker's specified
 * XML format. It works by taking an array of XNodes and transforming it to a
 * String containing the XML. This String is written to a File with the same
 * name of the specification and with the extension .xml.
 * @author Diego Henrique Oliveira de Souza
 */
public class LTS2XML {

    private String xml = "";

    public LTS2XML() {
        this.xml = "";
    }

    public String writeXMLforLTS(String specName, ArrayList<XMLNode> noxes, ArrayList<XMLNode> initials) {
        StringBuilder texto = new StringBuilder("");

        texto.append("<specification name=\"").append(specName).append("\">\n");
        texto.append("    <initialXNodes> \n");
        for (XMLNode inits : initials) {
            texto.append("        <initialXNode> \n");
            texto.append("            <ikey value=\"").append(inits.getKey()).append("\"/> \n");
            texto.append("        </initialNNode> \n");
        }
        texto.append("    </initialXnodes> \n\n");

        for (XMLNode in : initials) {
            JokerWindow.logIt("INITIAL KEY = " + in.getKey());
        }

        texto.append("    <xnodes> \n");
        for (XMLNode nox : noxes) {
            texto.append("        <xnode> \n");
            texto.append("            <key value=\"").append(nox.getKey()).append("\"/> \n");
            StringBuilder append = texto.append("            <event value=\"").append(nox.getEvent()).append("\"/> \n");
            StringBuilder append1 = texto.append("            <next value=\"").append(nox.getNext()).append("\"/> \n");
            texto.append("        </xnode> \n");
        }
        texto.append("    </xnodes> \n");
        texto.append("</specification>");

        this.xml = texto.toString();
        return xml;
    }

    public String getXml() {
        return xml;
    }
}
