package configuration;

import java.util.ArrayList;

/**
 * This class manages the font family and size of the editor.
 * @author Diego Henrique Oliveira de Souza
 */
public class Fonts {

    private ArrayList<String> fontes = new ArrayList<String>();
    private ArrayList<Integer> tamanhos = new ArrayList<Integer>();
    private String fonteAtual = "Monospaced";
    private int tamanhoAtual = 16;

    /**
     * This default constructor initializes the font family options
     * with all the fonts installed on the computer.
     * The sizes starts on 8, 2 by 2, ending in 30.
     */
    public Fonts() {
        String[] fontesDisponiveis = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        for (String string : fontesDisponiveis) {
            fontes.add(string);
        }

        tamanhos.add(8);
        tamanhos.add(10);
        tamanhos.add(12);
        tamanhos.add(14);
        tamanhos.add(16);
        tamanhos.add(18);
        tamanhos.add(20);
        tamanhos.add(22);
        tamanhos.add(24);
        tamanhos.add(26);
        tamanhos.add(28);
        tamanhos.add(30);
    }

    /**
     * This method returns the font family
     * @return the font family
     */
    public String getFonteAtual() {
        return fonteAtual;
    }

    /**
     * This method configures the font family with the received parameter
     * @param fonteAtual
     */
    public void setFonteAtual(String fonteAtual) {
        this.fonteAtual = fonteAtual;
    }

    /**
     * This method returns the possible font families
     * @return an array containing all the font families
     */
    public ArrayList<String> getFontes() {
        return fontes;
    }

    /**
     * This method configures the font family possibilities with the
     * received array
     * @param fontes
     */
    public void setFontes(ArrayList<String> fontes) {
        this.fontes = fontes;
    }

    /**
     * This method returns the font size
     * @return the font size
     */
    public int getTamanhoAtual() {
        return tamanhoAtual;
    }

    /**
     * This method configures the font size with the received parameter
     * @param tamanhoAtual
     */
    public void setTamanhoAtual(int tamanhoAtual) {
        this.tamanhoAtual = tamanhoAtual;
    }

    /**
     * This method returns an array containing all the possible font sizes
     * @return an array with the possible font sizes
     */
    public ArrayList<Integer> getTamanhos() {
        return tamanhos;
    }

    /**
     * This method configures the possible font sizes with the received
     * parameter
     * @param tamanhos
     */
    public void setTamanhos(ArrayList<Integer> tamanhos) {
        this.tamanhos = tamanhos;
    }
}
