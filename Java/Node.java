public class Node {

    private Node[] children;
    private String ID;
    private String info;
    private String scope;

    public Node(String id){
        children = new Node[50];
        ID = id;
    }

    public Node(String id, String in){
        children = new Node[50];
        ID = id;
        info = in;
    }

    public Node[] getChildren(){
        return this.children;
    }

    public void setChildren(Node[] c){
        this.children = c;
    }

    public String getID(){
        return this.ID;
    }

    public void setID(String i){
        this.ID = i;
    }

    public String getInfo(){
        return this.info;
    }

    public void setInfo(String i){
        this.info = i;
    }

    public String getScope(){
        return this.scope;
    }

    public void setScope(String i){
        this.scope = i;
    }
}
