package b;

import base.Token;
import gui.JokerWindow;
import io.ReadFile;
import io.SaveFile;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;
import z.ZB2XML;

/**
 * This class coordinates the process of reading a B file, generating the LTS by
 * communicating with ProB, read the LTS, converting it to ZNodes
 * (which are the same as the BNode),
 * converting them to XMLNodes and providing Animation for B.
 * @author Diego Henrique Oliveira de Souza
 */
public class BTransformation {

    int waitEvery = Integer.parseInt(JokerWindow.waitLTSEvery.getText());

    
    public BTransformation() {
        JokerWindow.barra.setString("COMPILING B SPECIFICATION...");
        JokerWindow.logIt("B choosen!");

        //executavel gerador de LTS
        String geradorLTS = JokerWindow.getOptions().getProbcliFolder();
        //comando para gerar o LTS
        String command = "-mc 10000 -nodead -save";
        //nome da especificacao
        String spec = JokerWindow.arq.getAbsolutePath();
        String LTS = "";

        final int wait = Integer.parseInt(JokerWindow.waitLTS.getText()) * 1000;

        //antes de gerar verifica se ha LTS para apagar
        File testFile = new File(JokerWindow.arq.getAbsolutePath() + ".pl");
        if (testFile.exists()) {
            testFile.delete();
        }

        boolean haLTS = false;
        try {
            //codigo que faz o trabalho em si
            Runtime r;
            Process p;

            r = Runtime.getRuntime();

            String comandoGeralB = "\"" + geradorLTS + "\" \"" + spec + "\" " + command + " " + spec + ".pl";

            JokerWindow.logIt("COMMAND = " + comandoGeralB);

            p = r.exec(geradorLTS + " " + spec + " " + command + " " + spec + ".pl");

            //espera um tempo para que o gerador crie o arquivo
            Runnable runner = new Runnable() {

                @Override
                public void run() {
                    boolean test = true;
                    int t = 0;
                    while (test && (t < wait)) {
                        try {
                            File testFile = new File(JokerWindow.arq.getAbsolutePath() + ".pl");
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
            runner.run();

            String pasta = JokerWindow.arq.getAbsolutePath().substring(0, JokerWindow.arq.getAbsolutePath().lastIndexOf(File.separator));
            String arquivo = JokerWindow.arq.getAbsolutePath().substring(JokerWindow.arq.getAbsolutePath().lastIndexOf(File.separator) + 1) + ".pl";

            //System.out.println("PASTA = " + pasta);
            //System.out.println("ARQUIVO = " + arquivo);

            File arquivos = new File(pasta);

            for (String string : arquivos.list()) {
                //System.out.println(string);
                if (string.equalsIgnoreCase(arquivo)) {
                    haLTS = true;
                }
            }

            if (!haLTS) {
                String s = "";
                BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
                BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

                // read the output from the command
//                System.out.println("Here is the standard output of the command:\n");
//                int cont = 0;
//                while ((s = stdInput.readLine()) != null && cont < 10) {
//                    System.out.println(s);
//                    cont++;
//                }

                // read any errors from the attempted command
                String errors = "";
                //System.out.println("Here is the standard error of the command (if any):\n");
                while ((s = stdError.readLine()) != null) {
                    errors += s + "\n";
                }

                JokerWindow.areaLog.setText(JokerWindow.areaLog.getText() + errors);

                //pintando de vermelho os erros de B

                //wrong token error
                if (errors.contains("! Unknown identifier ")) {
                    int begin = errors.lastIndexOf("! Unknown identifier ") + 21;

                    String wrongToken = ""; //errors.substring(begin, begin+50);

                    int end = 0;
                    for (int i = begin; i < errors.length(); i++) {
                        if (errors.charAt(i) == '!') {
                            end = i;
                            break;
                        }
                    }

                    wrongToken = errors.substring(begin, end);

                    JokerWindow.areaCodigo.setText("");
                    for (Token t : JokerWindow.tokensLexica) {
                        if (t.getConteudo().equals(wrongToken.trim())) {
                            t.setTipo("Wrong Token");
                        }

                        JokerWindow.colorirCodigo(t);
                    }
                }

                //erro de tipo
                if (errors.contains("! Could not infer type of ")) {
                    int begin = errors.lastIndexOf("! Could not infer type of ") + 26;

                    String wrongToken = ""; //errors.substring(begin, begin+50);

                    int end = 0;
                    for (int i = begin; i < errors.length(); i++) {
                        if (errors.charAt(i) == '!') {
                            end = i;
                            break;
                        }
                    }

                    wrongToken = errors.substring(begin, end);

                    JokerWindow.areaCodigo.setText("");
                    for (Token t : JokerWindow.tokensLexica) {
                        if (t.getConteudo().equals(wrongToken.trim())) {
                            t.setTipo("Wrong Token");
                        }

                        JokerWindow.colorirCodigo(t);
                    }
                }

            } else {
                LTS = ReadFile.leia(JokerWindow.arq.getAbsolutePath() + ".pl");
                JokerWindow.areaLts.setText(LTS);
                JokerWindow.abas.setTitleAt(2, SaveFile.getNomeArquivo() + ".pl");

                JokerWindow.barra.setString("B'S LTS GENERATED!");
                JokerWindow.logIt("B'S LTS GENERATED!");

                //XML
                JokerWindow.logIt("GENERATING XML FOR B SPECIFICATION");
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
            JokerWindow.barra.setString("ANIMATION FOR B AVAILABLE!");
            JokerWindow.abas.setSelectedIndex(0);
        }else{
            JokerWindow.barra.setForeground(Color.RED);
            JokerWindow.barra.setValue(50);
            JokerWindow.barra.setString("ERROR GENERATING B'S LTS!");
            JokerWindow.abas.setSelectedIndex(4);
            JokerWindow.abas.setTitleAt(2, "LTS");
            JokerWindow.abas.setTitleAt(3, "XML");
            JOptionPane.showMessageDialog(null, "An error occurred during the LTS generation!\nSee the LOG tab for more details.", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
