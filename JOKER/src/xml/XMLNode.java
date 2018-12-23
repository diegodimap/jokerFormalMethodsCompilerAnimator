package xml;

/**
 * This class represents the XMLNode, that is a single node in the LTS.
 * This object is used to animate the LTS for several languages.
 * @author Diego Henrique Oliveira de Souza
 */
public class XMLNode {

    private String key;
    private String event;
    private String next;
    private boolean isInit;

    public XMLNode(String key, String event, String next){
        this.key = key;
        this.event = event;
        this.next = next;
    }

    public XMLNode(boolean init, String key, String event, String next){
        this.key = key;
        this.event = event;
        this.next = next;
        this.isInit = init;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public boolean isIsInit() {
        return isInit;
    }

    public void setIsInit(boolean isInit) {
        this.isInit = isInit;
    }


}
