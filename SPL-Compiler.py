#SPL-Compiler
#Date: 01/04/2021

#IMPORTS
import re

def main():
    tokens = lexer()
    for x,tok in enumerate(tokens):
        if x == 0:
            print(str(tok))
        else:
            print("--->"+str(tok))
    exit(0)

def lexer():
    tokens = []
    tokSize = 0
    state = 1
    file = open("../uploads/practical_1.txt")
    tok = ""
    lineCount = 0
    strLen = 0
    for line in file:
        lineCount += 1
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
                        tok = "" #Skip let
                        state = 1 #ID State
                    elif re.match("\(", let):
                        tok = tok+let
                        state = 24 #ID State
                    elif re.match("\)", let):
                        tok = tok+let
                        state = 25 #ID State
                    elif re.match("\{", let):
                        tok = tok+let
                        state = 26 #ID State
                    elif re.match("\}", let):
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
                        print(LexError(tok, lineCount, "", let))
                        exit(0)
                elif state == 2: #ID State (ACCEPT)
                    if re.match("[a-z0-9]", let):
                        tok = tok+let
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 3: #e State (ACCEPT)
                    if re.match("l", let):
                        tok = tok+let
                        state = 30
                    elif re.match("q", let):
                        tok = tok+let
                        state = 31
                    elif re.match("[a-km-pr-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 4: #a State (ACCEPT)
                    if re.match("n", let):
                        tok = tok+let
                        state = 32
                    elif re.match("d", let):
                        tok = tok+let
                        state = 22
                    elif re.match("[a-ce-mo-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 5: #o State (ACCEPT)
                    if re.match("r", let):
                        tok = tok+let
                        state = 34
                    elif re.match("u", let):
                        tok = tok+let
                        state = 35
                    elif re.match("[a-qstv-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 6: #n State (ACCEPT)
                    if re.match("o", let):
                        tok = tok+let
                        state = 36
                    elif re.match("[a-np-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 7: #m State (ACCEPT)
                    if re.match("u", let):
                        tok = tok+let
                        state = 37
                    elif re.match("[a-mo-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 8: #p State (ACCEPT)
                    if re.match("r", let):
                        tok = tok+let
                        state = 38 
                    elif re.match("[a-qs-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 9: #i State (ACCEPT)
                    if re.match("f", let):
                        tok = tok+let
                        state = 39
                    elif re.match("n", let):
                        tok = tok+let
                        state = 40 #ID State
                    elif re.match("[a-eg-mo-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 10: #t State (ACCEPT)
                    if re.match("h", let):
                        tok = tok+let
                        state = 41
                    elif re.match("[a-gi-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 11: #w State (ACCEPT)
                    if re.match("h", let):
                        tok = tok+let
                        state = 42
                    elif re.match("[a-gi-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 12: #f State (ACCEPT)
                    if re.match("o", let):
                        tok = tok+let
                        state = 43
                    elif re.match("[a-np-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 13: #h State (ACCEPT)
                    if re.match("a", let):
                        tok = tok+let
                        state = 44
                    elif re.match("[b-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 14: #s State (ACCEPT)
                    if re.match("u", let):
                        tok = tok+let
                        state = 45
                    elif re.match("[a-tv-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 15: #STRING State (REJECT)
                    if re.match("\"", let):
                        tok = tok+let
                        state = 46 #String ACCEPT State
                    elif re.match("[a-z0-9]", let) and strLen < 8:
                        strLen+=1
                        tok = tok+let
                        state = 15 #ID State
                    else:
                        if re.match("[a-z0-9]", let):
                            tok = tok+let
                            print(LexError(tok, lineCount, "strLen", let))
                            exit(0)
                elif state == 16: #INT State (ACCEPT)
                    if re.match("[0-9]", let):
                        tok = tok+let
                        state = 16
                    else:
                        tokens.append(["T"+str(tokSize), "NUMBER", tok])  #ACCEPT INT
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 17: #0 State (ACCEPT)
                    if re.match("[0-9]", let): #REJECT
                        tok = tok+let
                        print(LexError(tok, lineCount, "zero", let))
                        exit(0)
                    else:
                        tokens.append(["T"+str(tokSize), "NUMBER", tok])  #ACCEPT INT
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 18: #- State (REJECT)
                    if re.match("[1-9]", let):
                        tok = tok+let
                        state = 16 #INT ACCEPT State
                    else:
                        tok = tok+let
                        print(LexError(tok, lineCount, "", let))
                        exit(0)
                elif state == 19:
                    tokens.append(["T"+str(tokSize), "Assignment SYMBOL", tok])  #ACCEPT SYMBOL
                    tokSize+=1
                    tok = ""
                    state = 1
                    notDone = True
                elif state == 20:
                    tokens.append(["T"+str(tokSize), "Comparison SYMBOL", tok])  #ACCEPT SYMBOL
                    tokSize+=1
                    tok = ""
                    state = 1
                    notDone = True
                elif state == 21:
                    tokens.append(["T"+str(tokSize), "Comparison SYMBOL", tok])  #ACCEPT SYMBOL
                    tokSize+=1
                    tok = ""
                    state = 1
                    notDone = True
                elif state == 22: #ad State (ACCEPT)
                    if re.match("d", let):
                        tok = tok+let
                        state = 23
                    elif re.match("[a-ce-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 23: #add State (ACCEPT)
                    if re.match("[a-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "Math KEYWORD", tok])  #ACCEPT KEYWORD
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 24:
                    tokens.append(["T"+str(tokSize), "Grouping SYMBOL", tok])  #ACCEPT SYMBOL
                    tokSize+=1
                    tok = ""
                    state = 1
                    notDone = True
                elif state == 25:
                    tokens.append(["T"+str(tokSize), "Grouping SYMBOL", tok])  #ACCEPT SYMBOL
                    tokSize+=1
                    tok = ""
                    state = 1
                    notDone = True
                elif state == 26:
                    tokens.append(["T"+str(tokSize), "Grouping SYMBOL", tok])  #ACCEPT SYMBOL
                    tokSize+=1
                    tok = ""
                    state = 1
                    notDone = True
                elif state == 27:
                    tokens.append(["T"+str(tokSize), "Grouping SYMBOL", tok])  #ACCEPT SYMBOL
                    tokSize+=1
                    tok = ""
                    state = 1
                    notDone = True
                elif state == 28:
                    tokens.append(["T"+str(tokSize), "Grouping SYMBOL", tok])  #ACCEPT SYMBOL
                    tokSize+=1
                    tok = ""
                    state = 1
                    notDone = True
                elif state == 29:
                    tokens.append(["T"+str(tokSize), "Grouping SYMBOL", tok])  #ACCEPT SYMBOL
                    tokSize+=1
                    tok = ""
                    state = 1
                    notDone = True
                elif state == 30: #el State (ACCEPT)
                    if re.match("s", let):
                        tok = tok+let
                        state = 47
                    elif re.match("[a-rt-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 31: #eq State (ACCEPT)
                    if re.match("[a-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "Comparison KEYWORD", tok])  #ACCEPT KEYWORD
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 32: #an State (ACCEPT)
                    if re.match("d", let):
                        tok = tok+let
                        state = 33
                    elif re.match("[a-ce-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 33: #and State (ACCEPT)
                    if re.match("[a-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "Boolean KEYWORD", tok])  #ACCEPT KEYWORD
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 34: #or State (ACCEPT)
                    if re.match("[a-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "Boolean KEYWORD", tok])  #ACCEPT KEYWORD
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 35: #ou State (ACCEPT)
                    if re.match("t", let):
                        tok = tok+let
                        state = 48
                    elif re.match("[a-su-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 36: #no State (ACCEPT)
                    if re.match("t", let):
                        tok = tok+let
                        state = 53
                    elif re.match("[a-su-z-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 37: #mu State (ACCEPT)
                    if re.match("l", let):
                        tok = tok+let
                        state = 54
                    elif re.match("[a-km-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 38: #pr State (ACCEPT)
                    if re.match("o", let):
                        tok = tok+let
                        state = 56
                    elif re.match("[a-np-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 39: #if State (ACCEPT)
                    if re.match("[a-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "Control KEYWORD", tok])  #ACCEPT KEYWORD
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 40: #in State (ACCEPT)
                    if re.match("p", let):
                        tok = tok+let
                        state = 58
                    elif re.match("[a-oq-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 41: #th State (ACCEPT)
                    if re.match("e", let):
                        tok = tok+let
                        state = 61
                    elif re.match("[a-df-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 42: #wh State (ACCEPT)
                    if re.match("i", let):
                        tok = tok+let
                        state = 63
                    elif re.match("[a-hj-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 43: #fo State (ACCEPT)
                    if re.match("r", let):
                        tok = tok+let
                        state = 66
                    elif re.match("[a-qs-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 44: #ha State (ACCEPT)
                    if re.match("l", let):
                        tok = tok+let
                        state = 67
                    elif re.match("[a-km-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 45: #su State (ACCEPT)
                    if re.match("b", let):
                        tok = tok+let
                        state = 69
                    elif re.match("[ac-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True

                elif state == 46: #STRING State (ACCEPT)
                    strLen = 0
                    tokens.append(["T"+str(tokSize), "STRING", tok]) #ACCEPT STRING
                    tokSize+=1
                    tok = ""
                    state = 1
                    notDone = True
                elif state == 47: #els State (ACCEPT)
                    if re.match("e", let):
                        tok = tok+let
                        state = 52
                    elif re.match("[a-df-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 48: #out State (ACCEPT)
                    if re.match("p", let):
                        tok = tok+let
                        state = 49
                    elif re.match("[a-oq-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 49: #outp State (ACCEPT)
                    if re.match("u", let):
                        tok = tok+let
                        state = 50
                    elif re.match("[a-tv-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 50: #outpu State (ACCEPT)
                    if re.match("t", let):
                        tok = tok+let
                        state = 51
                    elif re.match("[a-su-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 51: #output State (ACCEPT)
                    if re.match("[a-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "I/O KEYWORD", tok])  #ACCEPT KEYWORD
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 52: #else State (ACCEPT)
                    if re.match("[a-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "Control KEYWORD", tok])  #ACCEPT KEYWORD
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 53: #not State (ACCEPT)
                    if re.match("[a-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "Boolean KEYWORD", tok])  #ACCEPT KEYWORD
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 54: #mul State (ACCEPT)
                    if re.match("t", let):
                        tok = tok+let
                        state = 55
                    elif re.match("[a-su-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 55: #mult State (ACCEPT)
                    if re.match("[a-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "Math KEYWORD", tok])  #ACCEPT KEYWORD
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 56: #pro State (ACCEPT)
                    if re.match("c", let):
                        tok = tok+let
                        state = 57
                    elif re.match("[a-su-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 57: #proc State (ACCEPT)
                    if re.match("[a-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "Procedure KEYWORD", tok])  #ACCEPT KEYWORD
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 58: #inp State (ACCEPT)
                    if re.match("u", let):
                        tok = tok+let
                        state = 59
                    elif re.match("[a-tv-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 59: #inpu State (ACCEPT)
                    if re.match("t", let):
                        tok = tok+let
                        state = 60
                    elif re.match("[a-su-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 60: #input State (ACCEPT)
                    if re.match("[a-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "I/O KEYWORD", tok])  #ACCEPT KEYWORD
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 61: #the State (ACCEPT)
                    if re.match("n", let):
                        tok = tok+let
                        state = 62
                    elif re.match("[a-mo-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 62: #then State (ACCEPT)
                    if re.match("[a-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "Control KEYWORD", tok])  #ACCEPT KEYWORD
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 63: #whi State (ACCEPT)
                    if re.match("l", let):
                        tok = tok+let
                        state = 64
                    elif re.match("[a-km-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 64: #whil State (ACCEPT)
                    if re.match("e", let):
                        tok = tok+let
                        state = 65
                    elif re.match("[a-df-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 65: #while State (ACCEPT)
                    if re.match("[a-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "Control KEYWORD", tok])  #ACCEPT KEYWORD
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 66: #for State (ACCEPT)
                    if re.match("[a-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "Control KEYWORD", tok])  #ACCEPT KEYWORD
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 67: #hal State (ACCEPT)
                    if re.match("t", let):
                        tok = tok+let
                        state = 68
                    elif re.match("[a-su-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 68: #halt State (ACCEPT)
                    if re.match("[a-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "Special KEYWORD", tok])  #ACCEPT KEYWORD
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True
                elif state == 69: #sub State (ACCEPT)
                    if re.match("[a-z0-9]", let):
                        tok = tok+let
                        state = 2 #ID State
                    else:
                        tokens.append(["T"+str(tokSize), "Math KEYWORD", tok])  #ACCEPT KEYWORD
                        tokSize+=1
                        tok = ""
                        state = 1
                        notDone = True

    if state != 1 and state != 18:
        if state in [19,20,21,24,25,26,27,28,29]:
            tokens.append(["T"+str(tokSize), "SYMBOL", tok])  #ACCEPT SYMBOL
        elif state in [16,17]:
            tokens.append(["T"+str(tokSize), "NUMBER", tok])  #ACCEPT NUMBER
        elif state in [23,31,33,34,39,51,52,53,55,57,60,62,65,66,68,69]:
            tokens.append(["T"+str(tokSize), "KEYWORD", tok])  #ACCEPT KEYWORD
        elif state in [46]:
            tokens.append(["T"+str(tokSize), "STRING", tok])  #ACCEPT STRING
        elif state in [15]:
            print(LexError(tok, lineCount, "incompString", "a"))
            exit(0)
        else:
            tokens.append(["T"+str(tokSize), "ID", tok])  #ACCEPT ID
        tokSize+=1
        file.close()
    return tokens


def LexError(t, lin, s, l):
    if s == "strLen":
        return str("Lexical Error on Line "+str(lin)+": "+t+" (String too long!)")
    elif not re.match("[a-z0-9\"\-\(\)\{\};,<>=]", l):
        return str("Lexical Error on Line "+str(lin)+": "+t+" (Invalid Character: "+str(l)+")")
    elif s == "incompString":
        return str("Lexical Error on Line "+str(lin)+": "+t+" (Incomplete String!)")
    elif s == "zero":
        return str("Lexical Error on Line "+str(lin)+": "+t+" (Malformed Number!)")
    else:
        return str("Lexical Error on Line "+str(lin)+": "+t)

if __name__ == "__main__":
    main()





















