/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tests;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Test class for reading a .exe external files for generating LTS automatically
 * @author Diego Henrique Oliveira de Souza
 */
public class ReadExecutable {
    public static void main(String[] args) {
        //executável gerador de LTS
        String geradorLTS = "D:/09-PROJETOS_NETBEANS/CLOWN/cspm-0.4.0.0.exe";
        //comando para gerar o LTS
        String command = " fdr --main=";
        //processo inicial
        String start = "START";
        //nome da especificação
        String spec = " D:/student.csp";

        try {
            //código que faz o trabalho em si
            Runtime.getRuntime().exec(geradorLTS + command + start + spec);
        } catch (IOException ex) {
            Logger.getLogger(ReadExecutable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
