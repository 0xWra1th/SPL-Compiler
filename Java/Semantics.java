//Libraries
import java.util.*;

public class Semantics {

    //Private Variables
    ArrayList<ArrayList<String>> table;
    Tree tree;
    ArrayList<ArrayList<String>> varLists = new ArrayList<ArrayList<String>>(); //A List of Lists Listing Node IDs
    int varListCounter = 0;

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
         1) Two var's with the same name in the same scope point to the SAME memory. (IF name and scope are identical)
         2) Two var's within the same hierarchy of scopes with the same name point to the SAME memory. (IF either scope is a substring of the other and names are identical)
         3) Two var's in UN-RELATED scopes with the same name point to DIFFERENT memory. (IF either scope is a substring of the other, there is not a higher instance of the variable in a shared scope and the names are identical)
         4) Loop counting var in header of for loop is private to the loop, even if there is a variable in the scope with the same name. (IF var in a for loop is the same as the loop counter variable)
        */

        varScopeCheck();
        varRename();

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

    // --------- ERROR REPORTING FUNCTION ---------
    private void throwError(String err){
        System.out.println(err);
        //System.exit(0);
    }
    // --------------------------------------------
}
