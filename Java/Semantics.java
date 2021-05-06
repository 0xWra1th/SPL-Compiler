//Libraries
import java.util.*;

public class Semantics {

    //Private Variables
    ArrayList<ArrayList<String>> table;
    Tree tree;

    public Semantics(ArrayList<ArrayList<String>> tab, Tree tree){
        this.tree = tree;
        this.table = tab;
    }

    public void runAnalysis(){
        // ------------------- Procedure Name Checking -------------------
        // 1) No PROC can be named the same as a VAR ✓
        // 2) Two PROCs with identical scopes must have unique names ✓

        procVarCheck();
        procScopeCheck();

        // --------------------------------------------------------------
    }

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

    private void throwError(String err){
        System.out.println(err);
        //System.exit(0);
    }
}
