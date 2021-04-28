//Libraries
import java.util.*;

// Class that performs scoping tasks. Assigns scope to each node and stores it in the table.
public class Scoper {
    //Fresh table that we will populate with [id, info, scope] items.
    private ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
    private Tree tree;

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
        int lev = 1;
        if(n != null){
            //------DETERMINE SCOPE HERE------
            if(n.getInfo().equals("COND_LOOP")){
                newScope = newScope+"."+lev;
            }else if(n.getInfo().equals("PROC")){
                newScope = newScope+"."+lev;
            }
            //--------------------------------

            //Add newItem to array and add that new array to the table. (Table is a 2D array. Array of Arrays.)
			ArrayList<String> newItem = new ArrayList<String>();
            newItem.add(""+n.getID());
			newItem.add(n.getInfo());
            newItem.add(newScope);
			this.table.add(newItem);
			Node[] kids = n.getChildren();
            
            //Call the resursive function for all the children of this node.
			for(int i=0;i<kids.length;i++){
                scopeNode(kids[i], newScope);
			}

		}
    }

    //Accessor method used in the main class to get the table with scopes from the scoper
    public ArrayList<ArrayList<String>> getTable(){
        return this.table;
    }
}