import java.util.*;
//import java.io.*;
//import java.util.regex.*;

import javax.print.attribute.standard.PrinterMakeAndModel;
import javax.swing.text.DefaultStyledDocument.ElementSpec;

public class Parser {
    private ArrayList<Tree> stack = new ArrayList<Tree>();
    private String[][] theTable;
    private int tok = 0;
    private int ID = 1;
    private Tree t = new Tree();
    private ArrayList<ArrayList<String>> list;

    public Parser(){}

    public Tree runParser(ArrayList<ArrayList<String>> list){
		this.list = list;
        shift();
        tok++;
        //System.out.println("SHIFT");

        
        for(int m=0;m<list.size()+5;m++){
            //System.out.println("---------------------------------------------------------------------------- "+list.get(m).get(2));
            String type = checkAll();

            //OUTPUT STACK
            //System.out.print(" ================================================================= STACK: ");
            /*for(int i=stack.size();i>0;i--){
                System.out.print(""+stack.get(stack.size()-i).getHead().getInfo());
            }
            System.out.println("");*/

            if(type.equals("")){
                shift();
                //System.out.println("SHIFT");
                tok++;
            }else{
                //System.out.println("-- REDUCE("+type+","+ID+") --");
                ID = reduce(type, ID);
                m--;
            }
        }
        t = prog();
        //System.out.println(" --- PARSER FINISHED --- ");
		return t;
	}

    private void shift(){
        if(list.size() > tok){
            if(list.get(tok).get(1).length() > 6 && list.get(tok).get(1).substring(list.get(tok).get(1).length()-6).equals("SYMBOL")){
                stack.add(stack.size(), new Tree(new Node(""+ID,list.get(tok).get(2))));
            }else if(list.get(tok).get(1).length() > 7 && list.get(tok).get(1).substring(list.get(tok).get(1).length()-7).equals("KEYWORD")){
                stack.add(stack.size(), new Tree(new Node(""+ID,list.get(tok).get(2))));
            }else{
                stack.add(stack.size(), new Tree(new Node(""+ID,list.get(tok).get(1))));
            }
            ID++;
        }
    }

    private Tree prog(){
        Tree res = new Tree(new Node("0", "PROG"));
        Node head = res.getHead();
        Node[] kids = new Node[stack.size()];
        
        /*System.out.print("STACK: ");
        for(int i=0;i<stack.size();i++){
            System.out.print(stack.get(i).getHead().getInfo());
        }*/

        for(int i=0;i<stack.size();i++){
            
            if(!stack.get(i).getHead().getInfo().equals(";")){
                if(stack.get(i).getHead().getInfo().equals("CODE")){
                    kids[i] = stack.get(i).getHead();
                }else if(stack.get(i).getHead().getInfo().equals("PROC_DEFS")){
                    kids[i] = stack.get(i).getHead();
                }else{
                    System.out.println("Error encountered during parsing, aborting.");
                    System.exit(0);
                }
            }
        }
        head.setChildren(kids);
        return res;
    }

    private int reduce(String type, int ID){
        if(type.equals("VAR")){
            Node VAR = new Node(""+ID, "VAR");
            Node[] kids = {stack.get(stack.size()-1).getHead()};
            VAR.setChildren(kids);

            Tree newTree = new Tree(VAR);
            //System.out.println("REMOVED: "+stack.get(stack.size()-1).getHead().getInfo());
            stack.remove(stack.size()-1);
            stack.add(newTree);
        }else if(type.equals("IO")){
            Node IO = new Node(""+ID, "IO");
            //System.out.println("CHILDREN: "+stack.get(stack.size()-4).getHead().getInfo()+" , "+stack.get(stack.size()-2).getHead().getInfo());
            Node[] kids = {stack.get(stack.size()-4).getHead(), stack.get(stack.size()-2).getHead()};
            IO.setChildren(kids);

            Tree newTree = new Tree(IO);
            for(int i=1;i<=4;i++){
                //System.out.println("REMOVED: "+stack.get(stack.size()-1).getHead().getInfo());
                stack.remove(stack.size()-1);
            }
            
            stack.add(newTree);
        }else if(type.equals("INSTR")){
            Node INSTR = new Node(""+ID, "INSTR");
            //System.out.println("CHILDREN: "+stack.get(stack.size()-1).getHead().getInfo());
            Node[] kids = {stack.get(stack.size()-1).getHead()};
            INSTR.setChildren(kids);

            Tree newTree = new Tree(INSTR);
            for(int i=1;i<=1;i++){
                //System.out.println("REMOVED: "+stack.get(stack.size()-1).getHead().getInfo());
                stack.remove(stack.size()-1);
            }
            
            stack.add(newTree);
        }else if(type.equals("PROG1")){
            Node PROG = new Node(""+ID, "PROG");
            //System.out.println("CHILDREN: "+stack.get(stack.size()-1).getHead().getInfo());
            Node[] kids = {stack.get(stack.size()-1).getHead()};
            PROG.setChildren(kids);

            Tree newTree = new Tree(PROG);
            for(int i=1;i<=1;i++){
                //System.out.println("REMOVED: "+stack.get(stack.size()-1).getHead().getInfo());
                stack.remove(stack.size()-1);
            }
            
            stack.add(newTree);
        }else if(type.equals("PROG2")){
            Node PROG = new Node(""+ID, "PROG");
            //System.out.println("CHILDREN: "+stack.get(stack.size()-1).getHead().getInfo());
            Node[] kids = {stack.get(stack.size()-1).getHead()};
            PROG.setChildren(kids);

            Tree newTree = new Tree(PROG);
            for(int i=1;i<=1;i++){
                //System.out.println("REMOVED: "+stack.get(stack.size()-1).getHead().getInfo());
                stack.remove(stack.size()-1);
            }
            
            stack.add(newTree);
        }else if(type.equals("PROC_DEFS1")){
            Node PROC_DEFS = new Node(""+ID, "PROC_DEFS");
            //System.out.println("CHILDREN: "+stack.get(stack.size()-1).getHead().getInfo());
            Node[] kids = {stack.get(stack.size()-1).getHead()};
            PROC_DEFS.setChildren(kids);

            Tree newTree = new Tree(PROC_DEFS);
            for(int i=1;i<=1;i++){
               // System.out.println("REMOVED: "+stack.get(stack.size()-1).getHead().getInfo());
                stack.remove(stack.size()-1);
            }
            
            stack.add(newTree);
        }else if(type.equals("PROC_DEFS2")){
            Node PROC_DEFS = new Node(""+ID, "PROC_DEFS");
            //System.out.println("CHILDREN: "+stack.get(stack.size()-2).getHead().getInfo()+","+stack.get(stack.size()-1).getHead().getInfo());
            Node[] kids = {stack.get(stack.size()-2).getHead(),stack.get(stack.size()-1).getHead()};
            PROC_DEFS.setChildren(kids);

            Tree newTree = new Tree(PROC_DEFS);
            for(int i=1;i<=2;i++){
               //System.out.println("REMOVED: "+stack.get(stack.size()-1).getHead().getInfo());
                stack.remove(stack.size()-1);
            }
            
            stack.add(newTree);
        }else if(type.equals("CODE1")){
            Node CODE = new Node(""+ID, "CODE");
            //System.out.println("CHILDREN: "+stack.get(stack.size()-1).getHead().getInfo());
            Node[] kids = {stack.get(stack.size()-1).getHead()};
            CODE.setChildren(kids);

            Tree newTree = new Tree(CODE);
            for(int i=1;i<=1;i++){
                //System.out.println("REMOVED: "+stack.get(stack.size()-1).getHead().getInfo());
                stack.remove(stack.size()-1);
            }
            
            stack.add(newTree);
        }else if(type.equals("CODE2")){
            Node CODE = new Node(""+ID, "CODE");
            //System.out.println("CHILDREN: "+stack.get(stack.size()-3).getHead().getInfo()+","+stack.get(stack.size()-1).getHead().getInfo());
            Node[] kids = {stack.get(stack.size()-3).getHead(),stack.get(stack.size()-1).getHead()};
            CODE.setChildren(kids);

            Tree newTree = new Tree(CODE);
            for(int i=1;i<=3;i++){
                //System.out.println("REMOVED: "+stack.get(stack.size()-1).getHead().getInfo());
                stack.remove(stack.size()-1);
            }
            
            stack.add(newTree);
        }else if(type.equals("CALL")){
            Node CALL = new Node(""+ID, "CALL");
            //System.out.println("CHILDREN: "+stack.get(stack.size()-1).getHead().getInfo());
            Node[] kids = {stack.get(stack.size()-1).getHead()};
            CALL.setChildren(kids);

            Tree newTree = new Tree(CALL);
            for(int i=1;i<=1;i++){
                //System.out.println("REMOVED: "+stack.get(stack.size()-1).getHead().getInfo());
                stack.remove(stack.size()-1);
            }
            
            stack.add(newTree);
        }else if(type.equals("NUMEXPR")){
            Node NUMEXPR = new Node(""+ID, "NUMEXPR");
            //System.out.println("CHILDREN: "+stack.get(stack.size()-1).getHead().getInfo());
            Node[] kids = {stack.get(stack.size()-1).getHead()};
            NUMEXPR.setChildren(kids);

            Tree newTree = new Tree(NUMEXPR);
            for(int i=1;i<=1;i++){
                //System.out.println("REMOVED: "+stack.get(stack.size()-1).getHead().getInfo());
                stack.remove(stack.size()-1);
            }
            
            stack.add(newTree);
        }else if(type.equals("BOOL1")){
            Node BOOL = new Node(""+ID, "BOOL");
            //System.out.println("CHILDREN: "+stack.get(stack.size()-2).getHead().getInfo()+","+stack.get(stack.size()-1).getHead().getInfo());
            Node[] kids = {stack.get(stack.size()-2).getHead(),stack.get(stack.size()-1).getHead()};
            BOOL.setChildren(kids);

            Tree newTree = new Tree(BOOL);
            for(int i=1;i<=2;i++){
                //System.out.println("REMOVED: "+stack.get(stack.size()-1).getHead().getInfo());
                stack.remove(stack.size()-1);
            }
            
            stack.add(newTree);
        }else if(type.equals("BOOL2")){
            Node BOOL = new Node(""+ID, "BOOL");
            //System.out.println("CHILDREN: "+stack.get(stack.size()-4).getHead().getInfo()+","+stack.get(stack.size()-3).getHead().getInfo()+","+stack.get(stack.size()-2).getHead().getInfo());
            Node[] kids = {stack.get(stack.size()-4).getHead(),stack.get(stack.size()-3).getHead(),stack.get(stack.size()-2).getHead()};
            BOOL.setChildren(kids);

            Tree newTree = new Tree(BOOL);
            for(int i=1;i<=5;i++){
                //System.out.println("REMOVED: "+stack.get(stack.size()-1).getHead().getInfo());
                stack.remove(stack.size()-1);
            }
            
            stack.add(newTree);
        }else if(type.equals("BOOL3")){
            Node BOOL = new Node(""+ID, "BOOL");
            //System.out.println("CHILDREN: "+stack.get(stack.size()-6).getHead().getInfo()+","+stack.get(stack.size()-4).getHead().getInfo()+","+stack.get(stack.size()-2).getHead().getInfo());
            Node[] kids = {stack.get(stack.size()-6).getHead(),stack.get(stack.size()-4).getHead(),stack.get(stack.size()-2).getHead()};
            BOOL.setChildren(kids);

            Tree newTree = new Tree(BOOL);
            for(int i=1;i<=6;i++){
                //System.out.println("REMOVED: "+stack.get(stack.size()-1).getHead().getInfo());
                stack.remove(stack.size()-1);
            }
            
            stack.add(newTree);
        }else if(type.equals("ASSIGN")){
            Node ASSIGN = new Node(""+ID, "ASSIGN");
            //System.out.println("CHILDREN: "+stack.get(stack.size()-3).getHead().getInfo()+","+stack.get(stack.size()-1).getHead().getInfo());
            Node[] kids = {stack.get(stack.size()-3).getHead(),stack.get(stack.size()-1).getHead()};
            ASSIGN.setChildren(kids);

            Tree newTree = new Tree(ASSIGN);
            for(int i=1;i<=3;i++){
                //System.out.println("REMOVED: "+stack.get(stack.size()-1).getHead().getInfo());
                stack.remove(stack.size()-1);
            }
            
            stack.add(newTree);
        }else if(type.equals("CALC")){
            Node CALC = new Node(""+ID, "CALC");
            //System.out.println("CHILDREN: "+stack.get(stack.size()-6).getHead().getInfo()+","+stack.get(stack.size()-4).getHead().getInfo()+","+stack.get(stack.size()-2).getHead().getInfo());
            Node[] kids = {stack.get(stack.size()-6).getHead(),stack.get(stack.size()-4).getHead(),stack.get(stack.size()-2).getHead()};
            CALC.setChildren(kids);

            Tree newTree = new Tree(CALC);
            for(int i=1;i<=6;i++){
                //System.out.println("REMOVED: "+stack.get(stack.size()-1).getHead().getInfo());
                stack.remove(stack.size()-1);
            }
            
            stack.add(newTree);
        }else if(type.equals("PROC")){
            Node PROC = new Node(""+ID, "PROC");
            //System.out.println("CHILDREN: "+stack.get(stack.size()-5).getHead().getInfo()+","+stack.get(stack.size()-4).getHead().getInfo()+","+stack.get(stack.size()-2).getHead().getInfo());
            Node[] kids = {stack.get(stack.size()-5).getHead(),stack.get(stack.size()-4).getHead(),stack.get(stack.size()-2).getHead()};
            PROC.setChildren(kids);

            Tree newTree = new Tree(PROC);
            for(int i=1;i<=5;i++){
                //System.out.println("REMOVED: "+stack.get(stack.size()-1).getHead().getInfo());
                stack.remove(stack.size()-1);
            }
            
            stack.add(newTree);
        }else if(type.equals("COND_LOOP1")){
            Node COND_LOOP = new Node(""+ID, "COND_LOOP");
            //System.out.println("CHILDREN: "+stack.get(stack.size()-7).getHead().getInfo()+","+stack.get(stack.size()-5).getHead().getInfo()+","+stack.get(stack.size()-2).getHead().getInfo());
            Node[] kids = {stack.get(stack.size()-7).getHead(),stack.get(stack.size()-5).getHead(),stack.get(stack.size()-2).getHead()};
            COND_LOOP.setChildren(kids);

            Tree newTree = new Tree(COND_LOOP);
            for(int i=1;i<=7;i++){
                //System.out.println("REMOVED: "+stack.get(stack.size()-1).getHead().getInfo());
                stack.remove(stack.size()-1);
            }
            
            stack.add(newTree);
        }else if(type.equals("COND_LOOP1")){
            Node COND_LOOP = new Node(""+ID, "COND_LOOP");
            //System.out.println("CHILDREN: "+stack.get(stack.size()-7).getHead().getInfo()+","+stack.get(stack.size()-5).getHead().getInfo()+","+stack.get(stack.size()-2).getHead().getInfo());
            Node[] kids = {stack.get(stack.size()-7).getHead(),stack.get(stack.size()-5).getHead(),stack.get(stack.size()-2).getHead()};
            COND_LOOP.setChildren(kids);

            Tree newTree = new Tree(COND_LOOP);
            for(int i=1;i<=7;i++){
                //System.out.println("REMOVED: "+stack.get(stack.size()-1).getHead().getInfo());
                stack.remove(stack.size()-1);
            }
            
            stack.add(newTree);
        }else if(type.equals("COND_LOOP2")){
            Node COND_LOOP = new Node(""+ID, "COND_LOOP");
            //System.out.println("CHILDREN: "+stack.get(stack.size()-22).getHead().getInfo()+","+stack.get(stack.size()-20).getHead().getInfo()+","+stack.get(stack.size()-16).getHead().getInfo()+","+stack.get(stack.size()-14).getHead().getInfo()+","+stack.get(stack.size()-12).getHead().getInfo()+","+stack.get(stack.size()-8).getHead().getInfo()+","+stack.get(stack.size()-2).getHead().getInfo());
            Node[] kids = {stack.get(stack.size()-22).getHead(),stack.get(stack.size()-20).getHead(),stack.get(stack.size()-16).getHead(),stack.get(stack.size()-14).getHead(),stack.get(stack.size()-12).getHead(),stack.get(stack.size()-8).getHead(),stack.get(stack.size()-2).getHead()};
            COND_LOOP.setChildren(kids);

            Tree newTree = new Tree(COND_LOOP);
            for(int i=1;i<=22;i++){
                //System.out.println("REMOVED: "+stack.get(stack.size()-1).getHead().getInfo());
                stack.remove(stack.size()-1);
            }
            
            stack.add(newTree);
        }else if(type.equals("COND_BRANCH1")){
            Node COND_BRANCH = new Node(""+ID, "COND_BRANCH");
            //System.out.println("CHILDREN: "+stack.get(stack.size()-8).getHead().getInfo()+","+stack.get(stack.size()-6).getHead().getInfo()+","+stack.get(stack.size()-4).getHead().getInfo()+","+stack.get(stack.size()-2).getHead().getInfo());
            Node[] kids = {stack.get(stack.size()-8).getHead(),stack.get(stack.size()-6).getHead(),stack.get(stack.size()-4).getHead(),stack.get(stack.size()-2).getHead()};
            COND_BRANCH.setChildren(kids);

            Tree newTree = new Tree(COND_BRANCH);
            for(int i=1;i<=8;i++){
                //System.out.println("REMOVED: "+stack.get(stack.size()-1).getHead().getInfo());
                stack.remove(stack.size()-1);
            }
            
            stack.add(newTree);
        }else if(type.equals("COND_BRANCH2")){
            Node COND_BRANCH = new Node(""+ID, "COND_BRANCH");
            //System.out.println("CHILDREN: "+stack.get(stack.size()-12).getHead().getInfo()+","+stack.get(stack.size()-10).getHead().getInfo()+","+stack.get(stack.size()-8).getHead().getInfo()+","+stack.get(stack.size()-6).getHead().getInfo()+","+stack.get(stack.size()-4).getHead().getInfo()+","+stack.get(stack.size()-2).getHead().getInfo());
            Node[] kids = {stack.get(stack.size()-12).getHead(),stack.get(stack.size()-10).getHead(),stack.get(stack.size()-8).getHead(),stack.get(stack.size()-6).getHead(),stack.get(stack.size()-4).getHead(),stack.get(stack.size()-2).getHead()};
            COND_BRANCH.setChildren(kids);

            Tree newTree = new Tree(COND_BRANCH);
            for(int i=1;i<=12;i++){
                //System.out.println("REMOVED: "+stack.get(stack.size()-1).getHead().getInfo());
                stack.remove(stack.size()-1);
            }
            
            stack.add(newTree);
        }else if(type.equals("INTEGRATE_CODE")){
            Node CODE = new Node(""+ID, "CODE");
            //System.out.println("CHILDREN: "+stack.get(stack.size()-3).getHead().getChildren().length+","+stack.get(stack.size()-1).getHead().getChildren().length);
            Node[] kids = new Node[stack.get(stack.size()-3).getHead().getChildren().length+stack.get(stack.size()-1).getHead().getChildren().length];
            for(int x=0;x<stack.get(stack.size()-3).getHead().getChildren().length;x++){
                kids[x] = stack.get(stack.size()-3).getHead().getChildren()[x];
            }
            for(int x=0;x<stack.get(stack.size()-1).getHead().getChildren().length;x++){
                kids[x+stack.get(stack.size()-3).getHead().getChildren().length] = stack.get(stack.size()-1).getHead().getChildren()[x];
            }
            CODE.setChildren(kids);

            Tree newTree = new Tree(CODE);
            for(int i=1;i<=3;i++){
                //System.out.println("REMOVED: "+stack.get(stack.size()-1).getHead().getInfo());
                stack.remove(stack.size()-1);
            }
            
            stack.add(newTree);
        }else if(type.equals("REMOVE_SEMI")){
            //System.out.println("REMOVED: "+stack.get(stack.size()-2).getHead().getInfo());
            stack.remove(stack.size()-2);
        }
        return ID;
    }

    private Tree lookAhead(){
        Tree test = new Tree();
        if(list.get(tok).get(1).length() > 6 && list.get(tok).get(1).substring(list.get(tok).get(1).length()-6).equals("SYMBOL")){
            test = new Tree(new Node(""+ID,list.get(tok).get(2)));
        }else if(list.get(tok).get(1).length() > 7 && list.get(tok).get(1).substring(list.get(tok).get(1).length()-7).equals("KEYWORD")){
            test = new Tree(new Node(""+ID,list.get(tok).get(2)));
        }else{
            test = new Tree(new Node(""+ID,list.get(tok).get(1)));
        }
        return test;
    }

    private String checkAll(){
        String type = "";

        //RIGHT SIDE SIZE 1
        String shifts = stack.get(stack.size()-1).getHead().getInfo();
        /*if(shifts.equals("CODE")){
            type = "PROG1";
        }else if(shifts.equals("CODE;PROC_DEFS")){
            type = "PROG2";
        }else */
        if(shifts.equals("PROC")){
            type = "PROC_DEFS1";
        }else if(shifts.equals("INSTR")){
            type = "CODE1";
        }else if(shifts.equals("halt")){
            type = "INSTR";
        }else if(shifts.equals("IO")){
            type = "INSTR";
        }else if(shifts.equals("CALL")){
            type = "INSTR";
        }else if(shifts.equals("ASSIGN")){
            type = "INSTR";
        }else if(shifts.equals("COND_BRANCH")){
            type = "INSTR";
        }else if(shifts.equals("COND_LOOP")){
            type = "INSTR";
        }else if(shifts.equals("ID")){
            String s = "";
            if(stack.size()-2 >= 0){
                s = stack.get(stack.size()-2).getHead().getInfo();
                if((s.equals(";") || s.equals("}") || s.equals("{")) && (lookAhead().getHead().getInfo().equals(";") || lookAhead().getHead().getInfo().equals("}"))){
                    type = "CALL";
                }else if(!s.equals("proc")){
                    type = "VAR";
                }
            }
        }else if(shifts.equals("CALC")){
            type = "NUMEXPR";
        }else if(shifts.equals("NUMBER")){
            if(stack.size()>=17){
                if(!stack.get(stack.size()-17).getHead().getInfo().equals("for")){
                    type = "NUMEXPR";
                }
            }else if(stack.size()>=5){
                if(!stack.get(stack.size()-5).getHead().getInfo().equals("for")){
                    type = "NUMEXPR";
                }
            }
        }

        //RIGHT SIDE SIZE 2
        if(stack.size() >= 2){
            shifts = stack.get(stack.size()-2).getHead().getInfo()+shifts;
            if(shifts.equals("PROCPROC_DEFS")){
                type = "PROC_DEFS2";
            }else if(shifts.equals("notBOOL")){
                type = "BOOL1";
            }
        }

        //RIGHT SIDE SIZE 3
        if(stack.size() >= 3){
            shifts = stack.get(stack.size()-3).getHead().getInfo()+shifts;
            if(shifts.equals("CODE;CODE")){
                //System.out.println("********************************************************************************");
                type = "INTEGRATE_CODE";
            }else if(shifts.equals("INSTR;CODE")){
                type = "CODE2";
            }else if(shifts.equals("VAR=STRING")){
                type = "ASSIGN";
            }else if(shifts.equals("VAR=VAR")){
                type = "ASSIGN";
            }else if(shifts.equals("VAR=NUMEXPR")){
                type = "ASSIGN";
            }else if(shifts.equals("CODE;}")){
                type = "REMOVE_SEMI";
            }/*else if(shifts.equals("CODE;proc")){
                type = "REMOVE_SEMI";
            }*/
        }

        //RIGHT SIDE SIZE 4
        if(stack.size() >= 4){
            shifts = stack.get(stack.size()-4).getHead().getInfo()+shifts;
            if(shifts.equals("input(VAR)")){
                type = "IO";
            }else if(shifts.equals("output(VAR)")){
                type = "IO";
            }
        }

        //RIGHT SIDE SIZE 5
        if(stack.size() >= 5){
            shifts = stack.get(stack.size()-5).getHead().getInfo()+shifts;
            if(shifts.equals("procID{CODE}")){
                type = "PROC";
            }else if(shifts.equals("(VAR<VAR)")){
                type = "BOOL2";
            }else if(shifts.equals("(VAR>VAR)")){
                type = "BOOL2";
            }else if(shifts.equals("(NUMEXPR<VAR)")){
                type = "BOOL2";
            }else if(shifts.equals("(VAR<NUMEXPR)")){
                type = "BOOL2";
            }else if(shifts.equals("(NUMEXPR<NUMEXPR)")){
                type = "BOOL2";
            }else if(shifts.equals("(NUMEXPR>VAR)")){
                type = "BOOL2";
            }else if(shifts.equals("(VAR>NUMEXPR)")){
                type = "BOOL2";
            }else if(shifts.equals("(NUMEXPR>NUMEXPR)")){
                type = "BOOL2";
            }
        }

        //RIGHT SIDE SIZE 6
        if(stack.size() >= 6){
            shifts = stack.get(stack.size()-6).getHead().getInfo()+shifts;
            if(shifts.equals("add(NUMEXPR,NUMEXPR)")){
                type = "CALC";
            }else if(shifts.equals("add(VAR,VAR)")){
                type = "CALC";
            }else if(shifts.equals("add(VAR,NUMEXPR)")){
                type = "CALC";
            }else if(shifts.equals("add(NUMEXPR,VAR)")){
                type = "CALC";
            }else if(shifts.equals("sub(VAR,VAR)")){
                type = "CALC";
            }else if(shifts.equals("sub(VAR,NUMEXPR)")){
                type = "CALC";
            }else if(shifts.equals("sub(NUMEXPR,VAR)")){
                type = "CALC";
            }else if(shifts.equals("sub(NUMEXPR,NUMEXPR)")){
                type = "CALC";
            }else if(shifts.equals("mult(VAR,VAR)")){
                type = "CALC";
            }else if(shifts.equals("mult(VAR,NUMEXPR)")){
                type = "CALC";
            }else if(shifts.equals("mult(NUMEXPR,VAR)")){
                type = "CALC";
            }else if(shifts.equals("mult(NUMEXPR,NUMEXPR)")){
                type = "CALC";
            }else if(shifts.equals("eq(NUMEXPR,NUMEXPR)")){
                type = "BOOL3";
            }else if(shifts.equals("eq(VAR,VAR)")){
                type = "BOOL3";
            }else if(shifts.equals("eq(VAR,NUMEXPR)")){
                type = "BOOL3";
            }else if(shifts.equals("eq(NUMEXPR,VAR)")){
                type = "BOOL3";
            }else if(shifts.equals("eq(BOOL,BOOL)")){
                type = "BOOL3";
            }else if(shifts.equals("and(BOOL,BOOL)")){
                type = "BOOL3";
            }else if(shifts.equals("or(BOOL,BOOL)")){
                type = "BOOL3";
            }
        }

        //RIGHT SIDE SIZE 7
        if(stack.size() >= 7){
            shifts = stack.get(stack.size()-7).getHead().getInfo()+shifts;
            if(shifts.equals("while(BOOL){CODE}")){
                type = "COND_LOOP1";
            }
        }

        //RIGHT SIDE SIZE 9
        if(stack.size() >= 9){
            shifts = stack.get(stack.size()-8).getHead().getInfo()+shifts;
            if(shifts.equals("if(BOOL)then{CODE}")){
                if(!lookAhead().getHead().getInfo().equals("else")){
                    type = "COND_BRANCH1";
                }
            }
        }

        //RIGHT SIDE SIZE 12
        if(stack.size() >= 12){
            shifts = stack.get(stack.size()-9).getHead().getInfo()+shifts;
            shifts = stack.get(stack.size()-10).getHead().getInfo()+shifts;
            shifts = stack.get(stack.size()-11).getHead().getInfo()+shifts;
            shifts = stack.get(stack.size()-12).getHead().getInfo()+shifts;
            if(shifts.equals("if(BOOL)then{CODE}else{CODE}")){
                type = "COND_BRANCH2";
            }
        }

        //RIGHT SIDE SIZE 22
        if(stack.size() >= 22){
            shifts = stack.get(stack.size()-13).getHead().getInfo()+shifts;
            shifts = stack.get(stack.size()-14).getHead().getInfo()+shifts;
            shifts = stack.get(stack.size()-15).getHead().getInfo()+shifts;
            shifts = stack.get(stack.size()-16).getHead().getInfo()+shifts;
            shifts = stack.get(stack.size()-17).getHead().getInfo()+shifts;
            shifts = stack.get(stack.size()-18).getHead().getInfo()+shifts;
            shifts = stack.get(stack.size()-19).getHead().getInfo()+shifts;
            shifts = stack.get(stack.size()-20).getHead().getInfo()+shifts;
            shifts = stack.get(stack.size()-21).getHead().getInfo()+shifts;
            shifts = stack.get(stack.size()-22).getHead().getInfo()+shifts;
            if(shifts.equals("for(VAR=NUMBER;VAR<VAR;VAR=add(VAR,NUMBER)){CODE}")){
                type = "COND_LOOP2";
            }
        }

        return type;
    }

    public String[][] getTheTable(){
        return this.theTable;
    }

    public void setTheTable(String[][] t){
        this.theTable = t;
    }
}
