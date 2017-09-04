
package lunarcomp;

import java.util.ArrayList;

public class Lex {
    
    private ArrayList<Token> tokens;
    private String lexemas[];
    
    public String[] lerLexemas(String codigo) {
        String str = codigo.replace("\t", " ");
        str = str.replace("\n", " ");
        
        str = codigo.replace(";", " ; ").replace("(", " ( ").replace(")", " ) ").replace("{", " { ").replace("}", " } ");
        str = str.replace("+", " + ").replace("-", " - ").replace("/", " / ").replace("*", " * ").replace(">", " > ");
        str = str.replace("<", " < ").replace(">=", " >= ").replace("<=", " <= ").replace("==", " == ").replace("!=", " != ");
        str = str.replace("++", " ++ ").replace("--", " -- ").replace("=", " = ").replace("..", " .. ");
        str = str.replace("...", " ... ").replace("\t", " ").replace("\n", " ").replaceAll("[ ]+", " ");
        
        String lex[] = str.split(" ");
        
        return lex;
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
