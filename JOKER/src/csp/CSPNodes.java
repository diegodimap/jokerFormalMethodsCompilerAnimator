package csp;

import gui.JokerWindow;
import io.ReadFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class reads a File containing the LTS of a CSP specification and converts
 * it to an array of CSPNode objects.
 * @author Diego Henrique Oliveira de Souza
 */
public class CSPNodes {

    private int KEY_SIZE = 52;
    private static String s;
    private ArrayList<CSPNode> nos = new ArrayList<CSPNode>();
    private ArrayList<String> keys = new ArrayList<String>();
    private ArrayList<String> nexts = new ArrayList<String>();

    public CSPNodes(String specification) {
        this.s = specification;
        this.getNodes();
    }

    public static void main(String[] args) {
        String spec = "";
        try {
            spec = ReadFile.leia("./ex_csp_OK/student.csp.fdr");
        } catch (IOException ex) {
            Logger.getLogger(CSPNodes.class.getName()).log(Level.SEVERE, null, ex);
        }

        CSPNodes cspnodes = new CSPNodes(spec);

    }

    public void getNodes() {
        s = s.trim();
        s = s.replace(" ", "");
        s = s.replace("\n", "");
        s = s.replace(")GP_", ")\nGP_");
        String initReplace = s.substring(0, 50);
        //JokerWindow.logIt("INITREPLACE = " + initReplace);
        s = s.replace(initReplace, initReplace + "\n");

        //JokerWindow.logIt(s);

        //JokerWindow.logIt("INIT = " + init);
        String[] keyes = s.split("\n");

        keys.clear();
        for (String k : keyes) {
            if (k.contains("=")) {
//                JokerWindow.logIt("LINE: " + k);
                String partes[] = k.split("=");

                if (!keys.contains(partes[0])) {
//                    JokerWindow.logIt("PRE(=):" + partes[0]);
                    keys.add(partes[0]);
                    nexts.add(partes[1]);
                }

//                JokerWindow.logIt("POS(=):" + partes[1]);
//                JokerWindow.logIt("");
            }
        }

//        for (String string : keys) {
//            JokerWindow.logIt("KEY2: " + string);
//        }

        //JokerWindow.logIt("");

        //keys.remove("GPINIT");

//        for (int i = 0; i < keys.size(); i++) {
//            JokerWindow.logIt("KEY == " + keys.get(i));
//            JokerWindow.logIt("NEXT == " + nexts.get(i));
//            JokerWindow.logIt("::::::::::::::::::::::::::::::::::");
//        }

        //FIX IT :S
        String init = s.substring(7, 51).trim();
//        JokerWindow.logIt("INIT = " + init);
//        JokerWindow.logIt(keys.indexOf(init.trim()) + "");

        //init = nexts.get(keys.indexOf(init.trim()));

        CSPNode noInit = new CSPNode("root", "root", init);
        nos.add(noInit);


        String next = "";

        for (String k : keys) {
            String content = "";
            String key = "";

//            JokerWindow.logIt("**********************************************");
//            JokerWindow.logIt("KEY: " + k);
            next = nexts.get(keys.indexOf(k));

//            JokerWindow.logIt("NEXT: " + next);
//            JokerWindow.logIt("_____________________________________________");

            content = next;
            if (content.contains("\n")) {
                content.replaceAll("\n", "");
            }

            if (content.contains("[>")) {
                String partes[] = content.split("\\[\\>");

//                JokerWindow.logIt("[> PART[0] = " + partes[0].trim());
//                JokerWindow.logIt("[> PART[1] = " + partes[1].trim());

            } else if (content.contains("[]")) {
                String partes[] = content.split("\\[\\]");

                for (int i = 0; i < partes.length; i++) {
                    if (i == 0) {
                        partes[i] = partes[i].substring(1);
                    } else if (i == partes.length - 1) {
                        partes[i] = partes[i].substring(0, partes[i].length() - 1);
                    } else {
                        partes[i] = partes[i];
                    }

//                    JokerWindow.logIt("[] PART[i] = " + partes[i].trim());
                }

                for (int i = 0; i < partes.length; i++) {
                    String partes2[] = null;
                    if (partes[i].contains("->")) {
                        partes2 = partes[i].split("->");

//                        JokerWindow.logIt("[] -> PART[i][0] = " + partes2[0].trim());
//                        JokerWindow.logIt("[] -> PART[i][1] = " + partes2[1].trim());

                        for (int w = 0; w < partes.length - 1; w++) {
                            content = partes2[w].trim();
                            next = partes2[w + 1].trim();

                            CSPNode no = new CSPNode(content, k, next);
                            nos.add(no);

                            w++;
                        }

//                        JokerWindow.logIt("CONTENT: " + content);
//                        JokerWindow.logIt("K: " + k);
//                        JokerWindow.logIt("NEXT: " + next);

                    }

                }
            } else if (content.contains("->")) {
                String partes[] = content.split("->");

//                JokerWindow.logIt("-> PART[0] = " + partes[0].substring(1).trim());
//                JokerWindow.logIt("-> PART[1] = " + partes[1].substring(0, partes[1].length() - 1).trim());

                next = partes[1].substring(0, partes[1].length() - 1).trim();
                content = partes[0].substring(1).trim();

//                JokerWindow.logIt("NOS******************************");
//                JokerWindow.logIt("K: " + k);
//                JokerWindow.logIt("CONTENT: " + content);
//                JokerWindow.logIt("NEXT: " + next);

                CSPNode no = new CSPNode(content, k, next);
                nos.add(no);
            } else if (content.contains("~")) {
                //System.out.println("CONTAINS INTERNAL CHOICE");

                //System.out.println("CONTENT: " + content);

                String partes[] = content.split("~");

//                for (int i = 0; i < partes.length; i++) {
//                    System.out.println("PARTES " + i + " = " + partes[i]);
//                }

                Random ale = new Random();

                int qualquer = ale.nextInt(partes.length);

                String internalChoice = partes[qualquer];

                if (internalChoice.contains("\\[\\>")) {
                    partes = internalChoice.split("\\[\\>");

                    internalChoice = partes[0];
                }

                internalChoice = internalChoice.replace("(", "");
                internalChoice = internalChoice.replace(")", "");
                internalChoice = internalChoice.replace("|", "");

                //System.out.println("INTERNAL CHOICE = " + internalChoice);

                CSPNode no = new CSPNode("Internal Choice = " + qualquer, k, internalChoice);

                nos.add(no);

            } else { //SKIP or STOP

                if (content.contains("~")) {
                    String partes[] = content.split("~");

                    Random ale = new Random();

                    int qualquer = ale.nextInt(partes.length);

                    String internalChoice = partes[qualquer];

                    if (internalChoice.contains("\\[\\>")) {
                        partes = internalChoice.split("\\[\\>");

                        internalChoice = partes[0];
                    }

                    internalChoice = internalChoice.replace("(", "");
                    internalChoice = internalChoice.replace(")", "");
                    internalChoice = internalChoice.replace("|", "");

                    content = internalChoice;
                }


                if (content.contains("(")) {
                    content = content.substring(1);
                }

                if (content.contains(")")) {
                    content = content.substring(0, content.length() - 1);
                }
                if (!k.contains("GPINIT")) {
                    next = nexts.get(keys.indexOf(k));
                    next = next.substring(1, next.length() - 1);
                    if (content.startsWith("SK")) {
                        CSPNode no = new CSPNode("SKIP", k, k);
                        nos.add(no);
                    }

                    if (content.startsWith("ST")) {
                        CSPNode no = new CSPNode("STOP", k, k);
                        nos.add(no);
                    }

                    if (content.startsWith("GP_")) {
                        String keyNextNext = nexts.get(keys.indexOf(k));
                        keyNextNext = keyNextNext.substring(1, keyNextNext.length() - 1);
                        String contentNextNext = nexts.get(keys.indexOf(keyNextNext));

                        if (contentNextNext.contains("~")) {
                            contentNextNext = contentNextNext.substring(1, contentNextNext.length() - 1);
                            String partes[] = contentNextNext.split("~");

                            //RANDOM
                            String internalChoice = partes[0];

                            internalChoice = internalChoice.replace("(", "");
                            internalChoice = internalChoice.replace(")", "");
                            internalChoice = internalChoice.replace("|", "");

                            CSPNode no = new CSPNode("Internal Choice = 0", k, internalChoice);
                            nos.add(no);
                        } else {
                            contentNextNext = contentNextNext.substring(1, contentNextNext.length() - 1);
                            CSPNode no = new CSPNode(contentNextNext, k, k);
                            nos.add(no);
                        }
                    }
                }
            }
        }

        StringBuilder logger = new StringBuilder("");
        for (CSPNode no : nos) {
            logger.append("NOS******************************\n");
            logger.append("KEY:   ").append(no.getKey()).append("\n");
            logger.append("EVENT: ").append(no.getContent()).append("\n");
            logger.append("NEXT:  ").append(no.getNext()).append("\n\n");
        }

        JokerWindow.logIt(logger.toString());
    }

    public String locateGPInit(String specification) {
        String init = specification.substring(specification.indexOf("GPINIT"),
                s.indexOf("GPINIT") + KEY_SIZE);
        init = init.substring(9);

        return init;
    }

    public String locateNextNode(int pos) {
        return nexts.get(pos);
    }

    public int getClosingParenthesisPosition(String specification, int start) {
        boolean condition = true;
        int end = start + 1;
        int cont = 0;
        int t = 47;
        //JokerWindow.logIt("LENGHT: " + specification.length());
        while (condition) {
            //JokerWindow.logIt("END: " + end);
            if (specification.charAt(end) == '=') {
                //JokerWindow.logIt("= BOY");
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

    public ArrayList<CSPNode> getNos() {
        return nos;
    }
}
