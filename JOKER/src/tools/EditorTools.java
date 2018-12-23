package tools;

import java.util.ArrayList;

/**
 * This class contains basically configuration fields used
 * by Joker to set the Editor tools like COPY and PASTE.
 * @author Diego Henrique Oliveira de Souza
 */
public class EditorTools {

    private String copiado;
    private ArrayList<String> codigos = new ArrayList<String>();
    private int posicao = 0;
    private String template;

    public String getCopiado() {
        return copiado;
    }

    public void setCopiado(String copiado) {
        this.copiado = copiado;
    }

    public ArrayList<String> getCodigos() {
        return codigos;
    }

    public void setCodigos(ArrayList<String> codigos) {
        this.codigos = codigos;
    }

    public void addCodigo(String cod) {
        codigos.add(cod);
        posicao++;
    }

    public String getCodigo(int i) {
        posicao = i;
        return codigos.get(i);
    }

    public int getPosicao() {
        if (posicao > 3) {
            posicao -= 4;
        }
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public int getPosicaoRedo() {
        if (posicao < codigos.size()-2) {
            posicao += 2;
        }
        return posicao;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

}
