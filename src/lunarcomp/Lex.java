
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
        str = str.replace(".. .", " ... ").replace("\t", " ").replace("\n", " ").replaceAll("[ ]+", " ");
        
        lexemas = str.split(" ");
        
    }
    
    public void criar_tokens() {
        
        for(int i = 0; i < lexemas.length; i++) {
            switch(lexemas[i]) {
                case "launch":break;
                case "if":break;
                case "else": break;
                case "loop": break;
                case "until": break;
                case "int": break;
                case "real": break;
                case "char": break;
                case "bool": break;
                case "string": break;
                case "true": break;
                case "false": break;
                case ",": break;
                case ";": break;
                case "{": break;
                case "}": break;
                case "(": break;
                case ")": break;
                case "=": break;
                case "+": break;
                case "-": break;
                case "*": break;
                case "/": break;
                case "<": break;
                case ">": break;
                case "<=": break;
                case ">=": break;
                case "==": break;
                case "!=": break;
                case "&": break;
                case "|": break;
                case "++": break;
                case "--": break;
                case "!": break;
                case "..": break;
                case "...": break;
                default:/*checar se é número ou ID*/;
                
            }
        }
        
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
