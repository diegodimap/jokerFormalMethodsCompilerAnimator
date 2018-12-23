package io;

import java.io.File;
import javax.swing.JFileChooser;

/**
 * This class manages the .wav files.
 * @author Diego Henrique Oliveira de Souza
 */
public class SoundFile {

    private static String nomeArquivo = "";

    /**
     * This method reads a .wav file from a directory received as
     * parameter and returns it's path
     * @param path
     * @return the .wav's path
     */
    public static String lerArquivo(String path) {
        JFileChooser eleitor = new JFileChooser();
        eleitor.setDialogTitle("Open WAV File");
        eleitor.setFileFilter(new javax.swing.filechooser.FileFilter() {

            @Override
            public boolean accept(File f) {
                return (f.getName().endsWith(".wav") || f.isDirectory());
            }

            @Override
            public String getDescription() {
                return "WAV (.wav)";
            }
        });
        eleitor.setCurrentDirectory(new File(path));

        int result = eleitor.showOpenDialog(null);
        File arquivo = null;
        if (result == JFileChooser.APPROVE_OPTION) {
            arquivo = eleitor.getSelectedFile();
            nomeArquivo = arquivo.getName();
        }
        return arquivo.getAbsolutePath();
    }

    /**
     * This method returns the file name
     * @return the file name
     */
    public static String getNomeArquivo() {
        return nomeArquivo;
    }
}
