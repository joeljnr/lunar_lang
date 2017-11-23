package lunarcomp;

import java.util.regex.Pattern;

public class CodMaquina {
    
    String cod;
    
    public void gerarCodMaquina(String[] linhas) {
    
        int regCont = 0;
        String[] partes;
        cod = "\n";
        
        
        for(int i = 0; i < linhas.length; i++) {
            partes = linhas[i].split(" ");
            
            if(partes.length > 1 && partes[1].equals(":")) { //é um rótulo
                cod += partes[0] + " : ";
                
                for(int j = 0; j < partes.length; j++) {
                    if(partes[j].equals("if")) {
                        if(linhas[i].contains("<=")) {
                            cod += "jumpLE " + partes[j+1] + ", ";   
                        } else if(linhas[i].contains("==")) {
                            cod += "jumpEQ " + partes[j+1] + ", "; 
                        } else {
                            cod += "jump ";
                        }
                    } else if(partes[j].contains("end")) {
                        cod += partes[j] + "\n";
                    }
                }
            } else if(partes.length > 1 && partes[1].equals(":=")) { // é uma atribuicao
                String id = partes[0];
                char opr;
                int k = 0;
                String[] operandos;


                while(k < partes[2].length() && (partes[2].charAt(k) != '+' && partes[2].charAt(k) != '-' && partes[2].charAt(k) != '*' && partes[2].charAt(k) != '/')) {
                    k++;
                }

                if(k < partes[2].length()) { // é uma operação
                    opr = partes[2].charAt(k);
                    
                    operandos = partes[2].split(Pattern.quote(""+opr));
                    cod += "load R" + regCont + ", " + operandos[0] + "\n";
                    regCont++;
                    cod += "load R" + regCont + ", " + operandos[1] + "\n";
                    regCont++;
                    
                    for(int n = 0; n < linhas.length; n++) {
                        String rep = "R" + regCont;
                        linhas[n] = linhas[n].replace(id, rep);
                    }
                    
                    
                    switch(opr) {
                        case '+':
                            cod += "addi R"+ regCont +", R" + (regCont-2) + ", R" + (regCont-1) + "\n";
                            break;
                        case '-':
                            cod += "sub R"+ regCont +", R" + (regCont-2) + ", R" + (regCont-1) + "\n";
                            break;
                        case '/':
                            cod += "div R"+ regCont +", R" + (regCont-2) + ", R" + (regCont-1) + "\n";
                            break;
                        case '*':
                            cod += "mult R"+ regCont +", R" + (regCont-2) + ", R" + (regCont-1) + "\n";
                            break;
                    }
                    regCont++;
                } else {
                    cod += "load R" + regCont + ", " + partes[2] + "\n";
                    regCont++;
                }

            }
            
        }
        cod += "halt";
    }

    public String getCod(String[] linhas) {
        gerarCodMaquina(linhas);
        return cod;
    }
    
    
}
