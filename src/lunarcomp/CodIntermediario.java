package lunarcomp;

import java.util.ArrayList;

public class CodIntermediario {
    
    public String gerarCodIntermediario(ArrayList<Token> tokens) {
        String cod = "", atual = "", ini = "", fim = "", id = "";
        Token t;
        int idLoop = 0, idUntil = 0, idIf = 0;
        boolean els = false, down = false;
        for(int i = 0; i < tokens.size(); i++) {
            t = tokens.get(i);
            
            switch(t.getToken()) {
                case "T_IF":
                    int j = 0;
                    idIf++;
                    while(!tokens.get(j).getToken().equals("T_BRACESR"))
                        j++;
                    
                    if(tokens.get(j+1).getToken().equals("T_ELSE")) {//tem else
                        fim = "else" +idIf ;
                        els = true;
                    } else {
                        fim = "endif" + idIf;
                        els = false;
                    }
                    
                    atual = "if" + idIf;
                    cod += atual + " : if ";
                    break;
                case "T_ELSE": 
                    atual = "goto endelse" + idIf + "\nelse" + idIf + " :\n";
                    fim = "endelse" + idIf;
                    cod += atual;
                    break;
                case "T_LOOP": 
                    if(t.getLex().equals("loop")) {
                        idLoop++;
                        fim = "endloop" + idLoop;
                        atual = "loop" + idLoop;
                    } else if(t.getLex().equals("until")) {
                        idUntil++;
                        fim = "enduntil" + idUntil;
                        atual = "until"+idUntil;
                    }
                    cod += atual + ": if ";
                    break;
                case "T_LOOP_DOWN": 
                    cod += "<= " + tokens.get(i + 2).getLex() + " ";
                    down = true;
                    id = (String)tokens.get(i - 1).getLex();
                    i += 2;
                    break;
                case "T_LOOP_TO":
                    cod += ">= " + tokens.get(i + 1).getLex() + " ";
                    down = false;
                    id = (String)tokens.get(i - 1).getLex();
                    i++;
                    break;
                case "T_TYPE":
                    while(!tokens.get(i).getToken().equals("T_SEMICOLON"))
                        i++;
                    break;
                case "T_BOOL": 
                    cod += t.getLex() + " ";
                    break;
                case "T_COMMA": 
                    cod += " ";
                    break;
                case "T_SEMICOLON": 
                    cod += "\n";
                    break;
                case "T_BRACESR": 
                    if(fim.contains("endloop") || fim.contains("enduntil")) {
                        if(fim.contains("endloop"))
                            if(down)
                                cod += id + " = " + id + " - 1\n";
                            else
                                cod += id + " = " + id + " + 1\n";
                        
                        cod += "goto " + atual + "\n";
                        cod += fim + "\n";
                    } else if(fim.contains("endif") || fim.contains("endelse")) {
                        cod += fim + "\n";
                    }
                    fim = "";
                    break;
                case "T_PARL": 
                    cod += "";
                    break;
                case "T_PARR": 
                    cod += "goto " + fim + "\n";
                    break;
                case "T_ATR":
                    /*
                    
                    FAZER
                    
                    */
                    cod += t.getLex() + " ";
                    break;
                case "T_OPA": 
                    cod += t.getLex() + " ";
                    break;
                case "T_OPR":
                    if(!atual.contains("until")) {
                        if(t.getLex().equals("<"))
                            cod += ">=";
                        else if(t.getLex().equals(">"))
                            cod += "<=";
                        else if(t.getLex().equals("<="))
                            cod += ">";
                        else if(t.getLex().equals(">="))
                            cod += "<";
                        else if(t.getLex().equals("=="))
                            cod += "!=";
                    } else {
                        cod += t.getLex();
                    }
                    cod += " ";
                    break;
                case "T_OPL": 
                    if(t.getLex().equals("&")) {
                        cod += "| ";
                    } else {
                        cod += "& ";
                    }
                    break;
                case "T_OPU": 
                    if(t.getLex().equals("++"))
                        cod += "= "+ tokens.get(i-1).getLex() +" + 1";
                    else
                        cod += "= "+ tokens.get(i-1).getLex() +" - 1";
                    break;
                case "T_OPN": 
                    cod += t.getLex() + " ";
                    break;
                case "T_STRING": 
                    cod += t.getLex() + " ";
                    break;
                case "T_CHAR": 
                    cod += t.getLex() + " ";
                    break;
                case "T_NUM": 
                    cod += t.getLex() + " ";
                    break;
                case "T_ID": 
                    cod += t.getLex() + " ";
                    break;
            }
        }
        
        return cod;
    }
}
