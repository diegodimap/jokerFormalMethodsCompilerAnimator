package convert;

import gui.JokerWindow;
import io.SaveFile;
import java.util.ArrayList;
import xml.XMLNode;

/*
 * This class converts a XML in Joker's specified format to an array of XNode.
 * This array contain the nodes that represents the LTS. Each node is an object.
 * @author Diego Henrique Oliveira de Souza
 */
public class XML2OBJ {

    private String xml = "";
    private String specificationName = "";
    private ArrayList<XMLNode> nox = new ArrayList<XMLNode>();
    private ArrayList<String> initials = new ArrayList<String>();

    public XML2OBJ() {
        this.xml = "";
    }

    public void readXMLConvert2Obj(String xml) {
        this.xml = xml;

        String[] linhas = this.xml.split("\n");
        //this.xml = "";

        for (int i = 0; i < linhas.length; i++) {
            String initTemp = "";
            String temp = "";

            //initials
            if (linhas[i].startsWith("            <ikey ")) {
                initTemp = linhas[i].substring(linhas[i].indexOf("="));
                temp = initTemp;
                initTemp = new String(temp.substring(2, temp.length() - 4));
                initials.add(initTemp);
                i++;
            }
        }

        //pegando o id do no
        for (int i = 0; i < linhas.length; i++) {
            String temp = "";
            String idTemp = "";
            String actionTemp = "";
            String nextTemp = "";
            //JokerWindow.logIt("Linha: " + linhas[i]);

            //pegando o nome da spec
            if (linhas[i].startsWith("<specification")) {
                int teste = 0;
                for (int j = 0; j < linhas[i].length(); j++) {
                    if ((linhas[i].charAt(j) + "").equalsIgnoreCase("\"")) {
                        teste++;
                    } else {
                        if (teste == 1) {
                            this.specificationName += linhas[i].charAt(j);
                        }
                    }
                }
                //JokerWindow.logIt("SPEC NAME: " + specificationName);
            }  //id
            if (linhas[i].startsWith("            <key ")) {
                idTemp = linhas[i].substring(linhas[i].indexOf("="));
                temp = idTemp;
                idTemp = new String(temp.substring(2, temp.length() - 4));
                i++;
            }  //action
            if (linhas[i].startsWith("            <event ")) {
                int teste = 0;
                for (int j = 0; j < linhas[i].length(); j++) {
                    if ((linhas[i].charAt(j) + "").equalsIgnoreCase("\"")) {
                        teste++;
                    } else {
                        if (teste == 1) {
                            actionTemp += linhas[i].charAt(j);
                        }
                    }
                }
                i++;
            }  //next
            if (linhas[i].startsWith("            <next ")) {
                int teste = 0;
                for (int j = 0; j < linhas[i].length(); j++) {
                    if ((linhas[i].charAt(j) + "").equalsIgnoreCase("\"")) {
                        teste++;
                    } else {
                        if (teste == 1) {
                            nextTemp += linhas[i].charAt(j);
                        }
                    }
                }
                i++;
            }
            if (!idTemp.isEmpty()) {
                if (initials.contains(idTemp)) {
                    nox.add(new XMLNode(true, idTemp, actionTemp, nextTemp));
                } else {
                    nox.add(new XMLNode(false, idTemp, actionTemp, nextTemp));
                }
            }
        }//lines

        JokerWindow.logIt(SaveFile.getNomeArquivo() + " has " + nox.size() + " states.");

    }//method

    public ArrayList<XMLNode> getNox() {
        return nox;
    }

    public ArrayList<String> getInitials() {
        return initials;
    }
}
