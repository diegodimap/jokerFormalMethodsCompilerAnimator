package configuration;

import java.awt.Color;

/**
 * This class contains the color configuration of the Joker's editor.
 * @author Diego Henrique Oliveira de Souza
 */
public class EditorColors {

    private Color palavra_chave;
    private Color numeros;
    private Color key;
    private Color identificador;
    private Color comentario;
    private Color operadores;
    private Color ends;
    private Color nao_reconhecidos;
    private boolean isBold_palavra_chave = false;
    private boolean isBold_gate = false;
    private boolean isBold_processo = false;
    private boolean isBold_identificador = false;
    private boolean isBold_comentario = false;
    private boolean isBold_operadores = false;
    private boolean isBold_saida = false;
    private boolean isBold_nao_reconhecidos = true;
    private boolean isItalic_palavra_chave = false;
    private boolean isItalic_gate = false;
    private boolean isItalic_processo = false;
    private boolean isItalic_identificador = false;
    private boolean isItalic_comentario = false;
    private boolean isItalic_operadores = false;
    private boolean isItalic_saida = false;
    private boolean isItalic_nao_reconhecidos = true;

    /**
     * Default constructor initializes the fields
     */
    public EditorColors() {
        palavra_chave = Color.BLUE;
        numeros = Color.BLACK;
        key = Color.CYAN;
        identificador = Color.BLACK;
        comentario = Color.GRAY;
        operadores = new Color(100,149,237);
        ends = new Color(148, 0, 211);
        nao_reconhecidos = Color.RED;
    }

    /**
     * This method returns the comment's color
     * @return the comment's color
     */
    public Color getComentario() {
        return comentario;
    }

    /**
     * This method configures the comment's color with the received parameter
     * @param comentario
     */
    public void setComentario(Color comentario) {
        this.comentario = comentario;
    }

    /**
     * This method returns the [ and ] color
     * @return the [ and ] color
     */
    public Color getGate() {
        return numeros;
    }

    /**
     * This method configures the [ and ] color with the received parameter
     * @param color
     */
    public void setGate(Color gate) {
        this.numeros = gate;
    }

    /**
     * This method returns the identificator's color
     * @return the identificator's color
     */
    public Color getIdentificador() {
        return identificador;
    }

    /**
     * This method configures the identificator's color with the received parameter
     * @param identificador
     */
    public void setIdentificador(Color identificador) {
        this.identificador = identificador;
    }

    /**
     * This method returns the not-recognized word's color
     * @return the not-recognized word's color
     */
    public Color getNao_reconhecidos() {
        return nao_reconhecidos;
    }

    /**
     * This method configures the not-recognized word's color with the received
     * parameter
     * @param nao_reconhecidos
     */
    public void setNao_reconhecidos(Color nao_reconhecidos) {
        this.nao_reconhecidos = nao_reconhecidos;
    }

    /**
     * This method returns the operator's color
     * @return the operator's color
     */
    public Color getOperadores() {
        return operadores;
    }

    /**
     * This method configures the operator's color with the received parameter
     * @param operadores
     */
    public void setOperadores(Color operadores) {
        this.operadores = operadores;
    }

    /**
     * This method returns the keyword's color
     * @return the keyword's color
     */
    public Color getPalavra_chave() {
        return palavra_chave;
    }

    /**
     * This method configures the keyword's color with the received parameter
     * @param palavra_chave
     */
    public void setPalavra_chave(Color palavra_chave) {
        this.palavra_chave = palavra_chave;
    }

    /**
     * This method returns the ; color
     * @return the ; color
     */
    public Color getProcesso() {
        return key;
    }

    /**
     * This method configures the ; color with the received parameter
     * @param color
     */
    public void setProcesso(Color processo) {
        this.key = processo;
    }

    /**
     * This method returns the exit-type's color
     * @return the exit-type's color
     */
    public Color getSaida() {
        return ends;
    }

    /**
     * This method configures the exit-type's color with the received parameter
     * @param saida
     */
    public void setSaida(Color saida) {
        this.ends = saida;
    }

    /**
     * This method returns true if the comments must be in bold format
     * @return true to bold format for comments
     */
    public boolean isIsBold_comentario() {
        return isBold_comentario;
    }

    /**
     * This method configures the bold format to comments with the received
     * parameter
     * @param isBold_comentario
     */
    public void setIsBold_comentario(boolean isBold_comentario) {
        this.isBold_comentario = isBold_comentario;
    }

    /**
     * This method return true if the [ and ] must be in bold format
     * @return true for [ and ] in bold format
     */
    public boolean isIsBold_gate() {
        return isBold_gate;
    }

    /**
     * This method configures bold format to [ and ] with the received parameter
     * @param color
     */
    public void setIsBold_gate(boolean isBold_gate) {
        this.isBold_gate = isBold_gate;
    }

    /**
     * This method returns true for identificators in bold format
     * @return true for bold identificators
     */
    public boolean isIsBold_identificador() {
        return isBold_identificador;
    }

    /**
     * This method configures bold format for identificators with the received parameter
     * @param isBold_identificador
     */
    public void setIsBold_identificador(boolean isBold_identificador) {
        this.isBold_identificador = isBold_identificador;
    }

    /**
     * This method returns true for bold not-recognized words
     * @return true for bold not-recognized words
     */
    public boolean isIsBold_nao_reconhecidos() {
        return isBold_nao_reconhecidos;
    }

    /**
     * This method configures bold format for not-recognizes words with the
     * received parameter
     * @param isBold_nao_reconhecidos
     */
    public void setIsBold_nao_reconhecidos(boolean isBold_nao_reconhecidos) {
        this.isBold_nao_reconhecidos = isBold_nao_reconhecidos;
    }

    /**
     * This method returns true for operators in bold format
     * @return true for bold operators
     */
    public boolean isIsBold_operadores() {
        return isBold_operadores;
    }

    /**
     * This method configures bold format for operators with the received
     * parameter
     * @param isBold_operadores
     */
    public void setIsBold_operadores(boolean isBold_operadores) {
        this.isBold_operadores = isBold_operadores;
    }

    /**
     * This method returns true for bold keywords
     * @return true in case of bold keywords
     */
    public boolean isIsBold_palavra_chave() {
        return isBold_palavra_chave;
    }

    /**
     * This method configures bold format for keywords with the received
     * parameter
     * @param isBold_palavra_chave
     */
    public void setIsBold_palavra_chave(boolean isBold_palavra_chave) {
        this.isBold_palavra_chave = isBold_palavra_chave;
    }

    /**
     * This method returns true in case of bold ;
     * @return true for bold ;
     */
    public boolean isIsBold_processo() {
        return isBold_processo;
    }

    /**
     * This method configures the bold format for ; with the received
     * parameter
     * @param isBold
     */
    public void setIsBold_processo(boolean isBold_processo) {
        this.isBold_processo = isBold_processo;
    }

    /**
     * This method returns true for bold exit types
     * @return true for bold exit types
     */
    public boolean isIsBold_saida() {
        return isBold_saida;
    }

    /**
     * This method configures bold format for exit types with the received
     * parameter
     * @param isBold_saida
     */
    public void setIsBold_saida(boolean isBold_saida) {
        this.isBold_saida = isBold_saida;
    }

    /**
     * This method returns true for italic comments
     * @return true for italic comments
     */
    public boolean isIsItalic_comentario() {
        return isItalic_comentario;
    }

    /**
     * This method configures the italic format for comments with the received
     * parameter
     * @param isItalic_comentario
     */
    public void setIsItalic_comentario(boolean isItalic_comentario) {
        this.isItalic_comentario = isItalic_comentario;
    }

    /**
     * This method returns true for italic gates
     * @return true for italic gates
     */
    public boolean isIsItalic_gate() {
        return isItalic_gate;
    }

    /**
     * This method configures the italic format for gates
     * @param isItalic_gate
     */
    public void setIsItalic_gate(boolean isItalic_gate) {
        this.isItalic_gate = isItalic_gate;
    }

    /**
     * This method returns the italic format for identificators
     * @return true for italic identificators
     */
    public boolean isIsItalic_identificador() {
        return isItalic_identificador;
    }

    /**
     * This method configures the italic format for identificators with the received
     * parameter
     * @param isItalic_identificador
     */
    public void setIsItalic_identificador(boolean isItalic_identificador) {
        this.isItalic_identificador = isItalic_identificador;
    }

    /**
     * This method returns true for not-recognized words in italic format
     * @return true for italic not-recognized words
     */
    public boolean isIsItalic_nao_reconhecidos() {
        return isItalic_nao_reconhecidos;
    }

    /**
     * This method configures the italic format for not-recognized words with
     * the received parameter
     * @param isItalic_nao_reconhecidos
     */
    public void setIsItalic_nao_reconhecidos(boolean isItalic_nao_reconhecidos) {
        this.isItalic_nao_reconhecidos = isItalic_nao_reconhecidos;
    }

    /**
     * This method returns true for operators in italic format
     * @return true for italic operators
     */
    public boolean isIsItalic_operadores() {
        return isItalic_operadores;
    }

    /**
     * This method configures the italic format for operators with the received
     * parameter
     * @param isItalic_operadores
     */
    public void setIsItalic_operadores(boolean isItalic_operadores) {
        this.isItalic_operadores = isItalic_operadores;
    }

    /**
     * This method returns true for italic keywords
     * @return true for italic keywords
     */
    public boolean isIsItalic_palavra_chave() {
        return isItalic_palavra_chave;
    }

    /**
     * This method configures italic format for keywords with the received
     * parameter
     * @param isItalic_palavra_chave
     */
    public void setIsItalic_palavra_chave(boolean isItalic_palavra_chave) {
        this.isItalic_palavra_chave = isItalic_palavra_chave;
    }

    /**
     * This method returns true for italic ;
     * @return true for italic ;
     */
    public boolean isIsItalic_processo() {
        return isItalic_processo;
    }

    /**
     * This method configures italic format for ; with the received
     * parameter
     * @param isItalic_processo
     */
    public void setIsItalic_processo(boolean isItalic_processo) {
        this.isItalic_processo = isItalic_processo;
    }

    /**
     * This method returns true for italic exit types
     * @return true for italic exit types
     */
    public boolean isIsItalic_saida() {
        return isItalic_saida;
    }

    /**
     * This method configures italic format for exit types with the received
     * parameter
     * @param isItalic_saida
     */
    public void setIsItalic_saida(boolean isItalic_saida) {
        this.isItalic_saida = isItalic_saida;
    }
}
