//Date: 10/04/2020
//SPL-Compiler -> Converts SPL source code into an executable binary.

//Libraries
import java.util.*;

//Main Class
public class SPL_Compiler{
	public static void main(String[] args){
		Lexer lex = new Lexer();
		ArrayList<ArrayList<String>> tokens = lex.runLexer();

		/*for(int i=0;i<tokens.size();i++){
			if(i==0){
				//System.out.print(tokens.get(i));
				System.out.println("[\'"+tokens.get(i).get(0)+"\', \'"+tokens.get(i).get(1)+"\', \'"+tokens.get(i).get(2)+"\']");
			}else{
				//System.out.print(" -> "+tokens.get(i));
				System.out.println("--->[\'"+tokens.get(i).get(0)+"\', \'"+tokens.get(i).get(1)+"\', \'"+tokens.get(i).get(2)+"\']");
			}
		}*/

		Parser par = new Parser();
		Tree t = par.runParser(tokens);

		//System.out.println("\n-----------------------------\n|  Welcome To The Compiler  |\n-----------------------------\n\n");
		System.out.println("<center><textarea style=\"width: 500px;height:70%\">");
		printTree(t.getHead(), 0);
		System.out.println("</textarea></center>");
		//System.out.println("<pre>");
		//printTree(t.getHead(), 0);
		//System.out.println("</pre>");
	}

	private static void printTree(Node h, int tabs){
		if(h != null){
			String t = "";
			for(int i=0;i<tabs;i++){
				t = t+"  ";
			}
			System.out.println(""+t+""+h.getInfo());
			Node[] kids = h.getChildren();
			for(int i=0;i<kids.length;i++){
				printTree(kids[i], tabs+1);
			}
		}
	}
}