import java.util.*;
import java.io.*;
import java.util.regex.*;

public class Lexer {
    public Lexer(){}

    public ArrayList<ArrayList<String>> runLexer(){
		ArrayList<ArrayList<String>> tokens = new ArrayList<ArrayList<String>>();
	    int tokSize = 0;
	    int state = 1;
	    File file = new File("../uploads/practical_4.txt");
		//File file = new File("Prac_3_test/Accept_2.txt");
		//File file = new File("test.txt");
	    try{
		    Scanner scan = new Scanner(file);
		    ArrayList<String> newItem = new ArrayList<String>();
		    String tok = "";
		    int lineCount = 0;
		    int strLen = 0;

		    while(scan.hasNextLine()){
		        lineCount++;
		        String line = scan.nextLine()+"\n";
		        for(int i=0;i<line.length();i++){
		            boolean notDone = true;
		            String let = ""+line.charAt(i);
		            while(notDone){
		                notDone = false;
						if(state == 1){
		                    if(Pattern.compile("[b-dgj-lqruvx-z]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else if(Pattern.compile("e").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 3;
		                    }else if(Pattern.compile("a").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 4;
		                    }else if(Pattern.compile("o").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 5;
		                    }else if(Pattern.compile("n").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 6;
		                    }else if(Pattern.compile("m").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 7;
		                    }else if(Pattern.compile("p").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 8;
		                    }else if(Pattern.compile("i").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 9;
		                    }else if(Pattern.compile("t").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 10;
		                    }else if(Pattern.compile("w").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 11;
		                    }else if(Pattern.compile("f").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 12;
		                    }else if(Pattern.compile("h").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 13;
		                    }else if(Pattern.compile("s").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 14;
		                    }else if(Pattern.compile("\"").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 15;
		                    }else if(Pattern.compile("[1-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 16;
		                    }else if(Pattern.compile("0").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 17;
		                    }else if(Pattern.compile("-").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 18;
		                    }else if(Pattern.compile("=").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 19;
		                    }else if(Pattern.compile("<").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 20;
		                    }else if(Pattern.compile(">").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 21;
		                    }else if(Pattern.compile(" ").matcher(let).matches()){
		                        tok = "";
		                        state = 1;
		                    }else if(Pattern.compile("\n").matcher(let).matches()){
		                        tok = "";
		                        state = 1;
		                    }else if(Pattern.compile("[(]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 24;
		                    }else if(Pattern.compile("[)]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 25;
		                    }else if(Pattern.compile("[{]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 26;
		                    }else if(Pattern.compile("[}]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 27;
		                    }else if(Pattern.compile(",").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 28;
		                    }else if(Pattern.compile(";").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 29;
		                    }else{
		                        tok = tok+let;
		                        System.out.println(LexError(tok, lineCount, "",let));
		                        System.exit(0);
		                    }
		                }else if(state == 2){
		                    if(Pattern.compile("[a-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 3){
		                    if(Pattern.compile("l").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 30;
		                    }else if(Pattern.compile("q").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 31;
		                    }else if(Pattern.compile("[a-km-pr-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 4){
		                    if(Pattern.compile("n").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 32;
		                    }else if(Pattern.compile("d").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 22;
		                    }else if(Pattern.compile("[a-ce-mo-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 5){
		                    if(Pattern.compile("r").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 34;
		                    }else if(Pattern.compile("u").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 35;
		                    }else if(Pattern.compile("[a-qstv-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 6){
		                    if(Pattern.compile("o").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 36;
		                    }else if(Pattern.compile("[a-np-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 7){
		                    if(Pattern.compile("u").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 37;
		                    }else if(Pattern.compile("[a-mo-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 8){
		                    if(Pattern.compile("r").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 38;
		                    }else if(Pattern.compile("[a-qs-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 9){
		                    if(Pattern.compile("f").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 39;
		                    }else if(Pattern.compile("n").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 40;
		                    }else if(Pattern.compile("[a-eg-mo-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 10){
		                    if(Pattern.compile("h").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 41;
		                    }else if(Pattern.compile("[a-gi-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 11){
		                    if(Pattern.compile("h").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 42;
		                    }else if(Pattern.compile("[a-gi-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 12){
		                    if(Pattern.compile("o").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 43;
		                    }else if(Pattern.compile("[a-np-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 13){
		                    if(Pattern.compile("a").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 44;
		                    }else if(Pattern.compile("[b-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 14){
		                    if(Pattern.compile("u").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 45;
		                    }else if(Pattern.compile("[a-tv-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 15){
		                    if(Pattern.compile("\"").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 46;
		                    }else if(Pattern.compile("[a-z0-9 ]").matcher(let).matches() && strLen < 8){
		                        strLen++;
		                        tok = tok+let;
		                        state = 15;
		                    }else if(Pattern.compile("[\n]").matcher(let).matches() && strLen < 8){
		                    	continue;
		                    }else{
		                        if(Pattern.compile("[a-z0-9 ]").matcher(let).matches()){
		                            tok = tok+let;
		                            System.out.println(LexError(tok, lineCount, "strLen",let));
		                            System.exit(0);
		                        }else{
		                            tok = tok+let;
		                            System.out.println(LexError(tok, lineCount, "strError",let));
		                            System.exit(0);
		                        }
		                    }
		                }else if(state == 16){
		                    if(Pattern.compile("[0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 16;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("NUMBER");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 17){
		                    if(Pattern.compile("[0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        System.out.println(LexError(tok, lineCount, "zero",let));
		                        System.exit(0);
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("NUMBER");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 18){
		                    if(Pattern.compile("[1-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 16;
		                    }else{
		                        tok = tok+let;
		                        System.out.println(LexError(tok, lineCount, "",let));
		                        System.exit(0);
		                    }
		                }else if(state == 19){
		                    newItem = new ArrayList<String>();
							newItem.add("T"+tokSize);
							newItem.add("Assignment SYMBOL");
							newItem.add(tok);
							tokens.add(newItem);
		                    tokSize++;
		                    tok = "";
		                    state = 1;
		                    notDone = true;
		                }else if(state == 20){
		                    newItem = new ArrayList<String>();
							newItem.add("T"+tokSize);
							newItem.add("Comparison SYMBOL");
							newItem.add(tok);
							tokens.add(newItem);
		                    tokSize++;
		                    tok = "";
		                    state = 1;
		                    notDone = true;
		                }else if(state == 21){
		                    newItem = new ArrayList<String>();
							newItem.add("T"+tokSize);
							newItem.add("Comparison SYMBOL");
							newItem.add(tok);
							tokens.add(newItem);
		                    tokSize++;
		                    tok = "";
		                    state = 1;
		                    notDone = true;
		                }else if(state == 22){
		                    if(Pattern.compile("d").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 23;
		                    }else if(Pattern.compile("[a-ce-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 23){
		                    if(Pattern.compile("[a-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("Math KEYWORD");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 24){
		                    newItem = new ArrayList<String>();
							newItem.add("T"+tokSize);
							newItem.add("Grouping SYMBOL");
							newItem.add(tok);
							tokens.add(newItem);
		                    tokSize++;
		                    tok = "";
		                    state = 1;
		                    notDone = true;
		                }else if(state == 25){
		                    newItem = new ArrayList<String>();
							newItem.add("T"+tokSize);
							newItem.add("Grouping SYMBOL");
							newItem.add(tok);
							tokens.add(newItem);
		                    tokSize++;
		                    tok = "";
		                    state = 1;
		                    notDone = true;
		                }else if(state == 26){
		                    newItem = new ArrayList<String>();
							newItem.add("T"+tokSize);
							newItem.add("Grouping SYMBOL");
							newItem.add(tok);
							tokens.add(newItem);
		                    tokSize++;
		                    tok = "";
		                    state = 1;
		                    notDone = true;
		                }else if(state == 27){
		                    newItem = new ArrayList<String>();
							newItem.add("T"+tokSize);
							newItem.add("Grouping SYMBOL");
							newItem.add(tok);
							tokens.add(newItem);
		                    tokSize++;
		                    tok = "";
		                    state = 1;
		                    notDone = true;
		                }else if(state == 28){
		                    newItem = new ArrayList<String>();
							newItem.add("T"+tokSize);
							newItem.add("Grouping SYMBOL");
							newItem.add(tok);
							tokens.add(newItem);
		                    tokSize++;
		                    tok = "";
		                    state = 1;
		                    notDone = true;
		                }else if(state == 29){
		                    newItem = new ArrayList<String>();
							newItem.add("T"+tokSize);
							newItem.add("Grouping SYMBOL");
							newItem.add(tok);
							tokens.add(newItem);
		                    tokSize++;
		                    tok = "";
		                    state = 1;
		                    notDone = true;
		                }else if(state == 30){
		                    if(Pattern.compile("s").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 47;
		                    }else if(Pattern.compile("[a-rt-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 31){
		                    if(Pattern.compile("[a-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("Comparison KEYWORD");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 32){
		                    if(Pattern.compile("d").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 33;
		                    }else if(Pattern.compile("[a-ce-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 33){
		                    if(Pattern.compile("[a-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("Boolean KEYWORD");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 34){
		                    if(Pattern.compile("[a-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("Boolean KEYWORD");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 35){
		                    if(Pattern.compile("t").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 48;
		                    }else if(Pattern.compile("[a-su-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 36){
		                    if(Pattern.compile("t").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 53;
		                    }else if(Pattern.compile("[a-su-z-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 37){
		                    if(Pattern.compile("l").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 54;
		                    }else if(Pattern.compile("[a-km-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 38){
		                    if(Pattern.compile("o").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 56;
		                    }else if(Pattern.compile("[a-np-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 39){
		                    if(Pattern.compile("[a-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("Control KEYWORD");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 40){
		                    if(Pattern.compile("p").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 58;
		                    }else if(Pattern.compile("[a-oq-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 41){
		                    if(Pattern.compile("e").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 61;
		                    }else if(Pattern.compile("[a-df-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 42){
		                    if(Pattern.compile("i").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 63;
		                    }else if(Pattern.compile("[a-hj-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 43){
		                    if(Pattern.compile("r").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 66;
		                    }else if(Pattern.compile("[a-qs-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 44){
		                    if(Pattern.compile("l").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 67;
		                    }else if(Pattern.compile("[a-km-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 45){
		                    if(Pattern.compile("b").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 69;
		                    }else if(Pattern.compile("[ac-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 46){
		                    strLen = 0;
		                    newItem = new ArrayList<String>();
							newItem.add("T"+tokSize);
							newItem.add("STRING");
							newItem.add(tok);
							tokens.add(newItem);
		                    tokSize++;
		                    tok = "";
		                    state = 1;
		                    notDone = true;
		                }else if(state == 47){
		                    if(Pattern.compile("e").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 52;
		                    }else if(Pattern.compile("[a-df-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 48){
		                    if(Pattern.compile("p").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 49;
		                    }else if(Pattern.compile("[a-oq-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 49){
		                    if(Pattern.compile("u").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 50;
		                    }else if(Pattern.compile("[a-tv-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 50){
		                    if(Pattern.compile("t").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 51;
		                    }else if(Pattern.compile("[a-su-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 51){
		                    if(Pattern.compile("[a-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("I/O KEYWORD");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 52){
		                    if(Pattern.compile("[a-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("Control KEYWORD");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 53){
		                    if(Pattern.compile("[a-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("Boolean KEYWORD");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 54){
		                    if(Pattern.compile("t").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 55;
		                    }else if(Pattern.compile("[a-su-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 55){
		                    if(Pattern.compile("[a-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("Math KEYWORD");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 56){
		                    if(Pattern.compile("c").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 57;
		                    }else if(Pattern.compile("[a-su-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 57){
		                    if(Pattern.compile("[a-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("Procedure KEYWORD");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 58){
		                    if(Pattern.compile("u").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 59;
		                    }else if(Pattern.compile("[a-tv-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 59){
		                    if(Pattern.compile("t").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 60;
		                    }else if(Pattern.compile("[a-su-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 60){
		                    if(Pattern.compile("[a-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("I/O KEYWORD");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 61){
		                    if(Pattern.compile("n").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 62;
		                    }else if(Pattern.compile("[a-mo-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 62){
		                    if(Pattern.compile("[a-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("Control KEYWORD");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 63){
		                    if(Pattern.compile("l").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 64;
		                    }else if(Pattern.compile("[a-km-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 64){
		                    if(Pattern.compile("e").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 65;
		                    }else if(Pattern.compile("[a-df-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 65){
		                    if(Pattern.compile("[a-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("Control KEYWORD");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 66){
		                    if(Pattern.compile("[a-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("Control KEYWORD");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 67){
		                    if(Pattern.compile("t").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 68;
		                    }else if(Pattern.compile("[a-su-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("ID");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 68){
		                    if(Pattern.compile("[a-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("Special KEYWORD");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }else if(state == 69){
		                    if(Pattern.compile("[a-z0-9]").matcher(let).matches()){
		                        tok = tok+let;
		                        state = 2;
		                    }else{
		                        newItem = new ArrayList<String>();
								newItem.add("T"+tokSize);
								newItem.add("Math KEYWORD");
								newItem.add(tok);
								tokens.add(newItem);
		                        tokSize++;
		                        tok = "";
		                        state = 1;
		                        notDone = true;
		                    }
		                }
		            }
		        }
			}

			Integer[] NumberStates = new Integer[]{16,17};
			List<Integer> NumberStatesList = new ArrayList<>(Arrays.asList(NumberStates));

			Integer[] Control = new Integer[]{39,52,62,65,66};
			List<Integer> ControlList = new ArrayList<>(Arrays.asList(Control));

			Integer[] Grouping = new Integer[]{24,25,26,27,28,29};
			List<Integer> GroupingList = new ArrayList<>(Arrays.asList(Grouping));

			Integer[] IOKey = new Integer[]{51,60};
			List<Integer> IOKeyList = new ArrayList<>(Arrays.asList(IOKey));

			Integer[] MathKey = new Integer[]{23,55,69};
			List<Integer> MathKeyList = new ArrayList<>(Arrays.asList(MathKey));

			Integer[] BoolKey = new Integer[]{33,34,53};
			List<Integer> BoolKeyList = new ArrayList<>(Arrays.asList(BoolKey));

			if (state != 1 && state != 18){
		        if(NumberStatesList.contains(state)){
		            newItem = new ArrayList<String>();
					newItem.add("T"+tokSize);
					newItem.add("NUMBER");
					newItem.add(tok);
					tokens.add(newItem);
		        }else if(ControlList.contains(state)){
		            newItem = new ArrayList<String>();
					newItem.add("T"+tokSize);
					newItem.add("Control KEYWORD");
					newItem.add(tok);
					tokens.add(newItem);
		        }else if(GroupingList.contains(state)){
		            newItem = new ArrayList<String>();
					newItem.add("T"+tokSize);
					newItem.add("Grouping SYMBOL");
					newItem.add(tok);
					tokens.add(newItem);
		        }else if(IOKeyList.contains(state)){
		            newItem = new ArrayList<String>();
					newItem.add("T"+tokSize);
					newItem.add("I/O KEYWORD");
					newItem.add(tok);
					tokens.add(newItem);
		        }else if(MathKeyList.contains(state)){
		            newItem = new ArrayList<String>();
					newItem.add("T"+tokSize);
					newItem.add("Math KEYWORD");
					newItem.add(tok);
					tokens.add(newItem);
		        }else if(BoolKeyList.contains(state)){
		            newItem = new ArrayList<String>();
					newItem.add("T"+tokSize);
					newItem.add("Boolean KEYWORD");
					newItem.add(tok);
					tokens.add(newItem);
		        }else if(state == 19){
		            newItem = new ArrayList<String>();
					newItem.add("T"+tokSize);
					newItem.add("Assignment SYMBOL");
					newItem.add(tok);
					tokens.add(newItem);
		        }else if(state == 57){
		            newItem = new ArrayList<String>();
					newItem.add("T"+tokSize);
					newItem.add("Procedure KEYWORD");
					newItem.add(tok);
					tokens.add(newItem);
		        }else if(state == 31){
		            newItem = new ArrayList<String>();
					newItem.add("T"+tokSize);
					newItem.add("Comparison KEYWORD");
					newItem.add(tok);
					tokens.add(newItem);
		        }else if(state == 68){
		            newItem = new ArrayList<String>();
					newItem.add("T"+tokSize);
					newItem.add("Special KEYWORD");
					newItem.add(tok);
					tokens.add(newItem);
		        }else if(state == 46){
		            newItem = new ArrayList<String>();
					newItem.add("T"+tokSize);
					newItem.add("STRING");
					newItem.add(tok);
					tokens.add(newItem);
		        }else if(state == 15){
		            System.out.println(LexError(tok, lineCount, "incompString", "a"));
		            System.exit(0);
		        }
	        	tokSize++;
	        }else if(!tok.equals("\n") && !tok.equals(" ") && !tok.equals("") && !tok.equals("-")){
	            newItem = new ArrayList<String>();
				newItem.add("T"+tokSize);
				newItem.add("ID");
				newItem.add(tok);
				tokens.add(newItem);
				tokSize++;
	        }else if(tok.equals("-")){
	        	tok = tok+"-";
                System.out.println(LexError(tok, lineCount, "","-"));
                System.exit(0);
	        }
			scan.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		return tokens;
	}

	private String LexError(String t, int lin, String s, String l){
		if(s.equals("strLen")){
	        return ("Lexical Error on Line "+lin+": "+t+" (String too long!)");
		}else if(s.equals("strError")){
	        return ("Lexical Error on Line "+lin+": "+t+" (Invalid character in string!)");
	    }else if(!Pattern.compile("[-a-z0-9\"(){};,<>=]").matcher(l).matches()){
	        return ("Lexical Error on Line "+lin+": "+t+" (Invalid Character: "+l+")");
	    }else if(s.equals("incompString")){
	        return ("Lexical Error on Line "+lin+": "+t+" (Incomplete String!)");
	    }else if(s.equals("zero")){
	        return ("Lexical Error on Line "+lin+": "+t+" (Malformed Number!)");
	    }else{
	        return ("Lexical Error on Line "+lin+": "+t);
	    }
	}
}
