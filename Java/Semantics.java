//Libraries
import java.util.*;

public class Semantics {

    //Private Variables
    ArrayList<ArrayList<String>> table;
    Tree tree;
    ArrayList<ArrayList<String>> varLists = new ArrayList<ArrayList<String>>(); //A List of Lists Listing Node IDs
    int varListCounter = 0;
    boolean varsChecked = false;

    // -------------------------- CONSTRUCTORS --------------------------
    public Semantics(ArrayList<ArrayList<String>> tab, Tree tree){
        this.tree = tree;
        this.table = tab;
    }
    // ------------------------------------------------------------------
    
    // -------------------------------- CONTROL FUNCTION TO RUN PHASED ANALYSIS --------------------------------
    public void runAnalysis(){
        // ------------------- Procedure Name Checking -------------------
        /* RULES: 
         1) No PROC can be named the same as a VAR ✓
         2) Two PROCs with identical scopes must have unique names ✓ 
        */

        procVarCheck();
        procScopeCheck();

        // ---------------------------------------------------------------
        
        // ------------------- Memory Pointer Checking -------------------
        /* RULES:
         1) Two var's with the same name in the same scope point to the SAME memory. (IF name and scope are identical) ✓
         2) Two var's within the same hierarchy of scopes with the same name point to the SAME memory. (IF either scope is a substring of the other and names are identical) ✓
         3) Two var's in UN-RELATED scopes with the same name point to DIFFERENT memory. (IF either scope is a substring of the other, there is not a higher instance of the variable in a shared scope and the names are identical) ✓
         4) Loop counting var in header of for loop is private to the loop, even if there is a variable in the scope with the same name. (IF var in a for loop is the same as the loop counter variable) ✓
        */

        varScopeCheck();
        varRename();

        // ---------------------------------------------------------------
        
        // ------------------- Declaration Application Checking -------------------

        procedureCallCheck();
        callValidityCheck();
        forLoopCheck();

        // ---------------------------------------------------------------
    }
    // ---------------------------------------------------------------------------------------------------------
    
    // ---------------------------------------- VARIABLE CHECK FUNCTIONS ----------------------------------------
    private void varScopeCheck(){
        boolean same = false;
        String currForID = "";
        String currForVAR = "";
        String currForScope = "";
        String IcurrForID = "";
        String IcurrForVAR = "";
        String IcurrForScope = "";

        // ---------------------------- CREATE LISTS ----------------------------
        for(int i=0;i<table.size();i++){
            if(table.get(i).get(1).equals("VAR")){

                // Add var to list.
                varLists.add(new ArrayList<String>());
                varLists.get(varListCounter).add(table.get(i).get(0));

                // Check for other vars that match this memory pointer
                for(int x=0;x<table.size();x++){
                    same = false;
                    if(table.get(x).get(1).equals("for")){
                        currForID = table.get(x).get(0);
                        currForVAR = table.get(x+2).get(1);
                        currForScope = table.get(x).get(2);
                    }
                    if(x < table.size()-1 && i < table.size()-1 && table.get(x).get(1).equals("VAR") && !table.get(i).get(0).equals(table.get(x).get(0)) && table.get(i+1).get(1).equals(table.get(x+1).get(1))){
                        // ------ RULE 1 ------
                        if(table.get(i+1).get(2).equals(table.get(x+1).get(2))){
                            same = true;
                        }
                        
                        // ------ RULE 2 ------
                        if(table.get(i+1).get(2).contains(table.get(x+1).get(2)) || table.get(x+1).get(2).contains(table.get(i+1).get(2))){
                            same = true;
                        }
                        
                        // ------ RULE 3 ------
                        // IGNORE THOSE CASES
                        // --------------------
                        
                        // ------ RULE 4 ------
                        if((table.get(x).get(2).contains(currForScope) && table.get(x+1).get(1).equals(currForVAR)) && !(table.get(i).get(2).contains(currForScope) && table.get(i+1).get(1).equals(currForVAR))){
                            same = false;
                        }else if(!(table.get(x).get(2).contains(currForScope) && table.get(x+1).get(1).equals(currForVAR)) && (table.get(i).get(2).contains(currForScope) && table.get(i+1).get(1).equals(currForVAR))){
                            same = false;
                        }
                    }
                    if(same){
                        // Add var Node to the list
                        varLists.get(varListCounter).add(table.get(x).get(0));
                    }
                }
                varListCounter++;
            }else if(table.get(i).get(1).equals("for")){
                IcurrForID = table.get(i).get(0);
                IcurrForVAR = table.get(i+2).get(1);
                IcurrForScope = table.get(i).get(2);
            }
        }
        // ----------------------------------------------------------------------
        // -------------------------- DELETE DUPLICATES -------------------------
        for(int p=0;p<2;p++){
            for(int i=0;i<varListCounter;i++){
                boolean dupe = false;
                ArrayList<String> list = varLists.get(i);
                for(int x=0;x<varListCounter;x++){
                    boolean equal = true;
                    for(int y=0;y<list.size();y++){
                        if(x!=i && !varLists.get(x).contains(list.get(y))){
                            equal = false;
                            break;
                        }else if(x == i){
                            equal = false;
                            break;
                        }
                    }
                    if(equal){
                        dupe = true;
                    }
                }
                if(dupe){
                    //DELETE LIST

                    varLists.remove(i);
                    varListCounter--;
                }
            }
        }
        // ---------------------------------------------------------------------
        // -------------------------- DISPLAYING LISTS -------------------------
        //Sorting
        for(int i=0;i<varLists.size();i++){
            Collections.sort(varLists.get(i));
        }
        ArrayList<ArrayList<String>> tmp = new ArrayList<ArrayList<String>>();
        ArrayList<String> small = varLists.get(0);
        int smlIdx = 0;
        for(int i=0;i<varListCounter;i++){
            for(int x=0;x<varLists.size();x++){
                if(Integer.parseInt(varLists.get(x).get(0)) < Integer.parseInt(small.get(0))){
                    //System.out.println("Smaller");
                    small = varLists.get(x);
                    smlIdx = x;
                }
            }
            tmp.add(small);
            if(smlIdx < varLists.size()){
                varLists.remove(smlIdx);
            }
            if(varLists.size() > 0){
                small = varLists.get(0);
            }
            smlIdx = 0;
        }
        varLists = tmp;
        //Printing
        /*System.out.println("--------------------------------");
        for(int i=0;i<varLists.size();i++){
            ArrayList<String> list = varLists.get(i);
            System.out.print("[");
            for(int x=0;x<list.size();x++){
                if(x==0){
                    System.out.print(list.get(x));
                }else{
                    System.out.print(", "+list.get(x));
                }
            }
            System.out.print("]\n");
        }
        System.out.println("--------------------------------");*/
        // ----------------------------------------------------------------------
    }
    // ----------------------------------------------------------------------------------------------------------
    // -------------------------- RENAMES VARs ACCORDING TO MEMORY POINTER LIST --------------------------
    private void varRename(){
        for(int i=0;i<varListCounter;i++){
            ArrayList<String> list = varLists.get(i);
            for(int x=0;x<list.size();x++){
                int id = Integer.parseInt(list.get(x))-1;
                Node n = findNode(""+id , tree.getHead());
                if(n != null){
                    n.setInfo("v"+i);
                }
            }
        }
    }
    // ---------------------------------------------------------------------------------------------------
    // ----------- Recursively finds a Node by ID -----------
    private Node findNode(String ID, Node n){
        Node res = null;
        if(n != null){
            if(n.getID().equals(ID)){
                return n;
            }
            Node[] kids = n.getChildren();
            res = null;
            for(int i=0;res == null && i < kids.length;i++){
                res = findNode(ID, kids[i]);
            }
        }
        return res;
    }
    // ------------------------------------------------------

    // ----------- Recursively finds a Node by ID -----------
    private void removeNode(String ID){
    	//System.out.println("Remove Node!");
    	Node curr = new Node();
    	int nodeID = 0;
    	boolean found = false;

        while(!found){
        	curr = findNode(""+nodeID, tree.getHead());
        	if(curr != null){
	        	Node[] kids = curr.getChildren();
	        	Node[] newKids = new Node[kids.length-1];
	        	int nkSize = 0;
                boolean contained = false;

                for(int i=0;i<kids.length;i++){
                    if(kids[i] != null && kids[i].getID().equals(ID)){
                        contained = true;
                    }
                }
                if(contained){
    	        	if(newKids.length != 0){
    		        	for(int i=0;i<kids.length;i++){
    		        		if(kids[i] != null && !kids[i].getID().equals(ID)){
    		        			found = true;
                                System.out.println("nk: "+newKids.length+" : "+nkSize);
                                System.out.println("kids: "+kids.length+" : "+i);
    		        			newKids[nkSize] = kids[i];
    		        			nkSize++;
    		        		}
    		        	}
    		        	curr.setChildren(newKids);
    		        }else{
    		        	if(kids[0].getID().equals(ID)){
    	        			found = true;
    	        			curr.setChildren(newKids);
    	        		}
    		        }
                }
	        }
	        nodeID++;
        }
    }
    // ------------------------------------------------------

    // ------------- Trim Empty Nodes From Tree -------------
    private void trimTree(Node n){
        if(n != null){
            Node[] kids = n.getChildren();
            if(kids != null){
	            for(int i=0;i < kids.length;i++){
	            	if(kids[i] != null){
		            	int grandkids = kids[i].getChildren().length;
		            	if(grandkids == 0){
		            		Node[] newKids = new Node[kids.length-1];
		            		int size = 0;
		            		for(int x=0;x<kids.length;x++){
		            			if(kids[x] != null && kids[x] != kids[i]){
			            			newKids[size] = kids[x];
			            			size++;
		            			}
		            		}
		            		n.setChildren(newKids);
		            		kids = newKids;
		            	}
		                trimTree(kids[i]);
		            }
	            }
	        }
        }
    }
    // ------------------------------------------------------

    // -------------------------------- PROCEDURE CHECK FUNCTIONS --------------------------------
    private void procVarCheck(){
        for(int i=0;i<table.size();i++){
            if(table.get(i).get(1).equals("PROC_DEFS")){
                for(int x=0;x<table.size();x++){
                    if(x < table.size()-1 && i < table.size()-3 && table.get(x).get(1).equals("VAR")){
                        if(table.get(i+3).get(1).equals(table.get(x+1).get(1))){
                            throwError("ERROR -> Procedure and variable with the same name: "+table.get(i+3).get(1).substring(3));
                        }
                    }
                }
            }
        }
    }
    
    private void procScopeCheck(){
        for(int i=0;i<table.size();i++){
            if(table.get(i).get(1).equals("PROC_DEFS")){
                for(int x=0;x<table.size();x++){
                    if(x < table.size()-3 && i < table.size()-3 && table.get(x).get(1).equals("PROC_DEFS")){
                        if(table.get(i+3).get(1).equals(table.get(x+3).get(1)) && table.get(i).get(2).equals(table.get(x).get(2)) && !table.get(i).get(0).equals(table.get(x).get(0))){
                            throwError("ERROR -> Multiple procedures in scope "+table.get(i).get(2)+" with name: "+table.get(i+3).get(1).substring(3));
                        }
                    }
                }
            }
        }
    }
    // -------------------------------------------------------------------------------------------

	// -------------------------------- CHECK PROCEDURE IS CALLED --------------------------------
    private void procedureCallCheck(){
    /*  1) Find a proc ✓
    	2) Find all calls for that proc ✓
    	3) Check if the calls are from a valid scope ✓
    	4) Rename proc and calls OR remove proc from AST ✓ */
    	//System.out.println("PROCEDURE CALL CHECK!");
    	int procID = 0;
    	for(int i=0;i<table.size();i++){
            if(table.get(i).get(1).equals("PROC_DEFS")){
            	ArrayList<Node> calls = new ArrayList<Node>();
            	ArrayList<Node> validCalls = new ArrayList<Node>();
                for(int x=0;x<table.size();x++){
                    if(x < table.size()-2 && i < table.size()-3 && table.get(x).get(1).equals("CALL")){
                        if(table.get(i+3).get(1).equals(table.get(x+1).get(1))){
                            // CALL WITH SAME NAME FOUND, ADD TO LIST
                        	calls.add(findNode(table.get(x+1).get(0), tree.getHead()));
                        }
                    }
                }
                for(int x=0;x<calls.size();x++){
                	//CHECK SCOPE OF CALL
                	if(table.get(x).get(2).length() >= table.get(i).get(2).length()){	
	                	if(table.get(x).get(2).substring(0, table.get(i).get(2).length()).equals(table.get(i).get(2))){
	                		if(table.get(x).get(2).split(".").length - table.get(i).get(2).split(".").length <= 1){
	                			validCalls.add(calls.get(x));
	                		}
	                	}
                	}else{
	                	if(table.get(i).get(2).substring(0, table.get(x).get(2).length()).equals(table.get(x).get(2))){
	                		if(table.get(i).get(2).split(".").length - table.get(x).get(2).split(".").length <= 1){
	                			validCalls.add(calls.get(x));
	                		}
	                	}		
                	}
                }
                //RENAME OR REMOVE
                if(validCalls.size() > 0){
                	// RENAME
                	//System.out.println("RENAME: "+findNode(table.get(i+3).get(0), tree.getHead()).getInfo());
                	findNode(table.get(i+3).get(0), tree.getHead()).setInfo("p"+procID);
                	for(int x=0;x<validCalls.size();x++){
                		//System.out.println(validCalls.get(x).getInfo());
                		validCalls.get(x).setInfo("p"+procID);
                	}
                	procID++;
                }else{
                	// REMOVE
                	//System.out.println("REMOVE!");
                	boolean found = false;
                	int nodeID = 0;
                	Node curr = new Node();
                	Node proc = findNode(table.get(i).get(0), tree.getHead());
                	//System.out.println("PROC: " + proc.getID());
                	while(!found){
                		curr = findNode(table.get(nodeID).get(0), tree.getHead());
                		if(curr != null){
	                		Node[] kids = curr.getChildren();
	                		if(kids != null){
		                		for(int k=0;k<kids.length;k++){
		            				if(kids[k] != null && kids[k].getID() == proc.getID()){
		            					found = true;
		            				}
		                		}
		                	}
	                		if(found){
	                			int nkSize = 0;
	                			Node[] newKids = new Node[curr.getChildren().length-1];
	                			for(int k=0;k<kids.length;k++){
	                				if(kids[k].getID() != proc.getID()){
	                					newKids[nkSize] = kids[k];
	                					nkSize++;
	                				}
	                			}
	                		}
	                	}
                		nodeID++;
                	}
                }
            }
        }
    }
	// -------------------------------------------------------------------------------------------

	// --------------------------------- CHECK CALLS ARE DEFINED ---------------------------------
    private void callValidityCheck(){
    /*  1) Find a call that is not renamed. ✓
    	2) Remove the call from the AST ✓  */
    	//System.out.println("CALL VALIDITY CHECK!");

    	for(int i=0;i<table.size();i++){
    		Node node = findNode(""+i, tree.getHead());
    		if(node != null && node.getInfo().equals("CALL")){
    			boolean error = false;
    			Node call = node.getChildren()[0];
    			if(call.getInfo().substring(0,1).equals("p")){
    				String num = call.getInfo().substring(1);
    				try{
    					int test = Integer.parseInt(num);
    				}catch(Exception e) {
    					error = true;
    				}
    			}else{
    				error = true;
    			}
    			if(error){
    				System.out.println("ERROR: Invalid procedure call -> "+node.getChildren()[0].getInfo().substring(3));
    				removeNode(node.getID());
    				trimTree(tree.getHead());
    			}
    		}
    	}
    }
	// -------------------------------------------------------------------------------------------

	// --------------------------------- CHECK FOR LOOP VARIBLE ----------------------------------
    private void forLoopCheck(){
	/*  1) Find a For Loop.
    	2) Check that children 0,1,3 & 4 are all the same VAR   */
		//System.out.println("FOR LOOP CHECK!");

		for(int i=0;i<table.size()*2;i++){
    		Node node = findNode(""+i, tree.getHead());
    		//System.out.println(i);
    		if(node != null && node.getInfo().equals("COND_LOOP") && node.getChildren()[0].getInfo().equals("for")){
    			boolean remove = false;

    			String v1 = node.getChildren()[1].getChildren()[0].getInfo();
    			String v2 = node.getChildren()[2].getChildren()[0].getInfo();
    			String v3 = node.getChildren()[4].getChildren()[0].getInfo();
    			String v4 = node.getChildren()[5].getChildren()[0].getInfo();

    			if(!(v1.equals(v2) && v1.equals(v3) && v1.equals(v4))){
    				System.out.println("ERROR: Invalid For Loop Header!");
    				//remove = true;
    			}
    			varsChecked = false;
    			checkVars(v1, node.getChildren()[6]);
    			if(varsChecked){
    				System.out.println("ERROR: Loop counter modified in loop!");
    				//remove = true;
    			}
    			/*if(remove){
    				removeNode(node.getID());
    				trimTree(tree.getHead());
    			}*/
    		}
    	}
    }

    private void checkVars(String var, Node n){
        if(n != null){
            if(n.getInfo().equals("ASSIGN") && n.getChildren()[0].getChildren()[0].getInfo().equals(var)){
                varsChecked = true;
            }
            Node[] kids = n.getChildren();
            for(int i=0;i < kids.length;i++){
                checkVars(var, kids[i]);
            }
        }
    }
	// -------------------------------------------------------------------------------------------

    // --------- ERROR REPORTING FUNCTION ---------
    private void throwError(String err){
        System.out.println(err+"\n</textarea></center>");
        System.exit(0);
    }
    // --------------------------------------------
}
