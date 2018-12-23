package backup;

import io.ReadFile;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import z.ZBNode;

/*
 * This class is the prototype of the Z's LTS reader.
 * @author Diego Henrique Oliveira de Souza
 */
public class ReadLTSofZ {

    private static void getInitialNodes() {
        for (ZBNode zNode : nos) {
            if (zNode.getId() == 0) {
                opcoes.add(zNode);
            }
        }
    }

    private static void getNextNodes(int next) {
        opcoes.clear();
        for (ZBNode zNode : nos) {
            if (zNode.getId() == next) {
                opcoes.add(zNode);
            }
        }
    }
    private static int beginTam = 11;
    private static int next = 0;
    private static ArrayList<ZBNode> nos = new ArrayList<ZBNode>();
    private static ArrayList<ZBNode> opcoes = new ArrayList<ZBNode>();
    private static ArrayList<String> nozes = new ArrayList<String>();

    public static void main(String[] args) {

        String specification = "";
        try {
            specification = ReadFile.leia("statespace.pl");
        } catch (IOException ex) {
            //System.out.println("ERRO LEITURA");
        }

        String partes[] = specification.split("\n");

        for (int i = 1; i < partes.length; i++) { //ZERO pra incluir initialization
            if (partes[i].startsWith("spec_trans")) {
                nozes.add(partes[i]);
            }
            //System.out.println("PARTE[" + i + "] = " + partes[i]);
        }

        for (int i = 0; i < nozes.size(); i++) {
            //System.out.println("NOZES.get(" + i + ") = " + nozes.get(i));
            String partesDaParte[] = nozes.get(i).split("\'");

//            System.out.println("0 = " + partesDaParte[0]);
//            System.out.println("1 = " + partesDaParte[1]);
//            System.out.println("2 = " + partesDaParte[2]);

            if(partesDaParte[0].contains("root")){
                partesDaParte[0] = partesDaParte[0].replace("root", "0");
            }

            int id = Integer.parseInt(partesDaParte[0].substring(11, partesDaParte[0].length()-1));
            String act = partesDaParte[1];
            int next = Integer.parseInt(partesDaParte[2].substring(1, partesDaParte[2].length() - 2));

//            System.out.println("ID = " + id);
//            System.out.println("ACT = " + act);
//            System.out.println("NEXT = " + next);

            ZBNode no = new ZBNode(id, act, next);

            nos.add(no);
        }

        getInitialNodes();

        while (true) {
            String message = "Choose one of the options bellow: \n\n";
            int cont = 0;
            for (ZBNode zNode : opcoes) {
                message += cont++ + " - " + zNode.getAction() + "\n";
            }

            String op = JOptionPane.showInputDialog(null, message, "Choose an option", JOptionPane.QUESTION_MESSAGE);

            int digitado = Integer.parseInt(op);
            next = opcoes.get(digitado).getNext();

            getNextNodes(next);
        }

        //System.out.println("Z FILE: \n\n" + specification);
    }
}
