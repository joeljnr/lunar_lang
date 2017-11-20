package lunarcomp;

public class ID {
    private String nome;
    private String tipo;
    
    private String valorstring;
    private int valorint;
    private float valorfloat;
    private char valorchar;
    private boolean valorboolean;
    
    private boolean inicializado;
    private int ultimouso;

    public ID(String nome, String tipo, boolean inicializado, int ultimouso) {
        this.nome = nome;
        this.tipo = tipo;
        
        this.inicializado = inicializado;
        this.ultimouso = ultimouso;
    }
    
    public void setValor(String tipo, String valor) {
        this.inicializado = true;
        
        if(tipo.equals("string"))
            this.valorstring = valor;
        else if(tipo.equals("int"))
            this.valorint = Integer.parseInt(valor);
        else if(tipo.equals("float"))
            this.valorfloat = Float.parseFloat(valor);
        else if(tipo.equals("char"))
            this.valorchar = valor.charAt(0);
        else if(tipo.equals("bool"))
            this.valorboolean = Boolean.parseBoolean(valor);
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Object getValor() {
        if(this.tipo.equals("string"))
            return valorstring;
        else if(this.tipo.equals("int"))
            return valorint;
        else if(this.tipo.equals("float"))
            return valorfloat;
        else if(this.tipo.equals("char"))
            return valorchar;
        else if(this.tipo.equals("bool"))
            return valorboolean;
        else 
            return null;
    }

    public boolean isInicializado() {
        return inicializado;
    }

    public void setInicializado(boolean inicializado) {
        this.inicializado = inicializado;
    }

    public int getUltimouso() {
        return ultimouso;
    }

    public void setUltimouso(int ultimouso) {
        this.ultimouso = ultimouso;
    }
}
