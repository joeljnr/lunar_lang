
package lunarcomp;

import java.util.ArrayList;

public class Lex {
    
    private ArrayList<Token> tokens;
    private ArrayList<Token> ids;
    private String lexemas[];
    
    public void ler_lexemas(String codigo) {
        
        String str = codigo.replace(";", " ; ").replace("(", " ( ").replace(")", " ) ").replace("{", " { ").replace("}", " } ");
        str = str.replace("+", " + ").replace("-", " - ").replace("/", " / ").replace("*", " * ").replace(">", " > ");
        str = str.replace("<", " < ").replace(">=", " >= ").replace("<=", " <= ").replace("==", " == ").replace("!=", " != ").replace("!", " ! ");
        str = str.replace("++", " ++ ").replace("--", " -- ").replace("=", " = ").replace("..", " .. ").replace("\"", " \" ").replace(",", " , ");
        str = str.replace(".. .", " ... ").replace("\t", " ").replace("\n", " \n ").replaceAll("[ ]+", " ");
        
        lexemas = str.split(" ");
        
        
    }
    
    public void criar_tokens() {
        
        int linha = 1;
        int i, j;
        
        tokens = new ArrayList<Token>();
        ids = new ArrayList<Token>();
        for(i = 0; i < lexemas.length; i++) {
            if(lexemas[i].equals("..")) {
                j = i;
                while(j < lexemas.length && !lexemas[j].equals("\n")){
                    lexemas[j] = "";
                    j++;
                }
            } else if(lexemas[i].equals("...")) {
                j = i+1;
                while(j < lexemas.length && !lexemas[j].equals("...")){
                    
                    if(!lexemas[j].equals("\n"))
                        lexemas[j] = "";
                    j++;
                }
                if(j < lexemas.length)
                    lexemas[j] = "";
            }
            else { 
                switch(lexemas[i]) {
                    case "launch":
                        tokens.add(new Token("T_LAUNCH", "launch", linha));
                        break;
                    case "if":
                        tokens.add(new Token("T_IF", "if", linha));
                        break;
                    case "else": 
                        tokens.add(new Token("T_ELSE", "else", linha));
                        break;
                    case "loop": 
                        tokens.add(new Token("T_LOOP", "loop", linha));
                        break;
                    case "until": 
                        tokens.add(new Token("T_UNTIL", "until", linha));
                        break;
                    case "int": 
                        tokens.add(new Token("T_TYPE", "int", linha));
                        break;
                    case "real": 
                        tokens.add(new Token("T_TYPE", "real", linha));
                        break;
                    case "char": 
                        tokens.add(new Token("T_TYPE", "char", linha));
                        break;
                    case "bool": 
                        tokens.add(new Token("T_TYPE", "bool", linha));
                        break;
                    case "string": 
                        tokens.add(new Token("T_TYPE", "string", linha));
                        break;
                    case "true": 
                        tokens.add(new Token("T_BOOL", "true", linha));
                        break;
                    case "false": 
                        tokens.add(new Token("T_BOOL", "false", linha));
                        break;
                    case ",": 
                        tokens.add(new Token("T_COMMA", ",", linha));
                        break;
                    case ";": 
                        tokens.add(new Token("T_SEMICOLON", ";", linha));
                        break;
                    case "{": 
                        tokens.add(new Token("T_BRACESL", "{", linha));
                        break;
                    case "}": 
                        tokens.add(new Token("T_BRACESR", "}", linha));
                        break;
                    case "(": 
                        tokens.add(new Token("T_PARL", "(", linha));
                        break;
                    case ")": 
                        tokens.add(new Token("T_PARR", ")", linha));
                        break;
                    case "=": 
                        tokens.add(new Token("T_ATR", "=", linha));
                        break;
                    case "+": 
                        tokens.add(new Token("T_OPA", "+", linha));
                        break;
                    case "-": 
                        tokens.add(new Token("T_OPA", "-", linha));
                        break;
                    case "*": 
                        tokens.add(new Token("T_OPA", "*", linha));
                        break;
                    case "/": 
                        tokens.add(new Token("T_OPA", "/", linha));
                        break;
                    case "<": 
                        tokens.add(new Token("T_OPR", "<", linha));
                        break;
                    case ">": 
                        tokens.add(new Token("T_OPR", ">", linha));
                        break;
                    case "<=": 
                        tokens.add(new Token("T_OPR", "<=", linha));
                        break;
                    case ">=": 
                        tokens.add(new Token("T_OPR", ">=", linha));
                        break;
                    case "==": 
                        tokens.add(new Token("T_OPR", "==", linha));
                        break;
                    case "!=": 
                        tokens.add(new Token("T_OPR", "!=", linha));
                        break;
                    case "&": 
                        tokens.add(new Token("T_OPL", "&", linha));
                        break;
                    case "|": 
                        tokens.add(new Token("T_OPL", "|", linha));
                        break;
                    case "++": 
                        tokens.add(new Token("T_OPU", "++", linha));
                        break;
                    case "--": 
                        tokens.add(new Token("T_OPU", "--", linha));
                        break;
                    case "!": 
                        tokens.add(new Token("T_OPN", "!", linha));
                        break;
                    case "\"": 
                        tokens.add(new Token("T_ASPAS", "\"", linha));
                        break;
                    case "\n": 
                        linha++; 
                        break;
                    default:

                        if(verificaNum(lexemas[i]) == 2) // é int
                            tokens.add(new Token("T_NUM", Integer.parseInt(lexemas[i]), linha));
                        else if(verificaNum(lexemas[i]) == 1) // é float
                            tokens.add(new Token("T_NUM", Float.parseFloat(lexemas[i]), linha));
                        else if(verificaNum(lexemas[i]) == 0) // é ID
                            ids.add(new Token("T_ID", lexemas[i], linha));
                    break;
                }
            } 
        }
    }
    
    public int verificaNum(String str) {
        if(str != "\n" && str != "") {
            if(!Character.isDigit(str.charAt(0))) {
                return 0; //é ID;
            }

            for(int i = 0; i < str.length(); i++) {
                if(str.charAt(i) == '.')
                    return 1; //é float
            }

            return 2; //é int
        }
        return -1; //é barra n
    }
    
    public static Token next_token(String cod) {
        
        Token t = null;
        return t;
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public String[] getLexemas() {
        return lexemas;
    }

    public void setLexemas(String[] lexemas) {
        this.lexemas = lexemas;
    }

    public ArrayList<Token> getIds() {
        return ids;
    }

    public void setIds(ArrayList<Token> ids) {
        this.ids = ids;
    }
    
    
}
