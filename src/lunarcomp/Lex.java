
package lunarcomp;

import java.util.ArrayList;

public class Lex {
    
    private ArrayList<Token> tokens;
    private String lexemas[];
    
    public void ler_lexemas(String codigo) {
        
        String str = codigo.replace(";", " ; ").replace("(", " ( ").replace(")", " ) ").replace("{", " { ").replace("}", " } ");
        str = str.replace("+", " + ").replace("-", " - ").replace("/", " / ").replace("*", " * ").replace(">", " > ");
        str = str.replace("<", " < ").replace(">=", " >= ").replace("<=", " <= ").replace("==", " == ").replace("!=", " != ").replace("!", " ! ");
        str = str.replace("++", " ++ ").replace("--", " -- ").replace("=", " = ").replace("..", " .. ").replace("\"", " \" ").replace(",", " , ");
        str = str.replace(".. .", " ... ").replace("\t", " ").replaceAll("[ ]+", " ");
        
        lexemas = str.split(" ");
        
    }
    
    public void criar_tokens() {
        
        int linha = 0;
        
        for(int i = 0; i < lexemas.length; i++) {
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
                    tokens.add(new Token("T_SEMICOLON", ",", linha));
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
                    tokens.add(new Token("T_ART", "=", linha));
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
                case "..": break;
                case "...": break;
                case "\n": 
                    linha++; 
                    break;
                default:
                   
                    if(verificaNum(lexemas[i]) == 1) // é int
                        tokens.add(new Token("T_NUM", Integer.parseInt(lexemas[i]), linha));
                    else if(verificaNum(lexemas[i]) == 0) // é float
                        tokens.add(new Token("T_NUM", Float.parseFloat(lexemas[i]), linha));
                    else if(verificaNum(lexemas[i]) == -1) // é float
                        tokens.add(new Token("T_ID", lexemas[i], linha));
                
            }
        }
        
    }
    
    public int verificaNum(String str) {
        if(str.charAt(0) != '0' | str.charAt(0) != '1' | str.charAt(0) != '2' | str.charAt(0) != '3' | 
                str.charAt(0) != '4' | str.charAt(0) != '5' | str.charAt(0) != '6' | str.charAt(0) != '7' | str.charAt(0) != '8' | str.charAt(0) != '9' ) {
            return -1; //é ID;
        }
        
        for(int i = 0; i < str.length(); i++) {
            
            if(str.charAt(i) == '.')
                return 0; //é float
        }
        
        return 1; //é int
        
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
}
