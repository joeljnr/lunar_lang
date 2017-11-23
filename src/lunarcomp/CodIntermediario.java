package lunarcomp;

import java.util.ArrayList;

public class CodIntermediario {
    
    public String gerarCodIntermediario(ArrayList<Token> tokens) {
        String cod = "", atual = "", ini = "", fim = "", id = "";
        Token t;
        int idLoop = 0, idUntil = 0, idIf = 0, idTemp = 0;
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
                    ArrayList<String> opr = new ArrayList<String>();
                    String idAtr = (String)tokens.get(i-1).getLex();
                    i++;
                    String oprTemp;
                    while(i < tokens.size() && !tokens.get(i).getToken().equals("T_SEMICOLON")) {
                        if(tokens.get(i).getLex() instanceof Integer)
                            opr.add(Integer.toString((int)tokens.get(i).getLex()));
                        else if(tokens.get(i).getLex() instanceof Float)
                            opr.add(Float.toString((float)tokens.get(i).getLex()));
                        else 
                            opr.add((String)tokens.get(i).getLex());
                        
                        i++;
                    }
                    
                    for(int n = 0; n < opr.size(); n++) { //tratando * ou /
                        if(opr.get(n).equals("*") || opr.get(n).equals("/")) {
                            if(opr.size() > 3) {
                                idTemp++;
                                oprTemp = "";

                                oprTemp += opr.get(n - 1) + opr.get(n) + opr.get(n + 1); 
                                opr.remove(n);
                                opr.remove(n);
                                opr.set(n-1, "temp" + idTemp);

                                cod += opr.get(n-1) + " = " + oprTemp + "\n";
                                n = 0;
                            }
                        }
                    }

                    for(int n = 0; n < opr.size(); n++) { //tratando + ou -
                        if(opr.get(n).equals("+") || opr.get(n).equals("-")) {
                            if(opr.size() > 3) {
                                idTemp++;
                                oprTemp = "";

                                oprTemp += opr.get(n - 1) + opr.get(n) + opr.get(n + 1); 
                                opr.remove(n);
                                opr.remove(n);
                                opr.set(n-1, "temp" + idTemp);

                                cod += opr.get(n-1) + " = " + oprTemp + "\n";
                                n = 0;
                            }
                        }
                    }
                    
                    cod += idAtr + " = ";
                    for(int n = 0; n < opr.size(); n++)
                        cod += opr.get(n);
                    cod += "\n";
                    
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
                    if(t.getLex().equals("&") && !atual.contains("until")) {
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
                    if(!tokens.get(i + 1).getToken().equals("T_ATR"))
                        cod += t.getLex() + " ";
                    break;
            }
        }
        
        return cod;
    }
    
    public int buscaMultOuDiv(String opr) {
        int pos = -1;
        
        for(int i = 0; i < opr.length(); i++) {
            if(opr.charAt(i) == '*' || opr.charAt(i) == '*')
                pos = i;
        }
        
        return pos;
    }
    
}
