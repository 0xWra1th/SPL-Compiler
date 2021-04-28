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
        scopeNode(tree.getHead(), "0", 1);
    }

    private void scopeNode(Node n, String s, int l){
        System.out.println("L: "+l);
        String newScope = s;
        int lev = l;
        if(n != null){
            //------DETERMINE SCOPE HERE------
            if(n.getInfo().equals("COND_LOOP")){
                newScope = newScope+"."+(lev+1);
            }else if(n.getInfo().equals("PROC_DEFS")){
                newScope = newScope+"."+(lev+1);
            }
            //--------------------------------

            //Add newItem to array and add that new array to the table. (Table is a 2D array. Array of Arrays.)
			ArrayList<String> newItem = new ArrayList<String>();
            newItem.add(""+n.getID());
			newItem.add(n.getInfo());
            newItem.add(newScope);
			this.table.add(newItem);
			Node[] kids = n.getChildren();
            n.setScope(newScope);
            
            //Call the resursive function for all the children of this node.
            int x = -1;
            /*for(int i=0;i<kids.length;i++){
                if(kids[i] != null){
                    if(kids[i].getInfo().equals("COND_LOOP") || kids[i].getInfo().equals("PROC_DEFS")){
                        x++;
                        System.out.println("COND_LOOP OR PROC: "+x);
                    }
                }
			}*/


            
			for(int i=0;i<kids.length;i++){
                if(kids[i] != null){
                    if(kids[i].getInfo().equals("COND_LOOP") || kids[i].getInfo().equals("PROC_DEFS")){
                        x++;
                    }
                    scopeNode(kids[i], newScope, x);
                }
			}

		}
    }

    //Accessor method used in the main class to get the table with scopes from the scoper
    public ArrayList<ArrayList<String>> getTable(){
        return this.table;
    }
}