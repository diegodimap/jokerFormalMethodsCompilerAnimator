package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class reads a File containg text in many extensios, as .csp, .tex, .mch,
 * .lotos, .txt, .java and other ones.
 * @author Diego Henrique Oliveira de Souza
 */
public class ReadFile {

    private static String texto = "";

    /**
     * This method reads the text inside a File and returns it
     * @param caminho
     * @return the text inside the File
     */
    public static String leia(String caminho) throws IOException {
        texto = "";
        String str = "";
        File arquivo = new File(caminho);
        BufferedReader in = new BufferedReader(new FileReader(arquivo));
        while ((str = in.readLine()) != null) {
            texto += str + "\n";
        }
        in.close();
        return texto;
    }
}
