package lunarcomp;

import java.util.ArrayList;
import static lunarcomp.LunarComp._erros;

public class Syntax {
    
    private ArrayList<Token> tokens;
    
    public boolean termo(int pos) {
        Token t = tokens.get(pos);
        if(t.getToken().equals("T_ID") || t.getToken().equals("T_NUM") || t.getToken().equals("T_BOOL")  || t.getToken().equals("T_CHAR") || 
                t.getToken().equals("T_STRING")) 
            return true;
        else 
            return false;      
    }
    
    public boolean exp_unaria(int pos) {
        Token t = tokens.get(pos);
        if(termo(pos))
            return true;
        else if(t.getToken().equals("T_ID")) {
            t = tokens.get(++pos);
            if(t.getToken().equals("T_OPU"))
                return true;
            else {
                _erros.add(new Erro(t.getToken(), t.getLinha(), "Operador unário esperado"));
                return false;
            }
        }
        _erros.add(new Erro(t.getToken(), t.getLinha(), "Id ou termo esperado"));
        return false;
    }
    
    public boolean exp_aritmetica(int pos) {
        Token t = tokens.get(pos), t2 = tokens.get(pos+1);
        if(exp_unaria(pos) && !t2.getToken().equals("T_OPA"))
            return true;
        else if(exp_unaria(pos)) {
            t = tokens.get(++pos);
            if(t.getToken().equals("T_OPA")) {
                t = tokens.get(++pos);
                if(exp_aritmetica(pos)) {
                    return true;
                } else {
                    _erros.add(new Erro(t.getToken(), t.getLinha(), "Expressão aritimética esperada"));
                    return false;
                }
            } else {
                _erros.add(new Erro(t.getToken(), t.getLinha(), "Operador aritimético esperado"));
                return false;
            }   
        } else {
            _erros.add(new Erro(t.getToken(), t.getLinha(), "Expressão unária esperada"));
            return false;
        }
    }
    
}
