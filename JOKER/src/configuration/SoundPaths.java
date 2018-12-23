package configuration;

/**
 * This class contains the configuration of the sounds played by Joker
 * when an type of error occurs
 * @author Diego Henrique Oliveira de Souza
 */
public class SoundPaths {

    private String lexicalSoundPath;
    private String syntacticSoundPath;
    private String semanticSoundPath;
    private String xmlSoundPath;
    private String javaSoundPath;

    /**
     * This method returns the sound path for Java errors
     * @return the sound path for java errors
     */
    public String getJavaSoundPath() {
        return javaSoundPath;
    }

    /**
     * This method configures the path for a java error sound with the received
     * parameter
     * @param javaSoundPath
     */
    public void setJavaSoundPath(String javaSoundPath) {
        this.javaSoundPath = javaSoundPath;
    }

    /**
     * This method returns the lexical error sound path
     * @return the lexical error sound path
     */
    public String getLexicalSoundPath() {
        return lexicalSoundPath;
    }

    /**
     * This method configures the lexical error sound path with the received
     * parameter
     * @param lexicalSoundPath
     */
    public void setLexicalSoundPath(String lexicalSoundPath) {
        this.lexicalSoundPath = lexicalSoundPath;
    }

    /**
     * This method returns the semantic error sound path
     * @return the semantic error sound path
     */
    public String getSemanticSoundPath() {
        return semanticSoundPath;
    }

    /**
     * This method configures the semantic error sound path with the received
     * parameter
     * @param semanticSoundPath
     */
    public void setSemanticSoundPath(String semanticSoundPath) {
        this.semanticSoundPath = semanticSoundPath;
    }

    /**
     * This method returns the syntactic error sound path
     * @return the syntactic error sound path
     */
    public String getSyntacticSoundPath() {
        return syntacticSoundPath;
    }

    /**
     * This method configures the syntactic error sound path with the received
     * parameter
     * @param syntacticSoundPath
     */
    public void setSyntacticSoundPath(String syntacticSoundPath) {
        this.syntacticSoundPath = syntacticSoundPath;
    }

    /**
     * This method returns the XML error sound path
     * @return the XML error sound path
     */
    public String getXmlSoundPath() {
        return xmlSoundPath;
    }

    /**
     * This method configures the XML error sound path with the received
     * parameter
     * @param xmlSoundPath
     */
    public void setXmlSoundPath(String xmlSoundPath) {
        this.xmlSoundPath = xmlSoundPath;
    }
}
