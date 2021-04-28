//Libraries
import java.util.*;

// Class that performs scoping tasks. Assigns scope to each node and stores it in the table.
public class Scoper {
    //Fresh table that we will populate with [id, info, scope] items.
    private ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
    private Tree tree;
    private int scope = 0;

    //Constructor
    public Scoper(Tree t){
        this.tree = t;
        runScoper();
    }

    // Begin recursive scoping method
    private void runScoper(){
        scopeNode(tree.getHead(), "0");
    }

    private void scopeNode(Node n, String s){
        String newScope = s;
        if(n != null){
            //------DETERMINE SCOPE HERE------
            if(n.getInfo().equals("COND_LOOP")){
                scope++;
                newScope = newScope+"."+scope;
                ArrayList<String> newItem = new ArrayList<String>();
                newItem.add(""+n.getID());
                newItem.add(n.getInfo());
                newItem.add(newScope);
                this.table.add(newItem);
                n.setScope(newScope);
            }else if(n.getInfo().equals("PROC_DEFS")){
                scope++;
                ArrayList<String> newItem = new ArrayList<String>();
                newItem.add(""+n.getID());
                newItem.add(n.getInfo());
                newItem.add(newScope);
                this.table.add(newItem);
                n.setScope(newScope);
                newScope = newScope+"."+scope;
            }else{
                ArrayList<String> newItem = new ArrayList<String>();
                newItem.add(""+n.getID());
                newItem.add(n.getInfo());
                newItem.add(newScope);
                this.table.add(newItem);
                n.setScope(newScope);
            }
            //--------------------------------
            //Call the resursive function for all the children of this node.
            Node[] kids = n.getChildren();
			for(int i=0;i<kids.length;i++){
                if(kids[i] != null){
                    scopeNode(kids[i], newScope);
                }
			}

		}
    }

    //Accessor method used in the main class to get the table with scopes from the scoper
    public ArrayList<ArrayList<String>> getTable(){
        return this.table;
    }
}