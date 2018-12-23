package base;

/**
 * This class represents a Token.
 * A Token is an object that contains a word, a type, a line and a position
 * inside a specification code.
 * @author Diego Henrique Oliveira de Souza
 */
public class Token {
    private int inicio;
    private int fim;
    private int linha;
    private String conteudo;
    private String tipo;

    /**
     * This is the complete constructor. It receives all the parameters.
     * @param inicio is the start position in the code
     * @param fim is the end position in the code
     * @param linha is the line on the code
     * @param conteudo is the word in the code
     * @param tipo is the type
     */
    public Token(int inicio, int fim, int linha, String conteudo, String tipo) {
        this.inicio = inicio;
        this.fim = fim;
        this.linha = linha;
        this.conteudo = conteudo;
        this.tipo = tipo;
    }

    /**
     * This method returns the word of a token.
     * @return the word
     */
    public String getConteudo() {
        return conteudo;
    }

    /**
     * This method returns the end position of the token in the code.
     * @return the end position
     */
    public int getFim() {
        return fim;
    }

    /**
     * * This method returns the start position of the token in the code.
     * @return the start position
     */
    public int getInicio() {
        return inicio;
    }

    /**
     * This method returns the line of the token in the code.
     * @return the line
     */
    public int getLinha() {
        return linha;
    }

    /**
     * This method returns the type of this token.
     * @return the type
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * This method sets the word with the received parameter.
     * @param conteudo is the content of the word
     */
    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    /**
     * This method sets the end position with the received parameter.
     * @param fim is the end position
     */
    public void setFim(int fim) {
        this.fim = fim;
    }

    /**
     * This method sets the start position with the received parameter.
     * @param inicio is the start position
     */
    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    /**
     * This method sets the line of the token with the received parameter.
     * @param linha is the line of the token
     */
    public void setLinha(int linha) {
        this.linha = linha;
    }

    /**
     * This method sets the type of the token with the received parameter.
     * @param tipo is the type of the token
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
