package z;

/**
 * This class represents a single node for Z's LTS. 
 * @author Diego Henrique Oliveira de Souza
 */
public class ZBNode {
    private int id;
    private String action;
    private int next;

    public ZBNode(int id, String action, int next) {
        this.id = id;
        this.action = action;
        this.next = next;
    }

    public ZBNode() {
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }


}
