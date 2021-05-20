//Date: 10/04/2020
//SPL-Compiler -> Converts SPL source code into an executable binary.

//Libraries
import java.util.*;

//Main Class
public class SPL_Compiler{

	public static ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();

	public static void main(String[] args){
		Lexer lex = new Lexer();
		ArrayList<ArrayList<String>> tokens = lex.runLexer();

		Parser par = new Parser();
		Tree t = par.runParser(tokens);

		Scoper scope = new Scoper(t);
		table = scope.getTable();

		Semantics sem = new Semantics(table, t);
		sem.runAnalysis();

		// ------ OUTPUT ------
		System.out.println("<center><textarea style=\"width: 500px;height:70%\">");
		printTree(t.getHead(), 0);
		
		// *************************************** SHOULD NOT BE OUPUT IN FINAL VERSION *************************************************
		// This is mostly Symbol Table ouput formatting. Looping through the table array and displaying each item.
		System.out.println("\n             SYMBOL TABLE\n+---------------------------------------+");
		System.out.println("| ID\t|    INFO \t|    SCOPE\t|");
		System.out.println("+---------------------------------------+");
		for(int i=0;i<table.size();i++){
			for(int x=0;x<table.get(i).size();x++){
				if(x == 0){
					System.out.print("| "+table.get(i).get(x));
				}else if(x == 2){
					if(table.get(i).get(x).length() == 1){
						System.out.print(" \t|    "+table.get(i).get(x)+"\t");
					}else if(table.get(i).get(x-1).length() > 9){
						System.out.print(" |    "+table.get(i).get(x));
					}else{
						System.out.print(" \t|    "+table.get(i).get(x));
					}
				}else{
					System.out.print(" \t|    "+table.get(i).get(x));
				}
			}
			System.out.print(" \t|\n");
		}
		System.out.println("+---------------------------------------+");
		// ******************************************************************************************************************************

		System.out.println("</textarea></center>");
		// --------------------
	}

	//Recursive method to print the tree.
	//You need to add scope to output of each node!!!
	private static void printTree(Node h, int tabs){
		if(h != null){
			String t = "";
			for(int i=0;i<tabs;i++){
				t = t+"  ";
			}
			System.out.println(""+t+"["+h.getID()+", "+h.getInfo()+", "+h.getScope()+"]");
			Node[] kids = h.getChildren();
			for(int i=0;i<kids.length;i++){
				printTree(kids[i], tabs+1);
			}
		}
	}
}