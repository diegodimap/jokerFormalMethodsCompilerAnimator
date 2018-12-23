package io;

import gui.JokerWindow;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This method writes a text into a file in the file system.
 * @author Diego Henrique Oliveira de Souza
 */
public class WriteFile {

    /**
     * This method receives a path and a text, and writes a file in the
     * received file path
     * @param caminho is the path of the file 
     * @param texto is the text to be written in the file
     */
    public static void escreva(String caminho, String texto) throws IOException {
        JokerWindow.logIt("WRITING FILE: " + caminho + "\nTEXT: = \n" + texto);
        File arquivo = new File(caminho);
        PrintWriter pw = new PrintWriter(arquivo);
        pw.write(texto);
        pw.close();
    }
}
