public class Tree {
    private Node head;
    public Tree(){
        this.head = new Node("0");
    }

    public Tree(Node n){
        this.head = n;
    }

    public Node getHead(){
        return this.head;
    }

    public void setHead(Node h){
        this.head = h;
    }

    public String toString(){
        String out = ""+head.getID();
        Node curr = head;

        /*for(int i=0;i<curr.getChildren().length;i++){
            out = out+"\n"+listKids(curr.getChildren()[i]);
        }*/
        return out;
    }

    private String listKids(Node n){
        String kids = "Node "+n.getID();
        /*for(int i=0;i<n.getChildren().length;i++){
            if(n.getChildren() != null && n.getChildren()[i] != null){
                kids = kids+"->"+n.getChildren()[i].getID();
            }
        }
        for(int i=0;i<n.getChildren().length;i++){
            if(n.getChildren() != null && n.getChildren()[i] != null){
                kids = kids+"\n\t"+listKids(n.getChildren()[i]);
            }
        }*/
        return kids;
    }
}
