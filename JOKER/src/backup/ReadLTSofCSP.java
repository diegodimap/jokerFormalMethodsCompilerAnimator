package backup;

import csp.CSPNode;
import io.ReadFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * This class is the prototype of the CSP's LTS reader.
 * @author Diego Henrique Oliveira de Souza
 */
public class ReadLTSofCSP {

    private int KEY_SIZE = 52;
    private String s;
    private ArrayList<CSPNode> nos = new ArrayList<CSPNode>();

    public static void main(String[] args) {
        new ReadLTSofCSP();
    }

    public ReadLTSofCSP() {
        String specification = "";
        try {
            specification = ReadFile.leia("student.csp.fdr");
        } catch (IOException ex) {
            Logger.getLogger(ReadLTSofCSP.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.s = specification;
        //this.testSeparation(s);
        //System.out.println("CSP FILE: \n\n" + specification);
        String init = locateGPInit(specification);
       // System.out.println("INIT = " + init);
        JOptionPane.showMessageDialog(null, "INIT = " + init);
        //System.out.println("INIT size = " + locateGPInit(specification).length());
        String key = init + " =";
        boolean test = true;
        while (test) {
            String next = locateNextNode(specification, key);
            next = next.replace("\n", "");
            next = next.replace(" ", "");
            //System.out.println("NEXT = " + next);
            JOptionPane.showMessageDialog(null, "NEXT = " + next);

            String trabalho = next;

            if (next.contains("[>")) {
                //System.out.println("FOUND [> (não tratado ainda)");
                String partes[] = next.split("\\[\\>");

//                System.out.println("PARTES[0] = " + partes[0]);
//                System.out.println("PARTES[1] = " + partes[1]);

                String retorno = this.containsChoice(partes[0].substring(1));
                String[] retornos = retorno.split(",");
                trabalho = retornos[0];
                key = retornos[1];

            } else if (next.contains("[]")) {
                String retorno = this.containsChoice(next);
                String[] retornos = retorno.split(",");
                trabalho = retornos[0];
                key = retornos[1];
            } else {
                if (trabalho.contains("->")) {
                    String partes[] = trabalho.split("->");
                    String option = partes[0].substring(partes[0].indexOf("(") + 1);
                    //System.out.println("OPTION = " + option);

                    JOptionPane.showMessageDialog(null, "OPTION = " + option);
                    key = partes[1].trim();
                    key = key.substring(0, key.length() - 1);

                    CSPNode no = new CSPNode(option, key, locateNextNode(specification, key));
                    nos.add(no);
                    //System.out.println("NOVO NO, CONTENT = " + no.getContent() + ", KEY = " + no.getKey() + ", NEXT = " + no.getNext());


                    key += " = ";
                    //System.out.println("KEY2 = " + key);
                } else {
                    //System.out.println("SKIP");
                    JOptionPane.showMessageDialog(null, "SKIP");
                    test = false;
                }
            }
        }

    }

    public String containsChoice(String n) {
        String next = n;
        String trabalho = "";
        String key = "";
        next = next.substring(next.indexOf("("));
        String partes[] = next.split("\\[\\]");

        String options = "";
        for (int i = 0; i < partes.length; i++) {
            if (i == 0) {
                options += "Option " + i + " - " + partes[0].substring(1) + "\n";
            } else if (i == partes.length - 1) {
                options += "Option " + i + " - " + partes[partes.length - 1].substring(0, partes[partes.length - 1].length() - 1) + "\n";
            } else {
                options += "Option " + i + " - " + partes[i] + "\n";
            }
        }


        String input = JOptionPane.showInputDialog(null, "OPTIONS = \n" + options);
        int op = Integer.parseInt(input);

        if (op == 0) {
            trabalho = partes[op].substring(1);
        } else if (op == partes.length - 1) {
            trabalho = partes[op].substring(0, partes[op].length() - 1);
        } else {
            trabalho = partes[op];
        }

        partes = trabalho.split("->");
        String option = partes[0].substring(partes[0].indexOf("(") + 1);


        JOptionPane.showMessageDialog(null, "OPTION = " + option);
        key = partes[1].trim();
        if (key.contains("(")) {
            key = key.substring(1);
        }
        if (key.contains(")")) {
            key = key.substring(0, key.length() - 1);
        }
        key += " = ";
        //System.out.println("KEY1 = " + key);

        //NO
        CSPNode no = new CSPNode(option, key, locateNextNode(s, key));
        nos.add(no);
        //System.out.println("NOVO NO, CONTENT = " + no.getContent() + ", KEY = " + no.getKey() + ", NEXT = " + no.getNext());

        return trabalho + "," + key;
    }

    public String locateGPInit(String specification) {
        String init = specification.substring(specification.indexOf("GPINIT"),
                specification.indexOf("GPINIT") + KEY_SIZE);
        init = init.substring(9);

        return init;
    }

    public String locateNextNode(String specification, String key) {
        int start = specification.indexOf(key);
        int end = this.getClosingParenthesisPosition(specification, start);
        String next = specification.substring(start, end);

        return next;
    }

    public int getClosingParenthesisPosition(String specification, int start) {
        boolean condition = true;
        int end = start + 1;
        int cont = 0;
        int t = 47;
        //System.out.println("LENGHT: " + specification.length());
        while (condition) {
            //System.out.println("END: " + end);
            if (specification.charAt(end) == '=') {
                //System.out.println("= BOY");
                cont++;
                end++;
            }
            if (specification.length() == end + 1) {
                cont++;
                end++;
                t = 1;
            }
            if (cont == 2) {
                condition = false;
            }
            end++;
        }
        return end - t;
    }
}
