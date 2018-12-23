package z;

import gui.JokerWindow;
import java.util.ArrayList;

/**
 * This class is responsible of converting the Z's LTS from a text file to
 * an array of ZBNode objects.
 * @author Diego Henrique Oliveira de Souza
 */
public class ZBNodes {

    private String s;
    private ArrayList<ZBNode> noz = new ArrayList<ZBNode>();
    private ArrayList<String> nozes = new ArrayList<String>();

    public ZBNodes(String specification){
        this.s = specification;
        this.getNodes();
    }

    public void getNodes(){

        String partes[] = s.split("\n");

        for (int i = 1; i < partes.length; i++) { //ZERO pra incluir initialization
            if (partes[i].startsWith("spec_trans")) {
                nozes.add(partes[i]);
            }
            //JokerWindow.logIt("PARTE[" + i + "] = " + partes[i]);
        }

        for (int i = 0; i < nozes.size(); i++) {
            //JokerWindow.logIt("NOZES.get(" + i + ") = " + nozes.get(i));

            String partesDaParte[] = nozes.get(i).split("\'");

//            JokerWindow.logIt("0 = " + partesDaParte[0]);
//            JokerWindow.logIt("1 = " + partesDaParte[1]);
//            JokerWindow.logIt("2 = " + partesDaParte[2]);

            if (partesDaParte[0].contains("root")) {
                partesDaParte[0] = partesDaParte[0].replace("root", "0");
            }

            int id = Integer.parseInt(partesDaParte[0].substring(11, partesDaParte[0].length() - 1));
            String act = partesDaParte[1];
            int nextN = Integer.parseInt(partesDaParte[2].substring(1, partesDaParte[2].length() - 2));

            StringBuilder logger = new StringBuilder("");
            logger.append("NOZ******************************\n");
            logger.append("KEY =   ").append(id).append("\n");
            logger.append("EVENT = ").append(act).append("\n");
            logger.append("NEXT =  ").append(nextN).append("\n\n");

            JokerWindow.logIt(logger.toString());

            ZBNode no = new ZBNode(id, act, nextN);

            noz.add(no);
        }
    }

    public ArrayList<ZBNode> getNoz() {
        return noz;
    }

}
