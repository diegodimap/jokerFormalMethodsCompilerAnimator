package csp;

/**
 * This class is the base of the CSP animation. It is responsible for representing
 * the CSPNode as an object.
 * @author Diego Henrique Oliveira de Souza
 */
public class CSPNode {
    String content;
    String key;
    String next;

    public CSPNode(String content, String key, String next) {
        this.content = content;
        this.key = key;
        this.next = next;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

}
