//Libraries
import java.util.*;

public class Scoper {
    private ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
    private Tree tree;

    public Scoper(Tree t){
        this.tree = t;
        runScoper();
    }

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

			ArrayList<String> newItem = new ArrayList<String>();
            newItem.add(""+n.getID());
			newItem.add(n.getInfo());
            newItem.add(newScope);
			this.table.add(newItem);
			Node[] kids = n.getChildren();
            
			for(int i=0;i<kids.length;i++){
                scopeNode(kids[i], newScope);
			}

		}
    }

    public ArrayList<ArrayList<String>> getTable(){
        return this.table;
    }
}