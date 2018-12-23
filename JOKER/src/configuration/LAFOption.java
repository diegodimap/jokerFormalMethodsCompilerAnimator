package configuration;

import javax.swing.UIManager.LookAndFeelInfo;

/**
 * This class contains Joker's appearance configuration (Look And Feel)
 * @author Diego Henrique Oliveira de Souza
 */
public class LAFOption {

    private LookAndFeelInfo laf;

    /**
     * This method returns the Look And Feel selected by the user for Joker
     * @return the selected LAF
     */
    public LookAndFeelInfo getLaf() {
        return laf;
    }

    /**
     * This method configures the Look And Fell of Joker with the
     * appearance selected by the user
     * @param laf
     */
    public void setLaf(LookAndFeelInfo laf) {
        this.laf = laf;
    }

}
