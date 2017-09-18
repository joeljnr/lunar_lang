package lunarcomp;

import java.util.ArrayList;
import static lunarcomp.LunarComp._erros;

public class Syntax {
    
    private ArrayList<Token> tokens;
    private int pos;
    private Token atual;
    private boolean ultimo = true;
    
    public Syntax(ArrayList<Token> tokens, int pos) {
        this.tokens = tokens;
        this.pos = pos;
    }
    
    
    //melhorar o estado seguro
    public void erro(String token, int linha, String descr) {
        _erros.add(new Erro(token, linha, descr));
        boolean flag = false;
        if(pos < tokens.size()) {
            Token t  = tokens.get(pos);

            while(!flag && pos < tokens.size()) {
                switch(t.getToken()){
                    case "T_ID": flag = true; break;
                    case "T_LOOP": flag = true; break;
                    case "T_UNTIL": flag = true; break;
                    case "T_TYPE": flag = true; break;
                    case "T_SEMICOLON": flag = true; break;
                    case "T_BRACESL": flag = true; break;
                    default: pos++;
                }
                if(pos < tokens.size())
                    t = tokens.get(pos);
            }
        }
        
    }
    
    public RetornoSyntax termo() {
        if(pos < tokens.size()) {
            atual = tokens.get(pos);

            if(atual.getToken().equals("T_ID") || atual.getToken().equals("T_NUM") || atual.getToken().equals("T_BOOL")  || atual.getToken().equals("T_CHAR") || 
                    atual.getToken().equals("T_STRING")) {
                ultimo = true;
                return new RetornoSyntax(true, atual.getToken());
            }
            else  {
                ultimo = false;
                return new RetornoSyntax(false, atual.getToken()); 
            }
        }
        return new RetornoSyntax(false, atual.getToken()); 
    }
    
    public RetornoSyntax exp_unaria() {
        if(pos < tokens.size()) {
            atual = tokens.get(pos);

            if(termo().isAceito()) {
                ultimo = true;
                return new RetornoSyntax(true, atual.getToken());
            }
            else if(atual.getToken().equals("T_ID")) {
                atual = tokens.get(++pos);
                if(atual.getToken().equals("T_OPU")){
                    ultimo = true;
                    return new RetornoSyntax(true, atual.getToken());
                }
                else {
                    erro(atual.getToken(), atual.getLinha(), "[exp_unaria] Operador unário esperado");
                    ultimo = false;
                    return new RetornoSyntax(false, atual.getToken());
                }
            }
            erro(atual.getToken(), atual.getLinha(), "[exp_unaria] Id ou termo esperado");
        }
        ultimo = false;
        return new RetornoSyntax(false, atual.getToken());
        
    }
    
    public RetornoSyntax exp_aritmetica() {
        if(pos < tokens.size()) {
            atual = tokens.get(pos);

            if(exp_unaria().isAceito() && !tokens.get(pos+1).getToken().equals("T_OPA")) { 
                ultimo = true;
                return new RetornoSyntax(true, atual.getToken());
            }
            else if(exp_unaria().isAceito()) {
                atual = tokens.get(++pos);
                if(atual.getToken().equals("T_OPA")) {
                    atual = tokens.get(++pos);
                    if(exp_aritmetica().isAceito()) {
                        ultimo = true;
                        return new RetornoSyntax(true, atual.getToken());
                    } else {
                        erro(atual.getToken(), atual.getLinha(), "[exp_aritmetica] Expressão aritimética esperada");
                        ultimo = false;
                        return new RetornoSyntax(false, atual.getToken());
                    }
                } else {
                    erro(atual.getToken(), atual.getLinha(), "[exp_aritmetica] Operador aritimético esperado");
                    ultimo = false;
                    return new RetornoSyntax(false, atual.getToken());
                }   
            } else {
                erro(atual.getToken(), atual.getLinha(), "[exp_aritmetica] Expressão unária esperada");
                ultimo = false;
                return new RetornoSyntax(false, atual.getToken());
            }
        }
        return new RetornoSyntax(false, atual.getToken());
    }
    
    public RetornoSyntax exp_relacional() {
        if(pos < tokens.size()) {
            atual = tokens.get(pos);

            if(exp_aritmetica().isAceito() && !tokens.get(pos+1).getToken().equals("T_OPR")){
                ultimo = true;
                return new RetornoSyntax(true, atual.getToken());
            }
            else if(exp_aritmetica().isAceito()) {
                atual = tokens.get(++pos);
                if(atual.getToken().equals("T_OPR")) {
                    atual = tokens.get(++pos);
                    if(exp_aritmetica().isAceito()) {
                        ultimo = true;
                        return new RetornoSyntax(true, atual.getToken());
                    } else {
                        ultimo = false;
                        erro(atual.getToken(), atual.getLinha(), "[exp_relacional]Expressão aritimética esperada");
                        return new RetornoSyntax(false, atual.getToken());
                    }
                } else {
                    ultimo = false;
                    erro(atual.getToken(), atual.getLinha(), "[exp_relacional]Operador relacional esperado");
                    return new RetornoSyntax(false, atual.getToken());
                }   
            } else {
                ultimo = false;
                erro(atual.getToken(), atual.getLinha(), "[exp_relacional] Expressão aritmética esperada");
                return new RetornoSyntax(false, atual.getToken());
            }  
        }
        return new RetornoSyntax(false, atual.getToken());
    }
    
    public RetornoSyntax exp_logica() {
        if(pos < tokens.size()) {
            atual = tokens.get(pos);

            if(exp_relacional().isAceito() && !tokens.get(pos+1).getToken().equals("T_OPL")) {
                ultimo = true;
                return new RetornoSyntax(true, atual.getToken());
            }
            else if(exp_relacional().isAceito()) {
                atual = tokens.get(++pos);
                if(atual.getToken().equals("T_OPL")) {
                    atual = tokens.get(++pos);
                    if(exp_relacional().isAceito()) {
                        ultimo = true;
                        return new RetornoSyntax(true, atual.getToken());
                    } else {
                        erro(atual.getToken(), atual.getLinha(), "[exp_logica] Expressão relacional esperada");
                        ultimo = false;
                        return new RetornoSyntax(false, atual.getToken());
                    }
                } else {
                    erro(atual.getToken(), atual.getLinha(), "[exp_logica] Operador lógico esperado");
                    ultimo = false;
                    return new RetornoSyntax(false, atual.getToken());
                }   
            } else {
                erro(atual.getToken(), atual.getLinha(), "[exp_logica] Expressão relacional esperada");
                ultimo = false;
                return new RetornoSyntax(false, atual.getToken());
            }  
        }
        return new RetornoSyntax(false, atual.getToken());
    }
    
    public RetornoSyntax exp() {
        RetornoSyntax rs = exp_logica();
        ultimo = rs.isAceito();
        return rs;
    }
    
    public RetornoSyntax com_loop() {
        if(pos < tokens.size()) {
            atual = tokens.get(pos);

            if(atual.getToken().equals("T_LOOP")) {
                atual = tokens.get(++pos);
                if(atual.getToken().equals("T_PARL")) {
                    atual = tokens.get(++pos);
                    if(atual.getToken().equals("T_ID") && (tokens.get(pos+1).getToken().equals("T_LOOP_TO") || tokens.get(pos+1).getToken().equals("T_LOOP_DOWN"))) {
                        atual = tokens.get(++pos);
                        if(atual.getToken().equals("T_LOOP_TO")) {
                            atual = tokens.get(++pos);
                            if(termo().isAceito()) {
                                atual = tokens.get(++pos);
                                if(atual.getToken().equals("T_PARR")) {
                                    atual = tokens.get(++pos);
                                    if(atual.getToken().equals("T_BRACESL")) {
                                        atual = tokens.get(++pos);
                                        if(commands().isAceito()) {
                                            atual = tokens.get(pos);
                                            if(atual.getToken().equals("T_BRACESR")) {
                                                ultimo = true;
                                                return new RetornoSyntax(true, atual.getToken());
                                            } else {
                                                erro(atual.getToken(), atual.getLinha(), "[com_loop] Chave } esperado");
                                                ultimo = false;
                                                return new RetornoSyntax(false, atual.getToken());
                                            }
                                        } else {
                                            ultimo = false;
                                            return new RetornoSyntax(false, atual.getToken());
                                        }
                                    } else {
                                        erro(atual.getToken(), atual.getLinha(), "[com_loop] Chave { esperada");
                                        ultimo = false;
                                        return new RetornoSyntax(false, atual.getToken());
                                    }
                                } else {
                                    erro(atual.getToken(), atual.getLinha(), "[com_loop] Parênteses ) esperado");
                                    ultimo = false;
                                    return new RetornoSyntax(false, atual.getToken());
                                }
                            } else {
                                ultimo = false;
                                return new RetornoSyntax(false, atual.getToken());
                            }
                        } else if(atual.getToken().equals("T_LOOP_DOWN")) {
                            atual = tokens.get(++pos);
                            if(atual.getToken().equals("T_LOOP_TO")) {
                                atual = tokens.get(++pos);
                                if(termo().isAceito()) {
                                    atual = tokens.get(++pos);
                                    if(atual.getToken().equals("T_PARR")) {
                                        atual = tokens.get(++pos);
                                        if(atual.getToken().equals("T_BRACESL")) {
                                            atual = tokens.get(++pos);
                                            if(commands().isAceito()) {
                                                atual = tokens.get(pos);
                                                if(atual.getToken().equals("T_BRACESL")) {
                                                    ultimo = true;
                                                    return new RetornoSyntax(true, atual.getToken());
                                                } else {
                                                    erro(atual.getToken(), atual.getLinha(), "[com_loop] Chave { esperado");
                                                    ultimo = false;
                                                    return new RetornoSyntax(false, atual.getToken());
                                                }
                                            } else {
                                                ultimo = false;
                                                return new RetornoSyntax(false, atual.getToken());
                                            }
                                        } else {
                                            erro(atual.getToken(), atual.getLinha(), "[com_loop] Chave { esperada");
                                            ultimo = false;
                                            return new RetornoSyntax(false, atual.getToken());
                                        }
                                    } else {
                                        erro(atual.getToken(), atual.getLinha(), "[com_loop] Parênteses ) esperado");
                                        ultimo = false;
                                        return new RetornoSyntax(false, atual.getToken());
                                    }
                                } else {
                                    ultimo = false;
                                    return new RetornoSyntax(false, atual.getToken());
                                }
                            }
                        }
                    } else if(exp().isAceito()) {
                        atual = tokens.get(++pos);
                        if(atual.getToken().equals("T_PARR")) {
                            atual = tokens.get(++pos);
                            if(atual.getToken().equals("T_BRACESL")) {
                                atual = tokens.get(++pos);
                                if(commands().isAceito()) {
                                    atual = tokens.get(pos);
                                    if(atual.getToken().equals("T_BRACESR")) {
                                        ultimo = true;
                                        return new RetornoSyntax(true, atual.getToken());
                                    } else {
                                        erro(atual.getToken(), atual.getLinha(), "[com_loop] Chave } esperada");
                                        ultimo = false;
                                        return new RetornoSyntax(false, atual.getToken());
                                    }
                                } else {
                                    ultimo = false;
                                    return new RetornoSyntax(false, atual.getToken());
                                }
                            } else {
                                erro(atual.getToken(), atual.getLinha(), "[com_loop] Chave { esperada");
                                ultimo = false;
                                return new RetornoSyntax(false, atual.getToken());
                            }
                        } else {
                            erro(atual.getToken(), atual.getLinha(), "[com_loop] Parênteses ) esperado");
                            ultimo = false;
                            return new RetornoSyntax(false, atual.getToken());
                        }
                    } else {
                        ultimo = false;
                        return new RetornoSyntax(false, atual.getToken());
                    }
                } else {
                    erro(atual.getToken(), atual.getLinha(), "[com_loop] Parênteses ( esperado");
                    ultimo = false;
                    return new RetornoSyntax(false, atual.getToken());
                }   
            } else {
                ultimo = false;
                return new RetornoSyntax(false, atual.getToken());
            }
        }
        ultimo = false;
        return new RetornoSyntax(false, atual.getToken());
    }
    
    public RetornoSyntax com_if() {
        if(pos < tokens.size()) {
            atual = tokens.get(pos);

            if(atual.getToken().equals("T_IF")) {
                atual = tokens.get(++pos);
                if(atual.getToken().equals("T_PARL")){
                    atual = tokens.get(++pos);
                    if(exp().isAceito()) {
                        atual = tokens.get(++pos);
                        if(atual.getToken().equals("T_PARR")){
                            atual = tokens.get(++pos);
                            if(atual.getToken().equals("T_BRACESL")) {
                                atual = tokens.get(++pos);
                                if(commands().isAceito()) {
                                    atual = tokens.get(pos);
                                    if(atual.getToken().equals("T_BRACESR")){ 
                                        if(pos + 1 < tokens.size()) {
                                            if(!tokens.get(pos+1).getToken().equals("T_ELSE")){
                                                ultimo = true;
                                                return new RetornoSyntax(true, atual.getToken());
                                            } else {
                                                atual = tokens.get(++pos);
                                                if(atual.getToken().equals("T_ELSE")) {
                                                    atual = tokens.get(++pos);
                                                    if(atual.getToken().equals("T_BRACESL")) {
                                                        atual = tokens.get(++pos);
                                                        if(commands().isAceito()) {
                                                            atual = tokens.get(pos);
                                                            if(atual.getToken().equals("T_BRACESR")) {
                                                                ultimo = true;
                                                                return new RetornoSyntax(true, atual.getToken());
                                                            } else {
                                                                erro(atual.getToken(), atual.getLinha(), "[com_if] Chave } esperada");
                                                                ultimo = false;
                                                                return new RetornoSyntax(false, atual.getToken());
                                                            }
                                                        } else return new RetornoSyntax(false, atual.getToken());
                                                    } else {
                                                        erro(atual.getToken(), atual.getLinha(), "[com_if] Chave { esperada");
                                                        ultimo = false;
                                                        return new RetornoSyntax(false, atual.getToken());
                                                    }
                                                } else {
                                                    erro(atual.getToken(), atual.getLinha(), "[com_if] ELSE esperado");
                                                    ultimo = false;
                                                    return new RetornoSyntax(false, atual.getToken());
                                                }
                                            }      
                                        } else { 
                                            ultimo = true; 
                                            return new RetornoSyntax(true, atual.getToken()); 
                                        }
                                    } else {
                                        erro(atual.getToken(), atual.getLinha(), "[com_if] Chave } esperada");
                                        ultimo = false;
                                        return new RetornoSyntax(false, atual.getToken());
                                    }
                                } else { 
                                    ultimo = false; 
                                    return new RetornoSyntax(false, atual.getToken()); 
                                }
                            } else {
                                erro(atual.getToken(), atual.getLinha(), "[com_if] Chave { esperada");
                                ultimo = false;
                                return new RetornoSyntax(false, atual.getToken());
                            }
                        } else {
                            erro(atual.getToken(), atual.getLinha(), "[com_if] Parênteses ) esperado");
                            ultimo = false;
                            return new RetornoSyntax(false, atual.getToken());
                        }
                    } else { 
                        ultimo = false;
                        return new RetornoSyntax(false, atual.getToken());
                    }
                } else {
                    erro(atual.getToken(), atual.getLinha(), "[com_if] Parênteses ( esperado");
                    ultimo = false;
                    return new RetornoSyntax(false, atual.getToken());
                }
            } else {
                ultimo = false;
                return new RetornoSyntax(false, atual.getToken());
            }
        }
        return new RetornoSyntax(false, atual.getToken());
    }
    
    public RetornoSyntax com_atribuicao() {
        if(pos < tokens.size()) {
            atual = tokens.get(pos);

            if(atual.getToken().equals("T_ID")) {
                atual = tokens.get(++pos);
                if(atual.getToken().equals("T_OPU")) {
                    atual = tokens.get(++pos);
                    if(atual.getToken().equals("T_SEMICOLON")) {
                        ultimo = true;
                        return new RetornoSyntax(true, atual.getToken());
                    } else {
                        erro(atual.getToken(), atual.getLinha(), "[com_atribuicao] Ponto e vírgula esperado");
                        ultimo = false;
                        return new RetornoSyntax(false, atual.getToken());
                    }
                } else if(atual.getToken().equals("T_ATR")) {
                    atual = tokens.get(++pos);
                    if(exp_aritmetica().isAceito()) {
                        atual = tokens.get(++pos);
                        if(atual.getToken().equals("T_SEMICOLON")) {
                            ultimo = true;
                            return new RetornoSyntax(true, atual.getToken());
                        } else {
                            erro(atual.getToken(), atual.getLinha(), "[com_atribuicao] Ponto e vírgula esperado");
                            ultimo = false;
                            return new RetornoSyntax(false, atual.getToken());
                        }
                    } else { 
                        ultimo = false;
                        return new RetornoSyntax(false, atual.getToken());
                    }
                } else {
                    erro(atual.getToken(), atual.getLinha(), "[com_atribuicao] Símbolo de atribução esperado");
                    ultimo = false;
                    return new RetornoSyntax(false, atual.getToken());
                }
            }
        }
        ultimo = false;
        return new RetornoSyntax(false, atual.getToken());
    }
    
    public RetornoSyntax com_declaracao() {
        if(pos < tokens.size()) {
            atual = tokens.get(pos);

            if(atual.getToken().equals("T_TYPE")) {
                atual = tokens.get(++pos);
                if(atual.getToken().equals("T_ID")) {
                    atual = tokens.get(++pos);
                    if(atual.getToken().equals("T_SEMICOLON")) {
                        ultimo = true;
                        return new RetornoSyntax(true, atual.getToken());
                    } else {
                        erro(atual.getToken(), atual.getLinha(), "[com_declaracao] Ponto e vírgula esperado");
                        ultimo = false;
                        return new RetornoSyntax(false, atual.getToken());
                    }
                } else {
                    erro(atual.getToken(), atual.getLinha(), "[com_declaracao] ID esperado");
                    ultimo = false;
                    return new RetornoSyntax(false, atual.getToken());
                }
            } else {
                ultimo = false;
                return new RetornoSyntax(false, atual.getToken());
            }
        }
        return new RetornoSyntax(false, atual.getToken());
    }
    
    public RetornoSyntax command() {
        if(pos < tokens.size()) {
            atual = tokens.get(pos);
            RetornoSyntax rs;
            if(atual.getToken().equals("T_BRACESR") || com_declaracao().isAceito() || com_atribuicao().isAceito() || com_if().isAceito() || com_loop().isAceito()) {
                if(pos+1 < tokens.size())
                    atual = tokens.get(++pos);
                else
                    ultimo = true;
                rs = new RetornoSyntax(ultimo, atual.getToken());
                return rs;
                
            }
            else {
                rs = new RetornoSyntax(false, atual.getToken());
                return rs;
            }
        }
        return new RetornoSyntax(false, "commands");
    }
    
    public RetornoSyntax commands() {
        RetornoSyntax rs = command();
        
        if(rs != null) {
            if(pos < tokens.size()) {
                if(rs.getToken().equals("T_BRACESR")) {
                        return rs;
                } else if(rs.isAceito()){
                    return commands();
                } else
                    return rs;
            }
        }
        return rs;
    }
    
    public RetornoSyntax launch() {
        if(pos < tokens.size()) {
            atual = tokens.get(pos);

            if(atual.getToken().equals("T_LAUNCH")) {
                atual = tokens.get(++pos);
                if(atual.getToken().equals("T_BRACESL")) {
                    atual = tokens.get(++pos);
                    if(commands().isAceito()) {
                        //atual = tokens.get(++pos);
                        if(atual.getToken().equals("T_BRACESR")) {
                            return new RetornoSyntax(true, atual.getToken());
                        } else {
                            erro(atual.getToken(), atual.getLinha(), "[launch] Chave } esperada");
                            return new RetornoSyntax(false, atual.getToken());
                        }
                    } else if(atual.getToken().equals("T_BRACESR")) {
                            return new RetornoSyntax(true, atual.getToken());
                    } else {
                        erro(atual.getToken(), atual.getLinha(), "[launch] Chave } esperada");
                        return new RetornoSyntax(false, atual.getToken());
                    }
                } else {
                    erro(atual.getToken(), atual.getLinha(), "[launch] Chave { esperada");
                    return new RetornoSyntax(false, atual.getToken());
                }
            } else {
                erro(atual.getToken(), atual.getLinha(), "[launch] LAUNCH esperado");
                return new RetornoSyntax(false, "launch");
            }
        } 
        return new RetornoSyntax(false, atual.getToken());
        
    }
    
    
    
    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
    
    
    
}
