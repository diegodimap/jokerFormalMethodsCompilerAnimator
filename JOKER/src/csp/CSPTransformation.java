package csp;

import base.Token;
import gui.JokerWindow;
import io.ReadFile;
import io.SaveFile;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;

/**
 * This class coordinates all the process of reading a CSP file, communicating with
 * CSP Tool, converting the specification to
 * LTS, reading the LTS, converting it to CSPNodes, converting the CSPNodes to XMLNodes,
 * and providing the CSP Animation.
 * @author Diego Henrique Oliveira de Souza
 */
public class CSPTransformation {

    int waitEvery = Integer.parseInt(JokerWindow.waitLTSEvery.getText());

    public CSPTransformation() {
        JokerWindow.barra.setString("COMPILING CSP SPECIFICATION...");
        JokerWindow.logIt("CSP choosen!");
        //GERAR O LTS

        //executavel gerador de LTS
        String geradorLTS = JokerWindow.getOptions().getCspmFolder();
        //comando para gerar o LTS
        String command = "fdr --main=";
        //processo inicial
        String start = JOptionPane.showInputDialog(null, "Type the name of the main CSP process:", "Main CSP Process", JOptionPane.QUESTION_MESSAGE); //JokerWindow.mainProcess.getSelectedItem().toString();
        //nome da especificacao
        String spec = JokerWindow.arq.getAbsolutePath();
        String LTS = "";

        final int wait = Integer.parseInt(JokerWindow.waitLTS.getText());

        //antes de gerar verifica se ha LTS para apagar
        File testFile = new File(JokerWindow.arq.getAbsolutePath() + ".fdr");
        if (testFile.exists()) {
            testFile.delete();
        }

        boolean isError = false;
        try {
            //codigo que faz o trabalho em si
            String line = "";
            Runtime r = Runtime.getRuntime();
            Process p;

            String comandoGeralCSP = geradorLTS + " " + command + start + " " + spec;

            //comandoGeralCSP = comandoGeralCSP.replaceAll(" ","%");

            JokerWindow.logIt("COMMAND = " + comandoGeralCSP);

            p = r.exec(comandoGeralCSP);

//            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            while ((line = input.readLine()) != null) {
//                JokerWindow.areaLog.setText(JokerWindow.areaLog.getText() + line + "\n");
//            }
            JokerWindow.areaLog.setText(JokerWindow.areaLog.getText() + "*************************");
            JokerWindow.areaLog.setText(JokerWindow.areaLog.getText() + "\n\n");
            //input.close();

            JokerWindow.barra.setValue(75);
            JokerWindow.barra.setString("CSP'S LTS GENERATED!");
            JokerWindow.logIt("CSP'S LTS GENERATED!");

            //LER O LTS
            //espera um tempo para que o gerador crie o arquivo
            Runnable runnerC = new Runnable() {

                @Override
                public void run() {
                    boolean test = true;
                    int t = 0;
                    while (test && (t < wait)) {
                        try {
                            File testFile = new File(JokerWindow.arq.getAbsolutePath() + ".fdr");
                            if (testFile.exists()) {
                                test = false;
                            }
                            Thread.sleep(waitEvery);
                            t += 100;
                        } catch (InterruptedException e) {
                        }
                    }
                }
            };
            runnerC.run();

            String pasta = JokerWindow.arq.getAbsolutePath().substring(0, JokerWindow.arq.getAbsolutePath().lastIndexOf(File.separator));
            String arquivo = JokerWindow.arq.getAbsolutePath().substring(JokerWindow.arq.getAbsolutePath().lastIndexOf(File.separator) + 1) + ".fdr";

            //System.out.println("PASTA = " + pasta);
            //System.out.println("ARQUIVO = " + arquivo);

            File arquivos = new File(pasta);

            boolean haLTS = false;
            for (String string : arquivos.list()) {
                //System.out.println(string);
                if (string.equalsIgnoreCase(arquivo)) {
                    haLTS = true;
                }
            }

            if (!haLTS) {
                isError = true;
                BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line2 = "";
                String saida = "";
                while ((line2 = in.readLine()) != null) {
                    //System.out.println(line2);
                    saida += line2 + "\n";
                }
                in.close();
                JokerWindow.areaLog.setText(JokerWindow.areaLog.getText() + saida);

                //PINTAR A PALAVRA ERRADA DE VERMELHO
                String wrongToken = saida.substring(saida.lastIndexOf("tokenString"), saida.lastIndexOf("})"));
                wrongToken = wrongToken.substring(wrongToken.indexOf("=") + 3, wrongToken.length() - 1);
                wrongToken = wrongToken.trim();

                //System.out.println("ID = " + wrongToken);
                JokerWindow.areaCodigo.setText("");
                for (Token t : JokerWindow.tokensLexica) {
                    if (t.getConteudo().equals(wrongToken)) {
                        t.setTipo("Wrong Token");
                    }
                    JokerWindow.colorirCodigo(t);
                }
            } else {
                LTS = ReadFile.leia(JokerWindow.arq.getAbsolutePath() + ".fdr");
                JokerWindow.areaLts.setText(LTS);
                JokerWindow.abas.setTitleAt(2, SaveFile.getNomeArquivo() + ".fdr");

                //CHAMA PRA GERAR O XML
                JokerWindow.logIt("GENERATING XML FOR CSP SPECIFICATION");
                new CSP2XML(LTS);
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }

        JokerWindow.areaCodigo.setCaretPosition(0);
        JokerWindow.areaLts.setCaretPosition(0);
        JokerWindow.areaXml.setCaretPosition(0);
        JokerWindow.areaLog.setCaretPosition(0);

        if (!isError) {
            JokerWindow.barra.setValue(100);
            JokerWindow.barra.setString("ANIMATION FOR CSP AVAILABLE!");
            JokerWindow.abas.setSelectedIndex(0);
        } else {
            JokerWindow.barra.setForeground(Color.RED);
            JokerWindow.barra.setValue(50);
            JokerWindow.barra.setString("ERROR GENERATING CSP'S LTS!");
            JokerWindow.abas.setSelectedIndex(4);
            JokerWindow.abas.setTitleAt(2, "LTS");
            JokerWindow.abas.setTitleAt(3, "XML");
            JOptionPane.showMessageDialog(null, "An error occurred during the LTS generation!\nSee the LOG tab for more details", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
