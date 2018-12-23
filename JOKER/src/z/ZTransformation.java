package z;

import base.Token;
import gui.JokerWindow;
import io.ReadFile;
import io.SaveFile;
import io.WriteFile;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;

/**
 * This class coordinates all the process of reading a Z file, communicating with
 * ProB for generating the LTS, reading the file with the LTS, converting the
 * LTS to an array of ZNode objects, converting them to XMLNode objects and
 * providing animation for Z.
 * @author Diego Henrique Oliveira de Souza
 */
public class ZTransformation {

    int waitEvery = Integer.parseInt(JokerWindow.waitLTSEvery.getText());

    public ZTransformation() {
        JokerWindow.barra.setString("COMPILING Z SPECIFICATION...");
        JokerWindow.logIt("Z choosen!");

        //executavel gerador de LTS
        String geradorLTS = JokerWindow.getOptions().getProbcliFolder();
        //comando para gerar o LTS
        String command = "-mc 10000 -nodead -save";
        //nome da especificao
        String spec = JokerWindow.arq.getAbsolutePath();
        String LTS = "";

        final int wait = Integer.parseInt(JokerWindow.waitLTS.getText());

        //antes de gerar verifica se ha .PL para apagar
        File testFile = new File(JokerWindow.arq.getAbsolutePath() + ".pl");
        if (testFile.exists()) {
            testFile.delete();
        }

        //antes de gerar verifica se ha .FUZZ para apagar
        File testFile2 = new File(JokerWindow.arq.getAbsolutePath() + ".fuzz");
        if (testFile2.exists()) {
            testFile2.delete();
        }

        boolean haLTS = false;
        try {
            //codigo que faz o trabalho em si
            Runtime r;
            Process p;
            Runtime r2;
            Process p2 = null;

            r = Runtime.getRuntime();
            r2 = Runtime.getRuntime();
            String probFolder = JokerWindow.getOptions().getProbcliFolder();

            JokerWindow.logIt("SEPARATOR = " + File.pathSeparator);

            probFolder = probFolder.substring(0, probFolder.lastIndexOf(File.separator) + 1);
            JokerWindow.logIt("PROB FOLDER " + probFolder);

            String comandoGeralFuzz = probFolder + "lib" + File.separator + "fuzz -p " + probFolder
                    + "lib" + File.separator + "fuzzlib -l " + spec;

//            System.out.println(comandoGeralFuzz);

            //>> is not working, so it needs to be created manually
            //String commandFuzz = ; // + " >> " + spec + ".fuzz";
            JokerWindow.logIt("COMMAND = " + comandoGeralFuzz);
            p = r.exec(comandoGeralFuzz);

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader es = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            String line = "";
            String fuzzCode = "";
            String errors = "";
            while ((line = br.readLine()) != null) {
                fuzzCode += line + "\n";
                //System.out.println(line);
            }

            while ((line = es.readLine()) != null) {
                errors += line + "\n";
                //System.out.println(line);
            }

            if (errors.length() > 0) {
                JokerWindow.logIt(errors);

                //colorindo erros em vermelho
                int begin = errors.lastIndexOf("Identifier") + 11;
                int end = errors.lastIndexOf("is not declared");

                String wrongToken = ""; //errors.substring(begin, begin+50);
                wrongToken = errors.substring(begin, end);

                System.out.println("WRONG TOKEN = " + wrongToken);

                JokerWindow.areaCodigo.setText("");
                for (Token t : JokerWindow.tokensLexica) {
                    if (t.getConteudo().equals(wrongToken.trim())) {
                        t.setTipo("Wrong Token");
                    }

                    JokerWindow.colorirCodigo(t);
                }

            } else {
                haLTS = true;
                WriteFile.escreva(spec + ".fuzz", fuzzCode);
                JokerWindow.barra.setString("FUZZ FILE GENERATED!");
                JokerWindow.barra.setValue(50);
                JokerWindow.logIt("GENERATED FUZZ FILE \nNAME: " + spec + ".fuzz \n\n" + fuzzCode);

                Runnable runnerF = new Runnable() {

                    @Override
                    public void run() {
                        boolean test = true;
                        int t = 0;
                        while (test && (t < wait)) {
                            try {
                                File testFile = new File(JokerWindow.arq.getAbsolutePath() + ".fuzz");
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
                runnerF.run();

                String comandoGeralZ = geradorLTS + " " + spec + ".fuzz " + command + " " + spec + ".pl";
//            System.out.println(comandoGeralZ);
                JokerWindow.logIt("COMMAND = " + comandoGeralZ);

                p2 = r2.exec(comandoGeralZ);

                JokerWindow.barra.setString("Z'S LTS GENERATED!");
                JokerWindow.logIt("Z'S LTS GENERATED!");

                Runnable runnerF2 = new Runnable() {

                    @Override
                    public void run() {
                        boolean test2 = true;
                        int t2 = 0;
                        while (test2) {
                            try {
                                File testFile = new File(JokerWindow.arq.getAbsolutePath() + ".pl");
                                if (testFile.exists() && t2 > wait) {
                                    test2 = false;
                                }
                                Thread.sleep(waitEvery);
                                t2 += 100;
                            } catch (InterruptedException e) {
                            }
                        }
                    }
                };
                runnerF2.run();

                LTS = ReadFile.leia(JokerWindow.arq.getAbsolutePath() + ".pl");
                JokerWindow.areaLts.setText(LTS);
                JokerWindow.abas.setTitleAt(2, SaveFile.getNomeArquivo() + ".pl");

                //XML
                JokerWindow.logIt("GENERATING XML FOR Z SPECIFICATION");
                new ZB2XML(LTS);
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }

        JokerWindow.areaCodigo.setCaretPosition(0);
        JokerWindow.areaLts.setCaretPosition(0);
        JokerWindow.areaXml.setCaretPosition(0);
        JokerWindow.areaLog.setCaretPosition(0);

        if (haLTS) {
            JokerWindow.barra.setValue(100);
            JokerWindow.barra.setString("ANIMATION FOR Z AVAILABLE!");
            JokerWindow.abas.setSelectedIndex(0);
        } else {
            JokerWindow.barra.setForeground(Color.RED);
            JokerWindow.barra.setValue(50);
            JokerWindow.barra.setString("ERROR GENERATING Z'S LTS!");
            JokerWindow.abas.setSelectedIndex(4);
            JokerWindow.abas.setTitleAt(2, "LTS");
            JokerWindow.abas.setTitleAt(3, "XML");
            JOptionPane.showMessageDialog(null, "An error occurred during the LTS generation!\nSee the LOG tab for more details.", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
