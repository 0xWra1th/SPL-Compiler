#SPL-Compiler
#Date: 01/04/2021

#IMPORTS
import re

def main():
    tokens = lexer()

def lexer():
 	tokens = []
 	tokeSize = 0
 	state = 1
 	file = open("../uploads/practical_1.txt")
 	tok = ""
 	for line in file:
 		for let in line:
 			notDone = True
 			while notDone:
 				notDone = False
	 			if state == 1: #INITIAL State (REJECT)
	 				if re.match("[b-dgj-lqruvx-z]", let):
	 					tok = tok+let
	 					state = 2 #ID State
	 				elif re.match("e", let):
	 					tok = tok+let
	 					state = 3 #ID State
	 				elif re.match("a", let):
	 					tok = tok+let
	 					state = 4 #ID State
	 				elif re.match("o", let):
	 					tok = tok+let
	 					state = 5 #ID State
	 				elif re.match("n", let):
	 					tok = tok+let
	 					state = 6 #ID State
	 				elif re.match("m", let):
	 					tok = tok+let
	 					state = 7 #ID State
	 				elif re.match("p", let):
	 					tok = tok+let
	 					state = 8 #ID State
	 				elif re.match("i", let):
	 					tok = tok+let
	 					state = 9 #ID State
	 				elif re.match("t", let):
	 					tok = tok+let
	 					state = 10 #ID State
	 				elif re.match("w", let):
	 					tok = tok+let
	 					state = 11 #ID State
	 				elif re.match("f", let):
	 					tok = tok+let
	 					state = 12 #ID State
	 				elif re.match("h", let):
	 					tok = tok+let
	 					state = 13 #ID State
	 				elif re.match("s", let):
	 					tok = tok+let
	 					state = 14 #ID State
	 				elif re.match("\"", let):
	 					tok = tok+let
	 					state = 15 #ID State
	 				elif re.match("[1-9]", let):
	 					tok = tok+let
	 					state = 16 #ID State
	 				elif re.match("0", let):
	 					tok = tok+let
	 					state = 17 #ID State
	 				elif re.match("-", let):
	 					tok = tok+let
	 					state = 18 #ID State
	 				elif re.match("=", let):
	 					tok = tok+let
	 					state = 19 #ID State
	 				elif re.match("<", let):
	 					tok = tok+let
	 					state = 20 #ID State
	 				elif re.match(">", let):
	 					tok = tok+let
	 					state = 21 #ID State
	 				elif re.match(" ", let):
	 					tok = "" #Skip let
	 					state = 1 #ID State
	 				elif re.match("\n", let):
	 					tok = tok+let
	 					state = 23 #ID State
	 				elif re.match("(", let):
	 					tok = tok+let
	 					state = 24 #ID State
	 				elif re.match(")", let):
	 					tok = tok+let
	 					state = 25 #ID State
	 				elif re.match("{", let):
	 					tok = tok+let
	 					state = 26 #ID State
	 				elif re.match("}", let):
	 					tok = tok+let
	 					state = 27 #ID State
	 				elif re.match(",", let):
	 					tok = tok+let
	 					state = 28 #ID State
	 				elif re.match(";", let):
	 					tok = tok+let
	 					state = 29 #ID State
	 				else:
	 					tok = tok+let
	 					LexError(tok);
	 			elif state == 2: #ID State (ACCEPT)
	 				if re.match("[a-z0-9]"):
	 					tok = tok+let
	 				else:
	 					tokens[tokSize] = ["T"+str(tokSize), "ID", tok] #ACCEPT ID
	 					tokSize+=1
	 					tok = ""
	 					state = 1
	 					notDone = True
	 			elif state == 3: #e State (ACCEPT)
	 				if re.match("l", let):
	 					tok = tok+let
	 					state = 30 #ID State
	 				elif re.match("q", let):
	 					tok = tok+let
	 					state = 31 #ID State
	 				elif re.match("[a-km-pr-z0-9]", let):
	 					tok = tok+let
	 					state = 2 #ID State
	 				else:
	 					tokens[tokSize] = ["T"+str(tokSize), "ID", tok] #ACCEPT ID
	 					tokSize+=1
	 					tok = ""
	 					state = 1
	 					notDone = True
	 			elif state == 4: #a State (ACCEPT)
	 				if re.match("n", let):
	 					tok = tok+let
	 					state = 32 #ID State
	 				elif re.match("d", let):
	 					tok = tok+let
	 					state = 33 #ID State
	 				elif re.match("[a-ce-mo-z0-9]", let):
	 					tok = tok+let
	 					state = 2 #ID State
	 				else:
	 					tokens[tokSize] = ["T"+str(tokSize), "ID", tok] #ACCEPT ID
	 					tokSize+=1
	 					tok = ""
	 					state = 1
	 					notDone = True
	 			elif state == 5: #o State (ACCEPT)
	 				if re.match("u", let):
	 					tok = tok+let
	 					state = 34 #ID State
	 				elif re.match("r", let):
	 					tok = tok+let
	 					state = 35 #ID State
	 				elif re.match("[a-qstv-z0-9]", let):
	 					tok = tok+let
	 					state = 2 #ID State
	 				else:
	 					tokens[tokSize] = ["T"+str(tokSize), "ID", tok] #ACCEPT ID
	 					tokSize+=1
	 					tok = ""
	 					state = 1
	 					notDone = True
	 			elif state == 6: #n State (ACCEPT)
	 				if re.match("o", let):
	 					tok = tok+let
	 					state = 36 #ID State
	 				elif re.match("[a-np-z0-9]", let):
	 					tok = tok+let
	 					state = 2 #ID State
	 				else:
	 					tokens[tokSize] = ["T"+str(tokSize), "ID", tok] #ACCEPT ID
	 					tokSize+=1
	 					tok = ""
	 					state = 1
	 					notDone = True
	 			elif state == 7: #m State (ACCEPT)
	 				if re.match("u", let):
	 					tok = tok+let
	 					state = 37 #ID State
	 				elif re.match("[a-mo-z0-9]", let):
	 					tok = tok+let
	 					state = 2 #ID State
	 				else:
	 					tokens[tokSize] = ["T"+str(tokSize), "ID", tok] #ACCEPT ID
	 					tokSize+=1
	 					tok = ""
	 					state = 1
	 					notDone = True
	 			elif state == 8: #p State (ACCEPT)
	 				if re.match("r", let):
	 					tok = tok+let
	 					state = 38 #ID State
	 				elif re.match("[a-qs-z0-9]", let):
	 					tok = tok+let
	 					state = 2 #ID State
	 				else:
	 					tokens[tokSize] = ["T"+str(tokSize), "ID", tok] #ACCEPT ID
	 					tokSize+=1
	 					tok = ""
	 					state = 1
	 					notDone = True
	 			elif state == 9: #i State (ACCEPT)
	 				if re.match("f", let):
	 					tok = tok+let
	 					state = 39 #ID State
	 				elif re.match("n", let):
	 					tok = tok+let
	 					state = 40 #ID State
	 				elif re.match("[a-eg-mo-z0-9]", let):
	 					tok = tok+let
	 					state = 2 #ID State
	 				else:
	 					tokens[tokSize] = ["T"+str(tokSize), "ID", tok] #ACCEPT ID
	 					tokSize+=1
	 					tok = ""
	 					state = 1
	 					notDone = True
	 			elif state == 10: #t State (ACCEPT)
	 				if re.match("h", let):
	 					tok = tok+let
	 					state = 41 #ID State
	 				elif re.match("[a-gi-z0-9]", let):
	 					tok = tok+let
	 					state = 2 #ID State
	 				else:
	 					tokens[tokSize] = ["T"+str(tokSize), "ID", tok] #ACCEPT ID
	 					tokSize+=1
	 					tok = ""
	 					state = 1
	 					notDone = True
	 			elif state == 11: #w State (ACCEPT)
	 				if re.match("h", let):
	 					tok = tok+let
	 					state = 42 #ID State
	 				elif re.match("[a-gi-z0-9]", let):
	 					tok = tok+let
	 					state = 2 #ID State
	 				else:
	 					tokens[tokSize] = ["T"+str(tokSize), "ID", tok] #ACCEPT ID
	 					tokSize+=1
	 					tok = ""
	 					state = 1
	 					notDone = True
	 			elif state == 12: #f State (ACCEPT)
	 				if re.match("o", let):
	 					tok = tok+let
	 					state = 43 #ID State
	 				elif re.match("[a-np-z0-9]", let):
	 					tok = tok+let
	 					state = 2 #ID State
	 				else:
	 					tokens[tokSize] = ["T"+str(tokSize), "ID", tok] #ACCEPT ID
	 					tokSize+=1
	 					tok = ""
	 					state = 1
	 					notDone = True
	 			elif state == 13:
	 				if re.match("a", let):
	 					tok = tok+let
	 					state = 44 #ID State
	 				elif re.match("[b-z0-9]", let):
	 					tok = tok+let
	 					state = 2 #ID State
	 				else:
	 					tokens[tokSize] = ["T"+str(tokSize), "ID", tok] #ACCEPT ID
	 					tokSize+=1
	 					tok = ""
	 					state = 1
	 					notDone = True
	 			elif state == 14:
	 				if re.match("u", let):
	 					tok = tok+let
	 					state = 45 #ID State
	 				elif re.match("[a-tv-z0-9]", let):
	 					tok = tok+let
	 					state = 2 #ID State
	 				else:
	 					tokens[tokSize] = ["T"+str(tokSize), "ID", tok] #ACCEPT ID
	 					tokSize+=1
	 					tok = ""
	 					state = 1
	 					notDone = True
	 			elif state == 15:
	 			elif state == 16:
	 			elif state == 17:
	 			elif state == 18:
	 			elif state == 19:
	 			elif state == 20:
	 			elif state == 21:
	 			elif state == 22:
	 			elif state == 23:
	 			elif state == 24:
	 			elif state == 25:
	 			elif state == 26:
	 			elif state == 27:
	 			elif state == 28:

 	print(tokens)
 	return tokens

 def LexError(t):
 	print("ERROR in Token: "+t)
 	exit(0)

if __name__ == "__main__":
    main()