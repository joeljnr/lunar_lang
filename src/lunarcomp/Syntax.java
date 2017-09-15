package lunarcomp;

import java.util.ArrayList;
import static lunarcomp.LunarComp._erros;

public class Syntax {
    
    private ArrayList<Token> tokens;
    private int pos;
    
    //melhorar o estado seguro
    public void erro(String token, int linha, String descr) {
        _erros.add(new Erro(token, linha, descr));
        boolean flag = false;
        Token t  = tokens.get(pos);
        
        while(!flag) {
            switch(t.getToken()){
                case "T_ID": flag = true; break;
                case "T_IF": flag = true; break;
                case "T_LOOP": flag = true; break;
                case "T_LOOP_DOWN": flag = true; break;
                case "T_LOOP_TO": flag = true; break;
                case "T_UNTIL": flag = true; break;
                case "T_TYPE": flag = true; break;
                case "T_SEMICOLON": flag = true; break;
                case "T_BRACESL": flag = true; break;
                case "T_BRACESR": flag = true; break;
                default: pos++;
            }
            t = tokens.get(pos);
        }
        
    }
    
    public boolean termo() {
        Token t = tokens.get(pos);
        if(t.getToken().equals("T_ID") || t.getToken().equals("T_NUM") || t.getToken().equals("T_BOOL")  || t.getToken().equals("T_CHAR") || 
                t.getToken().equals("T_STRING")) 
            return true;
        else 
            return false;      
    }
    
    public boolean exp_unaria() {
        Token t = tokens.get(pos);
        if(termo())
            return true;
        else if(t.getToken().equals("T_ID")) {
            t = tokens.get(++pos);
            if(t.getToken().equals("T_OPU"))
                return true;
            else {
                erro(t.getToken(), t.getLinha(), "Operador unário esperado");
                return false;
            }
        }
        erro(t.getToken(), t.getLinha(), "Id ou termo esperado");
        return false;
    }
    
    public boolean exp_aritmetica() {
        Token t = tokens.get(pos), t2 = tokens.get(pos+1);
        if(exp_unaria() && !t2.getToken().equals("T_OPA"))
            return true;
        else if(exp_unaria()) {
            t = tokens.get(++pos);
            if(t.getToken().equals("T_OPA")) {
                t = tokens.get(++pos);
                if(exp_aritmetica()) {
                    return true;
                } else {
                    erro(t.getToken(), t.getLinha(), "Expressão aritimética esperada");
                    return false;
                }
            } else {
                erro(t.getToken(), t.getLinha(), "Operador aritimético esperado");
                return false;
            }   
        } else {
            erro(t.getToken(), t.getLinha(), "Expressão unária esperada");
            return false;
        }
    }
    
}
