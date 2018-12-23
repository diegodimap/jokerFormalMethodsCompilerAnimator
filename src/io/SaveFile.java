package io;

import gui.JokerWindow;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * This class has the function of saving the files changed in the editor.
 * It also saves the specification name along the process of animation, for
 * generating other files, such as the LTS and XML ones.
 * @author Diego Henrique Oliveira de Souza
 */
public class SaveFile {

    private static String nomeArquivo = "";
    public static File arquivo;
    private static String folderArquivo = "";

    public static void saveSameFile(String text) {
        try {
            PrintWriter pw = new PrintWriter(JokerWindow.arq);
            pw.write(text);
            pw.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "FAIL");
        }
    }

    public static void salvarArquivo(String str) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Save File");
        chooser.setCurrentDirectory(new File("."));
        int select = chooser.showSaveDialog(null);
        if (select == JFileChooser.APPROVE_OPTION) {
            try {
                File arquivo = chooser.getSelectedFile();
                folderArquivo = arquivo.getAbsolutePath();
                nomeArquivo = arquivo.getName();
                PrintWriter pw = new PrintWriter(arquivo);
                JokerWindow.arq = arquivo;
                pw.write(str);
                pw.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "FAIL");
            }
        }
    }

    public static String lerArquivo(String path) {
        String str, texto = "";
        JFileChooser eleitor = new JFileChooser();
        eleitor.setDialogTitle("Open File");
//        eleitor.setFileFilter(new javax.swing.filechooser.FileFilter() {
//
//            @Override
//            public boolean accept(File f) {
//                return (f.getName().endsWith(".lotos") || f.isDirectory());
//            }
//
//            @Override
//            public String getDescription() {
//                return "LOTOS (.lotos)";
//            }
//        });
        eleitor.setCurrentDirectory(new File(path));

        int result = eleitor.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File arquivo = eleitor.getSelectedFile();
            JokerWindow.setArquivo(arquivo);
            nomeArquivo = arquivo.getName();
            folderArquivo = arquivo.getAbsolutePath();
            try {
                BufferedReader in = new BufferedReader(new FileReader(arquivo));
                while ((str = in.readLine()) != null) {
                    texto += str + "\n";
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return texto;
    }

    public static String relerArquivo(File arq) {
        String str, texto = "";
            try {
                BufferedReader in = new BufferedReader(new FileReader(arq));
                while ((str = in.readLine()) != null) {
                    texto += str + "\n";
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return texto;
    }

    /**
     * This method returns the specification name
     * @return the specification name
     */
    public static String getNomeArquivo() {
        return nomeArquivo;
    }

    /**
     * This method saves a LOTOS code automatically. It receives a code and a
     * file as parameters. The code will be saved in the received file.
     * @param str
     * @param arquivo
     */
    public static void salvarArquivoAuto(String str, File arquivo) {
        try {
            PrintWriter pw = new PrintWriter(new File(arquivo.getAbsolutePath()
                    + File.separator + "AUTO_" + nomeArquivo));
            pw.write(str);
            pw.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "FAIL");
        }
    }

    public static String locateFile() {
        JFileChooser eleitor = new JFileChooser();
        eleitor.setDialogTitle("Open File");
        eleitor.setCurrentDirectory(new File("."));

        int result = eleitor.showOpenDialog(null);
        File arq = null;
        if (result == JFileChooser.APPROVE_OPTION) {
            arq = eleitor.getSelectedFile();
        }
        return arq.getAbsolutePath();
    }

    public static String getFolderArquivo() {
        return folderArquivo;
    }
}
